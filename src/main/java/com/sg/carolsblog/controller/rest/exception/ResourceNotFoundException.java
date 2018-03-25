/*
 * $Id: $
 * $Revision: $
 * $Author: $
 * $Date: $
 * Copyright (c) 2018 Trustwave Holdings, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of Trustwave Holdings, Inc.  Use of this software is governed by
 * the terms and conditions of the license statement and limited
 * warranty furnished with the software.
 *
 * IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD TRUSTWAVE HOLDINGS INC.,
 * ITS RELATED COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST
 * ANY CLAIMS OR LIABILITIES ARISING OUT OF OR RESULTING FROM THE USE,
 * MODIFICATION, OR DISTRIBUTION OF PROGRAMS OR FILES CREATED FROM,
 * BASED ON, AND/OR DERIVED FROM THIS SOURCE CODE FILE.
 */
package com.sg.carolsblog.controller.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * --- TODO: Class comments go here ---
 *
 * <b><pre>
 * Copyright (c) 2018 Trustwave Holdings, Inc.
 * All rights reserved.
 * </pre></b>
 *
 * @author bscaccia
 * @version $Revision: $
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
