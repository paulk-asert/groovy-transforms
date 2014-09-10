import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.transform.*
import java.lang.annotation.*
import org.codehaus.groovy.control.*

@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.METHOD])
@GroovyASTTransformationClass(classes = [MainTransformation])
@interface Main {}

import static groovyjarjarasm.asm.Opcodes.*
import static org.codehaus.groovy.ast.ClassHelper.VOID_TYPE
import static org.codehaus.groovy.ast.tools.GeneralUtils.*

@GroovyASTTransformation(phase = CompilePhase.INSTRUCTION_SELECTION)
class MainTransformation implements ASTTransformation {
    private static final ClassNode[] NO_EXCEPTIONS = ClassNode.EMPTY_ARRAY
    private static final ClassNode STRING_ARRAY = ClassHelper.STRING_TYPE.makeArray()

    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        // use guard clauses as a form of defensive programming.
        if (!astNodes) return
        if (!astNodes[0] || !astNodes[1]) return
        if (!(astNodes[0] instanceof AnnotationNode)) return
        if (astNodes[0].classNode?.name != Main.class.name) return
        if (!(astNodes[1] instanceof MethodNode)) return

        MethodNode annotatedMethod = astNodes[1]
        ClassNode declaringClass = annotatedMethod.declaringClass
        def callMethod = callX(ctorX(declaringClass), annotatedMethod.name)
        Statement body = block(stmt(callMethod))
        def visibility = ACC_STATIC | ACC_PUBLIC
        def parameters = params(param(STRING_ARRAY, 'args'))
        declaringClass.addMethod('main', visibility,
                VOID_TYPE, parameters, NO_EXCEPTIONS, body)
    }
}

new GroovyShell(getClass().classLoader).evaluate '''
class Greeter {
    @Main
    def greet() {
        println "Hello from the greet() method!"
    }
}
'''
