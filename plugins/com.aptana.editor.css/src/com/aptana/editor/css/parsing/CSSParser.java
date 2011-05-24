package com.aptana.editor.css.parsing;

import java.util.ArrayList;
import java.util.List;
import com.aptana.parsing.ast.IParseRootNode;
import com.aptana.parsing.lexer.IRange;
import com.aptana.parsing.ast.ParseRootNode;
import com.aptana.editor.css.parsing.ast.*;
import beaver.*;
import com.aptana.parsing.IParser;
import com.aptana.parsing.IParseState;

/**
 * This class is a LALR parser generated by
 * <a href="http://beaver.sourceforge.net">Beaver</a> v0.9.6.1
 * from the grammar specification "CSS.grammar".
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSSParser extends Parser implements IParser {

	static final ParsingTables PARSING_TABLES = new ParsingTables(
		"U9pDLcTuL4KKFI#xGSmYKXD4306fomQG98Im8OG8920gzeeDB1G54HL929tGEw7tteieY8g" +
		"WeA1WGKKHi1T4iQ1YGRsUcJSRzjkt#JQHFyn#P#vzzzwvRUQz7I2gCaPZ3CPY72PWF2PY4Y" +
		"PZ2gPY6gPZ1cPY5cPZ3kPY7kPZ0Ix5GYp2OYp1KYn38PPZ1LPY5LPZ3TPY7TPZ0pQMokD59" +
		"V8u0daeZu4OWWTH5#qm4fVZRXGW5cv4emmkG5bSY1XuK15ng80eD4SMMg0QKf0d$StAL5J6" +
		"$KX4OpH1Kro1PaX2CdhZ4VH49Mp2e#Y1FcYDwgY2J6IW3TBGm7YAeoSVlAYAHlIQYWwuinX" +
		"FlT2bo5DDF6myfSk$o3SMxUdHjaZ0A0p2O8Sy7dLmEyhX4boB6t6nI9YrjV6ONfk7Ugpl0T" +
		"Rt50OW5$4oFNYjxbrEcDwrTFGk3ntn46eKnReAbvbOAgeTgvE9rLdxYgUlq5Ytc5Xnk8cnR" +
		"cOi5piM6ijPppMuLSTG4eNRS1tkmWsCSOSZHZT7FIvqTyImTwubTwvlaTTKt8j#ZfspBH#d" +
		"vPFQwWdqfsLNcHhUKgRGiZi28rNFePQ12jMmArH3LLWQJHhQQesYu0cyabmZJK6g9Asq1Be" +
		"NzP6j9NujiKT3rDAwUwYphHgI6wfrmt2riHgEwxKEsYfqt0UBxvJBqU5oV7$BSqygyBshnB" +
		"Uh2j#FMEvyDUvMTNujQl04nVCjI60EjRbFTHYZ7hDim1nAup6wH1vLNsBCBdZvbCGzIU6EM" +
		"TpRD9u3FzBPcmo#bp7qcCaUsZbu#EJZsRTNUgXDSkHWvvh3NR1pZUSN8Dxau6KEjgTe$jJ#" +
		"Mzn$3tzk#WhloSik1Jp5SKsW6XJrHL7ReWoZghw4ocFfFRWluTVMr6jzbDhrsRsAPGU3rnR" +
		"NkmH7xtpqthYeTsvc7zW$9R5ZbJ6UuhIlMFeAZMKP2xKFIOoiOdaSiSBLawbZA8b232Ng5t" +
		"f6L8nsOUhn5HCZWUkzHLuzzDZUiNEsPJOjBStN9EVH7GvlgVG#CGCUFOUtT6kThb1pWGfBg" +
		"D4LAWqfPUe4Uda2IVcKV5P2JbcsOI#Kf8sIcEpKbr7fuf1ehB9e5QzrjP1ghEhPLkGInJ7q" +
		"Tt3pOFkBiOKugFX8u6X9T3HyLwABuNiPMXXcJGCT4HRNMcMDR0QnpV273Drbw37QzIBjOJz" +
		"RGqgIMqZCVK7yKVee99rOIX$7JVneJgT9Avhd34FTHBRXEnXQdwXhrayWdsNadJXDCNoEeQ" +
		"hEleOlC7GD4UXBFh4aA9$Tr7LsvcdruxJ3y2E9deRlJUI6gMjdvDgjFP5ipZTmiFSuyp$MD" +
		"IJy9R3B97A9FUnFubcvjTte6plNMCk8zKRVxTnhj5OHMy$HnrwJp$uWsG4ZEsNeoI1TT0H$" +
		"LOaZXayswziIMmn$cF8MP#sHNuoV72B9y1abgADFP1kSY6HpFe5Ziqta7paZVycVyeVyBgV" +
		"bD$bLIt$HyodYPsAhF2ipf11co4zwvkcKtVAqQ4#oIJPoNY#hPPMCaT4oIWfaf8oGuJ9CXi" +
		"eG6Io3pEfjiaucooIPA1DacgoLDR91fiipiiNeNo5UrjmkOgVcNjBp3Zs$IBoWkUU9xSHAM" +
		"IvBPAaiaWLycY$pPAxCaTdQPfQUP#evLmR8Kx9G5ak#39GyMIRZPPoCbUTaXVmjFyf9#Lw#" +
		"anFohHoNR#Hh#Kg#b2$aS$bCFfLFv6Cv9aVbY7maX#L3#K0EoVlodhmh1#KTULlUaZVbWEo" +
		"ND#HrsITxvJLvLNvWq0BU8Iq2l1r5wHjIymXemV$nD0qt$mMZUQUC8YoUzFCRVkRiPyv#vk" +
		"pd3IYTp#byRaNQYZI3D8EqDMbhJItUEDJzsKLOl2aeH1cuUDDGS1CMRoKk8egmD9ePf52UG" +
		"ff4NH9f4z8ccggxP1HXwNkbZxmDAmWkGjqrBGslGRP1LWZQ1I6vb6WQXFP1QFGV$jxfuG==");

	// suppress the error printouts
	private static class CSSEvents extends Events
	{
		public void scannerError(Scanner.Exception e)
		{
		}

		public void syntaxError(Symbol token)
		{
		}

		public void unexpectedTokenRemoved(Symbol token)
		{
		}

		public void missingTokenInserted(Symbol token)
		{
		}

		public void misspelledTokenReplaced(Symbol token)
		{
		}

		public void errorPhraseRemoved(Symbol error)
		{
		}
	}

	public synchronized IParseRootNode parse(IParseState parseState) throws java.lang.Exception
	{
		// grab source
		char[] characters = parseState.getSource();

		// make sure we have some source
		String source = (characters != null) ? new String(characters) : "";

		// create scanner and send source to it
		CSSScanner scanner = new CSSScanner();
		scanner.setSource(source);

		// parse
		ParseRootNode result = (ParseRootNode) parse(scanner);
		int start = parseState.getStartingOffset();
		int end = start + parseState.getSource().length;
		result.setLocation(start, end);

		// store results in the parse state
		parseState.setParseResult(result);

		// attach comments to parse root node
		IRange[] comments = scanner.getComments();
		CSSCommentNode[] commentNodes = new CSSCommentNode[comments.length];

		for (int i = 0; i < comments.length; i++)
		{
			IRange comment = comments[i];
			CSSCommentNode commentNode = new CSSCommentNode( //
				this.getSource(parseState, comment),
				comment.getStartingOffset(),
				comment.getEndingOffset()
			);

			commentNodes[i] = commentNode;
		}

		result.setCommentNodes(commentNodes);

		return result;
	}

	private String getSource(IParseState parseState, IRange comment)
	{
		char[] src = parseState.getSource();
		int length = comment.getLength();
		char[] dest = new char[length];

		System.arraycopy(src, comment.getStartingOffset(), dest, 0, length);

		return new String(dest);
	}

	public CSSParser() {
		super(PARSING_TABLES);


		report = new CSSEvents();
	}

	protected Symbol invokeReduceAction(int rule_num, int offset) {
		switch(rule_num) {
			case 0: // Program = Statements.p
			{
					final Symbol _symbol_p = _symbols[offset + 1];
					final ArrayList _list_p = (ArrayList) _symbol_p.value;
					final CSSNode[] p = _list_p == null ? new CSSNode[0] : (CSSNode[]) _list_p.toArray(new CSSNode[_list_p.size()]);
					
			return new CSSParseRootNode(p);
			}
			case 1: // Program = 
			{
					
			return new CSSParseRootNode();
			}
			case 2: // Statements = Statements Statement
			{
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2].value); return _symbols[offset + 1];
			}
			case 3: // Statements = Statement
			{
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
			}
			case 12: // CharSet = CHARSET STRING.s SEMICOLON
			{
					final Symbol _symbol_s = _symbols[offset + 2];
					final String s = (String) _symbol_s.value;
					
			return new CSSCharSetNode(s);
			}
			case 13: // Import = IMPORT ImportWord.s SEMICOLON
			{
					final Symbol _symbol_s = _symbols[offset + 2];
					final String s = (String) _symbol_s.value;
					
			return new CSSImportNode(s);
			}
			case 14: // Import = IMPORT ImportWord.s List.w SEMICOLON
			{
					final Symbol _symbol_s = _symbols[offset + 2];
					final String s = (String) _symbol_s.value;
					final Symbol _symbol_w = _symbols[offset + 3];
					final List<CSSTextNode> w = (List<CSSTextNode>) _symbol_w.value;
					
			return new CSSImportNode(s, w.toArray(new CSSTextNode[w.size()]));
			}
			case 15: // Media = MEDIA MediaExprs.m LCURLY Statements.s RCURLY
			{
					final Symbol _symbol_m = _symbols[offset + 2];
					final ArrayList _list_m = (ArrayList) _symbol_m.value;
					final beaver.Symbol[] m = _list_m == null ? new beaver.Symbol[0] : (beaver.Symbol[]) _list_m.toArray(new beaver.Symbol[_list_m.size()]);
					final Symbol _symbol_s = _symbols[offset + 4];
					final ArrayList _list_s = (ArrayList) _symbol_s.value;
					final CSSNode[] s = _list_s == null ? new CSSNode[0] : (CSSNode[]) _list_s.toArray(new CSSNode[_list_s.size()]);
					
			List<CSSTextNode> list = new ArrayList<CSSTextNode>();

			for (Symbol symbol : m)
			{
				CSSTextNode text = new CSSTextNode((String) symbol.value);

				text.setLocation(symbol.getStart(), symbol.getEnd());
				list.add(text);
			}

			return new CSSMediaNode(list.toArray(new CSSTextNode[list.size()]), s);
			}
			case 16: // MediaExprs = MediaExprs MediaExpr
			{
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2]); return _symbols[offset + 1];
			}
			case 17: // MediaExprs = MediaExpr
			{
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1]); return new Symbol(lst);
			}
			case 58: // Page = PAGE LCURLY RCURLY
			{
					
			return new CSSPageNode();
			}
			case 59: // Page = PAGE LCURLY Declarations.d RCURLY
			{
					final Symbol _symbol_d = _symbols[offset + 3];
					final List<CSSDeclarationNode> d = (List<CSSDeclarationNode>) _symbol_d.value;
					
			return new CSSPageNode(d);
			}
			case 60: // Page = PAGE COLON IDENTIFIER.s LCURLY RCURLY
			{
					final Symbol _symbol_s = _symbols[offset + 3];
					final String s = (String) _symbol_s.value;
					
			CSSPageSelectorNode pageSelector = new CSSPageSelectorNode(s);
			CSSPageNode result = new CSSPageNode();

			pageSelector.setLocation(_symbol_s.getStart(), _symbol_s.getEnd());
			result.setSelector(pageSelector);

			return result;
			}
			case 61: // Page = PAGE COLON IDENTIFIER.s LCURLY Declarations.d RCURLY
			{
					final Symbol _symbol_s = _symbols[offset + 3];
					final String s = (String) _symbol_s.value;
					final Symbol _symbol_d = _symbols[offset + 5];
					final List<CSSDeclarationNode> d = (List<CSSDeclarationNode>) _symbol_d.value;
					
			CSSPageSelectorNode pageSelector = new CSSPageSelectorNode(s);
			CSSPageNode result = new CSSPageNode(d);

			pageSelector.setLocation(_symbol_s.getStart(), _symbol_s.getEnd());
			result.setSelector(pageSelector);

			return result;
			}
			case 62: // FontFace = FONTFACE LCURLY RCURLY
			{
					
			return new CSSFontFaceNode();
			}
			case 63: // FontFace = FONTFACE LCURLY Declarations.d RCURLY
			{
					final Symbol _symbol_d = _symbols[offset + 3];
					final List<CSSDeclarationNode> d = (List<CSSDeclarationNode>) _symbol_d.value;
					
			return new CSSFontFaceNode(d);
			}
			case 64: // Namespace = NAMESPACE ImportWord.w SEMICOLON
			{
					final Symbol _symbol_w = _symbols[offset + 2];
					final String w = (String) _symbol_w.value;
					
			return new CSSNamespaceNode(w);
			}
			case 65: // Namespace = NAMESPACE IDENTIFIER.i ImportWord.w SEMICOLON
			{
					final Symbol _symbol_i = _symbols[offset + 2];
					final String i = (String) _symbol_i.value;
					final Symbol _symbol_w = _symbols[offset + 3];
					final String w = (String) _symbol_w.value;
					
			return new CSSNamespaceNode(i, w);
			}
			case 68: // Rule = Selectors.s LCURLY RCURLY
			{
					final Symbol _symbol_s = _symbols[offset + 1];
					final List<CSSSelectorNode> s = (List<CSSSelectorNode>) _symbol_s.value;
					
			CSSRuleNode result = new CSSRuleNode(s);

			for (CSSSelectorNode selector : s)
			{
				selector.setParent(result);
			}

			return result;
			}
			case 69: // Rule = Selectors.s LCURLY Declarations.d RCURLY
			{
					final Symbol _symbol_s = _symbols[offset + 1];
					final List<CSSSelectorNode> s = (List<CSSSelectorNode>) _symbol_s.value;
					final Symbol _symbol_d = _symbols[offset + 3];
					final List<CSSDeclarationNode> d = (List<CSSDeclarationNode>) _symbol_d.value;
					
			CSSRuleNode result = new CSSRuleNode(s, d);

			for (CSSSelectorNode selector : s)
			{
				selector.setParent(result);
			}

			CSSSelectorNode firstSelector = s.get(0);

			for (CSSDeclarationNode declaration : d)
			{
				declaration.setParent(firstSelector);
			}

			return result;
			}
			case 71: // Function = Identifier.i LPAREN Expression.e RPAREN
			{
					final Symbol _symbol_i = _symbols[offset + 1];
					final String i = (String) _symbol_i.value;
					final Symbol _symbol_e = _symbols[offset + 3];
					final CSSExpressionNode e = (CSSExpressionNode) _symbol_e.value;
					
			return new CSSFunctionNode(i, e);
			}
			case 72: // List = List COMMA IDENTIFIER.i
			{
					final Symbol _symbol_i = _symbols[offset + 3];
					final String i = (String) _symbol_i.value;
					
		List<CSSTextNode> list = (List<CSSTextNode>) _symbols[offset + 1].value;
		CSSTextNode text = new CSSTextNode(i);

		text.setLocation(_symbol_i.getStart(), _symbol_i.getEnd());
		list.add(text);

		return _symbols[offset + 1];
			}
			case 73: // List = IDENTIFIER.i
			{
					final Symbol _symbol_i = _symbols[offset + 1];
					final String i = (String) _symbol_i.value;
					
		List<CSSTextNode> list = new ArrayList<CSSTextNode>();
		CSSTextNode text = new CSSTextNode(i);

		text.setLocation(_symbol_i.getStart(), _symbol_i.getEnd());
		list.add(text);

		return new Symbol(list);
			}
			case 74: // Declarations = Declaration.d
			{
					final Symbol _symbol_d = _symbols[offset + 1];
					final CSSDeclarationNode d = (CSSDeclarationNode) _symbol_d.value;
					
			List<CSSDeclarationNode> list = new ArrayList<CSSDeclarationNode>();

			list.add(d);

			return new Symbol(list);
			}
			case 75: // Declarations = Declaration.d SEMICOLON.s
			{
					final Symbol _symbol_d = _symbols[offset + 1];
					final CSSDeclarationNode d = (CSSDeclarationNode) _symbol_d.value;
					final Symbol s = _symbols[offset + 2];
					
			List<CSSDeclarationNode> list = new ArrayList<CSSDeclarationNode>();

			d.setHasSemicolon(s);
			list.add(d);

			return new Symbol(list);
			}
			case 76: // Declarations = Declarations.ds Declaration.d
			{
					final Symbol _symbol_ds = _symbols[offset + 1];
					final List<CSSDeclarationNode> ds = (List<CSSDeclarationNode>) _symbol_ds.value;
					final Symbol _symbol_d = _symbols[offset + 2];
					final CSSDeclarationNode d = (CSSDeclarationNode) _symbol_d.value;
					
			ds.add(d);

			return _symbols[offset + 1];
			}
			case 77: // Declarations = Declarations.ds Declaration.d SEMICOLON.s
			{
					final Symbol _symbol_ds = _symbols[offset + 1];
					final List<CSSDeclarationNode> ds = (List<CSSDeclarationNode>) _symbol_ds.value;
					final Symbol _symbol_d = _symbols[offset + 2];
					final CSSDeclarationNode d = (CSSDeclarationNode) _symbol_d.value;
					final Symbol s = _symbols[offset + 3];
					
			d.setHasSemicolon(s);
			ds.add(d);

			return _symbols[offset + 1];
			}
			case 78: // Declaration = Identifier.i COLON Expression.e
			{
					final Symbol _symbol_i = _symbols[offset + 1];
					final String i = (String) _symbol_i.value;
					final Symbol _symbol_e = _symbols[offset + 3];
					final CSSExpressionNode e = (CSSExpressionNode) _symbol_e.value;
					
			return new CSSDeclarationNode(i, e);
			}
			case 79: // Declaration = Identifier.i COLON Expression.e IMPORTANT.s
			{
					final Symbol _symbol_i = _symbols[offset + 1];
					final String i = (String) _symbol_i.value;
					final Symbol _symbol_e = _symbols[offset + 3];
					final CSSExpressionNode e = (CSSExpressionNode) _symbol_e.value;
					final Symbol _symbol_s = _symbols[offset + 4];
					final String s = (String) _symbol_s.value;
					
			return new CSSDeclarationNode(i, e, s);
			}
			case 80: // Declaration = error
			{
					
			return new CSSErrorDeclarationNode();
			}
			case 81: // Expression = Expression.e Separator.s Term.t
			{
					final Symbol _symbol_e = _symbols[offset + 1];
					final CSSExpressionNode e = (CSSExpressionNode) _symbol_e.value;
					final Symbol _symbol_s = _symbols[offset + 2];
					final String s = (String) _symbol_s.value;
					final Symbol _symbol_t = _symbols[offset + 3];
					final CSSExpressionNode t = (CSSExpressionNode) _symbol_t.value;
					
			return new CSSTermListNode(e, t, s);
			}
			case 82: // Expression = Expression.e Term.t
			{
					final Symbol _symbol_e = _symbols[offset + 1];
					final CSSExpressionNode e = (CSSExpressionNode) _symbol_e.value;
					final Symbol _symbol_t = _symbols[offset + 2];
					final CSSExpressionNode t = (CSSExpressionNode) _symbol_t.value;
					
			return new CSSTermListNode(e, t);
			}
			case 84: // Expression = error
			{
					
			return new CSSErrorExpressionNode();
			}
			case 85: // Term = Primitive.p
			{
					final Symbol _symbol_p = _symbols[offset + 1];
					final String p = (String) _symbol_p.value;
					
			return new CSSTermNode(p);
			}
			case 87: // Selectors = Selectors.ss Combinator.c Selector.s
			{
					final Symbol _symbol_ss = _symbols[offset + 1];
					final List<CSSSelectorNode> ss = (List<CSSSelectorNode>) _symbol_ss.value;
					final Symbol _symbol_c = _symbols[offset + 2];
					final String c = (String) _symbol_c.value;
					final Symbol _symbol_s = _symbols[offset + 3];
					final CSSSelectorNode s = (CSSSelectorNode) _symbol_s.value;
					
			CSSSelectorNode lastSelector = ss.get(ss.size() - 1);
			lastSelector.setCombinator(c);

			ss.add(s);

			return _symbols[offset + 1];
			}
			case 88: // Selectors = Selector.s
			{
					final Symbol _symbol_s = _symbols[offset + 1];
					final CSSSelectorNode s = (CSSSelectorNode) _symbol_s.value;
					
			List<CSSSelectorNode> list = new ArrayList<CSSSelectorNode>();

			list.add(s);

			return new Symbol(list);
			}
			case 89: // Selector = Selector.s SimpleSelector.ss
			{
					final Symbol _symbol_s = _symbols[offset + 1];
					final CSSSelectorNode s = (CSSSelectorNode) _symbol_s.value;
					final Symbol _symbol_ss = _symbols[offset + 2];
					final CSSSimpleSelectorNode ss = (CSSSimpleSelectorNode) _symbol_ss.value;
					
			s.addChild(ss);

			return s;
			}
			case 90: // Selector = SimpleSelector.ss
			{
					final Symbol _symbol_ss = _symbols[offset + 1];
					final CSSSimpleSelectorNode ss = (CSSSimpleSelectorNode) _symbol_ss.value;
					
			CSSSelectorNode selector = new CSSSelectorNode();

			selector.addChild(ss);

			return selector;
			}
			case 91: // SimpleSelector = TypeOrUniversalSelector.t AttributeSelectors.a
			{
					final Symbol _symbol_t = _symbols[offset + 1];
					final String t = (String) _symbol_t.value;
					final Symbol _symbol_a = _symbols[offset + 2];
					final ArrayList _list_a = (ArrayList) _symbol_a.value;
					final CSSAttributeSelectorNode[] a = _list_a == null ? new CSSAttributeSelectorNode[0] : (CSSAttributeSelectorNode[]) _list_a.toArray(new CSSAttributeSelectorNode[_list_a.size()]);
					
			return new CSSSimpleSelectorNode(t, a);
			}
			case 92: // SimpleSelector = TypeOrUniversalSelector.t
			{
					final Symbol _symbol_t = _symbols[offset + 1];
					final String t = (String) _symbol_t.value;
					
			return new CSSSimpleSelectorNode(t);
			}
			case 93: // SimpleSelector = AttributeSelectors.a
			{
					final Symbol _symbol_a = _symbols[offset + 1];
					final ArrayList _list_a = (ArrayList) _symbol_a.value;
					final CSSAttributeSelectorNode[] a = _list_a == null ? new CSSAttributeSelectorNode[0] : (CSSAttributeSelectorNode[]) _list_a.toArray(new CSSAttributeSelectorNode[_list_a.size()]);
					
			return new CSSSimpleSelectorNode(a);
			}
			case 94: // AttributeSelectors = AttributeSelectors AttributeSelector
			{
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2].value); return _symbols[offset + 1];
			}
			case 95: // AttributeSelectors = AttributeSelector
			{
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
			}
			case 96: // AttributeSelector = CLASS.c
			{
					final Symbol _symbol_c = _symbols[offset + 1];
					final String c = (String) _symbol_c.value;
					
			return new CSSAttributeSelectorNode(c);
			}
			case 97: // AttributeSelector = COLON.c Identifier.i
			{
					final Symbol _symbol_c = _symbols[offset + 1];
					final String c = (String) _symbol_c.value;
					final Symbol _symbol_i = _symbols[offset + 2];
					final String i = (String) _symbol_i.value;
					
			return new CSSAttributeSelectorNode(c + i);
			}
			case 98: // AttributeSelector = COLON Function.f
			{
					final Symbol _symbol_f = _symbols[offset + 2];
					final CSSExpressionNode f = (CSSExpressionNode) _symbol_f.value;
					
			return new CSSAttributeSelectorNode(f);
			}
			case 99: // AttributeSelector = COLOR.c
			{
					final Symbol _symbol_c = _symbols[offset + 1];
					final String c = (String) _symbol_c.value;
					
			return new CSSAttributeSelectorNode(c);
			}
			case 100: // AttributeSelector = PROPERTY.p
			{
					final Symbol _symbol_p = _symbols[offset + 1];
					final String p = (String) _symbol_p.value;
					
			return new CSSAttributeSelectorNode(p);
			}
			case 101: // AttributeSelector = HASH.h
			{
					final Symbol _symbol_h = _symbols[offset + 1];
					final String h = (String) _symbol_h.value;
					
			return new CSSAttributeSelectorNode(h);
			}
			case 102: // AttributeSelector = LBRACKET.l Identifier.i RBRACKET.r
			{
					final Symbol _symbol_l = _symbols[offset + 1];
					final String l = (String) _symbol_l.value;
					final Symbol _symbol_i = _symbols[offset + 2];
					final String i = (String) _symbol_i.value;
					final Symbol _symbol_r = _symbols[offset + 3];
					final String r = (String) _symbol_r.value;
					
			return new CSSAttributeSelectorNode(l + i + r);
			}
			case 103: // AttributeSelector = LBRACKET.l Identifier.i AttributeValueOperator.o IdentiferOrString.s RBRACKET.r
			{
					final Symbol _symbol_l = _symbols[offset + 1];
					final String l = (String) _symbol_l.value;
					final Symbol _symbol_i = _symbols[offset + 2];
					final String i = (String) _symbol_i.value;
					final Symbol _symbol_o = _symbols[offset + 3];
					final String o = (String) _symbol_o.value;
					final Symbol _symbol_s = _symbols[offset + 4];
					final String s = (String) _symbol_s.value;
					final Symbol _symbol_r = _symbols[offset + 5];
					final String r = (String) _symbol_r.value;
					
			return new CSSAttributeSelectorNode(l + i + " " + o + " " + s + r);
			}
			case 4: // Statement = CharSet
			case 5: // Statement = Import
			case 6: // Statement = Media
			case 7: // Statement = Page
			case 8: // Statement = FontFace
			case 9: // Statement = Namespace
			case 10: // Statement = AtRule
			case 11: // Statement = Rule
			case 18: // MediaExpr = IDENTIFIER
			case 19: // MediaExpr = PROPERTY
			case 20: // MediaExpr = COLOR
			case 21: // MediaExpr = COLON
			case 22: // MediaExpr = RCURLY
			case 23: // MediaExpr = STRING
			case 24: // MediaExpr = LBRACKET
			case 25: // MediaExpr = CLASS
			case 26: // MediaExpr = HASH
			case 27: // MediaExpr = SEMICOLON
			case 28: // MediaExpr = URL
			case 29: // MediaExpr = STAR
			case 30: // MediaExpr = SELECTOR
			case 31: // MediaExpr = COMMA
			case 32: // MediaExpr = NUMBER
			case 33: // MediaExpr = PERCENTAGE
			case 34: // MediaExpr = LENGTH
			case 35: // MediaExpr = EMS
			case 36: // MediaExpr = EXS
			case 37: // MediaExpr = ANGLE
			case 38: // MediaExpr = TIME
			case 39: // MediaExpr = FREQUENCY
			case 40: // MediaExpr = PAGE
			case 41: // MediaExpr = AT_RULE
			case 42: // MediaExpr = CHARSET
			case 43: // MediaExpr = MEDIA
			case 44: // MediaExpr = FONTFACE
			case 45: // MediaExpr = NAMESPACE
			case 46: // MediaExpr = IMPORT
			case 47: // MediaExpr = RBRACKET
			case 48: // MediaExpr = LPAREN
			case 49: // MediaExpr = PLUS
			case 50: // MediaExpr = SLASH
			case 51: // MediaExpr = MINUS
			case 52: // MediaExpr = RPAREN
			case 53: // MediaExpr = IMPORTANT
			case 54: // MediaExpr = GREATER
			case 55: // MediaExpr = EQUAL
			case 56: // MediaExpr = INCLUDES
			case 57: // MediaExpr = DASHMATCH
			case 70: // Rule = error
			case 83: // Expression = Term
			case 86: // Term = Function
			case 104: // ImportWord = STRING
			case 105: // ImportWord = URL
			case 106: // Identifier = IDENTIFIER
			case 107: // Identifier = PROPERTY
			case 108: // IdentiferOrString = IDENTIFIER
			case 109: // IdentiferOrString = STRING
			case 110: // Separator = SLASH
			case 111: // Separator = COMMA
			case 112: // Separator = PLUS
			case 113: // Separator = MINUS
			case 114: // Combinator = COMMA
			case 115: // Combinator = PLUS
			case 116: // Combinator = GREATER
			case 117: // Primitive = NUMBER
			case 118: // Primitive = PERCENTAGE
			case 119: // Primitive = LENGTH
			case 120: // Primitive = EMS
			case 121: // Primitive = EXS
			case 122: // Primitive = ANGLE
			case 123: // Primitive = TIME
			case 124: // Primitive = FREQUENCY
			case 125: // Primitive = STRING
			case 126: // Primitive = IDENTIFIER
			case 127: // Primitive = URL
			case 128: // Primitive = COLOR
			case 129: // TypeOrUniversalSelector = IDENTIFIER
			case 130: // TypeOrUniversalSelector = STAR
			case 131: // TypeOrUniversalSelector = SELECTOR
			case 132: // AttributeValueOperator = EQUAL
			case 133: // AttributeValueOperator = INCLUDES
			case 134: // AttributeValueOperator = DASHMATCH
			{
				return _symbols[offset + 1];
			}
			case 66: // AtRule = AT_RULE STRING SEMICOLON
			{
				return _symbols[offset + 3];
			}
			case 67: // AtRule = AT_RULE STRING LCURLY RCURLY
			{
				return _symbols[offset + 4];
			}
			default:
				throw new IllegalArgumentException("unknown production #" + rule_num);
		}
	}
}
