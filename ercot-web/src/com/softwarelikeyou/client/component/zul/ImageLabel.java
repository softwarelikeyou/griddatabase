package com.softwarelikeyou.client.component.zul;

import java.io.IOException;
import java.io.Writer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.metainfo.LanguageDefinition;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.impl.LabelImageElement;

public class ImageLabel extends LabelImageElement {

	static final long serialVersionUID = 1L;
	
	private int _maxlength;
	private boolean _pre, _hyphen, _multiline;
	
	public int getMaxlength() {
		return _maxlength;
	}

	public void setMaxlength(int maxlength) {
		
		if (maxlength < 0) maxlength = 0;
		
		if (_maxlength != maxlength) {
			_maxlength = maxlength;
			smartUpdate("maxlength", _maxlength);
		}
		
	}

	public boolean isPre() {
		return _pre;
	}

	public void setPre(boolean pre) {
		if (_pre != pre) {
			_pre = pre;
			smartUpdate("pre", _pre);
		}
	}

	public boolean isMultiline() {
		return _multiline;
	}
	
	public void setMultiline(boolean multiline) {
		if (_multiline != multiline) {
			_multiline = multiline;
			smartUpdate("multiline", _multiline);
		}
	}
	
	public boolean isHyphen() {
		return _hyphen;
	}

	public void setHyphen(boolean hyphen) {
		if (_hyphen != hyphen) {
			_hyphen = hyphen;
			smartUpdate("hyphen", _hyphen);
		}
	}

	/** Whether to generate the value directly without ID.
	 * <p>Used only for component generated. Not for applications.
	 * @since 3.0.0
	 */
	public boolean isIdRequired() {
		final Component p = getParent();
		return p == null || !isVisible() 
			|| !isRawLabel(p) //|| !Components.isAutoId(getId())
			//|| isAsapRequired(Events.ON_CLICK)
			|| !isEmpty(getStyle()) || !isEmpty(getSclass())
			|| !isEmpty(getContext()) || !isEmpty(getTooltip())
			|| !isEmpty(getTooltiptext()) || !isEmpty(getPopup())
			|| !"false".equals(getDraggable())
			|| !"false".equals(getDroppable())
			//|| isAsapRequired(Events.ON_RIGHT_CLICK)
			|| !isEmpty(getAction())
			|| !isEmpty(getLeft()) || !isEmpty(getTop())
			|| !isEmpty(getWidth()) || !isEmpty(getHeight());
			//|| isAsapRequired(Events.ON_DOUBLE_CLICK);
	}
	
	private static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}
	
	private static boolean isRawLabel(Component comp) {
		final LanguageDefinition langdef =
			comp.getDefinition().getLanguageDefinition();
		return langdef != null && langdef.isRawLabel();
	}

	public String getEncodedText() {
		StringBuffer sb = null;
		final int len = getLabel().length();
		if (_pre || _multiline) {
			for (int j = 0, k;; j = k + 1) {
				k = getLabel().indexOf('\n', j);
				if (k < 0) {
					sb = encodeLine(sb, j, len);
					break;
				}

				if (sb == null) {
					assert j == 0;
					sb = new StringBuffer(getLabel().length() + 10);
				}
				sb = encodeLine(sb, j,
					k > j && getLabel().charAt(k - 1) == '\r' ? k - 1: k);
				sb.append("<br/>");
			}
		} else {
			sb = encodeLine(null, 0, len);
		}
		return sb != null ? sb.toString(): getLabel();
	}

	private StringBuffer encodeLine(StringBuffer sb, int b, int e) {
		boolean prews = _pre || _multiline;
		int linesz = 0;
		if (_maxlength > 0) {
			int deta = e - b;
			if (deta > _maxlength) {
				if (_hyphen) {
					linesz = _maxlength;
				} else if (!prews) {
					assert b == 0;
					int j = _maxlength;
					while (j > 0 && Character.isWhitespace(getLabel().charAt(j - 1)))
						--j;
					return new StringBuffer(j + 3)
						.append(getLabel().substring(0, j)).append("...");
				}
			}
		}

		l_linebreak:
		for (int cnt = 0, j = b; j < e; ++j) {
			final char cc = getLabel().charAt(j);
			String val = null;
			if (linesz > 0  && ++cnt > linesz && j + 1 < e) {
				sb = alloc(sb, j);
				if (Character.isLetterOrDigit(cc)
				&& Character.isLetterOrDigit(getLabel().charAt(j+1))) {
					cnt = 0;
					for (int k = sb.length(); cnt < 3; ++cnt) {
						if (!Character.isLetterOrDigit(sb.charAt(--k))) {
							sb.insert(k + 1, "<br/>");
							--j;
							continue l_linebreak;
						}
					}
					sb.append('-').append("<br/>").append(cc);
					cnt = 1;
					continue;
				} else if (!Character.isWhitespace(cc)) {
					sb.append(cc);
				}
				sb.append("<br/>");
				cnt = 0;
				continue;
			}

			if (cc == '\t') {
				if (prews) val = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			} else if (cc == ' ' || cc == '\f') {
				if (prews) val = "&nbsp;";
			} else {
				if (_multiline) prews = false;

				switch (cc) {
				case '<': val = "&lt;"; break;
				case '>': val = "&gt;"; break;
				case '&': val = "&amp;"; break;
				}
			}

			if (val != null) sb = alloc(sb, j).append(val);
			else if (sb != null) sb.append(cc);
		}
		return sb;
	}
	
	private StringBuffer alloc(StringBuffer sb, int e) {
		if (sb == null) {
			sb = new StringBuffer(getLabel().length() + 10);
			sb.append(getLabel().substring(0, e));
		}
		return sb;
	}

	public String getZclass() {
		return _zclass == null ? "z-imagelabel" : _zclass;
	}
	
	public void invalidate() {
		if (isIdRequired()) super.invalidate();
		else getParent().invalidate();
	}
	
	public void redraw(Writer out) throws IOException {
		if (isIdRequired()) super.redraw(out);
		else out.write(getEncodedText());
			//no processing; direct output if not ZUL
	}
	
	/** No child is allowed.
	 */
	public boolean isChildable() {
		return false;
	}
	
	public String getValue() {
		return getLabel();
	}
	
	public void setValue(String value) { 
		setLabel(value);
	}

	protected void renderProperties(ContentRenderer renderer) throws IOException {
		super.renderProperties(renderer);
		render(renderer, "maxlength", _maxlength);
		render(renderer, "label", getLabel());
		render(renderer, "multiline", _multiline);
		render(renderer, "pre", _pre);
		render(renderer, "image", getImage());
	}
	
}
