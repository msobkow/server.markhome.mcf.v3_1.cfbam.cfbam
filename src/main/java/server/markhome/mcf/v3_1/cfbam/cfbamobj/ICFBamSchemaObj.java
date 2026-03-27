// Description: Java 25 Schema Object interface for CFBam.

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
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamSchemaObj
	extends ICFSecSchemaObj,
		ICFIntSchemaObj
{
	public ICFSecSchema getCFSecBackingStore();
	public void setCFSecBackingStore(ICFSecSchema cfsecBackingStore);

	public ICFIntSchema getCFIntBackingStore();
	public void setCFIntBackingStore(ICFIntSchema cfintBackingStore);

	public ICFBamSchema getCFBamBackingStore();
	public void setCFBamBackingStore(ICFBamSchema cfbamBackingStore);

	/**
	 *	Get the Atom interface for the schema.
	 *
	 *	@return	The ICFBamAtomTableObj interface implementation for the schema.
	 */
	ICFBamAtomTableObj getAtomTableObj();

	/**
	 *	Get the BlobCol interface for the schema.
	 *
	 *	@return	The ICFBamBlobColTableObj interface implementation for the schema.
	 */
	ICFBamBlobColTableObj getBlobColTableObj();

	/**
	 *	Get the BlobDef interface for the schema.
	 *
	 *	@return	The ICFBamBlobDefTableObj interface implementation for the schema.
	 */
	ICFBamBlobDefTableObj getBlobDefTableObj();

	/**
	 *	Get the BlobType interface for the schema.
	 *
	 *	@return	The ICFBamBlobTypeTableObj interface implementation for the schema.
	 */
	ICFBamBlobTypeTableObj getBlobTypeTableObj();

	/**
	 *	Get the BoolCol interface for the schema.
	 *
	 *	@return	The ICFBamBoolColTableObj interface implementation for the schema.
	 */
	ICFBamBoolColTableObj getBoolColTableObj();

	/**
	 *	Get the BoolDef interface for the schema.
	 *
	 *	@return	The ICFBamBoolDefTableObj interface implementation for the schema.
	 */
	ICFBamBoolDefTableObj getBoolDefTableObj();

	/**
	 *	Get the BoolType interface for the schema.
	 *
	 *	@return	The ICFBamBoolTypeTableObj interface implementation for the schema.
	 */
	ICFBamBoolTypeTableObj getBoolTypeTableObj();

	/**
	 *	Get the Chain interface for the schema.
	 *
	 *	@return	The ICFBamChainTableObj interface implementation for the schema.
	 */
	ICFBamChainTableObj getChainTableObj();

	/**
	 *	Get the ClearDep interface for the schema.
	 *
	 *	@return	The ICFBamClearDepTableObj interface implementation for the schema.
	 */
	ICFBamClearDepTableObj getClearDepTableObj();

	/**
	 *	Get the ClearSubDep1 interface for the schema.
	 *
	 *	@return	The ICFBamClearSubDep1TableObj interface implementation for the schema.
	 */
	ICFBamClearSubDep1TableObj getClearSubDep1TableObj();

	/**
	 *	Get the ClearSubDep2 interface for the schema.
	 *
	 *	@return	The ICFBamClearSubDep2TableObj interface implementation for the schema.
	 */
	ICFBamClearSubDep2TableObj getClearSubDep2TableObj();

	/**
	 *	Get the ClearSubDep3 interface for the schema.
	 *
	 *	@return	The ICFBamClearSubDep3TableObj interface implementation for the schema.
	 */
	ICFBamClearSubDep3TableObj getClearSubDep3TableObj();

	/**
	 *	Get the ClearTopDep interface for the schema.
	 *
	 *	@return	The ICFBamClearTopDepTableObj interface implementation for the schema.
	 */
	ICFBamClearTopDepTableObj getClearTopDepTableObj();

	/**
	 *	Get the DateCol interface for the schema.
	 *
	 *	@return	The ICFBamDateColTableObj interface implementation for the schema.
	 */
	ICFBamDateColTableObj getDateColTableObj();

	/**
	 *	Get the DateDef interface for the schema.
	 *
	 *	@return	The ICFBamDateDefTableObj interface implementation for the schema.
	 */
	ICFBamDateDefTableObj getDateDefTableObj();

	/**
	 *	Get the DateType interface for the schema.
	 *
	 *	@return	The ICFBamDateTypeTableObj interface implementation for the schema.
	 */
	ICFBamDateTypeTableObj getDateTypeTableObj();

	/**
	 *	Get the DbKeyHash128Col interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash128ColTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash128ColTableObj getDbKeyHash128ColTableObj();

	/**
	 *	Get the DbKeyHash128Def interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash128DefTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash128DefTableObj getDbKeyHash128DefTableObj();

	/**
	 *	Get the DbKeyHash128Gen interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash128GenTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash128GenTableObj getDbKeyHash128GenTableObj();

	/**
	 *	Get the DbKeyHash128Type interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash128TypeTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash128TypeTableObj getDbKeyHash128TypeTableObj();

	/**
	 *	Get the DbKeyHash160Col interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash160ColTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash160ColTableObj getDbKeyHash160ColTableObj();

	/**
	 *	Get the DbKeyHash160Def interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash160DefTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash160DefTableObj getDbKeyHash160DefTableObj();

	/**
	 *	Get the DbKeyHash160Gen interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash160GenTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash160GenTableObj getDbKeyHash160GenTableObj();

	/**
	 *	Get the DbKeyHash160Type interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash160TypeTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash160TypeTableObj getDbKeyHash160TypeTableObj();

	/**
	 *	Get the DbKeyHash224Col interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash224ColTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash224ColTableObj getDbKeyHash224ColTableObj();

	/**
	 *	Get the DbKeyHash224Def interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash224DefTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash224DefTableObj getDbKeyHash224DefTableObj();

	/**
	 *	Get the DbKeyHash224Gen interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash224GenTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash224GenTableObj getDbKeyHash224GenTableObj();

	/**
	 *	Get the DbKeyHash224Type interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash224TypeTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash224TypeTableObj getDbKeyHash224TypeTableObj();

	/**
	 *	Get the DbKeyHash256Col interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash256ColTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash256ColTableObj getDbKeyHash256ColTableObj();

	/**
	 *	Get the DbKeyHash256Def interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash256DefTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash256DefTableObj getDbKeyHash256DefTableObj();

	/**
	 *	Get the DbKeyHash256Gen interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash256GenTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash256GenTableObj getDbKeyHash256GenTableObj();

	/**
	 *	Get the DbKeyHash256Type interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash256TypeTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash256TypeTableObj getDbKeyHash256TypeTableObj();

	/**
	 *	Get the DbKeyHash384Col interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash384ColTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash384ColTableObj getDbKeyHash384ColTableObj();

	/**
	 *	Get the DbKeyHash384Def interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash384DefTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash384DefTableObj getDbKeyHash384DefTableObj();

	/**
	 *	Get the DbKeyHash384Gen interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash384GenTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash384GenTableObj getDbKeyHash384GenTableObj();

	/**
	 *	Get the DbKeyHash384Type interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash384TypeTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash384TypeTableObj getDbKeyHash384TypeTableObj();

	/**
	 *	Get the DbKeyHash512Col interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash512ColTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash512ColTableObj getDbKeyHash512ColTableObj();

	/**
	 *	Get the DbKeyHash512Def interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash512DefTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash512DefTableObj getDbKeyHash512DefTableObj();

	/**
	 *	Get the DbKeyHash512Gen interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash512GenTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash512GenTableObj getDbKeyHash512GenTableObj();

	/**
	 *	Get the DbKeyHash512Type interface for the schema.
	 *
	 *	@return	The ICFBamDbKeyHash512TypeTableObj interface implementation for the schema.
	 */
	ICFBamDbKeyHash512TypeTableObj getDbKeyHash512TypeTableObj();

	/**
	 *	Get the DelDep interface for the schema.
	 *
	 *	@return	The ICFBamDelDepTableObj interface implementation for the schema.
	 */
	ICFBamDelDepTableObj getDelDepTableObj();

	/**
	 *	Get the DelSubDep1 interface for the schema.
	 *
	 *	@return	The ICFBamDelSubDep1TableObj interface implementation for the schema.
	 */
	ICFBamDelSubDep1TableObj getDelSubDep1TableObj();

	/**
	 *	Get the DelSubDep2 interface for the schema.
	 *
	 *	@return	The ICFBamDelSubDep2TableObj interface implementation for the schema.
	 */
	ICFBamDelSubDep2TableObj getDelSubDep2TableObj();

	/**
	 *	Get the DelSubDep3 interface for the schema.
	 *
	 *	@return	The ICFBamDelSubDep3TableObj interface implementation for the schema.
	 */
	ICFBamDelSubDep3TableObj getDelSubDep3TableObj();

	/**
	 *	Get the DelTopDep interface for the schema.
	 *
	 *	@return	The ICFBamDelTopDepTableObj interface implementation for the schema.
	 */
	ICFBamDelTopDepTableObj getDelTopDepTableObj();

	/**
	 *	Get the DoubleCol interface for the schema.
	 *
	 *	@return	The ICFBamDoubleColTableObj interface implementation for the schema.
	 */
	ICFBamDoubleColTableObj getDoubleColTableObj();

	/**
	 *	Get the DoubleDef interface for the schema.
	 *
	 *	@return	The ICFBamDoubleDefTableObj interface implementation for the schema.
	 */
	ICFBamDoubleDefTableObj getDoubleDefTableObj();

	/**
	 *	Get the DoubleType interface for the schema.
	 *
	 *	@return	The ICFBamDoubleTypeTableObj interface implementation for the schema.
	 */
	ICFBamDoubleTypeTableObj getDoubleTypeTableObj();

	/**
	 *	Get the EnumDef interface for the schema.
	 *
	 *	@return	The ICFBamEnumDefTableObj interface implementation for the schema.
	 */
	ICFBamEnumDefTableObj getEnumDefTableObj();

	/**
	 *	Get the EnumTag interface for the schema.
	 *
	 *	@return	The ICFBamEnumTagTableObj interface implementation for the schema.
	 */
	ICFBamEnumTagTableObj getEnumTagTableObj();

	/**
	 *	Get the EnumType interface for the schema.
	 *
	 *	@return	The ICFBamEnumTypeTableObj interface implementation for the schema.
	 */
	ICFBamEnumTypeTableObj getEnumTypeTableObj();

	/**
	 *	Get the FloatCol interface for the schema.
	 *
	 *	@return	The ICFBamFloatColTableObj interface implementation for the schema.
	 */
	ICFBamFloatColTableObj getFloatColTableObj();

	/**
	 *	Get the FloatDef interface for the schema.
	 *
	 *	@return	The ICFBamFloatDefTableObj interface implementation for the schema.
	 */
	ICFBamFloatDefTableObj getFloatDefTableObj();

	/**
	 *	Get the FloatType interface for the schema.
	 *
	 *	@return	The ICFBamFloatTypeTableObj interface implementation for the schema.
	 */
	ICFBamFloatTypeTableObj getFloatTypeTableObj();

	/**
	 *	Get the Id16Gen interface for the schema.
	 *
	 *	@return	The ICFBamId16GenTableObj interface implementation for the schema.
	 */
	ICFBamId16GenTableObj getId16GenTableObj();

	/**
	 *	Get the Id32Gen interface for the schema.
	 *
	 *	@return	The ICFBamId32GenTableObj interface implementation for the schema.
	 */
	ICFBamId32GenTableObj getId32GenTableObj();

	/**
	 *	Get the Id64Gen interface for the schema.
	 *
	 *	@return	The ICFBamId64GenTableObj interface implementation for the schema.
	 */
	ICFBamId64GenTableObj getId64GenTableObj();

	/**
	 *	Get the Index interface for the schema.
	 *
	 *	@return	The ICFBamIndexTableObj interface implementation for the schema.
	 */
	ICFBamIndexTableObj getIndexTableObj();

	/**
	 *	Get the IndexCol interface for the schema.
	 *
	 *	@return	The ICFBamIndexColTableObj interface implementation for the schema.
	 */
	ICFBamIndexColTableObj getIndexColTableObj();

	/**
	 *	Get the Int16Col interface for the schema.
	 *
	 *	@return	The ICFBamInt16ColTableObj interface implementation for the schema.
	 */
	ICFBamInt16ColTableObj getInt16ColTableObj();

	/**
	 *	Get the Int16Def interface for the schema.
	 *
	 *	@return	The ICFBamInt16DefTableObj interface implementation for the schema.
	 */
	ICFBamInt16DefTableObj getInt16DefTableObj();

	/**
	 *	Get the Int16Type interface for the schema.
	 *
	 *	@return	The ICFBamInt16TypeTableObj interface implementation for the schema.
	 */
	ICFBamInt16TypeTableObj getInt16TypeTableObj();

	/**
	 *	Get the Int32Col interface for the schema.
	 *
	 *	@return	The ICFBamInt32ColTableObj interface implementation for the schema.
	 */
	ICFBamInt32ColTableObj getInt32ColTableObj();

	/**
	 *	Get the Int32Def interface for the schema.
	 *
	 *	@return	The ICFBamInt32DefTableObj interface implementation for the schema.
	 */
	ICFBamInt32DefTableObj getInt32DefTableObj();

	/**
	 *	Get the Int32Type interface for the schema.
	 *
	 *	@return	The ICFBamInt32TypeTableObj interface implementation for the schema.
	 */
	ICFBamInt32TypeTableObj getInt32TypeTableObj();

	/**
	 *	Get the Int64Col interface for the schema.
	 *
	 *	@return	The ICFBamInt64ColTableObj interface implementation for the schema.
	 */
	ICFBamInt64ColTableObj getInt64ColTableObj();

	/**
	 *	Get the Int64Def interface for the schema.
	 *
	 *	@return	The ICFBamInt64DefTableObj interface implementation for the schema.
	 */
	ICFBamInt64DefTableObj getInt64DefTableObj();

	/**
	 *	Get the Int64Type interface for the schema.
	 *
	 *	@return	The ICFBamInt64TypeTableObj interface implementation for the schema.
	 */
	ICFBamInt64TypeTableObj getInt64TypeTableObj();

	/**
	 *	Get the NmTokenCol interface for the schema.
	 *
	 *	@return	The ICFBamNmTokenColTableObj interface implementation for the schema.
	 */
	ICFBamNmTokenColTableObj getNmTokenColTableObj();

	/**
	 *	Get the NmTokenDef interface for the schema.
	 *
	 *	@return	The ICFBamNmTokenDefTableObj interface implementation for the schema.
	 */
	ICFBamNmTokenDefTableObj getNmTokenDefTableObj();

	/**
	 *	Get the NmTokenType interface for the schema.
	 *
	 *	@return	The ICFBamNmTokenTypeTableObj interface implementation for the schema.
	 */
	ICFBamNmTokenTypeTableObj getNmTokenTypeTableObj();

	/**
	 *	Get the NmTokensCol interface for the schema.
	 *
	 *	@return	The ICFBamNmTokensColTableObj interface implementation for the schema.
	 */
	ICFBamNmTokensColTableObj getNmTokensColTableObj();

	/**
	 *	Get the NmTokensDef interface for the schema.
	 *
	 *	@return	The ICFBamNmTokensDefTableObj interface implementation for the schema.
	 */
	ICFBamNmTokensDefTableObj getNmTokensDefTableObj();

	/**
	 *	Get the NmTokensType interface for the schema.
	 *
	 *	@return	The ICFBamNmTokensTypeTableObj interface implementation for the schema.
	 */
	ICFBamNmTokensTypeTableObj getNmTokensTypeTableObj();

	/**
	 *	Get the NumberCol interface for the schema.
	 *
	 *	@return	The ICFBamNumberColTableObj interface implementation for the schema.
	 */
	ICFBamNumberColTableObj getNumberColTableObj();

	/**
	 *	Get the NumberDef interface for the schema.
	 *
	 *	@return	The ICFBamNumberDefTableObj interface implementation for the schema.
	 */
	ICFBamNumberDefTableObj getNumberDefTableObj();

	/**
	 *	Get the NumberType interface for the schema.
	 *
	 *	@return	The ICFBamNumberTypeTableObj interface implementation for the schema.
	 */
	ICFBamNumberTypeTableObj getNumberTypeTableObj();

	/**
	 *	Get the Param interface for the schema.
	 *
	 *	@return	The ICFBamParamTableObj interface implementation for the schema.
	 */
	ICFBamParamTableObj getParamTableObj();

	/**
	 *	Get the PopDep interface for the schema.
	 *
	 *	@return	The ICFBamPopDepTableObj interface implementation for the schema.
	 */
	ICFBamPopDepTableObj getPopDepTableObj();

	/**
	 *	Get the PopSubDep1 interface for the schema.
	 *
	 *	@return	The ICFBamPopSubDep1TableObj interface implementation for the schema.
	 */
	ICFBamPopSubDep1TableObj getPopSubDep1TableObj();

	/**
	 *	Get the PopSubDep2 interface for the schema.
	 *
	 *	@return	The ICFBamPopSubDep2TableObj interface implementation for the schema.
	 */
	ICFBamPopSubDep2TableObj getPopSubDep2TableObj();

	/**
	 *	Get the PopSubDep3 interface for the schema.
	 *
	 *	@return	The ICFBamPopSubDep3TableObj interface implementation for the schema.
	 */
	ICFBamPopSubDep3TableObj getPopSubDep3TableObj();

	/**
	 *	Get the PopTopDep interface for the schema.
	 *
	 *	@return	The ICFBamPopTopDepTableObj interface implementation for the schema.
	 */
	ICFBamPopTopDepTableObj getPopTopDepTableObj();

	/**
	 *	Get the Relation interface for the schema.
	 *
	 *	@return	The ICFBamRelationTableObj interface implementation for the schema.
	 */
	ICFBamRelationTableObj getRelationTableObj();

	/**
	 *	Get the RelationCol interface for the schema.
	 *
	 *	@return	The ICFBamRelationColTableObj interface implementation for the schema.
	 */
	ICFBamRelationColTableObj getRelationColTableObj();

	/**
	 *	Get the SchemaDef interface for the schema.
	 *
	 *	@return	The ICFBamSchemaDefTableObj interface implementation for the schema.
	 */
	ICFBamSchemaDefTableObj getSchemaDefTableObj();

	/**
	 *	Get the SchemaRef interface for the schema.
	 *
	 *	@return	The ICFBamSchemaRefTableObj interface implementation for the schema.
	 */
	ICFBamSchemaRefTableObj getSchemaRefTableObj();

	/**
	 *	Get the SchemaTweak interface for the schema.
	 *
	 *	@return	The ICFBamSchemaTweakTableObj interface implementation for the schema.
	 */
	ICFBamSchemaTweakTableObj getSchemaTweakTableObj();

	/**
	 *	Get the Scope interface for the schema.
	 *
	 *	@return	The ICFBamScopeTableObj interface implementation for the schema.
	 */
	ICFBamScopeTableObj getScopeTableObj();

	/**
	 *	Get the ServerListFunc interface for the schema.
	 *
	 *	@return	The ICFBamServerListFuncTableObj interface implementation for the schema.
	 */
	ICFBamServerListFuncTableObj getServerListFuncTableObj();

	/**
	 *	Get the ServerMethod interface for the schema.
	 *
	 *	@return	The ICFBamServerMethodTableObj interface implementation for the schema.
	 */
	ICFBamServerMethodTableObj getServerMethodTableObj();

	/**
	 *	Get the ServerObjFunc interface for the schema.
	 *
	 *	@return	The ICFBamServerObjFuncTableObj interface implementation for the schema.
	 */
	ICFBamServerObjFuncTableObj getServerObjFuncTableObj();

	/**
	 *	Get the ServerProc interface for the schema.
	 *
	 *	@return	The ICFBamServerProcTableObj interface implementation for the schema.
	 */
	ICFBamServerProcTableObj getServerProcTableObj();

	/**
	 *	Get the StringCol interface for the schema.
	 *
	 *	@return	The ICFBamStringColTableObj interface implementation for the schema.
	 */
	ICFBamStringColTableObj getStringColTableObj();

	/**
	 *	Get the StringDef interface for the schema.
	 *
	 *	@return	The ICFBamStringDefTableObj interface implementation for the schema.
	 */
	ICFBamStringDefTableObj getStringDefTableObj();

	/**
	 *	Get the StringType interface for the schema.
	 *
	 *	@return	The ICFBamStringTypeTableObj interface implementation for the schema.
	 */
	ICFBamStringTypeTableObj getStringTypeTableObj();

	/**
	 *	Get the TZDateCol interface for the schema.
	 *
	 *	@return	The ICFBamTZDateColTableObj interface implementation for the schema.
	 */
	ICFBamTZDateColTableObj getTZDateColTableObj();

	/**
	 *	Get the TZDateDef interface for the schema.
	 *
	 *	@return	The ICFBamTZDateDefTableObj interface implementation for the schema.
	 */
	ICFBamTZDateDefTableObj getTZDateDefTableObj();

	/**
	 *	Get the TZDateType interface for the schema.
	 *
	 *	@return	The ICFBamTZDateTypeTableObj interface implementation for the schema.
	 */
	ICFBamTZDateTypeTableObj getTZDateTypeTableObj();

	/**
	 *	Get the TZTimeCol interface for the schema.
	 *
	 *	@return	The ICFBamTZTimeColTableObj interface implementation for the schema.
	 */
	ICFBamTZTimeColTableObj getTZTimeColTableObj();

	/**
	 *	Get the TZTimeDef interface for the schema.
	 *
	 *	@return	The ICFBamTZTimeDefTableObj interface implementation for the schema.
	 */
	ICFBamTZTimeDefTableObj getTZTimeDefTableObj();

	/**
	 *	Get the TZTimeType interface for the schema.
	 *
	 *	@return	The ICFBamTZTimeTypeTableObj interface implementation for the schema.
	 */
	ICFBamTZTimeTypeTableObj getTZTimeTypeTableObj();

	/**
	 *	Get the TZTimestampCol interface for the schema.
	 *
	 *	@return	The ICFBamTZTimestampColTableObj interface implementation for the schema.
	 */
	ICFBamTZTimestampColTableObj getTZTimestampColTableObj();

	/**
	 *	Get the TZTimestampDef interface for the schema.
	 *
	 *	@return	The ICFBamTZTimestampDefTableObj interface implementation for the schema.
	 */
	ICFBamTZTimestampDefTableObj getTZTimestampDefTableObj();

	/**
	 *	Get the TZTimestampType interface for the schema.
	 *
	 *	@return	The ICFBamTZTimestampTypeTableObj interface implementation for the schema.
	 */
	ICFBamTZTimestampTypeTableObj getTZTimestampTypeTableObj();

	/**
	 *	Get the Table interface for the schema.
	 *
	 *	@return	The ICFBamTableTableObj interface implementation for the schema.
	 */
	ICFBamTableTableObj getTableTableObj();

	/**
	 *	Get the TableCol interface for the schema.
	 *
	 *	@return	The ICFBamTableColTableObj interface implementation for the schema.
	 */
	ICFBamTableColTableObj getTableColTableObj();

	/**
	 *	Get the TableTweak interface for the schema.
	 *
	 *	@return	The ICFBamTableTweakTableObj interface implementation for the schema.
	 */
	ICFBamTableTweakTableObj getTableTweakTableObj();

	/**
	 *	Get the TextCol interface for the schema.
	 *
	 *	@return	The ICFBamTextColTableObj interface implementation for the schema.
	 */
	ICFBamTextColTableObj getTextColTableObj();

	/**
	 *	Get the TextDef interface for the schema.
	 *
	 *	@return	The ICFBamTextDefTableObj interface implementation for the schema.
	 */
	ICFBamTextDefTableObj getTextDefTableObj();

	/**
	 *	Get the TextType interface for the schema.
	 *
	 *	@return	The ICFBamTextTypeTableObj interface implementation for the schema.
	 */
	ICFBamTextTypeTableObj getTextTypeTableObj();

	/**
	 *	Get the TimeCol interface for the schema.
	 *
	 *	@return	The ICFBamTimeColTableObj interface implementation for the schema.
	 */
	ICFBamTimeColTableObj getTimeColTableObj();

	/**
	 *	Get the TimeDef interface for the schema.
	 *
	 *	@return	The ICFBamTimeDefTableObj interface implementation for the schema.
	 */
	ICFBamTimeDefTableObj getTimeDefTableObj();

	/**
	 *	Get the TimeType interface for the schema.
	 *
	 *	@return	The ICFBamTimeTypeTableObj interface implementation for the schema.
	 */
	ICFBamTimeTypeTableObj getTimeTypeTableObj();

	/**
	 *	Get the TimestampCol interface for the schema.
	 *
	 *	@return	The ICFBamTimestampColTableObj interface implementation for the schema.
	 */
	ICFBamTimestampColTableObj getTimestampColTableObj();

	/**
	 *	Get the TimestampDef interface for the schema.
	 *
	 *	@return	The ICFBamTimestampDefTableObj interface implementation for the schema.
	 */
	ICFBamTimestampDefTableObj getTimestampDefTableObj();

	/**
	 *	Get the TimestampType interface for the schema.
	 *
	 *	@return	The ICFBamTimestampTypeTableObj interface implementation for the schema.
	 */
	ICFBamTimestampTypeTableObj getTimestampTypeTableObj();

	/**
	 *	Get the TokenCol interface for the schema.
	 *
	 *	@return	The ICFBamTokenColTableObj interface implementation for the schema.
	 */
	ICFBamTokenColTableObj getTokenColTableObj();

	/**
	 *	Get the TokenDef interface for the schema.
	 *
	 *	@return	The ICFBamTokenDefTableObj interface implementation for the schema.
	 */
	ICFBamTokenDefTableObj getTokenDefTableObj();

	/**
	 *	Get the TokenType interface for the schema.
	 *
	 *	@return	The ICFBamTokenTypeTableObj interface implementation for the schema.
	 */
	ICFBamTokenTypeTableObj getTokenTypeTableObj();

	/**
	 *	Get the Tweak interface for the schema.
	 *
	 *	@return	The ICFBamTweakTableObj interface implementation for the schema.
	 */
	ICFBamTweakTableObj getTweakTableObj();

	/**
	 *	Get the UInt16Col interface for the schema.
	 *
	 *	@return	The ICFBamUInt16ColTableObj interface implementation for the schema.
	 */
	ICFBamUInt16ColTableObj getUInt16ColTableObj();

	/**
	 *	Get the UInt16Def interface for the schema.
	 *
	 *	@return	The ICFBamUInt16DefTableObj interface implementation for the schema.
	 */
	ICFBamUInt16DefTableObj getUInt16DefTableObj();

	/**
	 *	Get the UInt16Type interface for the schema.
	 *
	 *	@return	The ICFBamUInt16TypeTableObj interface implementation for the schema.
	 */
	ICFBamUInt16TypeTableObj getUInt16TypeTableObj();

	/**
	 *	Get the UInt32Col interface for the schema.
	 *
	 *	@return	The ICFBamUInt32ColTableObj interface implementation for the schema.
	 */
	ICFBamUInt32ColTableObj getUInt32ColTableObj();

	/**
	 *	Get the UInt32Def interface for the schema.
	 *
	 *	@return	The ICFBamUInt32DefTableObj interface implementation for the schema.
	 */
	ICFBamUInt32DefTableObj getUInt32DefTableObj();

	/**
	 *	Get the UInt32Type interface for the schema.
	 *
	 *	@return	The ICFBamUInt32TypeTableObj interface implementation for the schema.
	 */
	ICFBamUInt32TypeTableObj getUInt32TypeTableObj();

	/**
	 *	Get the UInt64Col interface for the schema.
	 *
	 *	@return	The ICFBamUInt64ColTableObj interface implementation for the schema.
	 */
	ICFBamUInt64ColTableObj getUInt64ColTableObj();

	/**
	 *	Get the UInt64Def interface for the schema.
	 *
	 *	@return	The ICFBamUInt64DefTableObj interface implementation for the schema.
	 */
	ICFBamUInt64DefTableObj getUInt64DefTableObj();

	/**
	 *	Get the UInt64Type interface for the schema.
	 *
	 *	@return	The ICFBamUInt64TypeTableObj interface implementation for the schema.
	 */
	ICFBamUInt64TypeTableObj getUInt64TypeTableObj();

	/**
	 *	Get the Uuid6Col interface for the schema.
	 *
	 *	@return	The ICFBamUuid6ColTableObj interface implementation for the schema.
	 */
	ICFBamUuid6ColTableObj getUuid6ColTableObj();

	/**
	 *	Get the Uuid6Def interface for the schema.
	 *
	 *	@return	The ICFBamUuid6DefTableObj interface implementation for the schema.
	 */
	ICFBamUuid6DefTableObj getUuid6DefTableObj();

	/**
	 *	Get the Uuid6Gen interface for the schema.
	 *
	 *	@return	The ICFBamUuid6GenTableObj interface implementation for the schema.
	 */
	ICFBamUuid6GenTableObj getUuid6GenTableObj();

	/**
	 *	Get the Uuid6Type interface for the schema.
	 *
	 *	@return	The ICFBamUuid6TypeTableObj interface implementation for the schema.
	 */
	ICFBamUuid6TypeTableObj getUuid6TypeTableObj();

	/**
	 *	Get the UuidCol interface for the schema.
	 *
	 *	@return	The ICFBamUuidColTableObj interface implementation for the schema.
	 */
	ICFBamUuidColTableObj getUuidColTableObj();

	/**
	 *	Get the UuidDef interface for the schema.
	 *
	 *	@return	The ICFBamUuidDefTableObj interface implementation for the schema.
	 */
	ICFBamUuidDefTableObj getUuidDefTableObj();

	/**
	 *	Get the UuidGen interface for the schema.
	 *
	 *	@return	The ICFBamUuidGenTableObj interface implementation for the schema.
	 */
	ICFBamUuidGenTableObj getUuidGenTableObj();

	/**
	 *	Get the UuidType interface for the schema.
	 *
	 *	@return	The ICFBamUuidTypeTableObj interface implementation for the schema.
	 */
	ICFBamUuidTypeTableObj getUuidTypeTableObj();

	/**
	 *	Get the Value interface for the schema.
	 *
	 *	@return	The ICFBamValueTableObj interface implementation for the schema.
	 */
	ICFBamValueTableObj getValueTableObj();
}
