// Description: Java 25 interface for a CFBam data object factory.

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

package server.markhome.mcf.v3_1.cfbam.cfbam;

public interface ICFBamFactory
{
	/**
	 *	Get the Atom Factory interface for the schema.
	 *
	 *	@return	The Atom Factory interface for the schema.
	 */
	public ICFBamAtomFactory getFactoryAtom();

	/**
	 *	Get the BlobCol Factory interface for the schema.
	 *
	 *	@return	The BlobCol Factory interface for the schema.
	 */
	public ICFBamBlobColFactory getFactoryBlobCol();

	/**
	 *	Get the BlobDef Factory interface for the schema.
	 *
	 *	@return	The BlobDef Factory interface for the schema.
	 */
	public ICFBamBlobDefFactory getFactoryBlobDef();

	/**
	 *	Get the BlobType Factory interface for the schema.
	 *
	 *	@return	The BlobType Factory interface for the schema.
	 */
	public ICFBamBlobTypeFactory getFactoryBlobType();

	/**
	 *	Get the BoolCol Factory interface for the schema.
	 *
	 *	@return	The BoolCol Factory interface for the schema.
	 */
	public ICFBamBoolColFactory getFactoryBoolCol();

	/**
	 *	Get the BoolDef Factory interface for the schema.
	 *
	 *	@return	The BoolDef Factory interface for the schema.
	 */
	public ICFBamBoolDefFactory getFactoryBoolDef();

	/**
	 *	Get the BoolType Factory interface for the schema.
	 *
	 *	@return	The BoolType Factory interface for the schema.
	 */
	public ICFBamBoolTypeFactory getFactoryBoolType();

	/**
	 *	Get the Chain Factory interface for the schema.
	 *
	 *	@return	The Chain Factory interface for the schema.
	 */
	public ICFBamChainFactory getFactoryChain();

	/**
	 *	Get the ClearDep Factory interface for the schema.
	 *
	 *	@return	The ClearDep Factory interface for the schema.
	 */
	public ICFBamClearDepFactory getFactoryClearDep();

	/**
	 *	Get the ClearSubDep1 Factory interface for the schema.
	 *
	 *	@return	The ClearSubDep1 Factory interface for the schema.
	 */
	public ICFBamClearSubDep1Factory getFactoryClearSubDep1();

	/**
	 *	Get the ClearSubDep2 Factory interface for the schema.
	 *
	 *	@return	The ClearSubDep2 Factory interface for the schema.
	 */
	public ICFBamClearSubDep2Factory getFactoryClearSubDep2();

	/**
	 *	Get the ClearSubDep3 Factory interface for the schema.
	 *
	 *	@return	The ClearSubDep3 Factory interface for the schema.
	 */
	public ICFBamClearSubDep3Factory getFactoryClearSubDep3();

	/**
	 *	Get the ClearTopDep Factory interface for the schema.
	 *
	 *	@return	The ClearTopDep Factory interface for the schema.
	 */
	public ICFBamClearTopDepFactory getFactoryClearTopDep();

	/**
	 *	Get the DateCol Factory interface for the schema.
	 *
	 *	@return	The DateCol Factory interface for the schema.
	 */
	public ICFBamDateColFactory getFactoryDateCol();

	/**
	 *	Get the DateDef Factory interface for the schema.
	 *
	 *	@return	The DateDef Factory interface for the schema.
	 */
	public ICFBamDateDefFactory getFactoryDateDef();

	/**
	 *	Get the DateType Factory interface for the schema.
	 *
	 *	@return	The DateType Factory interface for the schema.
	 */
	public ICFBamDateTypeFactory getFactoryDateType();

	/**
	 *	Get the DbKeyHash128Col Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash128Col Factory interface for the schema.
	 */
	public ICFBamDbKeyHash128ColFactory getFactoryDbKeyHash128Col();

	/**
	 *	Get the DbKeyHash128Def Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash128Def Factory interface for the schema.
	 */
	public ICFBamDbKeyHash128DefFactory getFactoryDbKeyHash128Def();

	/**
	 *	Get the DbKeyHash128Gen Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash128Gen Factory interface for the schema.
	 */
	public ICFBamDbKeyHash128GenFactory getFactoryDbKeyHash128Gen();

	/**
	 *	Get the DbKeyHash128Type Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash128Type Factory interface for the schema.
	 */
	public ICFBamDbKeyHash128TypeFactory getFactoryDbKeyHash128Type();

	/**
	 *	Get the DbKeyHash160Col Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash160Col Factory interface for the schema.
	 */
	public ICFBamDbKeyHash160ColFactory getFactoryDbKeyHash160Col();

	/**
	 *	Get the DbKeyHash160Def Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash160Def Factory interface for the schema.
	 */
	public ICFBamDbKeyHash160DefFactory getFactoryDbKeyHash160Def();

	/**
	 *	Get the DbKeyHash160Gen Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash160Gen Factory interface for the schema.
	 */
	public ICFBamDbKeyHash160GenFactory getFactoryDbKeyHash160Gen();

	/**
	 *	Get the DbKeyHash160Type Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash160Type Factory interface for the schema.
	 */
	public ICFBamDbKeyHash160TypeFactory getFactoryDbKeyHash160Type();

	/**
	 *	Get the DbKeyHash224Col Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash224Col Factory interface for the schema.
	 */
	public ICFBamDbKeyHash224ColFactory getFactoryDbKeyHash224Col();

	/**
	 *	Get the DbKeyHash224Def Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash224Def Factory interface for the schema.
	 */
	public ICFBamDbKeyHash224DefFactory getFactoryDbKeyHash224Def();

	/**
	 *	Get the DbKeyHash224Gen Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash224Gen Factory interface for the schema.
	 */
	public ICFBamDbKeyHash224GenFactory getFactoryDbKeyHash224Gen();

	/**
	 *	Get the DbKeyHash224Type Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash224Type Factory interface for the schema.
	 */
	public ICFBamDbKeyHash224TypeFactory getFactoryDbKeyHash224Type();

	/**
	 *	Get the DbKeyHash256Col Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash256Col Factory interface for the schema.
	 */
	public ICFBamDbKeyHash256ColFactory getFactoryDbKeyHash256Col();

	/**
	 *	Get the DbKeyHash256Def Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash256Def Factory interface for the schema.
	 */
	public ICFBamDbKeyHash256DefFactory getFactoryDbKeyHash256Def();

	/**
	 *	Get the DbKeyHash256Gen Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash256Gen Factory interface for the schema.
	 */
	public ICFBamDbKeyHash256GenFactory getFactoryDbKeyHash256Gen();

	/**
	 *	Get the DbKeyHash256Type Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash256Type Factory interface for the schema.
	 */
	public ICFBamDbKeyHash256TypeFactory getFactoryDbKeyHash256Type();

	/**
	 *	Get the DbKeyHash384Col Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash384Col Factory interface for the schema.
	 */
	public ICFBamDbKeyHash384ColFactory getFactoryDbKeyHash384Col();

	/**
	 *	Get the DbKeyHash384Def Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash384Def Factory interface for the schema.
	 */
	public ICFBamDbKeyHash384DefFactory getFactoryDbKeyHash384Def();

	/**
	 *	Get the DbKeyHash384Gen Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash384Gen Factory interface for the schema.
	 */
	public ICFBamDbKeyHash384GenFactory getFactoryDbKeyHash384Gen();

	/**
	 *	Get the DbKeyHash384Type Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash384Type Factory interface for the schema.
	 */
	public ICFBamDbKeyHash384TypeFactory getFactoryDbKeyHash384Type();

	/**
	 *	Get the DbKeyHash512Col Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash512Col Factory interface for the schema.
	 */
	public ICFBamDbKeyHash512ColFactory getFactoryDbKeyHash512Col();

	/**
	 *	Get the DbKeyHash512Def Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash512Def Factory interface for the schema.
	 */
	public ICFBamDbKeyHash512DefFactory getFactoryDbKeyHash512Def();

	/**
	 *	Get the DbKeyHash512Gen Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash512Gen Factory interface for the schema.
	 */
	public ICFBamDbKeyHash512GenFactory getFactoryDbKeyHash512Gen();

	/**
	 *	Get the DbKeyHash512Type Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash512Type Factory interface for the schema.
	 */
	public ICFBamDbKeyHash512TypeFactory getFactoryDbKeyHash512Type();

	/**
	 *	Get the DelDep Factory interface for the schema.
	 *
	 *	@return	The DelDep Factory interface for the schema.
	 */
	public ICFBamDelDepFactory getFactoryDelDep();

	/**
	 *	Get the DelSubDep1 Factory interface for the schema.
	 *
	 *	@return	The DelSubDep1 Factory interface for the schema.
	 */
	public ICFBamDelSubDep1Factory getFactoryDelSubDep1();

	/**
	 *	Get the DelSubDep2 Factory interface for the schema.
	 *
	 *	@return	The DelSubDep2 Factory interface for the schema.
	 */
	public ICFBamDelSubDep2Factory getFactoryDelSubDep2();

	/**
	 *	Get the DelSubDep3 Factory interface for the schema.
	 *
	 *	@return	The DelSubDep3 Factory interface for the schema.
	 */
	public ICFBamDelSubDep3Factory getFactoryDelSubDep3();

	/**
	 *	Get the DelTopDep Factory interface for the schema.
	 *
	 *	@return	The DelTopDep Factory interface for the schema.
	 */
	public ICFBamDelTopDepFactory getFactoryDelTopDep();

	/**
	 *	Get the DoubleCol Factory interface for the schema.
	 *
	 *	@return	The DoubleCol Factory interface for the schema.
	 */
	public ICFBamDoubleColFactory getFactoryDoubleCol();

	/**
	 *	Get the DoubleDef Factory interface for the schema.
	 *
	 *	@return	The DoubleDef Factory interface for the schema.
	 */
	public ICFBamDoubleDefFactory getFactoryDoubleDef();

	/**
	 *	Get the DoubleType Factory interface for the schema.
	 *
	 *	@return	The DoubleType Factory interface for the schema.
	 */
	public ICFBamDoubleTypeFactory getFactoryDoubleType();

	/**
	 *	Get the EnumDef Factory interface for the schema.
	 *
	 *	@return	The EnumDef Factory interface for the schema.
	 */
	public ICFBamEnumDefFactory getFactoryEnumDef();

	/**
	 *	Get the EnumTag Factory interface for the schema.
	 *
	 *	@return	The EnumTag Factory interface for the schema.
	 */
	public ICFBamEnumTagFactory getFactoryEnumTag();

	/**
	 *	Get the EnumType Factory interface for the schema.
	 *
	 *	@return	The EnumType Factory interface for the schema.
	 */
	public ICFBamEnumTypeFactory getFactoryEnumType();

	/**
	 *	Get the FloatCol Factory interface for the schema.
	 *
	 *	@return	The FloatCol Factory interface for the schema.
	 */
	public ICFBamFloatColFactory getFactoryFloatCol();

	/**
	 *	Get the FloatDef Factory interface for the schema.
	 *
	 *	@return	The FloatDef Factory interface for the schema.
	 */
	public ICFBamFloatDefFactory getFactoryFloatDef();

	/**
	 *	Get the FloatType Factory interface for the schema.
	 *
	 *	@return	The FloatType Factory interface for the schema.
	 */
	public ICFBamFloatTypeFactory getFactoryFloatType();

	/**
	 *	Get the Id16Gen Factory interface for the schema.
	 *
	 *	@return	The Id16Gen Factory interface for the schema.
	 */
	public ICFBamId16GenFactory getFactoryId16Gen();

	/**
	 *	Get the Id32Gen Factory interface for the schema.
	 *
	 *	@return	The Id32Gen Factory interface for the schema.
	 */
	public ICFBamId32GenFactory getFactoryId32Gen();

	/**
	 *	Get the Id64Gen Factory interface for the schema.
	 *
	 *	@return	The Id64Gen Factory interface for the schema.
	 */
	public ICFBamId64GenFactory getFactoryId64Gen();

	/**
	 *	Get the Index Factory interface for the schema.
	 *
	 *	@return	The Index Factory interface for the schema.
	 */
	public ICFBamIndexFactory getFactoryIndex();

	/**
	 *	Get the IndexCol Factory interface for the schema.
	 *
	 *	@return	The IndexCol Factory interface for the schema.
	 */
	public ICFBamIndexColFactory getFactoryIndexCol();

	/**
	 *	Get the IndexTweak Factory interface for the schema.
	 *
	 *	@return	The IndexTweak Factory interface for the schema.
	 */
	public ICFBamIndexTweakFactory getFactoryIndexTweak();

	/**
	 *	Get the Int16Col Factory interface for the schema.
	 *
	 *	@return	The Int16Col Factory interface for the schema.
	 */
	public ICFBamInt16ColFactory getFactoryInt16Col();

	/**
	 *	Get the Int16Def Factory interface for the schema.
	 *
	 *	@return	The Int16Def Factory interface for the schema.
	 */
	public ICFBamInt16DefFactory getFactoryInt16Def();

	/**
	 *	Get the Int16Type Factory interface for the schema.
	 *
	 *	@return	The Int16Type Factory interface for the schema.
	 */
	public ICFBamInt16TypeFactory getFactoryInt16Type();

	/**
	 *	Get the Int32Col Factory interface for the schema.
	 *
	 *	@return	The Int32Col Factory interface for the schema.
	 */
	public ICFBamInt32ColFactory getFactoryInt32Col();

	/**
	 *	Get the Int32Def Factory interface for the schema.
	 *
	 *	@return	The Int32Def Factory interface for the schema.
	 */
	public ICFBamInt32DefFactory getFactoryInt32Def();

	/**
	 *	Get the Int32Type Factory interface for the schema.
	 *
	 *	@return	The Int32Type Factory interface for the schema.
	 */
	public ICFBamInt32TypeFactory getFactoryInt32Type();

	/**
	 *	Get the Int64Col Factory interface for the schema.
	 *
	 *	@return	The Int64Col Factory interface for the schema.
	 */
	public ICFBamInt64ColFactory getFactoryInt64Col();

	/**
	 *	Get the Int64Def Factory interface for the schema.
	 *
	 *	@return	The Int64Def Factory interface for the schema.
	 */
	public ICFBamInt64DefFactory getFactoryInt64Def();

	/**
	 *	Get the Int64Type Factory interface for the schema.
	 *
	 *	@return	The Int64Type Factory interface for the schema.
	 */
	public ICFBamInt64TypeFactory getFactoryInt64Type();

	/**
	 *	Get the NmTokenCol Factory interface for the schema.
	 *
	 *	@return	The NmTokenCol Factory interface for the schema.
	 */
	public ICFBamNmTokenColFactory getFactoryNmTokenCol();

	/**
	 *	Get the NmTokenDef Factory interface for the schema.
	 *
	 *	@return	The NmTokenDef Factory interface for the schema.
	 */
	public ICFBamNmTokenDefFactory getFactoryNmTokenDef();

	/**
	 *	Get the NmTokenType Factory interface for the schema.
	 *
	 *	@return	The NmTokenType Factory interface for the schema.
	 */
	public ICFBamNmTokenTypeFactory getFactoryNmTokenType();

	/**
	 *	Get the NmTokensCol Factory interface for the schema.
	 *
	 *	@return	The NmTokensCol Factory interface for the schema.
	 */
	public ICFBamNmTokensColFactory getFactoryNmTokensCol();

	/**
	 *	Get the NmTokensDef Factory interface for the schema.
	 *
	 *	@return	The NmTokensDef Factory interface for the schema.
	 */
	public ICFBamNmTokensDefFactory getFactoryNmTokensDef();

	/**
	 *	Get the NmTokensType Factory interface for the schema.
	 *
	 *	@return	The NmTokensType Factory interface for the schema.
	 */
	public ICFBamNmTokensTypeFactory getFactoryNmTokensType();

	/**
	 *	Get the NumberCol Factory interface for the schema.
	 *
	 *	@return	The NumberCol Factory interface for the schema.
	 */
	public ICFBamNumberColFactory getFactoryNumberCol();

	/**
	 *	Get the NumberDef Factory interface for the schema.
	 *
	 *	@return	The NumberDef Factory interface for the schema.
	 */
	public ICFBamNumberDefFactory getFactoryNumberDef();

	/**
	 *	Get the NumberType Factory interface for the schema.
	 *
	 *	@return	The NumberType Factory interface for the schema.
	 */
	public ICFBamNumberTypeFactory getFactoryNumberType();

	/**
	 *	Get the Param Factory interface for the schema.
	 *
	 *	@return	The Param Factory interface for the schema.
	 */
	public ICFBamParamFactory getFactoryParam();

	/**
	 *	Get the PopDep Factory interface for the schema.
	 *
	 *	@return	The PopDep Factory interface for the schema.
	 */
	public ICFBamPopDepFactory getFactoryPopDep();

	/**
	 *	Get the PopSubDep1 Factory interface for the schema.
	 *
	 *	@return	The PopSubDep1 Factory interface for the schema.
	 */
	public ICFBamPopSubDep1Factory getFactoryPopSubDep1();

	/**
	 *	Get the PopSubDep2 Factory interface for the schema.
	 *
	 *	@return	The PopSubDep2 Factory interface for the schema.
	 */
	public ICFBamPopSubDep2Factory getFactoryPopSubDep2();

	/**
	 *	Get the PopSubDep3 Factory interface for the schema.
	 *
	 *	@return	The PopSubDep3 Factory interface for the schema.
	 */
	public ICFBamPopSubDep3Factory getFactoryPopSubDep3();

	/**
	 *	Get the PopTopDep Factory interface for the schema.
	 *
	 *	@return	The PopTopDep Factory interface for the schema.
	 */
	public ICFBamPopTopDepFactory getFactoryPopTopDep();

	/**
	 *	Get the Relation Factory interface for the schema.
	 *
	 *	@return	The Relation Factory interface for the schema.
	 */
	public ICFBamRelationFactory getFactoryRelation();

	/**
	 *	Get the RelationCol Factory interface for the schema.
	 *
	 *	@return	The RelationCol Factory interface for the schema.
	 */
	public ICFBamRelationColFactory getFactoryRelationCol();

	/**
	 *	Get the RoleDef Factory interface for the schema.
	 *
	 *	@return	The RoleDef Factory interface for the schema.
	 */
	public ICFBamRoleDefFactory getFactoryRoleDef();

	/**
	 *	Get the SchemaDef Factory interface for the schema.
	 *
	 *	@return	The SchemaDef Factory interface for the schema.
	 */
	public ICFBamSchemaDefFactory getFactorySchemaDef();

	/**
	 *	Get the SchemaRef Factory interface for the schema.
	 *
	 *	@return	The SchemaRef Factory interface for the schema.
	 */
	public ICFBamSchemaRefFactory getFactorySchemaRef();

	/**
	 *	Get the SchemaRole Factory interface for the schema.
	 *
	 *	@return	The SchemaRole Factory interface for the schema.
	 */
	public ICFBamSchemaRoleFactory getFactorySchemaRole();

	/**
	 *	Get the SchemaTweak Factory interface for the schema.
	 *
	 *	@return	The SchemaTweak Factory interface for the schema.
	 */
	public ICFBamSchemaTweakFactory getFactorySchemaTweak();

	/**
	 *	Get the Scope Factory interface for the schema.
	 *
	 *	@return	The Scope Factory interface for the schema.
	 */
	public ICFBamScopeFactory getFactoryScope();

	/**
	 *	Get the ServerListFunc Factory interface for the schema.
	 *
	 *	@return	The ServerListFunc Factory interface for the schema.
	 */
	public ICFBamServerListFuncFactory getFactoryServerListFunc();

	/**
	 *	Get the ServerMethod Factory interface for the schema.
	 *
	 *	@return	The ServerMethod Factory interface for the schema.
	 */
	public ICFBamServerMethodFactory getFactoryServerMethod();

	/**
	 *	Get the ServerObjFunc Factory interface for the schema.
	 *
	 *	@return	The ServerObjFunc Factory interface for the schema.
	 */
	public ICFBamServerObjFuncFactory getFactoryServerObjFunc();

	/**
	 *	Get the ServerProc Factory interface for the schema.
	 *
	 *	@return	The ServerProc Factory interface for the schema.
	 */
	public ICFBamServerProcFactory getFactoryServerProc();

	/**
	 *	Get the StringCol Factory interface for the schema.
	 *
	 *	@return	The StringCol Factory interface for the schema.
	 */
	public ICFBamStringColFactory getFactoryStringCol();

	/**
	 *	Get the StringDef Factory interface for the schema.
	 *
	 *	@return	The StringDef Factory interface for the schema.
	 */
	public ICFBamStringDefFactory getFactoryStringDef();

	/**
	 *	Get the StringType Factory interface for the schema.
	 *
	 *	@return	The StringType Factory interface for the schema.
	 */
	public ICFBamStringTypeFactory getFactoryStringType();

	/**
	 *	Get the TZDateCol Factory interface for the schema.
	 *
	 *	@return	The TZDateCol Factory interface for the schema.
	 */
	public ICFBamTZDateColFactory getFactoryTZDateCol();

	/**
	 *	Get the TZDateDef Factory interface for the schema.
	 *
	 *	@return	The TZDateDef Factory interface for the schema.
	 */
	public ICFBamTZDateDefFactory getFactoryTZDateDef();

	/**
	 *	Get the TZDateType Factory interface for the schema.
	 *
	 *	@return	The TZDateType Factory interface for the schema.
	 */
	public ICFBamTZDateTypeFactory getFactoryTZDateType();

	/**
	 *	Get the TZTimeCol Factory interface for the schema.
	 *
	 *	@return	The TZTimeCol Factory interface for the schema.
	 */
	public ICFBamTZTimeColFactory getFactoryTZTimeCol();

	/**
	 *	Get the TZTimeDef Factory interface for the schema.
	 *
	 *	@return	The TZTimeDef Factory interface for the schema.
	 */
	public ICFBamTZTimeDefFactory getFactoryTZTimeDef();

	/**
	 *	Get the TZTimeType Factory interface for the schema.
	 *
	 *	@return	The TZTimeType Factory interface for the schema.
	 */
	public ICFBamTZTimeTypeFactory getFactoryTZTimeType();

	/**
	 *	Get the TZTimestampCol Factory interface for the schema.
	 *
	 *	@return	The TZTimestampCol Factory interface for the schema.
	 */
	public ICFBamTZTimestampColFactory getFactoryTZTimestampCol();

	/**
	 *	Get the TZTimestampDef Factory interface for the schema.
	 *
	 *	@return	The TZTimestampDef Factory interface for the schema.
	 */
	public ICFBamTZTimestampDefFactory getFactoryTZTimestampDef();

	/**
	 *	Get the TZTimestampType Factory interface for the schema.
	 *
	 *	@return	The TZTimestampType Factory interface for the schema.
	 */
	public ICFBamTZTimestampTypeFactory getFactoryTZTimestampType();

	/**
	 *	Get the Table Factory interface for the schema.
	 *
	 *	@return	The Table Factory interface for the schema.
	 */
	public ICFBamTableFactory getFactoryTable();

	/**
	 *	Get the TableCol Factory interface for the schema.
	 *
	 *	@return	The TableCol Factory interface for the schema.
	 */
	public ICFBamTableColFactory getFactoryTableCol();

	/**
	 *	Get the TableTweak Factory interface for the schema.
	 *
	 *	@return	The TableTweak Factory interface for the schema.
	 */
	public ICFBamTableTweakFactory getFactoryTableTweak();

	/**
	 *	Get the TextCol Factory interface for the schema.
	 *
	 *	@return	The TextCol Factory interface for the schema.
	 */
	public ICFBamTextColFactory getFactoryTextCol();

	/**
	 *	Get the TextDef Factory interface for the schema.
	 *
	 *	@return	The TextDef Factory interface for the schema.
	 */
	public ICFBamTextDefFactory getFactoryTextDef();

	/**
	 *	Get the TextType Factory interface for the schema.
	 *
	 *	@return	The TextType Factory interface for the schema.
	 */
	public ICFBamTextTypeFactory getFactoryTextType();

	/**
	 *	Get the TimeCol Factory interface for the schema.
	 *
	 *	@return	The TimeCol Factory interface for the schema.
	 */
	public ICFBamTimeColFactory getFactoryTimeCol();

	/**
	 *	Get the TimeDef Factory interface for the schema.
	 *
	 *	@return	The TimeDef Factory interface for the schema.
	 */
	public ICFBamTimeDefFactory getFactoryTimeDef();

	/**
	 *	Get the TimeType Factory interface for the schema.
	 *
	 *	@return	The TimeType Factory interface for the schema.
	 */
	public ICFBamTimeTypeFactory getFactoryTimeType();

	/**
	 *	Get the TimestampCol Factory interface for the schema.
	 *
	 *	@return	The TimestampCol Factory interface for the schema.
	 */
	public ICFBamTimestampColFactory getFactoryTimestampCol();

	/**
	 *	Get the TimestampDef Factory interface for the schema.
	 *
	 *	@return	The TimestampDef Factory interface for the schema.
	 */
	public ICFBamTimestampDefFactory getFactoryTimestampDef();

	/**
	 *	Get the TimestampType Factory interface for the schema.
	 *
	 *	@return	The TimestampType Factory interface for the schema.
	 */
	public ICFBamTimestampTypeFactory getFactoryTimestampType();

	/**
	 *	Get the TokenCol Factory interface for the schema.
	 *
	 *	@return	The TokenCol Factory interface for the schema.
	 */
	public ICFBamTokenColFactory getFactoryTokenCol();

	/**
	 *	Get the TokenDef Factory interface for the schema.
	 *
	 *	@return	The TokenDef Factory interface for the schema.
	 */
	public ICFBamTokenDefFactory getFactoryTokenDef();

	/**
	 *	Get the TokenType Factory interface for the schema.
	 *
	 *	@return	The TokenType Factory interface for the schema.
	 */
	public ICFBamTokenTypeFactory getFactoryTokenType();

	/**
	 *	Get the Tweak Factory interface for the schema.
	 *
	 *	@return	The Tweak Factory interface for the schema.
	 */
	public ICFBamTweakFactory getFactoryTweak();

	/**
	 *	Get the UInt16Col Factory interface for the schema.
	 *
	 *	@return	The UInt16Col Factory interface for the schema.
	 */
	public ICFBamUInt16ColFactory getFactoryUInt16Col();

	/**
	 *	Get the UInt16Def Factory interface for the schema.
	 *
	 *	@return	The UInt16Def Factory interface for the schema.
	 */
	public ICFBamUInt16DefFactory getFactoryUInt16Def();

	/**
	 *	Get the UInt16Type Factory interface for the schema.
	 *
	 *	@return	The UInt16Type Factory interface for the schema.
	 */
	public ICFBamUInt16TypeFactory getFactoryUInt16Type();

	/**
	 *	Get the UInt32Col Factory interface for the schema.
	 *
	 *	@return	The UInt32Col Factory interface for the schema.
	 */
	public ICFBamUInt32ColFactory getFactoryUInt32Col();

	/**
	 *	Get the UInt32Def Factory interface for the schema.
	 *
	 *	@return	The UInt32Def Factory interface for the schema.
	 */
	public ICFBamUInt32DefFactory getFactoryUInt32Def();

	/**
	 *	Get the UInt32Type Factory interface for the schema.
	 *
	 *	@return	The UInt32Type Factory interface for the schema.
	 */
	public ICFBamUInt32TypeFactory getFactoryUInt32Type();

	/**
	 *	Get the UInt64Col Factory interface for the schema.
	 *
	 *	@return	The UInt64Col Factory interface for the schema.
	 */
	public ICFBamUInt64ColFactory getFactoryUInt64Col();

	/**
	 *	Get the UInt64Def Factory interface for the schema.
	 *
	 *	@return	The UInt64Def Factory interface for the schema.
	 */
	public ICFBamUInt64DefFactory getFactoryUInt64Def();

	/**
	 *	Get the UInt64Type Factory interface for the schema.
	 *
	 *	@return	The UInt64Type Factory interface for the schema.
	 */
	public ICFBamUInt64TypeFactory getFactoryUInt64Type();

	/**
	 *	Get the Uuid6Col Factory interface for the schema.
	 *
	 *	@return	The Uuid6Col Factory interface for the schema.
	 */
	public ICFBamUuid6ColFactory getFactoryUuid6Col();

	/**
	 *	Get the Uuid6Def Factory interface for the schema.
	 *
	 *	@return	The Uuid6Def Factory interface for the schema.
	 */
	public ICFBamUuid6DefFactory getFactoryUuid6Def();

	/**
	 *	Get the Uuid6Gen Factory interface for the schema.
	 *
	 *	@return	The Uuid6Gen Factory interface for the schema.
	 */
	public ICFBamUuid6GenFactory getFactoryUuid6Gen();

	/**
	 *	Get the Uuid6Type Factory interface for the schema.
	 *
	 *	@return	The Uuid6Type Factory interface for the schema.
	 */
	public ICFBamUuid6TypeFactory getFactoryUuid6Type();

	/**
	 *	Get the UuidCol Factory interface for the schema.
	 *
	 *	@return	The UuidCol Factory interface for the schema.
	 */
	public ICFBamUuidColFactory getFactoryUuidCol();

	/**
	 *	Get the UuidDef Factory interface for the schema.
	 *
	 *	@return	The UuidDef Factory interface for the schema.
	 */
	public ICFBamUuidDefFactory getFactoryUuidDef();

	/**
	 *	Get the UuidGen Factory interface for the schema.
	 *
	 *	@return	The UuidGen Factory interface for the schema.
	 */
	public ICFBamUuidGenFactory getFactoryUuidGen();

	/**
	 *	Get the UuidType Factory interface for the schema.
	 *
	 *	@return	The UuidType Factory interface for the schema.
	 */
	public ICFBamUuidTypeFactory getFactoryUuidType();

	/**
	 *	Get the Value Factory interface for the schema.
	 *
	 *	@return	The Value Factory interface for the schema.
	 */
	public ICFBamValueFactory getFactoryValue();

}
