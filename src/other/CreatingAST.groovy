package other

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.expr.*

new ReturnStatement(
        new ConstructorCallExpression(
                ClassHelper.make(Date),
                ArgumentListExpression.EMPTY_ARGUMENTS
        )
)

import static org.codehaus.groovy.ast.tools.GeneralUtils.*
import static org.codehaus.groovy.ast.ClassHelper.*

returnS(ctorX(make(Date)))

import org.codehaus.groovy.ast.builder.AstBuilder

def ast2 = new AstBuilder().buildFromSpec {
    returnStatement {
        constructorCall(Date) {
            argumentList {}
        }
    }
}

def ast3 = new AstBuilder().buildFromString('new Date()')

def ast = new AstBuilder().buildFromCode {
    new Date()
}

///////////////

