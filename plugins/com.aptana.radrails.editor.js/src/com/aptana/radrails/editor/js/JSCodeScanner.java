package com.aptana.radrails.editor.js;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

import com.aptana.radrails.editor.common.RegexpRule;
import com.aptana.radrails.editor.common.WhitespaceDetector;
import com.aptana.radrails.editor.common.theme.ThemeUtil;

/**
 * @author Kevin Lindsey
 * @author cwilliams
 */
public class JSCodeScanner extends RuleBasedScanner {

    private static final String[] KEYWORD_OPERATORS = new String[] { "delete", "in", "instanceof", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "new", "typeof", "with" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    private static final String[] KEYWORD_CONTROL = new String[] { "break", "case", "catch", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "continue", "default", "do", "else", "finally", "for", "goto", "if", "import", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
            "package", "return", "switch", "throw", "try", "while" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

    /**
     * CodeScanner
     */
    public JSCodeScanner() {
        List<IRule> rules = new ArrayList<IRule>();

        // Add generic whitespace rule.
        rules.add(new WhitespaceRule(new WhitespaceDetector()));

        // Add word rule for keywords, types, and constants.
        WordRule wordRule = new WordRule(new WordDetector(), Token.UNDEFINED);
        IToken keyword = ThemeUtil.getToken("keyword.control.js"); //$NON-NLS-1$
        for (String word : KEYWORD_CONTROL) {
            wordRule.addWord(word, keyword);
        }
        IToken keywordOperators = ThemeUtil.getToken("keyword.operator.js"); //$NON-NLS-1$
        for (String word : KEYWORD_OPERATORS) {
            wordRule.addWord(word, keywordOperators);
        }
        wordRule.addWord("true", ThemeUtil.getToken("constant.language.boolean.true.js")); //$NON-NLS-1$ //$NON-NLS-2$
        wordRule.addWord("false", ThemeUtil.getToken("constant.language.boolean.false.js")); //$NON-NLS-1$ //$NON-NLS-2$
        wordRule.addWord("null", ThemeUtil.getToken("constant.language.null.js")); //$NON-NLS-1$ //$NON-NLS-2$

        wordRule.addWord("Infinity", ThemeUtil.getToken("constant.language.js")); //$NON-NLS-1$ //$NON-NLS-2$
        wordRule.addWord("NaN", ThemeUtil.getToken("constant.language.js")); //$NON-NLS-1$ //$NON-NLS-2$
        wordRule.addWord("undefined", ThemeUtil.getToken("constant.language.js")); //$NON-NLS-1$ //$NON-NLS-2$

        wordRule.addWord("super", ThemeUtil.getToken("variable.language.js")); //$NON-NLS-1$ //$NON-NLS-2$
        wordRule.addWord("this", ThemeUtil.getToken("variable.language.js")); //$NON-NLS-1$ //$NON-NLS-2$

        wordRule.addWord("debugger", ThemeUtil.getToken("keyword.other.js")); //$NON-NLS-1$ //$NON-NLS-2$
        rules.add(wordRule);

        rules
                .add(new RegexpRule(
                        "!|\\$|%|&|\\*|\\-\\-|\\-|\\+\\+|\\+|~|===|==|=|!=|!==|<=|>=|<<=|>>=|>>>=|<>|<|>|!|&&|\\|\\||\\?\\:|\\*=|(?<!\\()/=|%=|\\+=|\\-=|&=|\\^=|\\b(in|instanceof|new|delete|typeof|void)\\b", //$NON-NLS-1$
                        ThemeUtil.getToken("keyword.operator.js"))); //$NON-NLS-1$
        rules.add(new RegexpRule("\\b((0(x|X)[0-9a-fA-F]+)|([0-9]+(\\.[0-9]+)?))\\b", ThemeUtil //$NON-NLS-1$
                .getToken("constant.numeric.js"))); //$NON-NLS-1$
        rules.add(new RegexpRule("\\.(warn|info|log|error|time|timeEnd|assert)\\b", ThemeUtil //$NON-NLS-1$
                .getToken("support.function.js.firebug"))); //$NON-NLS-1$
        rules
                .add(new RegexpRule(
                        "\\b(s(ub(stringData|mit)|plitText|e(t(NamedItem|Attribute(Node)?)|lect))|has(ChildNodes|Feature)|namedItem|c(l(ick|o(se|neNode))|reate(C(omment|DATASection|aption)|T(Head|extNode|Foot)|DocumentFragment|ProcessingInstruction|E(ntityReference|lement)|Attribute))|tabIndex|i(nsert(Row|Before|Cell|Data)|tem)|open|delete(Row|C(ell|aption)|T(Head|Foot)|Data)|focus|write(ln)?|a(dd|ppend(Child|Data))|re(set|place(Child|Data)|move(NamedItem|Child|Attribute(Node)?)?)|get(NamedItem|Element(sBy(Name|TagName)|ById)|Attribute(Node)?)|blur)\\b(?=\\()", //$NON-NLS-1$
                        ThemeUtil.getToken("support.function.dom.js"))); //$NON-NLS-1$
        rules
                .add(new RegexpRule(
                        "\\b(s(h(ift|ow(Mod(elessDialog|alDialog)|Help))|croll(X|By(Pages|Lines)?|Y|To)?|t(op|rike)|i(n|zeToContent|debar|gnText)|ort|u(p|b(str(ing)?)?)|pli(ce|t)|e(nd|t(Re(sizable|questHeader)|M(i(nutes|lliseconds)|onth)|Seconds|Ho(tKeys|urs)|Year|Cursor|Time(out)?|Interval|ZOptions|Date|UTC(M(i(nutes|lliseconds)|onth)|Seconds|Hours|Date|FullYear)|FullYear|Active)|arch)|qrt|lice|avePreferences|mall)|h(ome|andleEvent)|navigate|c(har(CodeAt|At)|o(s|n(cat|textual|firm)|mpile)|eil|lear(Timeout|Interval)?|a(ptureEvents|ll)|reate(StyleSheet|Popup|EventObject))|t(o(GMTString|S(tring|ource)|U(TCString|pperCase)|Lo(caleString|werCase))|est|a(n|int(Enabled)?))|i(s(NaN|Finite)|ndexOf|talics)|d(isableExternalCapture|ump|etachEvent)|u(n(shift|taint|escape|watch)|pdateCommands)|j(oin|avaEnabled)|p(o(p|w)|ush|lugins.refresh|a(ddings|rse(Int|Float)?)|r(int|ompt|eference))|e(scape|nableExternalCapture|val|lementFromPoint|x(p|ec(Script|Command)?))|valueOf|UTC|queryCommand(State|Indeterm|Enabled|Value)|f(i(nd|le(ModifiedDate|Size|CreatedDate|UpdatedDate)|xed)|o(nt(size|color)|rward)|loor|romCharCode)|watch|l(ink|o(ad|g)|astIndexOf)|a(sin|nchor|cos|t(tachEvent|ob|an(2)?)|pply|lert|b(s|ort))|r(ou(nd|teEvents)|e(size(By|To)|calc|turnValue|place|verse|l(oad|ease(Capture|Events)))|andom)|g(o|et(ResponseHeader|M(i(nutes|lliseconds)|onth)|Se(conds|lection)|Hours|Year|Time(zoneOffset)?|Da(y|te)|UTC(M(i(nutes|lliseconds)|onth)|Seconds|Hours|Da(y|te)|FullYear)|FullYear|A(ttention|llResponseHeaders)))|m(in|ove(B(y|elow)|To(Absolute)?|Above)|ergeAttributes|a(tch|rgins|x))|b(toa|ig|o(ld|rderWidths)|link|ack))\\b(?=\\()", //$NON-NLS-1$
                        ThemeUtil.getToken("support.function.js"))); //$NON-NLS-1$

        setRules(rules.toArray(new IRule[rules.size()]));
    }
}
