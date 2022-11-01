package cdtdemo;

import org.eclipse.cdt.core.dom.ast.IASTProblem;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.gnu.c.GCCLanguage;
import org.eclipse.cdt.core.parser.*;
import org.eclipse.cdt.internal.core.dom.rewrite.astwriter.ASTWriter;
import org.eclipse.cdt.internal.core.dom.rewrite.astwriter.ProblemRuntimeException;
import org.eclipse.core.runtime.CoreException;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        FileContent fileContent = FileContent.createForExternalFileLocation("../../../../test.c");
        IScannerInfo scannerInfo = new ScannerInfo(new HashMap<>(), new String[0]);
        IParserLogService log = new DefaultLogService();
        IncludeFileContentProvider emptyIncludes = IncludeFileContentProvider.getEmptyFilesProvider();
        int opts = 0;
        IASTTranslationUnit tu;
        try {
            tu = GCCLanguage.getDefault().getASTTranslationUnit(fileContent, scannerInfo, emptyIncludes, null, opts, log);
            System.out.println(new ASTWriter().write(tu));
        } catch (CoreException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ProblemRuntimeException e) {
            IASTProblem problem = e.getProblem().getProblem();
            System.err.println("At line " + problem.getFileLocation().getStartingLineNumber() + ":");
            System.err.println(problem.getRawSignature());
            System.err.println(problem.getMessageWithLocation());
            e.printStackTrace();
            System.exit(2);
        }
    }
}