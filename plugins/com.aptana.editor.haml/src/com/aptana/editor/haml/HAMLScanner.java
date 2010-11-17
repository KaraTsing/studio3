/**
 * This file Copyright (c) 2005-2010 Aptana, Inc. This program is
 * dual-licensed under both the Aptana Public License and the GNU General
 * Public license. You may elect to use one or the other of these licenses.
 * 
 * This program is distributed in the hope that it will be useful, but
 * AS-IS and WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, TITLE, or
 * NONINFRINGEMENT. Redistribution, except as permitted by whichever of
 * the GPL or APL you select, is prohibited.
 *
 * 1. For the GPL license (GPL), you can redistribute and/or modify this
 * program under the terms of the GNU General Public License,
 * Version 3, as published by the Free Software Foundation.  You should
 * have received a copy of the GNU General Public License, Version 3 along
 * with this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Aptana provides a special exception to allow redistribution of this file
 * with certain other free and open source software ("FOSS") code and certain additional terms
 * pursuant to Section 7 of the GPL. You may view the exception and these
 * terms on the web at http://www.aptana.com/legal/gpl/.
 * 
 * 2. For the Aptana Public License (APL), this program and the
 * accompanying materials are made available under the terms of the APL
 * v1.0 which accompanies this distribution, and is available at
 * http://www.aptana.com/legal/apl/.
 * 
 * You may view the GPL, Aptana's exception and additional terms, and the
 * APL in the file titled license.html at the root of the corresponding
 * plugin containing this source file.
 * 
 * Any modifications to this file must keep this entire header intact.
 */
package com.aptana.editor.haml;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

import com.aptana.editor.common.text.rules.RegexpRule;
import com.aptana.editor.common.text.rules.SingleCharacterRule;
import com.aptana.editor.common.text.rules.WhitespaceDetector;
import com.aptana.theme.IThemeManager;
import com.aptana.theme.ThemePlugin;

public class HAMLScanner extends BufferedRuleBasedScanner {

	private static final boolean OPTIMIZE_REGEXP_RULES = true;

	public HAMLScanner() {
		List<IRule> rules = new ArrayList<IRule>();

		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new WhitespaceDetector()));

		// FIXME Must be at end of line (only \s*\n can follow)
		rules.add(new SingleCharacterRule('|', createToken("punctuation.separator.continuation.haml"))); //$NON-NLS-1$

		// tags
		WordRule rule = new WordRule(new IWordDetector() {

			public boolean isWordStart(char c) {
				return c == '%';
			}

			public boolean isWordPart(char c) {
				return Character.isLetterOrDigit(c) || c == '_' || c == '-';
			}
		}, createToken("entity.name.tag.haml")); //$NON-NLS-1$
		rules.add(rule);

		// ids
		rule = new WordRule(new IWordDetector() {
			public boolean isWordStart(char c) {
				return c == '#';
			}

			public boolean isWordPart(char c) {
				return Character.isLetterOrDigit(c) || c == '_' || c == '-';
			}
		}, createToken("entity.other.attribute-name.id")); //$NON-NLS-1$
		rules.add(rule);

		// classes
		rule = new WordRule(new IWordDetector() {

			public boolean isWordStart(char c) {
				return c == '.';
			}

			public boolean isWordPart(char c) {
				return Character.isLetterOrDigit(c) || c == '_' || c == '-';
			}
		}, createToken("entity.other.attribute-name.class")); //$NON-NLS-1$
		rules.add(rule);

		// TODO Optimize by turning this into WordRules!
		// escape character FIXME Must be at beginning of line (can only be preceded by spaces*)
		rules.add(new RegexpRule("\\\\.", createToken("meta.escape.haml"), OPTIMIZE_REGEXP_RULES)); //$NON-NLS-1$ //$NON-NLS-2$
		setRules(rules.toArray(new IRule[rules.size()]));
	}

	protected IToken createToken(String string) {
		return getThemeManager().getToken(string);
	}

	protected IThemeManager getThemeManager() {
		return ThemePlugin.getDefault().getThemeManager();
	}
}
