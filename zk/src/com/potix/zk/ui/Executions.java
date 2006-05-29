/* Executions.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Fri Jun  3 17:55:08     2005, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.potix.zk.ui;

import java.util.Map;
import java.io.Reader;
import java.io.Writer;
import java.io.IOException;

import com.potix.idom.Document;
import com.potix.zk.ui.metainfo.PageDefinition;
import com.potix.zk.ui.metainfo.LanguageDefinition;
import com.potix.zk.ui.sys.UiEngine;
import com.potix.zk.ui.sys.WebAppCtrl;

/**
 * Utilities to access {@link Execution}.
 *
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 * @version $Revision: 1.13 $ $Date: 2006/05/29 04:28:00 $
 */
public class Executions {
	/** Stores the current {@link Execution}. */
	protected static final ThreadLocal _exec = new ThreadLocal();

	/** Returns the current execution.
	 */
	public static final Execution getCurrent() {
		return (Execution)_exec.get();
	}

	/** Evluates the specified expression by use of the current context
	 * ({@link #getCurrent}).
	 * @param comp as the self variable (ignored if null)
	 */
	public static final Object evaluate(Component comp,
	String expr, Class expectedType) {
		return getCurrent().evaluate(comp, expr, expectedType);
	}
	/** Evluates the specified expression with the resolver of the current
	 * execution ({@link #getCurrent}).
	 *
	 * @param page used as the self variable and to retrieve the page definition
	 * if pagedef is not defined. Ignored if null.
	 * @param pagedef the page definition used to retrieve the function mapper.
	 * If null and page is not null, page's definition is used.
	 * If both null, the current page's definition is used.
	 */
	public static final Object evaluate(PageDefinition pagedef, Page page,
	String expr, Class expectedType) {
		return getCurrent().evaluate(pagedef, page, expr, expectedType);
	}

	/** Creates components from a page file specified by an URI.
	 * Shortcut to {@link Execution#createComponents(String, Component, Map)}.
	 *
	 * @param parent the parent component, or null if you want it to be
	 * a root component. If parent is null, the page is assumed to be
	 * the current page, which is determined by the execution context.
	 * @param params a map of parameters that is accessible by the arg variable
	 * in EL, or by {@link Execution#getArg}.
	 * Ignored if null.
	 * @see #createComponents(PageDefinition, Component, Map)
	 */
	public static final Component createComponents(
	String uri, Component parent, Map params) {
		return getCurrent().createComponents(uri, parent, params);
	}
	/** Creates components based on the specified page definition.
	 * Shortcut to {@link Execution#createComponents(PageDefinition, Component, Map)}.
	 *
	 * @param pagedef the page definition to use. It cannot be null.
	 * @param parent the parent component, or null if you want it to be
	 * a root component. If parent is null, the page is assumed to be
	 * the current page, which is determined by the execution context.
	 * @param params a map of parameters that is accessible by the arg variable
	 * in EL, or by {@link Execution#getArg}.
	 * Ignored if null.
	 * @return the first component being created.
	 * @see #createComponents(String, Component, Map)
	 */
	public static final Component createComponents(PageDefinition pagedef,
	Component parent, Map params) {
		return getCurrent().createComponents(pagedef, parent, params);
	}

	/** Creates components from the raw content specified by a string.
	 * Shortcut to {@link Execution#createComponentsDirectly(String, String, Component, Map)}.
	 *
	 * @param content the raw content of the page. It must be a XML and
	 * compliant to the page format (such as ZUL).
	 * @param extension the default extension if the content doesn't specify
	 * an language. Ignored if null.
	 * If the content doesn't specify an language, {@link LanguageDefinition#lookupByExtension}
	 * is called.
	 * @param parent the parent component, or null if you want it to be
	 * a root component. If parent is null, the page is assumed to be
	 * the current page, which is determined by the execution context.
	 * @param params a map of parameters that is accessible by the arg variable
	 * in EL, or by {@link Execution#getArg}.
	 * Ignored if null.
	 * @see #createComponents(PageDefinition, Component, Map)
	 * @see #createComponents(String, Component, Map)
	 * @see #createComponentsDirectly(Document, String, Component, Map)
	 * @see #createComponentsDirectly(Reader, String, Component, Map)
	 */
	public static final Component createComponentsDirectly(String content,
	String extension, Component parent, Map params) {
		return getCurrent().createComponentsDirectly(content, extension, parent, params);
	}
	/** Creates components from the raw content specified by a DOM tree.
	 * Shortcut to {@link Execution#createComponentsDirectly(Document, String, Component, Map)}.
	 *
	 * @param content the raw content in DOM.
	 * @param extension the default extension if the content doesn't specify
	 * an language. Ignored if null.
	 * If the content doesn't specify an language, {@link LanguageDefinition#lookupByExtension}
	 * is called.
s	 * @param parent the parent component, or null if you want it to be
	 * a root component. If parent is null, the page is assumed to be
	 * the current page, which is determined by the execution context.
	 * @param params a map of parameters that is accessible by the arg variable
	 * in EL, or by {@link Execution#getArg}.
	 * Ignored if null.
	 * @see #createComponents(PageDefinition, Component, Map)
	 * @see #createComponents(String, Component, Map)
	 * @see #createComponentsDirectly(String, String, Component, Map)
	 * @see #createComponentsDirectly(Reader, String, Component, Map)
	 */
	public static final Component createComponentsDirectly(Document content,
	String extension, Component parent, Map params) {
		return getCurrent().createComponentsDirectly(content, extension, parent, params);
	}
	/** Creates components from the raw content read from the specified reader.
	 * Shortcut to {@link Execution#createComponentsDirectly(Reader, String, Component, Map)}.
	 *
	 * <p>The raw content is loader and parsed to a page defintion by use of
	 * {@link Execution#getPageDefinitionDirectly(Reader, String)}, and then
	 * invokes {@link #createComponents(PageDefinition,Component,Map)}
	 * to create components.
	 *
	 * @param reader the reader to retrieve the raw content.
	 * @param extension the default extension if the content of reader doesn't specify
	 * an language. Ignored if null.
	 * If the content doesn't specify an language, {@link LanguageDefinition#lookupByExtension}
	 * is called.
	 * @param parent the parent component, or null if you want it to be
	 * a root component. If parent is null, the page is assumed to be
	 * the current page, which is determined by the execution context.
	 * @param params a map of parameters that is accessible by the arg variable
	 * in EL, or by {@link Execution#getArg}.
	 * Ignored if null.
	 * @see #createComponents(PageDefinition, Component, Map)
	 * @see #createComponents(String, Component, Map)
	 * @see #createComponentsDirectly(Document, String, Component, Map)
	 * @see #createComponentsDirectly(String, String, Component, Map)
	 */
	public static Component createComponentsDirectly(Reader reader,
	String extension, Component parent, Map params)
	throws IOException {
		return getCurrent().createComponentsDirectly(reader, extension, parent, params);
	}

	/** Sends a temporary redirect response to the client using the specified
	 * redirect location URL by use of the current execution,
	 * {@link #getCurrent}.
	 *
	 * <p>After calling this method, the caller shall end the processing
	 * immediately (by returning). All pending requests and events will
	 * be dropped.
	 * @see Execution#sendRedirect
	 */
	public static void sendRedirect(String uri) {
		getCurrent().sendRedirect(uri);
	}

	/** A shortcut of Executions.getCurrent.include(page).
	 *
	 * @see Execution#include(Writer,String,Map,int)
	 * @see Execution#include(String)
	 */
	public static void include(String page)
	throws IOException {
		getCurrent().include(page);
	}
	/** A shortcut of Executions.getCurrent.forward(page).
	 *
	 * @see Execution#forward(Writer,String,Map,int)
	 * @see Execution#forward(String)
	 */
	public static void forward(String page)
	throws IOException {
		getCurrent().forward(page);
	}

	//-- wait/notify --//
	/** Suspends the current processing of an event and wait until the
	 * other thread invokes {@link #notify(Object)}, {@link #notifyAll(Object)},
	 * {@link #notify(Page, Object)} or {@link #notifyAll(Page, Object)}
	 * for the specified object.
	 *
	 * <p>It can only be called when the current thread is processing an event.
	 * And, when called, the current processing is suspended and ZK continues
	 * to process the next event and finally render the result.
	 *
	 * <p>It is typical use to implement a modal dialog where it won't return
	 * until the modal dialog ends.
	 *
	 * @param obj any non-null object to identify what to wait, such that
	 * {@link #notify(Object)} and {@link #notify(Page, Object)} knows
	 * which object to notify.
	 * @exception UiException if it is called not during event processing.
	 */
	public static final void wait(Object obj)
	throws InterruptedException {
		getUiEngine().wait(obj);
	}
	/** Wakes up a single event processing thread that is waiting on the
	 * specified object.
	 *
	 * <p>Unlike {@link #notify(Page, Object)}, this method can be invoked only
	 * if the same desktop is locked for processing requests.
	 *
	 * @param obj any non-null object to identify what to notify. It must be
	 * same object passed to {@link #wait}.
	 * @see #notify(Page, Object)
	 * @see #notifyAll(Object)
	 * @exception UiException if it is called not during event processing.
	 */
	public static final void notify(Object obj) {
		getUiEngine().notify(obj);
	}
	/** Wakes up all event processing thread that are waiting on the
	 * specified object.
	 *
	 * <p>Unlike {@link #notify(Page, Object)}, this method can be invoked only
	 * if the same desktop is locked for processing requests.
	 *
	 * @param obj any non-null object to identify what to notify. It must be
	 * same object passed to {@link #wait}.
	 * @see #notify(Page, Object)
	 * @see #notifyAll(Object)
	 * @exception UiException if it is called not during event processing.
	 */
	public static final void notifyAll(Object obj) {
		getUiEngine().notifyAll(obj);
	}
	/** Wakes up a single event processing thread for the specified page
	 * that is waiting on the specified object.
	 *
	 * <p>Unlike {@link #notify(Object)}, this method can be called any time.
	 * It is designed to let working threads resume an event processing
	 * thread.
	 *
	 * <p>If this method is NOT called in an event processing thread,
	 * the resumed thread won't execute until the next request is received.
	 * To enforce it happen, you might use the timer component (found in ZUL).
	 *
	 * @param page the page which the suspended thread is processing.
	 * It could be any page of the same desktop of the suspended thread.
	 * @param obj any non-null object to identify what to notify. It must be
	 * same object passed to {@link #wait}.
	 * @see #notify(Object)
	 * @see #notifyAll(Page, Object)
	 */
	public static final void notify(Page page, Object obj) {
		getUiEngine().notify(page, obj);
	}
	/** Wakes up all event processing theads for the specified page
	 * that are waiting on the specified object.
	 *
	 * <p>Unlike {@link #notifyAll(Object)}, this method can be called any time.
	 * It is designed to let working threads resume an event processing
	 * thread.
	 *
	 * <p>If this method is NOT called in an event processing thread,
	 * the resumed thread won't execute until the next request is received.
	 * To enforce it happen, you might use the timer component (found in ZUL).
	 *
	 * @param page the page which the suspended thread is processing.
	 * It could be any page of the same desktop of the suspended thread.
	 * @param obj any non-null object to identify what to notify. It must be
	 * same object passed to {@link #wait}.
	 * @see #notify(Object)
	 * @see #notifyAll(Page, Object)
	 */
	public static final  void notifyAll(Page page, Object obj) {
		getUiEngine().notifyAll(page, obj);
	}

	private static final UiEngine getUiEngine() {
		return ((WebAppCtrl)getCurrent().getDesktop().getWebApp()).getUiEngine();
	}
}
