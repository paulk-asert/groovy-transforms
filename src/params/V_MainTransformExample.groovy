package params

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.transform.*
import java.lang.annotation.*
import org.codehaus.groovy.control.*

@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.METHOD])
@GroovyASTTransformationClass(classes = [MainTransformation])
@interface Main {
    boolean merge() default false
}

import static org.codehaus.groovy.ast.ClassHelper.VOID_TYPE
import static org.codehaus.groovy.ast.tools.GeneralUtils.*

@GroovyASTTransformation(phase = CompilePhase.INSTRUCTION_SELECTION)
class MainTransformation extends AbstractASTTransformation {
    private static final ClassNode[] NO_EXCEPTIONS = ClassNode.EMPTY_ARRAY
    private static final ClassNode STRING_ARRAY = ClassHelper.STRING_TYPE.makeArray()

    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        init(astNodes, sourceUnit)
        if (astNodes[0].classNode?.name != Main.class.name) return
        MethodNode annotatedMethod = astNodes[1]
        boolean merge = getMemberValue(astNodes[0], 'merge')

        ClassNode declaringClass = annotatedMethod.declaringClass
        def name = annotatedMethod.name
        def parameters = params(param(STRING_ARRAY, 'args'))
        def existing = declaringClass.getDeclaredMethod('main', parameters)
        if (existing && !merge) {
            addError("@Main method $name found but main already exists!", annotatedMethod)
            return
        }

        def callMethod = callX(ctorX(declaringClass), name)
        BlockStatement body
        if (existing) {
            if (existing.code instanceof BlockStatement) {
                body = existing.code
            } else {
                body = block(existing.code)
            }
            body.addStatement(stmt(callMethod))
        } else {
            body = block(stmt(callMethod))
        }
        def visibility = ACC_STATIC | ACC_PUBLIC
        declaringClass.addMethod('main', visibility,
                VOID_TYPE, parameters, NO_EXCEPTIONS, body)
    }
}

new GroovyShell(getClass().classLoader).evaluate '''
class Greeter {
    public static void main(String[] args) {
        println 'Hello from main()'
    }

    @params.Main(merge=true)
    def greet() {
        println "Hello from the greet() method!"
    }
    @params.Main(merge=true)
    def greet2() {
        println "Hello from the greet2() method!"
    }
}
'''
