
// Description: Java 25 Factory service implementation for ServerMethod buffers

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 */

package server.markhome.mcf.v3_1.cfbam.cfbam.buff;

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfbam.cfbampub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbampubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprot.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfbam.cfbamobj.*;

/*
 *	Java 25 Factory service implementation for ServerMethod buffers.
 */
@Service("cfbam31BuffServerMethodFactoryService")
public class CFBamBuffServerMethodFactoryService
	implements ICFBamServerMethodFactory
{
	public CFBamBuffServerMethodFactoryService() {
	}

	@Override
	public ICFBamServerMethodByUNameIdxKey newByUNameIdxKey() {
		ICFBamServerMethodByUNameIdxKey key =
			new CFBamBuffServerMethodByUNameIdxKey();
		return( key );
	}

	public CFBamBuffServerMethodByUNameIdxKey ensureByUNameIdxKey(ICFBamServerMethodByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffServerMethodByUNameIdxKey) {
			return( (CFBamBuffServerMethodByUNameIdxKey)key );
		}
		else {
			CFBamBuffServerMethodByUNameIdxKey mapped = new CFBamBuffServerMethodByUNameIdxKey();
			mapped.setRequiredTableId( key.getRequiredTableId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

	@Override
	public ICFBamServerMethodByMethTableIdxKey newByMethTableIdxKey() {
		ICFBamServerMethodByMethTableIdxKey key =
			new CFBamBuffServerMethodByMethTableIdxKey();
		return( key );
	}

	public CFBamBuffServerMethodByMethTableIdxKey ensureByMethTableIdxKey(ICFBamServerMethodByMethTableIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffServerMethodByMethTableIdxKey) {
			return( (CFBamBuffServerMethodByMethTableIdxKey)key );
		}
		else {
			CFBamBuffServerMethodByMethTableIdxKey mapped = new CFBamBuffServerMethodByMethTableIdxKey();
			mapped.setRequiredTableId( key.getRequiredTableId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamServerMethodByMethCodeVisIdxKey newByMethCodeVisIdxKey() {
		ICFBamServerMethodByMethCodeVisIdxKey key =
			new CFBamBuffServerMethodByMethCodeVisIdxKey();
		return( key );
	}

	public CFBamBuffServerMethodByMethCodeVisIdxKey ensureByMethCodeVisIdxKey(ICFBamServerMethodByMethCodeVisIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffServerMethodByMethCodeVisIdxKey) {
			return( (CFBamBuffServerMethodByMethCodeVisIdxKey)key );
		}
		else {
			CFBamBuffServerMethodByMethCodeVisIdxKey mapped = new CFBamBuffServerMethodByMethCodeVisIdxKey();
			mapped.setRequiredCodeVis( key.getRequiredCodeVis() );
			return( mapped );
		}
	}

	@Override
	public ICFBamServerMethodByMethTableVisIdxKey newByMethTableVisIdxKey() {
		ICFBamServerMethodByMethTableVisIdxKey key =
			new CFBamBuffServerMethodByMethTableVisIdxKey();
		return( key );
	}

	public CFBamBuffServerMethodByMethTableVisIdxKey ensureByMethTableVisIdxKey(ICFBamServerMethodByMethTableVisIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffServerMethodByMethTableVisIdxKey) {
			return( (CFBamBuffServerMethodByMethTableVisIdxKey)key );
		}
		else {
			CFBamBuffServerMethodByMethTableVisIdxKey mapped = new CFBamBuffServerMethodByMethTableVisIdxKey();
			mapped.setRequiredTableId( key.getRequiredTableId() );
			mapped.setRequiredCodeVis( key.getRequiredCodeVis() );
			return( mapped );
		}
	}

	@Override
	public ICFBamServerMethodByDefSchemaIdxKey newByDefSchemaIdxKey() {
		ICFBamServerMethodByDefSchemaIdxKey key =
			new CFBamBuffServerMethodByDefSchemaIdxKey();
		return( key );
	}

	public CFBamBuffServerMethodByDefSchemaIdxKey ensureByDefSchemaIdxKey(ICFBamServerMethodByDefSchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffServerMethodByDefSchemaIdxKey) {
			return( (CFBamBuffServerMethodByDefSchemaIdxKey)key );
		}
		else {
			CFBamBuffServerMethodByDefSchemaIdxKey mapped = new CFBamBuffServerMethodByDefSchemaIdxKey();
			mapped.setOptionalDefSchemaId( key.getOptionalDefSchemaId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamServerMethod newRec() {
		ICFBamServerMethod rec =
			new CFBamBuffServerMethod();
		return( rec );
	}

	public CFBamBuffServerMethod ensureRec(ICFBamServerMethod rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffServerMethod) {
			return ((CFBamBuffServerMethod)rec);
		}
		else {	
			switch (rec.getClassCode()) {
				case ICFBamServerMethod.CLASS_CODE: {
					CFBamBuffServerMethod mapped = new CFBamBuffServerMethod();
					mapped.set(rec);
					return(mapped); }
				case ICFBamServerObjFunc.CLASS_CODE: {
					CFBamBuffServerObjFunc mapped = new CFBamBuffServerObjFunc();
					mapped.set((ICFBamServerObjFunc)rec);
					return(mapped); }
				case ICFBamServerProc.CLASS_CODE: {
					CFBamBuffServerProc mapped = new CFBamBuffServerProc();
					mapped.set((ICFBamServerProc)rec);
					return(mapped); }
				case ICFBamServerListFunc.CLASS_CODE: {
					CFBamBuffServerListFunc mapped = new CFBamBuffServerListFunc();
					mapped.set((ICFBamServerListFunc)rec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureRec",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamServerMethod",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamServerMethod");
			}
		}
	}

	@Override
	public ICFBamServerMethodH newHRec() {
		ICFBamServerMethodH hrec =
			new CFBamBuffServerMethodH();
		return( hrec );
	}

	public CFBamBuffServerMethodH ensureHRec(ICFBamServerMethodH hrec) {
		if( hrec == null ) {
			return( null );
		}
		else if (hrec instanceof CFBamBuffServerMethodH) {
			return ((CFBamBuffServerMethodH)hrec);
		}
		else {	
			switch (hrec.getClassCode()) {
				case ICFBamServerMethod.CLASS_CODE: {
					CFBamBuffServerMethodH mapped = new CFBamBuffServerMethodH();
					mapped.set(hrec);
					return(mapped); }
				case ICFBamServerObjFunc.CLASS_CODE: {
					CFBamBuffServerObjFuncH mapped = new CFBamBuffServerObjFuncH();
					mapped.set((ICFBamServerObjFuncH)hrec);
					return(mapped); }
				case ICFBamServerProc.CLASS_CODE: {
					CFBamBuffServerProcH mapped = new CFBamBuffServerProcH();
					mapped.set((ICFBamServerProcH)hrec);
					return(mapped); }
				case ICFBamServerListFunc.CLASS_CODE: {
					CFBamBuffServerListFuncH mapped = new CFBamBuffServerListFuncH();
					mapped.set((ICFBamServerListFuncH)hrec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureHRec",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamServerMethod",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamServerMethod");
			}
		}
	}
}
