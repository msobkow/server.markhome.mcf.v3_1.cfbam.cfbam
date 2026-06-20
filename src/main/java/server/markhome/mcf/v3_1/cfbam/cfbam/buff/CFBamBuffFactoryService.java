// Description: Java 25 buffer implementation of a CFBam factory service.

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.buff.*;
import server.markhome.mcf.v3_1.cfint.cfint.buff.*;

@Service("cfbam31BuffFactoryService")
public class CFBamBuffFactoryService
	implements ICFBamFactory
{

	@Autowired
	@Qualifier("cfbam31BuffAtomFactoryService")
	protected ICFBamAtomFactory factoryAtom;

	@Autowired
	@Qualifier("cfbam31BuffBlobColFactoryService")
	protected ICFBamBlobColFactory factoryBlobCol;

	@Autowired
	@Qualifier("cfbam31BuffBlobDefFactoryService")
	protected ICFBamBlobDefFactory factoryBlobDef;

	@Autowired
	@Qualifier("cfbam31BuffBlobTypeFactoryService")
	protected ICFBamBlobTypeFactory factoryBlobType;

	@Autowired
	@Qualifier("cfbam31BuffBoolColFactoryService")
	protected ICFBamBoolColFactory factoryBoolCol;

	@Autowired
	@Qualifier("cfbam31BuffBoolDefFactoryService")
	protected ICFBamBoolDefFactory factoryBoolDef;

	@Autowired
	@Qualifier("cfbam31BuffBoolTypeFactoryService")
	protected ICFBamBoolTypeFactory factoryBoolType;

	@Autowired
	@Qualifier("cfbam31BuffChainFactoryService")
	protected ICFBamChainFactory factoryChain;

	@Autowired
	@Qualifier("cfbam31BuffClearDepFactoryService")
	protected ICFBamClearDepFactory factoryClearDep;

	@Autowired
	@Qualifier("cfbam31BuffClearSubDep1FactoryService")
	protected ICFBamClearSubDep1Factory factoryClearSubDep1;

	@Autowired
	@Qualifier("cfbam31BuffClearSubDep2FactoryService")
	protected ICFBamClearSubDep2Factory factoryClearSubDep2;

	@Autowired
	@Qualifier("cfbam31BuffClearSubDep3FactoryService")
	protected ICFBamClearSubDep3Factory factoryClearSubDep3;

	@Autowired
	@Qualifier("cfbam31BuffClearTopDepFactoryService")
	protected ICFBamClearTopDepFactory factoryClearTopDep;

	@Autowired
	@Qualifier("cfbam31BuffDateColFactoryService")
	protected ICFBamDateColFactory factoryDateCol;

	@Autowired
	@Qualifier("cfbam31BuffDateDefFactoryService")
	protected ICFBamDateDefFactory factoryDateDef;

	@Autowired
	@Qualifier("cfbam31BuffDateTypeFactoryService")
	protected ICFBamDateTypeFactory factoryDateType;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash128ColFactoryService")
	protected ICFBamDbKeyHash128ColFactory factoryDbKeyHash128Col;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash128DefFactoryService")
	protected ICFBamDbKeyHash128DefFactory factoryDbKeyHash128Def;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash128GenFactoryService")
	protected ICFBamDbKeyHash128GenFactory factoryDbKeyHash128Gen;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash128TypeFactoryService")
	protected ICFBamDbKeyHash128TypeFactory factoryDbKeyHash128Type;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash160ColFactoryService")
	protected ICFBamDbKeyHash160ColFactory factoryDbKeyHash160Col;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash160DefFactoryService")
	protected ICFBamDbKeyHash160DefFactory factoryDbKeyHash160Def;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash160GenFactoryService")
	protected ICFBamDbKeyHash160GenFactory factoryDbKeyHash160Gen;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash160TypeFactoryService")
	protected ICFBamDbKeyHash160TypeFactory factoryDbKeyHash160Type;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash224ColFactoryService")
	protected ICFBamDbKeyHash224ColFactory factoryDbKeyHash224Col;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash224DefFactoryService")
	protected ICFBamDbKeyHash224DefFactory factoryDbKeyHash224Def;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash224GenFactoryService")
	protected ICFBamDbKeyHash224GenFactory factoryDbKeyHash224Gen;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash224TypeFactoryService")
	protected ICFBamDbKeyHash224TypeFactory factoryDbKeyHash224Type;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash256ColFactoryService")
	protected ICFBamDbKeyHash256ColFactory factoryDbKeyHash256Col;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash256DefFactoryService")
	protected ICFBamDbKeyHash256DefFactory factoryDbKeyHash256Def;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash256GenFactoryService")
	protected ICFBamDbKeyHash256GenFactory factoryDbKeyHash256Gen;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash256TypeFactoryService")
	protected ICFBamDbKeyHash256TypeFactory factoryDbKeyHash256Type;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash384ColFactoryService")
	protected ICFBamDbKeyHash384ColFactory factoryDbKeyHash384Col;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash384DefFactoryService")
	protected ICFBamDbKeyHash384DefFactory factoryDbKeyHash384Def;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash384GenFactoryService")
	protected ICFBamDbKeyHash384GenFactory factoryDbKeyHash384Gen;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash384TypeFactoryService")
	protected ICFBamDbKeyHash384TypeFactory factoryDbKeyHash384Type;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash512ColFactoryService")
	protected ICFBamDbKeyHash512ColFactory factoryDbKeyHash512Col;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash512DefFactoryService")
	protected ICFBamDbKeyHash512DefFactory factoryDbKeyHash512Def;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash512GenFactoryService")
	protected ICFBamDbKeyHash512GenFactory factoryDbKeyHash512Gen;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash512TypeFactoryService")
	protected ICFBamDbKeyHash512TypeFactory factoryDbKeyHash512Type;

	@Autowired
	@Qualifier("cfbam31BuffDelDepFactoryService")
	protected ICFBamDelDepFactory factoryDelDep;

	@Autowired
	@Qualifier("cfbam31BuffDelSubDep1FactoryService")
	protected ICFBamDelSubDep1Factory factoryDelSubDep1;

	@Autowired
	@Qualifier("cfbam31BuffDelSubDep2FactoryService")
	protected ICFBamDelSubDep2Factory factoryDelSubDep2;

	@Autowired
	@Qualifier("cfbam31BuffDelSubDep3FactoryService")
	protected ICFBamDelSubDep3Factory factoryDelSubDep3;

	@Autowired
	@Qualifier("cfbam31BuffDelTopDepFactoryService")
	protected ICFBamDelTopDepFactory factoryDelTopDep;

	@Autowired
	@Qualifier("cfbam31BuffDoubleColFactoryService")
	protected ICFBamDoubleColFactory factoryDoubleCol;

	@Autowired
	@Qualifier("cfbam31BuffDoubleDefFactoryService")
	protected ICFBamDoubleDefFactory factoryDoubleDef;

	@Autowired
	@Qualifier("cfbam31BuffDoubleTypeFactoryService")
	protected ICFBamDoubleTypeFactory factoryDoubleType;

	@Autowired
	@Qualifier("cfbam31BuffEnumDefFactoryService")
	protected ICFBamEnumDefFactory factoryEnumDef;

	@Autowired
	@Qualifier("cfbam31BuffEnumTagFactoryService")
	protected ICFBamEnumTagFactory factoryEnumTag;

	@Autowired
	@Qualifier("cfbam31BuffEnumTypeFactoryService")
	protected ICFBamEnumTypeFactory factoryEnumType;

	@Autowired
	@Qualifier("cfbam31BuffFloatColFactoryService")
	protected ICFBamFloatColFactory factoryFloatCol;

	@Autowired
	@Qualifier("cfbam31BuffFloatDefFactoryService")
	protected ICFBamFloatDefFactory factoryFloatDef;

	@Autowired
	@Qualifier("cfbam31BuffFloatTypeFactoryService")
	protected ICFBamFloatTypeFactory factoryFloatType;

	@Autowired
	@Qualifier("cfbam31BuffId16GenFactoryService")
	protected ICFBamId16GenFactory factoryId16Gen;

	@Autowired
	@Qualifier("cfbam31BuffId32GenFactoryService")
	protected ICFBamId32GenFactory factoryId32Gen;

	@Autowired
	@Qualifier("cfbam31BuffId64GenFactoryService")
	protected ICFBamId64GenFactory factoryId64Gen;

	@Autowired
	@Qualifier("cfbam31BuffIndexFactoryService")
	protected ICFBamIndexFactory factoryIndex;

	@Autowired
	@Qualifier("cfbam31BuffIndexColFactoryService")
	protected ICFBamIndexColFactory factoryIndexCol;

	@Autowired
	@Qualifier("cfbam31BuffIndexTweakFactoryService")
	protected ICFBamIndexTweakFactory factoryIndexTweak;

	@Autowired
	@Qualifier("cfbam31BuffInt16ColFactoryService")
	protected ICFBamInt16ColFactory factoryInt16Col;

	@Autowired
	@Qualifier("cfbam31BuffInt16DefFactoryService")
	protected ICFBamInt16DefFactory factoryInt16Def;

	@Autowired
	@Qualifier("cfbam31BuffInt16TypeFactoryService")
	protected ICFBamInt16TypeFactory factoryInt16Type;

	@Autowired
	@Qualifier("cfbam31BuffInt32ColFactoryService")
	protected ICFBamInt32ColFactory factoryInt32Col;

	@Autowired
	@Qualifier("cfbam31BuffInt32DefFactoryService")
	protected ICFBamInt32DefFactory factoryInt32Def;

	@Autowired
	@Qualifier("cfbam31BuffInt32TypeFactoryService")
	protected ICFBamInt32TypeFactory factoryInt32Type;

	@Autowired
	@Qualifier("cfbam31BuffInt64ColFactoryService")
	protected ICFBamInt64ColFactory factoryInt64Col;

	@Autowired
	@Qualifier("cfbam31BuffInt64DefFactoryService")
	protected ICFBamInt64DefFactory factoryInt64Def;

	@Autowired
	@Qualifier("cfbam31BuffInt64TypeFactoryService")
	protected ICFBamInt64TypeFactory factoryInt64Type;

	@Autowired
	@Qualifier("cfbam31BuffNmTokenColFactoryService")
	protected ICFBamNmTokenColFactory factoryNmTokenCol;

	@Autowired
	@Qualifier("cfbam31BuffNmTokenDefFactoryService")
	protected ICFBamNmTokenDefFactory factoryNmTokenDef;

	@Autowired
	@Qualifier("cfbam31BuffNmTokenTypeFactoryService")
	protected ICFBamNmTokenTypeFactory factoryNmTokenType;

	@Autowired
	@Qualifier("cfbam31BuffNmTokensColFactoryService")
	protected ICFBamNmTokensColFactory factoryNmTokensCol;

	@Autowired
	@Qualifier("cfbam31BuffNmTokensDefFactoryService")
	protected ICFBamNmTokensDefFactory factoryNmTokensDef;

	@Autowired
	@Qualifier("cfbam31BuffNmTokensTypeFactoryService")
	protected ICFBamNmTokensTypeFactory factoryNmTokensType;

	@Autowired
	@Qualifier("cfbam31BuffNumberColFactoryService")
	protected ICFBamNumberColFactory factoryNumberCol;

	@Autowired
	@Qualifier("cfbam31BuffNumberDefFactoryService")
	protected ICFBamNumberDefFactory factoryNumberDef;

	@Autowired
	@Qualifier("cfbam31BuffNumberTypeFactoryService")
	protected ICFBamNumberTypeFactory factoryNumberType;

	@Autowired
	@Qualifier("cfbam31BuffParamFactoryService")
	protected ICFBamParamFactory factoryParam;

	@Autowired
	@Qualifier("cfbam31BuffPopDepFactoryService")
	protected ICFBamPopDepFactory factoryPopDep;

	@Autowired
	@Qualifier("cfbam31BuffPopSubDep1FactoryService")
	protected ICFBamPopSubDep1Factory factoryPopSubDep1;

	@Autowired
	@Qualifier("cfbam31BuffPopSubDep2FactoryService")
	protected ICFBamPopSubDep2Factory factoryPopSubDep2;

	@Autowired
	@Qualifier("cfbam31BuffPopSubDep3FactoryService")
	protected ICFBamPopSubDep3Factory factoryPopSubDep3;

	@Autowired
	@Qualifier("cfbam31BuffPopTopDepFactoryService")
	protected ICFBamPopTopDepFactory factoryPopTopDep;

	@Autowired
	@Qualifier("cfbam31BuffRelationFactoryService")
	protected ICFBamRelationFactory factoryRelation;

	@Autowired
	@Qualifier("cfbam31BuffRelationColFactoryService")
	protected ICFBamRelationColFactory factoryRelationCol;

	@Autowired
	@Qualifier("cfbam31BuffRoleDefFactoryService")
	protected ICFBamRoleDefFactory factoryRoleDef;

	@Autowired
	@Qualifier("cfbam31BuffSchemaDefFactoryService")
	protected ICFBamSchemaDefFactory factorySchemaDef;

	@Autowired
	@Qualifier("cfbam31BuffSchemaRefFactoryService")
	protected ICFBamSchemaRefFactory factorySchemaRef;

	@Autowired
	@Qualifier("cfbam31BuffSchemaRoleFactoryService")
	protected ICFBamSchemaRoleFactory factorySchemaRole;

	@Autowired
	@Qualifier("cfbam31BuffSchemaTweakFactoryService")
	protected ICFBamSchemaTweakFactory factorySchemaTweak;

	@Autowired
	@Qualifier("cfbam31BuffScopeFactoryService")
	protected ICFBamScopeFactory factoryScope;

	@Autowired
	@Qualifier("cfbam31BuffServerListFuncFactoryService")
	protected ICFBamServerListFuncFactory factoryServerListFunc;

	@Autowired
	@Qualifier("cfbam31BuffServerMethodFactoryService")
	protected ICFBamServerMethodFactory factoryServerMethod;

	@Autowired
	@Qualifier("cfbam31BuffServerObjFuncFactoryService")
	protected ICFBamServerObjFuncFactory factoryServerObjFunc;

	@Autowired
	@Qualifier("cfbam31BuffServerProcFactoryService")
	protected ICFBamServerProcFactory factoryServerProc;

	@Autowired
	@Qualifier("cfbam31BuffStringColFactoryService")
	protected ICFBamStringColFactory factoryStringCol;

	@Autowired
	@Qualifier("cfbam31BuffStringDefFactoryService")
	protected ICFBamStringDefFactory factoryStringDef;

	@Autowired
	@Qualifier("cfbam31BuffStringTypeFactoryService")
	protected ICFBamStringTypeFactory factoryStringType;

	@Autowired
	@Qualifier("cfbam31BuffTZDateColFactoryService")
	protected ICFBamTZDateColFactory factoryTZDateCol;

	@Autowired
	@Qualifier("cfbam31BuffTZDateDefFactoryService")
	protected ICFBamTZDateDefFactory factoryTZDateDef;

	@Autowired
	@Qualifier("cfbam31BuffTZDateTypeFactoryService")
	protected ICFBamTZDateTypeFactory factoryTZDateType;

	@Autowired
	@Qualifier("cfbam31BuffTZTimeColFactoryService")
	protected ICFBamTZTimeColFactory factoryTZTimeCol;

	@Autowired
	@Qualifier("cfbam31BuffTZTimeDefFactoryService")
	protected ICFBamTZTimeDefFactory factoryTZTimeDef;

	@Autowired
	@Qualifier("cfbam31BuffTZTimeTypeFactoryService")
	protected ICFBamTZTimeTypeFactory factoryTZTimeType;

	@Autowired
	@Qualifier("cfbam31BuffTZTimestampColFactoryService")
	protected ICFBamTZTimestampColFactory factoryTZTimestampCol;

	@Autowired
	@Qualifier("cfbam31BuffTZTimestampDefFactoryService")
	protected ICFBamTZTimestampDefFactory factoryTZTimestampDef;

	@Autowired
	@Qualifier("cfbam31BuffTZTimestampTypeFactoryService")
	protected ICFBamTZTimestampTypeFactory factoryTZTimestampType;

	@Autowired
	@Qualifier("cfbam31BuffTableFactoryService")
	protected ICFBamTableFactory factoryTable;

	@Autowired
	@Qualifier("cfbam31BuffTableColFactoryService")
	protected ICFBamTableColFactory factoryTableCol;

	@Autowired
	@Qualifier("cfbam31BuffTableTweakFactoryService")
	protected ICFBamTableTweakFactory factoryTableTweak;

	@Autowired
	@Qualifier("cfbam31BuffTextColFactoryService")
	protected ICFBamTextColFactory factoryTextCol;

	@Autowired
	@Qualifier("cfbam31BuffTextDefFactoryService")
	protected ICFBamTextDefFactory factoryTextDef;

	@Autowired
	@Qualifier("cfbam31BuffTextTypeFactoryService")
	protected ICFBamTextTypeFactory factoryTextType;

	@Autowired
	@Qualifier("cfbam31BuffTimeColFactoryService")
	protected ICFBamTimeColFactory factoryTimeCol;

	@Autowired
	@Qualifier("cfbam31BuffTimeDefFactoryService")
	protected ICFBamTimeDefFactory factoryTimeDef;

	@Autowired
	@Qualifier("cfbam31BuffTimeTypeFactoryService")
	protected ICFBamTimeTypeFactory factoryTimeType;

	@Autowired
	@Qualifier("cfbam31BuffTimestampColFactoryService")
	protected ICFBamTimestampColFactory factoryTimestampCol;

	@Autowired
	@Qualifier("cfbam31BuffTimestampDefFactoryService")
	protected ICFBamTimestampDefFactory factoryTimestampDef;

	@Autowired
	@Qualifier("cfbam31BuffTimestampTypeFactoryService")
	protected ICFBamTimestampTypeFactory factoryTimestampType;

	@Autowired
	@Qualifier("cfbam31BuffTokenColFactoryService")
	protected ICFBamTokenColFactory factoryTokenCol;

	@Autowired
	@Qualifier("cfbam31BuffTokenDefFactoryService")
	protected ICFBamTokenDefFactory factoryTokenDef;

	@Autowired
	@Qualifier("cfbam31BuffTokenTypeFactoryService")
	protected ICFBamTokenTypeFactory factoryTokenType;

	@Autowired
	@Qualifier("cfbam31BuffTweakFactoryService")
	protected ICFBamTweakFactory factoryTweak;

	@Autowired
	@Qualifier("cfbam31BuffUInt16ColFactoryService")
	protected ICFBamUInt16ColFactory factoryUInt16Col;

	@Autowired
	@Qualifier("cfbam31BuffUInt16DefFactoryService")
	protected ICFBamUInt16DefFactory factoryUInt16Def;

	@Autowired
	@Qualifier("cfbam31BuffUInt16TypeFactoryService")
	protected ICFBamUInt16TypeFactory factoryUInt16Type;

	@Autowired
	@Qualifier("cfbam31BuffUInt32ColFactoryService")
	protected ICFBamUInt32ColFactory factoryUInt32Col;

	@Autowired
	@Qualifier("cfbam31BuffUInt32DefFactoryService")
	protected ICFBamUInt32DefFactory factoryUInt32Def;

	@Autowired
	@Qualifier("cfbam31BuffUInt32TypeFactoryService")
	protected ICFBamUInt32TypeFactory factoryUInt32Type;

	@Autowired
	@Qualifier("cfbam31BuffUInt64ColFactoryService")
	protected ICFBamUInt64ColFactory factoryUInt64Col;

	@Autowired
	@Qualifier("cfbam31BuffUInt64DefFactoryService")
	protected ICFBamUInt64DefFactory factoryUInt64Def;

	@Autowired
	@Qualifier("cfbam31BuffUInt64TypeFactoryService")
	protected ICFBamUInt64TypeFactory factoryUInt64Type;

	@Autowired
	@Qualifier("cfbam31BuffUuid6ColFactoryService")
	protected ICFBamUuid6ColFactory factoryUuid6Col;

	@Autowired
	@Qualifier("cfbam31BuffUuid6DefFactoryService")
	protected ICFBamUuid6DefFactory factoryUuid6Def;

	@Autowired
	@Qualifier("cfbam31BuffUuid6GenFactoryService")
	protected ICFBamUuid6GenFactory factoryUuid6Gen;

	@Autowired
	@Qualifier("cfbam31BuffUuid6TypeFactoryService")
	protected ICFBamUuid6TypeFactory factoryUuid6Type;

	@Autowired
	@Qualifier("cfbam31BuffUuidColFactoryService")
	protected ICFBamUuidColFactory factoryUuidCol;

	@Autowired
	@Qualifier("cfbam31BuffUuidDefFactoryService")
	protected ICFBamUuidDefFactory factoryUuidDef;

	@Autowired
	@Qualifier("cfbam31BuffUuidGenFactoryService")
	protected ICFBamUuidGenFactory factoryUuidGen;

	@Autowired
	@Qualifier("cfbam31BuffUuidTypeFactoryService")
	protected ICFBamUuidTypeFactory factoryUuidType;

	@Autowired
	@Qualifier("cfbam31BuffValueFactoryService")
	protected ICFBamValueFactory factoryValue;


	public CFBamBuffFactoryService() { }

	@Override
	public ICFBamAtomFactory getFactoryAtom() {
		return( factoryAtom );
	}

	@Override
	public ICFBamBlobColFactory getFactoryBlobCol() {
		return( factoryBlobCol );
	}

	@Override
	public ICFBamBlobDefFactory getFactoryBlobDef() {
		return( factoryBlobDef );
	}

	@Override
	public ICFBamBlobTypeFactory getFactoryBlobType() {
		return( factoryBlobType );
	}

	@Override
	public ICFBamBoolColFactory getFactoryBoolCol() {
		return( factoryBoolCol );
	}

	@Override
	public ICFBamBoolDefFactory getFactoryBoolDef() {
		return( factoryBoolDef );
	}

	@Override
	public ICFBamBoolTypeFactory getFactoryBoolType() {
		return( factoryBoolType );
	}

	@Override
	public ICFBamChainFactory getFactoryChain() {
		return( factoryChain );
	}

	@Override
	public ICFBamClearDepFactory getFactoryClearDep() {
		return( factoryClearDep );
	}

	@Override
	public ICFBamClearSubDep1Factory getFactoryClearSubDep1() {
		return( factoryClearSubDep1 );
	}

	@Override
	public ICFBamClearSubDep2Factory getFactoryClearSubDep2() {
		return( factoryClearSubDep2 );
	}

	@Override
	public ICFBamClearSubDep3Factory getFactoryClearSubDep3() {
		return( factoryClearSubDep3 );
	}

	@Override
	public ICFBamClearTopDepFactory getFactoryClearTopDep() {
		return( factoryClearTopDep );
	}

	@Override
	public ICFBamDateColFactory getFactoryDateCol() {
		return( factoryDateCol );
	}

	@Override
	public ICFBamDateDefFactory getFactoryDateDef() {
		return( factoryDateDef );
	}

	@Override
	public ICFBamDateTypeFactory getFactoryDateType() {
		return( factoryDateType );
	}

	@Override
	public ICFBamDbKeyHash128ColFactory getFactoryDbKeyHash128Col() {
		return( factoryDbKeyHash128Col );
	}

	@Override
	public ICFBamDbKeyHash128DefFactory getFactoryDbKeyHash128Def() {
		return( factoryDbKeyHash128Def );
	}

	@Override
	public ICFBamDbKeyHash128GenFactory getFactoryDbKeyHash128Gen() {
		return( factoryDbKeyHash128Gen );
	}

	@Override
	public ICFBamDbKeyHash128TypeFactory getFactoryDbKeyHash128Type() {
		return( factoryDbKeyHash128Type );
	}

	@Override
	public ICFBamDbKeyHash160ColFactory getFactoryDbKeyHash160Col() {
		return( factoryDbKeyHash160Col );
	}

	@Override
	public ICFBamDbKeyHash160DefFactory getFactoryDbKeyHash160Def() {
		return( factoryDbKeyHash160Def );
	}

	@Override
	public ICFBamDbKeyHash160GenFactory getFactoryDbKeyHash160Gen() {
		return( factoryDbKeyHash160Gen );
	}

	@Override
	public ICFBamDbKeyHash160TypeFactory getFactoryDbKeyHash160Type() {
		return( factoryDbKeyHash160Type );
	}

	@Override
	public ICFBamDbKeyHash224ColFactory getFactoryDbKeyHash224Col() {
		return( factoryDbKeyHash224Col );
	}

	@Override
	public ICFBamDbKeyHash224DefFactory getFactoryDbKeyHash224Def() {
		return( factoryDbKeyHash224Def );
	}

	@Override
	public ICFBamDbKeyHash224GenFactory getFactoryDbKeyHash224Gen() {
		return( factoryDbKeyHash224Gen );
	}

	@Override
	public ICFBamDbKeyHash224TypeFactory getFactoryDbKeyHash224Type() {
		return( factoryDbKeyHash224Type );
	}

	@Override
	public ICFBamDbKeyHash256ColFactory getFactoryDbKeyHash256Col() {
		return( factoryDbKeyHash256Col );
	}

	@Override
	public ICFBamDbKeyHash256DefFactory getFactoryDbKeyHash256Def() {
		return( factoryDbKeyHash256Def );
	}

	@Override
	public ICFBamDbKeyHash256GenFactory getFactoryDbKeyHash256Gen() {
		return( factoryDbKeyHash256Gen );
	}

	@Override
	public ICFBamDbKeyHash256TypeFactory getFactoryDbKeyHash256Type() {
		return( factoryDbKeyHash256Type );
	}

	@Override
	public ICFBamDbKeyHash384ColFactory getFactoryDbKeyHash384Col() {
		return( factoryDbKeyHash384Col );
	}

	@Override
	public ICFBamDbKeyHash384DefFactory getFactoryDbKeyHash384Def() {
		return( factoryDbKeyHash384Def );
	}

	@Override
	public ICFBamDbKeyHash384GenFactory getFactoryDbKeyHash384Gen() {
		return( factoryDbKeyHash384Gen );
	}

	@Override
	public ICFBamDbKeyHash384TypeFactory getFactoryDbKeyHash384Type() {
		return( factoryDbKeyHash384Type );
	}

	@Override
	public ICFBamDbKeyHash512ColFactory getFactoryDbKeyHash512Col() {
		return( factoryDbKeyHash512Col );
	}

	@Override
	public ICFBamDbKeyHash512DefFactory getFactoryDbKeyHash512Def() {
		return( factoryDbKeyHash512Def );
	}

	@Override
	public ICFBamDbKeyHash512GenFactory getFactoryDbKeyHash512Gen() {
		return( factoryDbKeyHash512Gen );
	}

	@Override
	public ICFBamDbKeyHash512TypeFactory getFactoryDbKeyHash512Type() {
		return( factoryDbKeyHash512Type );
	}

	@Override
	public ICFBamDelDepFactory getFactoryDelDep() {
		return( factoryDelDep );
	}

	@Override
	public ICFBamDelSubDep1Factory getFactoryDelSubDep1() {
		return( factoryDelSubDep1 );
	}

	@Override
	public ICFBamDelSubDep2Factory getFactoryDelSubDep2() {
		return( factoryDelSubDep2 );
	}

	@Override
	public ICFBamDelSubDep3Factory getFactoryDelSubDep3() {
		return( factoryDelSubDep3 );
	}

	@Override
	public ICFBamDelTopDepFactory getFactoryDelTopDep() {
		return( factoryDelTopDep );
	}

	@Override
	public ICFBamDoubleColFactory getFactoryDoubleCol() {
		return( factoryDoubleCol );
	}

	@Override
	public ICFBamDoubleDefFactory getFactoryDoubleDef() {
		return( factoryDoubleDef );
	}

	@Override
	public ICFBamDoubleTypeFactory getFactoryDoubleType() {
		return( factoryDoubleType );
	}

	@Override
	public ICFBamEnumDefFactory getFactoryEnumDef() {
		return( factoryEnumDef );
	}

	@Override
	public ICFBamEnumTagFactory getFactoryEnumTag() {
		return( factoryEnumTag );
	}

	@Override
	public ICFBamEnumTypeFactory getFactoryEnumType() {
		return( factoryEnumType );
	}

	@Override
	public ICFBamFloatColFactory getFactoryFloatCol() {
		return( factoryFloatCol );
	}

	@Override
	public ICFBamFloatDefFactory getFactoryFloatDef() {
		return( factoryFloatDef );
	}

	@Override
	public ICFBamFloatTypeFactory getFactoryFloatType() {
		return( factoryFloatType );
	}

	@Override
	public ICFBamId16GenFactory getFactoryId16Gen() {
		return( factoryId16Gen );
	}

	@Override
	public ICFBamId32GenFactory getFactoryId32Gen() {
		return( factoryId32Gen );
	}

	@Override
	public ICFBamId64GenFactory getFactoryId64Gen() {
		return( factoryId64Gen );
	}

	@Override
	public ICFBamIndexFactory getFactoryIndex() {
		return( factoryIndex );
	}

	@Override
	public ICFBamIndexColFactory getFactoryIndexCol() {
		return( factoryIndexCol );
	}

	@Override
	public ICFBamIndexTweakFactory getFactoryIndexTweak() {
		return( factoryIndexTweak );
	}

	@Override
	public ICFBamInt16ColFactory getFactoryInt16Col() {
		return( factoryInt16Col );
	}

	@Override
	public ICFBamInt16DefFactory getFactoryInt16Def() {
		return( factoryInt16Def );
	}

	@Override
	public ICFBamInt16TypeFactory getFactoryInt16Type() {
		return( factoryInt16Type );
	}

	@Override
	public ICFBamInt32ColFactory getFactoryInt32Col() {
		return( factoryInt32Col );
	}

	@Override
	public ICFBamInt32DefFactory getFactoryInt32Def() {
		return( factoryInt32Def );
	}

	@Override
	public ICFBamInt32TypeFactory getFactoryInt32Type() {
		return( factoryInt32Type );
	}

	@Override
	public ICFBamInt64ColFactory getFactoryInt64Col() {
		return( factoryInt64Col );
	}

	@Override
	public ICFBamInt64DefFactory getFactoryInt64Def() {
		return( factoryInt64Def );
	}

	@Override
	public ICFBamInt64TypeFactory getFactoryInt64Type() {
		return( factoryInt64Type );
	}

	@Override
	public ICFBamNmTokenColFactory getFactoryNmTokenCol() {
		return( factoryNmTokenCol );
	}

	@Override
	public ICFBamNmTokenDefFactory getFactoryNmTokenDef() {
		return( factoryNmTokenDef );
	}

	@Override
	public ICFBamNmTokenTypeFactory getFactoryNmTokenType() {
		return( factoryNmTokenType );
	}

	@Override
	public ICFBamNmTokensColFactory getFactoryNmTokensCol() {
		return( factoryNmTokensCol );
	}

	@Override
	public ICFBamNmTokensDefFactory getFactoryNmTokensDef() {
		return( factoryNmTokensDef );
	}

	@Override
	public ICFBamNmTokensTypeFactory getFactoryNmTokensType() {
		return( factoryNmTokensType );
	}

	@Override
	public ICFBamNumberColFactory getFactoryNumberCol() {
		return( factoryNumberCol );
	}

	@Override
	public ICFBamNumberDefFactory getFactoryNumberDef() {
		return( factoryNumberDef );
	}

	@Override
	public ICFBamNumberTypeFactory getFactoryNumberType() {
		return( factoryNumberType );
	}

	@Override
	public ICFBamParamFactory getFactoryParam() {
		return( factoryParam );
	}

	@Override
	public ICFBamPopDepFactory getFactoryPopDep() {
		return( factoryPopDep );
	}

	@Override
	public ICFBamPopSubDep1Factory getFactoryPopSubDep1() {
		return( factoryPopSubDep1 );
	}

	@Override
	public ICFBamPopSubDep2Factory getFactoryPopSubDep2() {
		return( factoryPopSubDep2 );
	}

	@Override
	public ICFBamPopSubDep3Factory getFactoryPopSubDep3() {
		return( factoryPopSubDep3 );
	}

	@Override
	public ICFBamPopTopDepFactory getFactoryPopTopDep() {
		return( factoryPopTopDep );
	}

	@Override
	public ICFBamRelationFactory getFactoryRelation() {
		return( factoryRelation );
	}

	@Override
	public ICFBamRelationColFactory getFactoryRelationCol() {
		return( factoryRelationCol );
	}

	@Override
	public ICFBamRoleDefFactory getFactoryRoleDef() {
		return( factoryRoleDef );
	}

	@Override
	public ICFBamSchemaDefFactory getFactorySchemaDef() {
		return( factorySchemaDef );
	}

	@Override
	public ICFBamSchemaRefFactory getFactorySchemaRef() {
		return( factorySchemaRef );
	}

	@Override
	public ICFBamSchemaRoleFactory getFactorySchemaRole() {
		return( factorySchemaRole );
	}

	@Override
	public ICFBamSchemaTweakFactory getFactorySchemaTweak() {
		return( factorySchemaTweak );
	}

	@Override
	public ICFBamScopeFactory getFactoryScope() {
		return( factoryScope );
	}

	@Override
	public ICFBamServerListFuncFactory getFactoryServerListFunc() {
		return( factoryServerListFunc );
	}

	@Override
	public ICFBamServerMethodFactory getFactoryServerMethod() {
		return( factoryServerMethod );
	}

	@Override
	public ICFBamServerObjFuncFactory getFactoryServerObjFunc() {
		return( factoryServerObjFunc );
	}

	@Override
	public ICFBamServerProcFactory getFactoryServerProc() {
		return( factoryServerProc );
	}

	@Override
	public ICFBamStringColFactory getFactoryStringCol() {
		return( factoryStringCol );
	}

	@Override
	public ICFBamStringDefFactory getFactoryStringDef() {
		return( factoryStringDef );
	}

	@Override
	public ICFBamStringTypeFactory getFactoryStringType() {
		return( factoryStringType );
	}

	@Override
	public ICFBamTZDateColFactory getFactoryTZDateCol() {
		return( factoryTZDateCol );
	}

	@Override
	public ICFBamTZDateDefFactory getFactoryTZDateDef() {
		return( factoryTZDateDef );
	}

	@Override
	public ICFBamTZDateTypeFactory getFactoryTZDateType() {
		return( factoryTZDateType );
	}

	@Override
	public ICFBamTZTimeColFactory getFactoryTZTimeCol() {
		return( factoryTZTimeCol );
	}

	@Override
	public ICFBamTZTimeDefFactory getFactoryTZTimeDef() {
		return( factoryTZTimeDef );
	}

	@Override
	public ICFBamTZTimeTypeFactory getFactoryTZTimeType() {
		return( factoryTZTimeType );
	}

	@Override
	public ICFBamTZTimestampColFactory getFactoryTZTimestampCol() {
		return( factoryTZTimestampCol );
	}

	@Override
	public ICFBamTZTimestampDefFactory getFactoryTZTimestampDef() {
		return( factoryTZTimestampDef );
	}

	@Override
	public ICFBamTZTimestampTypeFactory getFactoryTZTimestampType() {
		return( factoryTZTimestampType );
	}

	@Override
	public ICFBamTableFactory getFactoryTable() {
		return( factoryTable );
	}

	@Override
	public ICFBamTableColFactory getFactoryTableCol() {
		return( factoryTableCol );
	}

	@Override
	public ICFBamTableTweakFactory getFactoryTableTweak() {
		return( factoryTableTweak );
	}

	@Override
	public ICFBamTextColFactory getFactoryTextCol() {
		return( factoryTextCol );
	}

	@Override
	public ICFBamTextDefFactory getFactoryTextDef() {
		return( factoryTextDef );
	}

	@Override
	public ICFBamTextTypeFactory getFactoryTextType() {
		return( factoryTextType );
	}

	@Override
	public ICFBamTimeColFactory getFactoryTimeCol() {
		return( factoryTimeCol );
	}

	@Override
	public ICFBamTimeDefFactory getFactoryTimeDef() {
		return( factoryTimeDef );
	}

	@Override
	public ICFBamTimeTypeFactory getFactoryTimeType() {
		return( factoryTimeType );
	}

	@Override
	public ICFBamTimestampColFactory getFactoryTimestampCol() {
		return( factoryTimestampCol );
	}

	@Override
	public ICFBamTimestampDefFactory getFactoryTimestampDef() {
		return( factoryTimestampDef );
	}

	@Override
	public ICFBamTimestampTypeFactory getFactoryTimestampType() {
		return( factoryTimestampType );
	}

	@Override
	public ICFBamTokenColFactory getFactoryTokenCol() {
		return( factoryTokenCol );
	}

	@Override
	public ICFBamTokenDefFactory getFactoryTokenDef() {
		return( factoryTokenDef );
	}

	@Override
	public ICFBamTokenTypeFactory getFactoryTokenType() {
		return( factoryTokenType );
	}

	@Override
	public ICFBamTweakFactory getFactoryTweak() {
		return( factoryTweak );
	}

	@Override
	public ICFBamUInt16ColFactory getFactoryUInt16Col() {
		return( factoryUInt16Col );
	}

	@Override
	public ICFBamUInt16DefFactory getFactoryUInt16Def() {
		return( factoryUInt16Def );
	}

	@Override
	public ICFBamUInt16TypeFactory getFactoryUInt16Type() {
		return( factoryUInt16Type );
	}

	@Override
	public ICFBamUInt32ColFactory getFactoryUInt32Col() {
		return( factoryUInt32Col );
	}

	@Override
	public ICFBamUInt32DefFactory getFactoryUInt32Def() {
		return( factoryUInt32Def );
	}

	@Override
	public ICFBamUInt32TypeFactory getFactoryUInt32Type() {
		return( factoryUInt32Type );
	}

	@Override
	public ICFBamUInt64ColFactory getFactoryUInt64Col() {
		return( factoryUInt64Col );
	}

	@Override
	public ICFBamUInt64DefFactory getFactoryUInt64Def() {
		return( factoryUInt64Def );
	}

	@Override
	public ICFBamUInt64TypeFactory getFactoryUInt64Type() {
		return( factoryUInt64Type );
	}

	@Override
	public ICFBamUuid6ColFactory getFactoryUuid6Col() {
		return( factoryUuid6Col );
	}

	@Override
	public ICFBamUuid6DefFactory getFactoryUuid6Def() {
		return( factoryUuid6Def );
	}

	@Override
	public ICFBamUuid6GenFactory getFactoryUuid6Gen() {
		return( factoryUuid6Gen );
	}

	@Override
	public ICFBamUuid6TypeFactory getFactoryUuid6Type() {
		return( factoryUuid6Type );
	}

	@Override
	public ICFBamUuidColFactory getFactoryUuidCol() {
		return( factoryUuidCol );
	}

	@Override
	public ICFBamUuidDefFactory getFactoryUuidDef() {
		return( factoryUuidDef );
	}

	@Override
	public ICFBamUuidGenFactory getFactoryUuidGen() {
		return( factoryUuidGen );
	}

	@Override
	public ICFBamUuidTypeFactory getFactoryUuidType() {
		return( factoryUuidType );
	}

	@Override
	public ICFBamValueFactory getFactoryValue() {
		return( factoryValue );
	}

}
