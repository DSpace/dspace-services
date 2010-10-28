/**
 * Copyright (c) 2002-2010, DuraSpace.  All rights reserved
 * Licensed under the DuraSpace License.
 *
 * A copy of the DuraSpace License has been included in this
 * distribution and is available at: http://www.dspace.org/license
 */
package org.dspace.utils.servicemanager;


/**
 * This is a special marker interface which is used to indicate that this
 * service is not a provider and thus should not be included in the 
 * provider stacks which are being setup and stored.  Any service which 
 * implements this interface will not be able to be added to the 
 * provider stack.  In some cases this results in an exception but it
 * mostly just results in the object being ignored and not placed into 
 * the stack.
 * 
 * @author Aaron Zeckoski (azeckoski @ gmail.com)
 */
public interface NotProvider {

    // This area intentionally left blank

}
