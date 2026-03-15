// Description: Java 25 Table Object implementation for Value.

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

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamValueTableObj
	implements ICFBamValueTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamValue.CLASS_CODE;
	protected static final int backingClassCode = ICFBamValue.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamValueObj> members;
	private Map<CFLibDbKeyHash256, ICFBamValueObj> allValue;
	private Map< ICFBamValueByUNameIdxKey,
		ICFBamValueObj > indexByUNameIdx;
	private Map< ICFBamValueByScopeIdxKey,
		Map<CFLibDbKeyHash256, ICFBamValueObj > > indexByScopeIdx;
	private Map< ICFBamValueByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamValueObj > > indexByDefSchemaIdx;
	private Map< ICFBamValueByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamValueObj > > indexByPrevIdx;
	private Map< ICFBamValueByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamValueObj > > indexByNextIdx;
	private Map< ICFBamValueByContPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamValueObj > > indexByContPrevIdx;
	private Map< ICFBamValueByContNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamValueObj > > indexByContNextIdx;
	public static String TABLE_NAME = "Value";
	public static String TABLE_DBNAME = "valdef";

	public CFBamValueTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamValueObj>();
		allValue = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
	}

	public CFBamValueTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamValueObj>();
		allValue = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamValueTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamValueTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allValue = null;
		indexByUNameIdx = null;
		indexByScopeIdx = null;
		indexByDefSchemaIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		indexByContPrevIdx = null;
		indexByContNextIdx = null;
		List<ICFBamValueObj> toForget = new LinkedList<ICFBamValueObj>();
		ICFBamValueObj cur = null;
		Iterator<ICFBamValueObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamValueObj.
	 */
	@Override
	public ICFBamValueObj newInstance() {
		ICFBamValueObj inst = new CFBamValueObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamValueObj.
	 */
	@Override
	public ICFBamValueEditObj newEditInstance( ICFBamValueObj orig ) {
		ICFBamValueEditObj edit = new CFBamValueEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamValueObj constructByClassCode( int backingClassCode ) {
		ICFBamValueObj obj = null;
		if( backingClassCode == ICFBamValue.CLASS_CODE) {
			obj = ((ICFBamSchemaObj)schema).getValueTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamAtom.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getAtomTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamBlobDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getBlobDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamBlobType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getBlobTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamBlobCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getBlobColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamBoolDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getBoolDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamBoolType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getBoolTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamBoolCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getBoolColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDateDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDateDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDateType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDateTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDateCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDateColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDoubleDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDoubleDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDoubleType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDoubleTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDoubleCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDoubleColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamFloatDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getFloatDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamFloatType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getFloatTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamFloatCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getFloatColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamInt16Def.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getInt16DefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamInt16Type.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getInt16TypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamId16Gen.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getId16GenTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamEnumDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getEnumDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamEnumType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getEnumTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamInt16Col.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getInt16ColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamInt32Def.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getInt32DefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamInt32Type.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getInt32TypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamId32Gen.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getId32GenTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamInt32Col.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getInt32ColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamInt64Def.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getInt64DefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamInt64Type.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getInt64TypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamId64Gen.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getId64GenTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamInt64Col.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getInt64ColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamNmTokenDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getNmTokenDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamNmTokenType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getNmTokenTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamNmTokenCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getNmTokenColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamNmTokensDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getNmTokensDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamNmTokensType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getNmTokensTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamNmTokensCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getNmTokensColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamNumberDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getNumberDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamNumberType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getNumberTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamNumberCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getNumberColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash128Def.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash128DefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash128Col.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash128ColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash128Type.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash128TypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash128Gen.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash128GenTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash160Def.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash160DefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash160Col.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash160ColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash160Type.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash160TypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash160Gen.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash160GenTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash224Def.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash224DefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash224Col.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash224ColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash224Type.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash224TypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash224Gen.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash224GenTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash256Def.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash256DefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash256Col.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash256ColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash256Type.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash256TypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash256Gen.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash256GenTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash384Def.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash384DefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash384Col.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash384ColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash384Type.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash384TypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash384Gen.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash384GenTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash512Def.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash512DefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash512Col.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash512ColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash512Type.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash512TypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamDbKeyHash512Gen.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getDbKeyHash512GenTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamStringDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getStringDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamStringType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getStringTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamStringCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getStringColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTZDateDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTZDateDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTZDateType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTZDateTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTZDateCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTZDateColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTZTimeDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTZTimeDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTZTimeType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTZTimeTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTZTimeCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTZTimeColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTZTimestampDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTZTimestampDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTZTimestampType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTZTimestampTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTZTimestampCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTZTimestampColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTextDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTextDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTextType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTextTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTextCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTextColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTimeDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTimeDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTimeType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTimeTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTimeCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTimeColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTimestampDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTimestampDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTimestampType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTimestampTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTimestampCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTimestampColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTokenDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTokenDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTokenType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTokenTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTokenCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTokenColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUInt16Def.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUInt16DefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUInt16Type.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUInt16TypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUInt16Col.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUInt16ColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUInt32Def.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUInt32DefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUInt32Type.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUInt32TypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUInt32Col.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUInt32ColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUInt64Def.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUInt64DefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUInt64Type.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUInt64TypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUInt64Col.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUInt64ColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUuidDef.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUuidDefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUuidType.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUuidTypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUuidGen.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUuidGenTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUuidCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUuidColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUuid6Def.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUuid6DefTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUuid6Type.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUuid6TypeTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUuid6Gen.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUuid6GenTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamUuid6Col.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getUuid6ColTableObj().newInstance();
		}
		else if( backingClassCode == ICFBamTableCol.CLASS_CODE ) {
			obj = ((ICFBamSchemaObj)schema).getTableColTableObj().newInstance();
		}
		return( obj );
	}

	@Override
	public ICFBamValueObj realiseValue( ICFBamValueObj Obj ) {
		ICFBamValueObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamValueObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamValueObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUNameIdx != null ) {
				ICFBamValueByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByScopeIdx != null ) {
				ICFBamValueByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.remove( keepObj.getPKey() );
					if( mapScopeIdx.size() <= 0 ) {
						indexByScopeIdx.remove( keyScopeIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.remove( keepObj.getPKey() );
					if( mapPrevIdx.size() <= 0 ) {
						indexByPrevIdx.remove( keyPrevIdx );
					}
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.remove( keepObj.getPKey() );
					if( mapNextIdx.size() <= 0 ) {
						indexByNextIdx.remove( keyNextIdx );
					}
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.remove( keepObj.getPKey() );
					if( mapContPrevIdx.size() <= 0 ) {
						indexByContPrevIdx.remove( keyContPrevIdx );
					}
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.remove( keepObj.getPKey() );
					if( mapContNextIdx.size() <= 0 ) {
						indexByContNextIdx.remove( keyContNextIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUNameIdx != null ) {
				ICFBamValueByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByScopeIdx != null ) {
				ICFBamValueByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allValue != null ) {
				allValue.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allValue != null ) {
				allValue.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUNameIdx != null ) {
				ICFBamValueByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
				keyUNameIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByScopeIdx != null ) {
				ICFBamValueByScopeIdxKey keyScopeIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
				keyScopeIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapScopeIdx = indexByScopeIdx.get( keyScopeIdx );
				if( mapScopeIdx != null ) {
					mapScopeIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPrevIdx != null ) {
				ICFBamValueByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamValueByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContPrevIdx != null ) {
				ICFBamValueByContPrevIdxKey keyContPrevIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
				keyContPrevIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapContPrevIdx = indexByContPrevIdx.get( keyContPrevIdx );
				if( mapContPrevIdx != null ) {
					mapContPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContNextIdx != null ) {
				ICFBamValueByContNextIdxKey keyContNextIdx =
					schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
				keyContNextIdx.setRequiredScopeId( keepObj.getRequiredScopeId() );
				keyContNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamValueObj > mapContNextIdx = indexByContNextIdx.get( keyContNextIdx );
				if( mapContNextIdx != null ) {
					mapContNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamValueObj createValue( ICFBamValueObj Obj ) {
		ICFBamValueObj obj = Obj;
		ICFBamValue rec = obj.getValueRec();
		schema.getCFBamBackingStore().getTableValue().createValue(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamValueObj)(obj.realise());
		}
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamValueObj readValue( CFLibDbKeyHash256 pkey ) {
		return( readValue( pkey, false ) );
	}

	@Override
	public ICFBamValueObj readValue( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamValueObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamValue readRec = schema.getCFBamBackingStore().getTableValue().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamValueObj)schema.getValueTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamValueObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamValueObj readCachedValue( CFLibDbKeyHash256 pkey ) {
		ICFBamValueObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeValue( ICFBamValueObj obj )
	{
		final String S_ProcName = "CFBamValueTableObj.reallyDeepDisposeValue() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamValueObj existing = readCachedValue( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamValueByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		keyUNameIdx.setRequiredScopeId( existing.getRequiredScopeId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamValueByScopeIdxKey keyScopeIdx = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		keyScopeIdx.setRequiredScopeId( existing.getRequiredScopeId() );

		ICFBamValueByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );

		ICFBamValueByPrevIdxKey keyPrevIdx = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		keyPrevIdx.setOptionalPrevId( existing.getOptionalPrevId() );

		ICFBamValueByNextIdxKey keyNextIdx = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		keyNextIdx.setOptionalNextId( existing.getOptionalNextId() );

		ICFBamValueByContPrevIdxKey keyContPrevIdx = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		keyContPrevIdx.setRequiredScopeId( existing.getRequiredScopeId() );
		keyContPrevIdx.setOptionalPrevId( existing.getOptionalPrevId() );

		ICFBamValueByContNextIdxKey keyContNextIdx = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		keyContNextIdx.setRequiredScopeId( existing.getRequiredScopeId() );
		keyContNextIdx.setOptionalNextId( existing.getOptionalNextId() );


		schema.getTableColTableObj().deepDisposeTableColByDataIdx( existing.getRequiredId() );
		schema.getIndexColTableObj().deepDisposeIndexColByColIdx( existing.getRequiredId() );

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexByScopeIdx != null ) {
			if( indexByScopeIdx.containsKey( keyScopeIdx ) ) {
				indexByScopeIdx.get( keyScopeIdx ).remove( pkey );
				if( indexByScopeIdx.get( keyScopeIdx ).size() <= 0 ) {
					indexByScopeIdx.remove( keyScopeIdx );
				}
			}
		}

		if( indexByDefSchemaIdx != null ) {
			if( indexByDefSchemaIdx.containsKey( keyDefSchemaIdx ) ) {
				indexByDefSchemaIdx.get( keyDefSchemaIdx ).remove( pkey );
				if( indexByDefSchemaIdx.get( keyDefSchemaIdx ).size() <= 0 ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}
		}

		if( indexByPrevIdx != null ) {
			if( indexByPrevIdx.containsKey( keyPrevIdx ) ) {
				indexByPrevIdx.get( keyPrevIdx ).remove( pkey );
				if( indexByPrevIdx.get( keyPrevIdx ).size() <= 0 ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}
		}

		if( indexByNextIdx != null ) {
			if( indexByNextIdx.containsKey( keyNextIdx ) ) {
				indexByNextIdx.get( keyNextIdx ).remove( pkey );
				if( indexByNextIdx.get( keyNextIdx ).size() <= 0 ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}
		}

		if( indexByContPrevIdx != null ) {
			if( indexByContPrevIdx.containsKey( keyContPrevIdx ) ) {
				indexByContPrevIdx.get( keyContPrevIdx ).remove( pkey );
				if( indexByContPrevIdx.get( keyContPrevIdx ).size() <= 0 ) {
					indexByContPrevIdx.remove( keyContPrevIdx );
				}
			}
		}

		if( indexByContNextIdx != null ) {
			if( indexByContNextIdx.containsKey( keyContNextIdx ) ) {
				indexByContNextIdx.get( keyContNextIdx ).remove( pkey );
				if( indexByContNextIdx.get( keyContNextIdx ).size() <= 0 ) {
					indexByContNextIdx.remove( keyContNextIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeValue( CFLibDbKeyHash256 pkey ) {
		ICFBamValueObj obj = readCachedValue( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamValueObj lockValue( CFLibDbKeyHash256 pkey ) {
		ICFBamValueObj locked = null;
		ICFBamValue lockRec = schema.getCFBamBackingStore().getTableValue().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamValueObj)schema.getValueTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamValueObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockValue", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamValueObj> readAllValue() {
		return( readAllValue( false ) );
	}

	@Override
	public List<ICFBamValueObj> readAllValue( boolean forceRead ) {
		final String S_ProcName = "readAllValue";
		if( ( allValue == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamValueObj> map = new HashMap<CFLibDbKeyHash256,ICFBamValueObj>();
			allValue = map;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readAllDerived( null );
			ICFBamValue rec;
			ICFBamValueObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamValueObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamValueObj realised = (ICFBamValueObj)obj.realise();
			}
		}
		int len = allValue.size();
		ICFBamValueObj arr[] = new ICFBamValueObj[len];
		Iterator<ICFBamValueObj> valIter = allValue.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			@Override
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamValueObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamValueObj> readCachedAllValue() {
		final String S_ProcName = "readCachedAllValue";
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>();
		if( allValue != null ) {
			int len = allValue.size();
			ICFBamValueObj arr[] = new ICFBamValueObj[len];
			Iterator<ICFBamValueObj> valIter = allValue.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamValueObj readValueByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readValueByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamValueObj readValueByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamValueObj obj = readValue( Id, forceRead );
		return( obj );
	}

	@Override
	public ICFBamValueObj readValueByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		return( readValueByUNameIdx( ScopeId,
			Name,
			false ) );
	}

	@Override
	public ICFBamValueObj readValueByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamValueObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamValueObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamValue rec = schema.getCFBamBackingStore().getTableValue().readDerivedByUNameIdx( null,
				ScopeId,
				Name );
			if( rec != null ) {
				obj = (ICFBamValueObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamValueObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamValueObj> readValueByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		return( readValueByScopeIdx( ScopeId,
			false ) );
	}

	@Override
	public List<ICFBamValueObj> readValueByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead )
	{
		final String S_ProcName = "readValueByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		Map<CFLibDbKeyHash256, ICFBamValueObj> dict;
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamValueObj > >();
		}
		if( ( ! forceRead ) && indexByScopeIdx.containsKey( key ) ) {
			dict = indexByScopeIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamValueObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByScopeIdx( null,
				ScopeId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamValueObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamValueObj realised = (ICFBamValueObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByScopeIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamValueObj arr[] = new ICFBamValueObj[len];
		Iterator<ICFBamValueObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			@Override
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamValueObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamValueObj> readValueByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readValueByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamValueObj> readValueByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readValueByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamValueObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamValueObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamValueObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamValueObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamValueObj realised = (ICFBamValueObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamValueObj arr[] = new ICFBamValueObj[len];
		Iterator<ICFBamValueObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			@Override
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamValueObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamValueObj> readValueByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readValueByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamValueObj> readValueByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readValueByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamValueObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamValueObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamValueObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamValueObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamValueObj realised = (ICFBamValueObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamValueObj arr[] = new ICFBamValueObj[len];
		Iterator<ICFBamValueObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			@Override
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamValueObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamValueObj> readValueByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readValueByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamValueObj> readValueByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readValueByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamValueObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamValueObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamValueObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByNextIdx( null,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamValueObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamValueObj realised = (ICFBamValueObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamValueObj arr[] = new ICFBamValueObj[len];
		Iterator<ICFBamValueObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			@Override
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamValueObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamValueObj> readValueByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		return( readValueByContPrevIdx( ScopeId,
			PrevId,
			false ) );
	}

	@Override
	public List<ICFBamValueObj> readValueByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readValueByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamValueObj> dict;
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamValueObj > >();
		}
		if( ( ! forceRead ) && indexByContPrevIdx.containsKey( key ) ) {
			dict = indexByContPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamValueObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContPrevIdx( null,
				ScopeId,
				PrevId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamValueObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamValueObj realised = (ICFBamValueObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamValueObj arr[] = new ICFBamValueObj[len];
		Iterator<ICFBamValueObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			@Override
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamValueObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamValueObj> readValueByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		return( readValueByContNextIdx( ScopeId,
			NextId,
			false ) );
	}

	@Override
	public List<ICFBamValueObj> readValueByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readValueByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamValueObj> dict;
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamValueObj > >();
		}
		if( ( ! forceRead ) && indexByContNextIdx.containsKey( key ) ) {
			dict = indexByContNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamValueObj>();
			ICFBamValueObj obj;
			ICFBamValue[] recList = schema.getCFBamBackingStore().getTableValue().readDerivedByContNextIdx( null,
				ScopeId,
				NextId );
			ICFBamValue rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamValueObj)schema.getValueTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamValueObj realised = (ICFBamValueObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamValueObj arr[] = new ICFBamValueObj[len];
		Iterator<ICFBamValueObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			@Override
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamValueObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamValueObj readCachedValueByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamValueObj obj = null;
		obj = readCachedValue( Id );
		return( obj );
	}

	@Override
	public ICFBamValueObj readCachedValueByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamValueObj obj = null;
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamValueObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamValueObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamValueObj> readCachedValueByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "readCachedValueByScopeIdx";
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>();
		if( indexByScopeIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamValueObj> dict;
			if( indexByScopeIdx.containsKey( key ) ) {
				dict = indexByScopeIdx.get( key );
				int len = dict.size();
				ICFBamValueObj arr[] = new ICFBamValueObj[len];
				Iterator<ICFBamValueObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamValueObj obj;
			Iterator<ICFBamValueObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			@Override
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamValueObj> readCachedValueByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedValueByDefSchemaIdx";
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamValueObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamValueObj arr[] = new ICFBamValueObj[len];
				Iterator<ICFBamValueObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamValueObj obj;
			Iterator<ICFBamValueObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			@Override
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamValueObj> readCachedValueByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedValueByPrevIdx";
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamValueObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamValueObj arr[] = new ICFBamValueObj[len];
				Iterator<ICFBamValueObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamValueObj obj;
			Iterator<ICFBamValueObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			@Override
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamValueObj> readCachedValueByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedValueByNextIdx";
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamValueObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamValueObj arr[] = new ICFBamValueObj[len];
				Iterator<ICFBamValueObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamValueObj obj;
			Iterator<ICFBamValueObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			@Override
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamValueObj> readCachedValueByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedValueByContPrevIdx";
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>();
		if( indexByContPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamValueObj> dict;
			if( indexByContPrevIdx.containsKey( key ) ) {
				dict = indexByContPrevIdx.get( key );
				int len = dict.size();
				ICFBamValueObj arr[] = new ICFBamValueObj[len];
				Iterator<ICFBamValueObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamValueObj obj;
			Iterator<ICFBamValueObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			@Override
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamValueObj> readCachedValueByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedValueByContNextIdx";
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamValueObj> arrayList = new ArrayList<ICFBamValueObj>();
		if( indexByContNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamValueObj> dict;
			if( indexByContNextIdx.containsKey( key ) ) {
				dict = indexByContNextIdx.get( key );
				int len = dict.size();
				ICFBamValueObj arr[] = new ICFBamValueObj[len];
				Iterator<ICFBamValueObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamValueObj obj;
			Iterator<ICFBamValueObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamValueObj> cmp = new Comparator<ICFBamValueObj>() {
			@Override
			public int compare( ICFBamValueObj lhs, ICFBamValueObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeValueByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamValueObj obj = readCachedValueByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeValueByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		ICFBamValueObj obj = readCachedValueByUNameIdx( ScopeId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeValueByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		final String S_ProcName = "deepDisposeValueByScopeIdx";
		ICFBamValueObj obj;
		List<ICFBamValueObj> arrayList = readCachedValueByScopeIdx( ScopeId );
		if( arrayList != null )  {
			Iterator<ICFBamValueObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeValueByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeValueByDefSchemaIdx";
		ICFBamValueObj obj;
		List<ICFBamValueObj> arrayList = readCachedValueByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamValueObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeValueByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeValueByPrevIdx";
		ICFBamValueObj obj;
		List<ICFBamValueObj> arrayList = readCachedValueByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamValueObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeValueByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeValueByNextIdx";
		ICFBamValueObj obj;
		List<ICFBamValueObj> arrayList = readCachedValueByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamValueObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeValueByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeValueByContPrevIdx";
		ICFBamValueObj obj;
		List<ICFBamValueObj> arrayList = readCachedValueByContPrevIdx( ScopeId,
				PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamValueObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeValueByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeValueByContNextIdx";
		ICFBamValueObj obj;
		List<ICFBamValueObj> arrayList = readCachedValueByContNextIdx( ScopeId,
				NextId );
		if( arrayList != null )  {
			Iterator<ICFBamValueObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamValueObj updateValue( ICFBamValueObj Obj ) {
		ICFBamValueObj obj = Obj;
		schema.getCFBamBackingStore().getTableValue().updateValue( null,
			Obj.getValueRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getValueTableObj().getClassCode() ) {
			obj = (ICFBamValueObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteValue( ICFBamValueObj Obj ) {
		ICFBamValueObj obj = Obj;
		ICFBamValueObj prev = obj.getOptionalLookupPrev();
		ICFBamValueObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableValue().deleteValue( null,
			obj.getValueRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteValueByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamValueObj obj = readValue(Id);
		if( obj != null ) {
			ICFBamValueEditObj editObj = (ICFBamValueEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamValueEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeValueByIdIdx( Id );
	}

	@Override
	public void deleteValueByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamValueByUNameIdxKey,
				ICFBamValueObj >();
		}
		ICFBamValueByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByUNameIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setRequiredName( Name );
		ICFBamValueObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableValue().deleteValueByUNameIdx( null,
				ScopeId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableValue().deleteValueByUNameIdx( null,
				ScopeId,
				Name );
		}
		deepDisposeValueByUNameIdx( ScopeId,
				Name );
	}

	@Override
	public void deleteValueByScopeIdx( CFLibDbKeyHash256 ScopeId )
	{
		ICFBamValueByScopeIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByScopeIdxKey();
		key.setRequiredScopeId( ScopeId );
		if( indexByScopeIdx == null ) {
			indexByScopeIdx = new HashMap< ICFBamValueByScopeIdxKey,
				Map< CFLibDbKeyHash256, ICFBamValueObj > >();
		}
		if( indexByScopeIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamValueObj> dict = indexByScopeIdx.get( key );
			schema.getCFBamBackingStore().getTableValue().deleteValueByScopeIdx( null,
				ScopeId );
			Iterator<ICFBamValueObj> iter = dict.values().iterator();
			ICFBamValueObj obj;
			List<ICFBamValueObj> toForget = new LinkedList<ICFBamValueObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByScopeIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableValue().deleteValueByScopeIdx( null,
				ScopeId );
		}
		deepDisposeValueByScopeIdx( ScopeId );
	}

	@Override
	public void deleteValueByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamValueByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamValueByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamValueObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamValueObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableValue().deleteValueByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamValueObj> iter = dict.values().iterator();
			ICFBamValueObj obj;
			List<ICFBamValueObj> toForget = new LinkedList<ICFBamValueObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableValue().deleteValueByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeValueByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteValueByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamValueByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamValueObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamValueObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableValue().deleteValueByPrevIdx( null,
				PrevId );
			Iterator<ICFBamValueObj> iter = dict.values().iterator();
			ICFBamValueObj obj;
			List<ICFBamValueObj> toForget = new LinkedList<ICFBamValueObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByPrevIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableValue().deleteValueByPrevIdx( null,
				PrevId );
		}
		deepDisposeValueByPrevIdx( PrevId );
	}

	@Override
	public void deleteValueByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamValueByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamValueObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamValueObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableValue().deleteValueByNextIdx( null,
				NextId );
			Iterator<ICFBamValueObj> iter = dict.values().iterator();
			ICFBamValueObj obj;
			List<ICFBamValueObj> toForget = new LinkedList<ICFBamValueObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNextIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableValue().deleteValueByNextIdx( null,
				NextId );
		}
		deepDisposeValueByNextIdx( NextId );
	}

	@Override
	public void deleteValueByContPrevIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 PrevId )
	{
		ICFBamValueByContPrevIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContPrevIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalPrevId( PrevId );
		if( indexByContPrevIdx == null ) {
			indexByContPrevIdx = new HashMap< ICFBamValueByContPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamValueObj > >();
		}
		if( indexByContPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamValueObj> dict = indexByContPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableValue().deleteValueByContPrevIdx( null,
				ScopeId,
				PrevId );
			Iterator<ICFBamValueObj> iter = dict.values().iterator();
			ICFBamValueObj obj;
			List<ICFBamValueObj> toForget = new LinkedList<ICFBamValueObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByContPrevIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableValue().deleteValueByContPrevIdx( null,
				ScopeId,
				PrevId );
		}
		deepDisposeValueByContPrevIdx( ScopeId,
				PrevId );
	}

	@Override
	public void deleteValueByContNextIdx( CFLibDbKeyHash256 ScopeId,
		CFLibDbKeyHash256 NextId )
	{
		ICFBamValueByContNextIdxKey key = schema.getCFBamBackingStore().getFactoryValue().newByContNextIdxKey();
		key.setRequiredScopeId( ScopeId );
		key.setOptionalNextId( NextId );
		if( indexByContNextIdx == null ) {
			indexByContNextIdx = new HashMap< ICFBamValueByContNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamValueObj > >();
		}
		if( indexByContNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamValueObj> dict = indexByContNextIdx.get( key );
			schema.getCFBamBackingStore().getTableValue().deleteValueByContNextIdx( null,
				ScopeId,
				NextId );
			Iterator<ICFBamValueObj> iter = dict.values().iterator();
			ICFBamValueObj obj;
			List<ICFBamValueObj> toForget = new LinkedList<ICFBamValueObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByContNextIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableValue().deleteValueByContNextIdx( null,
				ScopeId,
				NextId );
		}
		deepDisposeValueByContNextIdx( ScopeId,
				NextId );
	}

	/**
	 *	Move the CFBamValueObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamValueObj refreshed cache instance.
	 */
	@Override
	public ICFBamValueObj moveUpValue( ICFBamValueObj Obj ) {
		ICFBamValueObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpValue" );
		}
		ICFBamValue rec = schema.getCFBamBackingStore().getTableValue().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getValueTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamValueObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamValueObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamValueObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamValueObj refreshed cache instance.
	 */
	@Override
	public ICFBamValueObj moveDownValue( ICFBamValueObj Obj ) {
		ICFBamValueObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownValue" );
		}
		ICFBamValue rec = schema.getCFBamBackingStore().getTableValue().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getValueTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamValueObj)obj.realise();
			ICFBamValueObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamValueObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamValueObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}