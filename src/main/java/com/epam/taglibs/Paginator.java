package com.epam.taglibs;

import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Paginator extends SimpleTagSupport {
	private String uri;
	private int currPage;
	private int totalPages;
	private int maxLinks = 10;

	private Writer getWriter() {
		JspWriter out = getJspContext().getOut();

		return out;
	}

	@Override
	public void doTag() throws JspException {
		Writer out = getWriter();

		boolean lastPage = currPage == totalPages;
		// first visible page in nav menu
		int pgStart = Math.max(currPage - maxLinks / 2, 1);
		// last visible page in nav menu
		int pgEnd = pgStart + maxLinks;

		// check out of range
		if (pgEnd > totalPages + 1) {
			int diff = pgEnd - totalPages;
			pgStart -= diff - 1;
			if (pgStart < 1)
				pgStart = 1;
			pgEnd = totalPages + 1;
		}

		try {
			out.write("<ul class=\"pagination\">");

			if (currPage > 1) {
				out.write(constructLink(1, "First",
						""));
				out.write(constructLink(currPage - 1, "<",
						"paginatorPrev"));
			}
			for (int i = pgStart; i < pgEnd; i++) {
				if (i == currPage)
					out.write(constructLink(currPage, String.valueOf(currPage),
							"active"));
				else
					out.write(constructLink(i));
			}

			if (!lastPage) {
				out.write(constructLink(currPage + 1, ">", null));
				out.write(constructLink(totalPages, "Last",
						""));
			}
			out.write("</ul>");
			out.write(constructGoToForm());

		} catch (java.io.IOException ex) {
			throw new JspException("Error in Paginator tag", ex);
		}
	}

	private String constructLink(int page) {
		return constructLink(page, String.valueOf(page), null);
	}

	private String constructLink(int page, String text, String className) {
		StringBuilder link = new StringBuilder("<li");
		if (className != null) {
			link.append(" class=\"");
			link.append(className);
			link.append("\"");
		}
		link.append(">").append("<a href=\"")
				.append(uri.replace("##", String.valueOf(page))).append("\">")
				.append(text).append("</a></li>");
		return link.toString();
	}

	private String constructGoToForm() {
		String context = uri.replace("/?page=##", "");
		String goToForm = new String(
				"<form action='"
						+ context
						+ "/'>"
						+ "<label for='itemsPerPage' class='goToLabel1'>items per page</label>"
						+ " <input type='text' class='itemsInput'"
						+ " name='itemsPerPage'> <label for='page' class='goToLabel2'"
						+ " >page #</label> <input type='text'"
						+ " class='pageInput' name='page'> <input"
						+ " type='submit' class='btn btn-default goBtn' value='Go'>"
						+ "</form>");
		return goToForm;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setMaxLinks(int maxLinks) {
		this.maxLinks = maxLinks;
	}
}