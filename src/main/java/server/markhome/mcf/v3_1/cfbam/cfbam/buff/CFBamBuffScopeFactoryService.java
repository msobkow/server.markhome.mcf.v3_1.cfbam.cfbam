
// Description: Java 25 Factory service implementation for Scope buffers

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
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.buff.*;
import server.markhome.mcf.v3_1.cfint.cfint.buff.*;

/*
 *	Java 25 Factory service implementation for Scope buffers.
 */
@Service("cfbam31BuffScopeFactoryService")
public class CFBamBuffScopeFactoryService
	implements ICFBamScopeFactory
{
	public CFBamBuffScopeFactoryService() {
	}

	@Override
	public ICFBamScopeHPKey newHPKey() {
		ICFBamScopeHPKey hpkey =
			new CFBamBuffScopeHPKey();
		return( hpkey );
	}

	public CFBamBuffScopeHPKey ensureHPKey(ICFBamScopeHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFBamBuffScopeHPKey) {
			return( (CFBamBuffScopeHPKey)key );
		}
		else {
			CFBamBuffScopeHPKey mapped = new CFBamBuffScopeHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredId( key.getRequiredId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamScopeByTenantIdxKey newByTenantIdxKey() {
		ICFBamScopeByTenantIdxKey key =
			new CFBamBuffScopeByTenantIdxKey();
		return( key );
	}

	public CFBamBuffScopeByTenantIdxKey ensureByTenantIdxKey(ICFBamScopeByTenantIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffScopeByTenantIdxKey) {
			return( (CFBamBuffScopeByTenantIdxKey)key );
		}
		else {
			CFBamBuffScopeByTenantIdxKey mapped = new CFBamBuffScopeByTenantIdxKey();
			mapped.setRequiredTenantId( key.getRequiredTenantId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamScope newRec() {
		ICFBamScope rec =
			new CFBamBuffScope();
		return( rec );
	}

	public CFBamBuffScope ensureRec(ICFBamScope rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffScope) {
			return ((CFBamBuffScope)rec);
		}
		else {	
			switch (rec.getClassCode()) {
				case ICFBamScope.CLASS_CODE: {
					CFBamBuffScope mapped = new CFBamBuffScope();
					mapped.set(rec);
					return(mapped); }
				case ICFBamSchemaDef.CLASS_CODE: {
					CFBamBuffSchemaDef mapped = new CFBamBuffSchemaDef();
					mapped.set((ICFBamSchemaDef)rec);
					return(mapped); }
				case ICFBamSchemaRef.CLASS_CODE: {
					CFBamBuffSchemaRef mapped = new CFBamBuffSchemaRef();
					mapped.set((ICFBamSchemaRef)rec);
					return(mapped); }
				case ICFBamServerMethod.CLASS_CODE: {
					CFBamBuffServerMethod mapped = new CFBamBuffServerMethod();
					mapped.set((ICFBamServerMethod)rec);
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
				case ICFBamTable.CLASS_CODE: {
					CFBamBuffTable mapped = new CFBamBuffTable();
					mapped.set((ICFBamTable)rec);
					return(mapped); }
				case ICFBamClearDep.CLASS_CODE: {
					CFBamBuffClearDep mapped = new CFBamBuffClearDep();
					mapped.set((ICFBamClearDep)rec);
					return(mapped); }
				case ICFBamClearSubDep1.CLASS_CODE: {
					CFBamBuffClearSubDep1 mapped = new CFBamBuffClearSubDep1();
					mapped.set((ICFBamClearSubDep1)rec);
					return(mapped); }
				case ICFBamClearSubDep2.CLASS_CODE: {
					CFBamBuffClearSubDep2 mapped = new CFBamBuffClearSubDep2();
					mapped.set((ICFBamClearSubDep2)rec);
					return(mapped); }
				case ICFBamClearSubDep3.CLASS_CODE: {
					CFBamBuffClearSubDep3 mapped = new CFBamBuffClearSubDep3();
					mapped.set((ICFBamClearSubDep3)rec);
					return(mapped); }
				case ICFBamClearTopDep.CLASS_CODE: {
					CFBamBuffClearTopDep mapped = new CFBamBuffClearTopDep();
					mapped.set((ICFBamClearTopDep)rec);
					return(mapped); }
				case ICFBamDelDep.CLASS_CODE: {
					CFBamBuffDelDep mapped = new CFBamBuffDelDep();
					mapped.set((ICFBamDelDep)rec);
					return(mapped); }
				case ICFBamDelSubDep1.CLASS_CODE: {
					CFBamBuffDelSubDep1 mapped = new CFBamBuffDelSubDep1();
					mapped.set((ICFBamDelSubDep1)rec);
					return(mapped); }
				case ICFBamDelSubDep2.CLASS_CODE: {
					CFBamBuffDelSubDep2 mapped = new CFBamBuffDelSubDep2();
					mapped.set((ICFBamDelSubDep2)rec);
					return(mapped); }
				case ICFBamDelSubDep3.CLASS_CODE: {
					CFBamBuffDelSubDep3 mapped = new CFBamBuffDelSubDep3();
					mapped.set((ICFBamDelSubDep3)rec);
					return(mapped); }
				case ICFBamDelTopDep.CLASS_CODE: {
					CFBamBuffDelTopDep mapped = new CFBamBuffDelTopDep();
					mapped.set((ICFBamDelTopDep)rec);
					return(mapped); }
				case ICFBamIndex.CLASS_CODE: {
					CFBamBuffIndex mapped = new CFBamBuffIndex();
					mapped.set((ICFBamIndex)rec);
					return(mapped); }
				case ICFBamPopDep.CLASS_CODE: {
					CFBamBuffPopDep mapped = new CFBamBuffPopDep();
					mapped.set((ICFBamPopDep)rec);
					return(mapped); }
				case ICFBamPopSubDep1.CLASS_CODE: {
					CFBamBuffPopSubDep1 mapped = new CFBamBuffPopSubDep1();
					mapped.set((ICFBamPopSubDep1)rec);
					return(mapped); }
				case ICFBamPopSubDep2.CLASS_CODE: {
					CFBamBuffPopSubDep2 mapped = new CFBamBuffPopSubDep2();
					mapped.set((ICFBamPopSubDep2)rec);
					return(mapped); }
				case ICFBamPopSubDep3.CLASS_CODE: {
					CFBamBuffPopSubDep3 mapped = new CFBamBuffPopSubDep3();
					mapped.set((ICFBamPopSubDep3)rec);
					return(mapped); }
				case ICFBamPopTopDep.CLASS_CODE: {
					CFBamBuffPopTopDep mapped = new CFBamBuffPopTopDep();
					mapped.set((ICFBamPopTopDep)rec);
					return(mapped); }
				case ICFBamRelation.CLASS_CODE: {
					CFBamBuffRelation mapped = new CFBamBuffRelation();
					mapped.set((ICFBamRelation)rec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureRec",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamScope",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamScope");
			}
		}
	}

	@Override
	public ICFBamScopeH newHRec() {
		ICFBamScopeH hrec =
			new CFBamBuffScopeH();
		return( hrec );
	}

	public CFBamBuffScopeH ensureHRec(ICFBamScopeH hrec) {
		if( hrec == null ) {
			return( null );
		}
		else if (hrec instanceof CFBamBuffScopeH) {
			return ((CFBamBuffScopeH)hrec);
		}
		else {	
			switch (hrec.getClassCode()) {
				case ICFBamScope.CLASS_CODE: {
					CFBamBuffScopeH mapped = new CFBamBuffScopeH();
					mapped.set(hrec);
					return(mapped); }
				case ICFBamSchemaDef.CLASS_CODE: {
					CFBamBuffSchemaDefH mapped = new CFBamBuffSchemaDefH();
					mapped.set((ICFBamSchemaDefH)hrec);
					return(mapped); }
				case ICFBamSchemaRef.CLASS_CODE: {
					CFBamBuffSchemaRefH mapped = new CFBamBuffSchemaRefH();
					mapped.set((ICFBamSchemaRefH)hrec);
					return(mapped); }
				case ICFBamServerMethod.CLASS_CODE: {
					CFBamBuffServerMethodH mapped = new CFBamBuffServerMethodH();
					mapped.set((ICFBamServerMethodH)hrec);
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
				case ICFBamTable.CLASS_CODE: {
					CFBamBuffTableH mapped = new CFBamBuffTableH();
					mapped.set((ICFBamTableH)hrec);
					return(mapped); }
				case ICFBamClearDep.CLASS_CODE: {
					CFBamBuffClearDepH mapped = new CFBamBuffClearDepH();
					mapped.set((ICFBamClearDepH)hrec);
					return(mapped); }
				case ICFBamClearSubDep1.CLASS_CODE: {
					CFBamBuffClearSubDep1H mapped = new CFBamBuffClearSubDep1H();
					mapped.set((ICFBamClearSubDep1H)hrec);
					return(mapped); }
				case ICFBamClearSubDep2.CLASS_CODE: {
					CFBamBuffClearSubDep2H mapped = new CFBamBuffClearSubDep2H();
					mapped.set((ICFBamClearSubDep2H)hrec);
					return(mapped); }
				case ICFBamClearSubDep3.CLASS_CODE: {
					CFBamBuffClearSubDep3H mapped = new CFBamBuffClearSubDep3H();
					mapped.set((ICFBamClearSubDep3H)hrec);
					return(mapped); }
				case ICFBamClearTopDep.CLASS_CODE: {
					CFBamBuffClearTopDepH mapped = new CFBamBuffClearTopDepH();
					mapped.set((ICFBamClearTopDepH)hrec);
					return(mapped); }
				case ICFBamDelDep.CLASS_CODE: {
					CFBamBuffDelDepH mapped = new CFBamBuffDelDepH();
					mapped.set((ICFBamDelDepH)hrec);
					return(mapped); }
				case ICFBamDelSubDep1.CLASS_CODE: {
					CFBamBuffDelSubDep1H mapped = new CFBamBuffDelSubDep1H();
					mapped.set((ICFBamDelSubDep1H)hrec);
					return(mapped); }
				case ICFBamDelSubDep2.CLASS_CODE: {
					CFBamBuffDelSubDep2H mapped = new CFBamBuffDelSubDep2H();
					mapped.set((ICFBamDelSubDep2H)hrec);
					return(mapped); }
				case ICFBamDelSubDep3.CLASS_CODE: {
					CFBamBuffDelSubDep3H mapped = new CFBamBuffDelSubDep3H();
					mapped.set((ICFBamDelSubDep3H)hrec);
					return(mapped); }
				case ICFBamDelTopDep.CLASS_CODE: {
					CFBamBuffDelTopDepH mapped = new CFBamBuffDelTopDepH();
					mapped.set((ICFBamDelTopDepH)hrec);
					return(mapped); }
				case ICFBamIndex.CLASS_CODE: {
					CFBamBuffIndexH mapped = new CFBamBuffIndexH();
					mapped.set((ICFBamIndexH)hrec);
					return(mapped); }
				case ICFBamPopDep.CLASS_CODE: {
					CFBamBuffPopDepH mapped = new CFBamBuffPopDepH();
					mapped.set((ICFBamPopDepH)hrec);
					return(mapped); }
				case ICFBamPopSubDep1.CLASS_CODE: {
					CFBamBuffPopSubDep1H mapped = new CFBamBuffPopSubDep1H();
					mapped.set((ICFBamPopSubDep1H)hrec);
					return(mapped); }
				case ICFBamPopSubDep2.CLASS_CODE: {
					CFBamBuffPopSubDep2H mapped = new CFBamBuffPopSubDep2H();
					mapped.set((ICFBamPopSubDep2H)hrec);
					return(mapped); }
				case ICFBamPopSubDep3.CLASS_CODE: {
					CFBamBuffPopSubDep3H mapped = new CFBamBuffPopSubDep3H();
					mapped.set((ICFBamPopSubDep3H)hrec);
					return(mapped); }
				case ICFBamPopTopDep.CLASS_CODE: {
					CFBamBuffPopTopDepH mapped = new CFBamBuffPopTopDepH();
					mapped.set((ICFBamPopTopDepH)hrec);
					return(mapped); }
				case ICFBamRelation.CLASS_CODE: {
					CFBamBuffRelationH mapped = new CFBamBuffRelationH();
					mapped.set((ICFBamRelationH)hrec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureHRec",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamScope",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamScope");
			}
		}
	}
}
