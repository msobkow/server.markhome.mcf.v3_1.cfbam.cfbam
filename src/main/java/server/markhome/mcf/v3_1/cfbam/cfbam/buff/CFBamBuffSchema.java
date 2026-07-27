// Description: Java 25 buffer implementation of a CFBam schema.

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
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import org.apache.commons.text.StringEscapeUtils;
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
import server.markhome.mcf.v3_1.cfbam.cfbam.buff.CFBamBuffHooksSchema;

public class CFBamBuffSchema
	implements ICFBamSchema,
		ICFSecSchema,
		ICFIntSchema
{
	private static CFBamBuffHooksSchema cfbamBuffHooksSchema = null;


	protected ICFBamAtomTable tableAtom;
	protected ICFBamBlobColTable tableBlobCol;
	protected ICFBamBlobDefTable tableBlobDef;
	protected ICFBamBlobTypeTable tableBlobType;
	protected ICFBamBoolColTable tableBoolCol;
	protected ICFBamBoolDefTable tableBoolDef;
	protected ICFBamBoolTypeTable tableBoolType;
	protected ICFBamChainTable tableChain;
	protected ICFBamClearDepTable tableClearDep;
	protected ICFBamClearSubDep1Table tableClearSubDep1;
	protected ICFBamClearSubDep2Table tableClearSubDep2;
	protected ICFBamClearSubDep3Table tableClearSubDep3;
	protected ICFBamClearTopDepTable tableClearTopDep;
	protected ICFSecClusterTable tableCluster;
	protected ICFBamDateColTable tableDateCol;
	protected ICFBamDateDefTable tableDateDef;
	protected ICFBamDateTypeTable tableDateType;
	protected ICFBamDbKeyHash128ColTable tableDbKeyHash128Col;
	protected ICFBamDbKeyHash128DefTable tableDbKeyHash128Def;
	protected ICFBamDbKeyHash128GenTable tableDbKeyHash128Gen;
	protected ICFBamDbKeyHash128TypeTable tableDbKeyHash128Type;
	protected ICFBamDbKeyHash160ColTable tableDbKeyHash160Col;
	protected ICFBamDbKeyHash160DefTable tableDbKeyHash160Def;
	protected ICFBamDbKeyHash160GenTable tableDbKeyHash160Gen;
	protected ICFBamDbKeyHash160TypeTable tableDbKeyHash160Type;
	protected ICFBamDbKeyHash224ColTable tableDbKeyHash224Col;
	protected ICFBamDbKeyHash224DefTable tableDbKeyHash224Def;
	protected ICFBamDbKeyHash224GenTable tableDbKeyHash224Gen;
	protected ICFBamDbKeyHash224TypeTable tableDbKeyHash224Type;
	protected ICFBamDbKeyHash256ColTable tableDbKeyHash256Col;
	protected ICFBamDbKeyHash256DefTable tableDbKeyHash256Def;
	protected ICFBamDbKeyHash256GenTable tableDbKeyHash256Gen;
	protected ICFBamDbKeyHash256TypeTable tableDbKeyHash256Type;
	protected ICFBamDbKeyHash384ColTable tableDbKeyHash384Col;
	protected ICFBamDbKeyHash384DefTable tableDbKeyHash384Def;
	protected ICFBamDbKeyHash384GenTable tableDbKeyHash384Gen;
	protected ICFBamDbKeyHash384TypeTable tableDbKeyHash384Type;
	protected ICFBamDbKeyHash512ColTable tableDbKeyHash512Col;
	protected ICFBamDbKeyHash512DefTable tableDbKeyHash512Def;
	protected ICFBamDbKeyHash512GenTable tableDbKeyHash512Gen;
	protected ICFBamDbKeyHash512TypeTable tableDbKeyHash512Type;
	protected ICFBamDelDepTable tableDelDep;
	protected ICFBamDelSubDep1Table tableDelSubDep1;
	protected ICFBamDelSubDep2Table tableDelSubDep2;
	protected ICFBamDelSubDep3Table tableDelSubDep3;
	protected ICFBamDelTopDepTable tableDelTopDep;
	protected ICFBamDoubleColTable tableDoubleCol;
	protected ICFBamDoubleDefTable tableDoubleDef;
	protected ICFBamDoubleTypeTable tableDoubleType;
	protected ICFBamEnumDefTable tableEnumDef;
	protected ICFBamEnumTagTable tableEnumTag;
	protected ICFBamEnumTypeTable tableEnumType;
	protected ICFBamFloatColTable tableFloatCol;
	protected ICFBamFloatDefTable tableFloatDef;
	protected ICFBamFloatTypeTable tableFloatType;
	protected ICFSecISOCcyTable tableISOCcy;
	protected ICFSecISOCtryTable tableISOCtry;
	protected ICFSecISOCtryCcyTable tableISOCtryCcy;
	protected ICFSecISOCtryLangTable tableISOCtryLang;
	protected ICFSecISOLangTable tableISOLang;
	protected ICFSecISOTZoneTable tableISOTZone;
	protected ICFBamId16GenTable tableId16Gen;
	protected ICFBamId32GenTable tableId32Gen;
	protected ICFBamId64GenTable tableId64Gen;
	protected ICFBamIndexTable tableIndex;
	protected ICFBamIndexColTable tableIndexCol;
	protected ICFBamIndexTweakTable tableIndexTweak;
	protected ICFBamInt16ColTable tableInt16Col;
	protected ICFBamInt16DefTable tableInt16Def;
	protected ICFBamInt16TypeTable tableInt16Type;
	protected ICFBamInt32ColTable tableInt32Col;
	protected ICFBamInt32DefTable tableInt32Def;
	protected ICFBamInt32TypeTable tableInt32Type;
	protected ICFBamInt64ColTable tableInt64Col;
	protected ICFBamInt64DefTable tableInt64Def;
	protected ICFBamInt64TypeTable tableInt64Type;
	protected ICFIntLicenseTable tableLicense;
	protected ICFIntMajorVersionTable tableMajorVersion;
	protected ICFIntMimeTypeTable tableMimeType;
	protected ICFIntMinorVersionTable tableMinorVersion;
	protected ICFBamNmTokenColTable tableNmTokenCol;
	protected ICFBamNmTokenDefTable tableNmTokenDef;
	protected ICFBamNmTokenTypeTable tableNmTokenType;
	protected ICFBamNmTokensColTable tableNmTokensCol;
	protected ICFBamNmTokensDefTable tableNmTokensDef;
	protected ICFBamNmTokensTypeTable tableNmTokensType;
	protected ICFBamNumberColTable tableNumberCol;
	protected ICFBamNumberDefTable tableNumberDef;
	protected ICFBamNumberTypeTable tableNumberType;
	protected ICFBamParamTable tableParam;
	protected ICFBamPopDepTable tablePopDep;
	protected ICFBamPopSubDep1Table tablePopSubDep1;
	protected ICFBamPopSubDep2Table tablePopSubDep2;
	protected ICFBamPopSubDep3Table tablePopSubDep3;
	protected ICFBamPopTopDepTable tablePopTopDep;
	protected ICFBamRelationTable tableRelation;
	protected ICFBamRelationColTable tableRelationCol;
	protected ICFBamRoleDefTable tableRoleDef;
	protected ICFBamSchemaDefTable tableSchemaDef;
	protected ICFBamSchemaRefTable tableSchemaRef;
	protected ICFBamSchemaRoleTable tableSchemaRole;
	protected ICFBamSchemaTweakTable tableSchemaTweak;
	protected ICFBamScopeTable tableScope;
	protected ICFSecSecClusGrpTable tableSecClusGrp;
	protected ICFSecSecClusGrpMembTable tableSecClusGrpMemb;
	protected ICFSecSecClusRoleTable tableSecClusRole;
	protected ICFSecSecClusRoleMembTable tableSecClusRoleMemb;
	protected ICFSecSecSessionTable tableSecSession;
	protected ICFSecSecSysGrpTable tableSecSysGrp;
	protected ICFSecSecSysGrpIncTable tableSecSysGrpInc;
	protected ICFSecSecSysGrpMembTable tableSecSysGrpMemb;
	protected ICFSecSecSysRoleTable tableSecSysRole;
	protected ICFSecSecSysRoleEnablesTable tableSecSysRoleEnables;
	protected ICFSecSecSysRoleMembTable tableSecSysRoleMemb;
	protected ICFSecSecTentGrpTable tableSecTentGrp;
	protected ICFSecSecTentGrpMembTable tableSecTentGrpMemb;
	protected ICFSecSecTentRoleTable tableSecTentRole;
	protected ICFSecSecTentRoleMembTable tableSecTentRoleMemb;
	protected ICFSecSecUserTable tableSecUser;
	protected ICFSecSecUserEMConfTable tableSecUserEMConf;
	protected ICFSecSecUserPWHistoryTable tableSecUserPWHistory;
	protected ICFSecSecUserPWResetTable tableSecUserPWReset;
	protected ICFSecSecUserPasswordTable tableSecUserPassword;
	protected ICFBamServerListFuncTable tableServerListFunc;
	protected ICFBamServerMethodTable tableServerMethod;
	protected ICFBamServerObjFuncTable tableServerObjFunc;
	protected ICFBamServerProcTable tableServerProc;
	protected ICFBamStringColTable tableStringCol;
	protected ICFBamStringDefTable tableStringDef;
	protected ICFBamStringTypeTable tableStringType;
	protected ICFIntSubProjectTable tableSubProject;
	protected ICFSecSysClusterTable tableSysCluster;
	protected ICFBamTZDateColTable tableTZDateCol;
	protected ICFBamTZDateDefTable tableTZDateDef;
	protected ICFBamTZDateTypeTable tableTZDateType;
	protected ICFBamTZTimeColTable tableTZTimeCol;
	protected ICFBamTZTimeDefTable tableTZTimeDef;
	protected ICFBamTZTimeTypeTable tableTZTimeType;
	protected ICFBamTZTimestampColTable tableTZTimestampCol;
	protected ICFBamTZTimestampDefTable tableTZTimestampDef;
	protected ICFBamTZTimestampTypeTable tableTZTimestampType;
	protected ICFBamTableTable tableTable;
	protected ICFBamTableColTable tableTableCol;
	protected ICFSecTableInfoTable tableTableInfo;
	protected ICFBamTableTweakTable tableTableTweak;
	protected ICFSecTenantTable tableTenant;
	protected ICFBamTextColTable tableTextCol;
	protected ICFBamTextDefTable tableTextDef;
	protected ICFBamTextTypeTable tableTextType;
	protected ICFBamTimeColTable tableTimeCol;
	protected ICFBamTimeDefTable tableTimeDef;
	protected ICFBamTimeTypeTable tableTimeType;
	protected ICFBamTimestampColTable tableTimestampCol;
	protected ICFBamTimestampDefTable tableTimestampDef;
	protected ICFBamTimestampTypeTable tableTimestampType;
	protected ICFIntTldTable tableTld;
	protected ICFBamTokenColTable tableTokenCol;
	protected ICFBamTokenDefTable tableTokenDef;
	protected ICFBamTokenTypeTable tableTokenType;
	protected ICFIntTopDomainTable tableTopDomain;
	protected ICFIntTopProjectTable tableTopProject;
	protected ICFBamTweakTable tableTweak;
	protected ICFBamUInt16ColTable tableUInt16Col;
	protected ICFBamUInt16DefTable tableUInt16Def;
	protected ICFBamUInt16TypeTable tableUInt16Type;
	protected ICFBamUInt32ColTable tableUInt32Col;
	protected ICFBamUInt32DefTable tableUInt32Def;
	protected ICFBamUInt32TypeTable tableUInt32Type;
	protected ICFBamUInt64ColTable tableUInt64Col;
	protected ICFBamUInt64DefTable tableUInt64Def;
	protected ICFBamUInt64TypeTable tableUInt64Type;
	protected ICFIntURLProtocolTable tableURLProtocol;
	protected ICFBamUuid6ColTable tableUuid6Col;
	protected ICFBamUuid6DefTable tableUuid6Def;
	protected ICFBamUuid6GenTable tableUuid6Gen;
	protected ICFBamUuid6TypeTable tableUuid6Type;
	protected ICFBamUuidColTable tableUuidCol;
	protected ICFBamUuidDefTable tableUuidDef;
	protected ICFBamUuidGenTable tableUuidGen;
	protected ICFBamUuidTypeTable tableUuidType;
	protected ICFBamValueTable tableValue;

	@Override
	public int initClassMapEntries(int value) {
		return( ICFBamSchema.doInitClassMapEntries(value) );
	}

	@Override
	public void wireRecConstructors() {
		ICFSecSchema.ClassMapEntry entry;
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamScope.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamScope ret = new CFBamBuffScope();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamScope.CLASS_CODE)[" + ICFBamScope.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamSchemaDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamSchemaDef ret = new CFBamBuffSchemaDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamSchemaDef.CLASS_CODE)[" + ICFBamSchemaDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamSchemaRef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamSchemaRef ret = new CFBamBuffSchemaRef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamSchemaRef.CLASS_CODE)[" + ICFBamSchemaRef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerMethod.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamServerMethod ret = new CFBamBuffServerMethod();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerMethod.CLASS_CODE)[" + ICFBamServerMethod.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerObjFunc.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamServerObjFunc ret = new CFBamBuffServerObjFunc();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerObjFunc.CLASS_CODE)[" + ICFBamServerObjFunc.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerProc.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamServerProc ret = new CFBamBuffServerProc();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerProc.CLASS_CODE)[" + ICFBamServerProc.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTable.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTable ret = new CFBamBuffTable();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTable.CLASS_CODE)[" + ICFBamTable.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTweak.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTweak ret = new CFBamBuffTweak();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTweak.CLASS_CODE)[" + ICFBamTweak.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTableTweak.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTableTweak ret = new CFBamBuffTableTweak();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTableTweak.CLASS_CODE)[" + ICFBamTableTweak.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamSchemaTweak.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamSchemaTweak ret = new CFBamBuffSchemaTweak();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamSchemaTweak.CLASS_CODE)[" + ICFBamSchemaTweak.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamIndexTweak.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamIndexTweak ret = new CFBamBuffIndexTweak();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamIndexTweak.CLASS_CODE)[" + ICFBamIndexTweak.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamValue.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamValue ret = new CFBamBuffValue();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamValue.CLASS_CODE)[" + ICFBamValue.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamAtom.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamAtom ret = new CFBamBuffAtom();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamAtom.CLASS_CODE)[" + ICFBamAtom.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamBlobDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamBlobDef ret = new CFBamBuffBlobDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamBlobDef.CLASS_CODE)[" + ICFBamBlobDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamBlobType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamBlobType ret = new CFBamBuffBlobType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamBlobType.CLASS_CODE)[" + ICFBamBlobType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamBoolDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamBoolDef ret = new CFBamBuffBoolDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamBoolDef.CLASS_CODE)[" + ICFBamBoolDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamBoolType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamBoolType ret = new CFBamBuffBoolType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamBoolType.CLASS_CODE)[" + ICFBamBoolType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamChain.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamChain ret = new CFBamBuffChain();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamChain.CLASS_CODE)[" + ICFBamChain.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearDep.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamClearDep ret = new CFBamBuffClearDep();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearDep.CLASS_CODE)[" + ICFBamClearDep.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearSubDep1.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamClearSubDep1 ret = new CFBamBuffClearSubDep1();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearSubDep1.CLASS_CODE)[" + ICFBamClearSubDep1.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearSubDep2.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamClearSubDep2 ret = new CFBamBuffClearSubDep2();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearSubDep2.CLASS_CODE)[" + ICFBamClearSubDep2.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearSubDep3.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamClearSubDep3 ret = new CFBamBuffClearSubDep3();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearSubDep3.CLASS_CODE)[" + ICFBamClearSubDep3.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearTopDep.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamClearTopDep ret = new CFBamBuffClearTopDep();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamClearTopDep.CLASS_CODE)[" + ICFBamClearTopDep.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDateDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDateDef ret = new CFBamBuffDateDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDateDef.CLASS_CODE)[" + ICFBamDateDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDateType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDateType ret = new CFBamBuffDateType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDateType.CLASS_CODE)[" + ICFBamDateType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelDep.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDelDep ret = new CFBamBuffDelDep();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelDep.CLASS_CODE)[" + ICFBamDelDep.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelSubDep1.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDelSubDep1 ret = new CFBamBuffDelSubDep1();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelSubDep1.CLASS_CODE)[" + ICFBamDelSubDep1.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelSubDep2.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDelSubDep2 ret = new CFBamBuffDelSubDep2();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelSubDep2.CLASS_CODE)[" + ICFBamDelSubDep2.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelSubDep3.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDelSubDep3 ret = new CFBamBuffDelSubDep3();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelSubDep3.CLASS_CODE)[" + ICFBamDelSubDep3.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelTopDep.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDelTopDep ret = new CFBamBuffDelTopDep();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDelTopDep.CLASS_CODE)[" + ICFBamDelTopDep.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDoubleDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDoubleDef ret = new CFBamBuffDoubleDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDoubleDef.CLASS_CODE)[" + ICFBamDoubleDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDoubleType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDoubleType ret = new CFBamBuffDoubleType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDoubleType.CLASS_CODE)[" + ICFBamDoubleType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamEnumTag.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamEnumTag ret = new CFBamBuffEnumTag();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamEnumTag.CLASS_CODE)[" + ICFBamEnumTag.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamFloatDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamFloatDef ret = new CFBamBuffFloatDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamFloatDef.CLASS_CODE)[" + ICFBamFloatDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamFloatType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamFloatType ret = new CFBamBuffFloatType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamFloatType.CLASS_CODE)[" + ICFBamFloatType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamIndex.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamIndex ret = new CFBamBuffIndex();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamIndex.CLASS_CODE)[" + ICFBamIndex.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamIndexCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamIndexCol ret = new CFBamBuffIndexCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamIndexCol.CLASS_CODE)[" + ICFBamIndexCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt16Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt16Def ret = new CFBamBuffInt16Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt16Def.CLASS_CODE)[" + ICFBamInt16Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt16Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt16Type ret = new CFBamBuffInt16Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt16Type.CLASS_CODE)[" + ICFBamInt16Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt32Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt32Def ret = new CFBamBuffInt32Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt32Def.CLASS_CODE)[" + ICFBamInt32Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt32Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt32Type ret = new CFBamBuffInt32Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt32Type.CLASS_CODE)[" + ICFBamInt32Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt64Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt64Def ret = new CFBamBuffInt64Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt64Def.CLASS_CODE)[" + ICFBamInt64Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt64Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt64Type ret = new CFBamBuffInt64Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt64Type.CLASS_CODE)[" + ICFBamInt64Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokenDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNmTokenDef ret = new CFBamBuffNmTokenDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokenDef.CLASS_CODE)[" + ICFBamNmTokenDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokenType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNmTokenType ret = new CFBamBuffNmTokenType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokenType.CLASS_CODE)[" + ICFBamNmTokenType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokensDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNmTokensDef ret = new CFBamBuffNmTokensDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokensDef.CLASS_CODE)[" + ICFBamNmTokensDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokensType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNmTokensType ret = new CFBamBuffNmTokensType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokensType.CLASS_CODE)[" + ICFBamNmTokensType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNumberDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNumberDef ret = new CFBamBuffNumberDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNumberDef.CLASS_CODE)[" + ICFBamNumberDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNumberType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNumberType ret = new CFBamBuffNumberType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNumberType.CLASS_CODE)[" + ICFBamNumberType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamParam.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamParam ret = new CFBamBuffParam();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamParam.CLASS_CODE)[" + ICFBamParam.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopDep.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamPopDep ret = new CFBamBuffPopDep();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopDep.CLASS_CODE)[" + ICFBamPopDep.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopSubDep1.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamPopSubDep1 ret = new CFBamBuffPopSubDep1();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopSubDep1.CLASS_CODE)[" + ICFBamPopSubDep1.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopSubDep2.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamPopSubDep2 ret = new CFBamBuffPopSubDep2();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopSubDep2.CLASS_CODE)[" + ICFBamPopSubDep2.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopSubDep3.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamPopSubDep3 ret = new CFBamBuffPopSubDep3();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopSubDep3.CLASS_CODE)[" + ICFBamPopSubDep3.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopTopDep.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamPopTopDep ret = new CFBamBuffPopTopDep();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamPopTopDep.CLASS_CODE)[" + ICFBamPopTopDep.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamRelation.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamRelation ret = new CFBamBuffRelation();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamRelation.CLASS_CODE)[" + ICFBamRelation.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamRelationCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamRelationCol ret = new CFBamBuffRelationCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamRelationCol.CLASS_CODE)[" + ICFBamRelationCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerListFunc.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamServerListFunc ret = new CFBamBuffServerListFunc();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamServerListFunc.CLASS_CODE)[" + ICFBamServerListFunc.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash128Def ret = new CFBamBuffDbKeyHash128Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Def.CLASS_CODE)[" + ICFBamDbKeyHash128Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash128Col ret = new CFBamBuffDbKeyHash128Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Col.CLASS_CODE)[" + ICFBamDbKeyHash128Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash128Type ret = new CFBamBuffDbKeyHash128Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Type.CLASS_CODE)[" + ICFBamDbKeyHash128Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash128Gen ret = new CFBamBuffDbKeyHash128Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash128Gen.CLASS_CODE)[" + ICFBamDbKeyHash128Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash160Def ret = new CFBamBuffDbKeyHash160Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Def.CLASS_CODE)[" + ICFBamDbKeyHash160Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash160Col ret = new CFBamBuffDbKeyHash160Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Col.CLASS_CODE)[" + ICFBamDbKeyHash160Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash160Type ret = new CFBamBuffDbKeyHash160Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Type.CLASS_CODE)[" + ICFBamDbKeyHash160Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash160Gen ret = new CFBamBuffDbKeyHash160Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash160Gen.CLASS_CODE)[" + ICFBamDbKeyHash160Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash224Def ret = new CFBamBuffDbKeyHash224Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Def.CLASS_CODE)[" + ICFBamDbKeyHash224Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash224Col ret = new CFBamBuffDbKeyHash224Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Col.CLASS_CODE)[" + ICFBamDbKeyHash224Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash224Type ret = new CFBamBuffDbKeyHash224Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Type.CLASS_CODE)[" + ICFBamDbKeyHash224Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash224Gen ret = new CFBamBuffDbKeyHash224Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash224Gen.CLASS_CODE)[" + ICFBamDbKeyHash224Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash256Def ret = new CFBamBuffDbKeyHash256Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Def.CLASS_CODE)[" + ICFBamDbKeyHash256Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash256Col ret = new CFBamBuffDbKeyHash256Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Col.CLASS_CODE)[" + ICFBamDbKeyHash256Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash256Type ret = new CFBamBuffDbKeyHash256Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Type.CLASS_CODE)[" + ICFBamDbKeyHash256Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash256Gen ret = new CFBamBuffDbKeyHash256Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash256Gen.CLASS_CODE)[" + ICFBamDbKeyHash256Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash384Def ret = new CFBamBuffDbKeyHash384Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Def.CLASS_CODE)[" + ICFBamDbKeyHash384Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash384Col ret = new CFBamBuffDbKeyHash384Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Col.CLASS_CODE)[" + ICFBamDbKeyHash384Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash384Type ret = new CFBamBuffDbKeyHash384Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Type.CLASS_CODE)[" + ICFBamDbKeyHash384Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash384Gen ret = new CFBamBuffDbKeyHash384Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash384Gen.CLASS_CODE)[" + ICFBamDbKeyHash384Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash512Def ret = new CFBamBuffDbKeyHash512Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Def.CLASS_CODE)[" + ICFBamDbKeyHash512Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash512Col ret = new CFBamBuffDbKeyHash512Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Col.CLASS_CODE)[" + ICFBamDbKeyHash512Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash512Type ret = new CFBamBuffDbKeyHash512Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Type.CLASS_CODE)[" + ICFBamDbKeyHash512Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDbKeyHash512Gen ret = new CFBamBuffDbKeyHash512Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDbKeyHash512Gen.CLASS_CODE)[" + ICFBamDbKeyHash512Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamStringDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamStringDef ret = new CFBamBuffStringDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamStringDef.CLASS_CODE)[" + ICFBamStringDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamStringType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamStringType ret = new CFBamBuffStringType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamStringType.CLASS_CODE)[" + ICFBamStringType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZDateDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZDateDef ret = new CFBamBuffTZDateDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZDateDef.CLASS_CODE)[" + ICFBamTZDateDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZDateType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZDateType ret = new CFBamBuffTZDateType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZDateType.CLASS_CODE)[" + ICFBamTZDateType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimeDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZTimeDef ret = new CFBamBuffTZTimeDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimeDef.CLASS_CODE)[" + ICFBamTZTimeDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimeType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZTimeType ret = new CFBamBuffTZTimeType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimeType.CLASS_CODE)[" + ICFBamTZTimeType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimestampDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZTimestampDef ret = new CFBamBuffTZTimestampDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimestampDef.CLASS_CODE)[" + ICFBamTZTimestampDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimestampType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZTimestampType ret = new CFBamBuffTZTimestampType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimestampType.CLASS_CODE)[" + ICFBamTZTimestampType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTableCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTableCol ret = new CFBamBuffTableCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTableCol.CLASS_CODE)[" + ICFBamTableCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTextDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTextDef ret = new CFBamBuffTextDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTextDef.CLASS_CODE)[" + ICFBamTextDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTextType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTextType ret = new CFBamBuffTextType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTextType.CLASS_CODE)[" + ICFBamTextType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimeDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTimeDef ret = new CFBamBuffTimeDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimeDef.CLASS_CODE)[" + ICFBamTimeDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimeType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTimeType ret = new CFBamBuffTimeType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimeType.CLASS_CODE)[" + ICFBamTimeType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimestampDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTimestampDef ret = new CFBamBuffTimestampDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimestampDef.CLASS_CODE)[" + ICFBamTimestampDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimestampType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTimestampType ret = new CFBamBuffTimestampType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimestampType.CLASS_CODE)[" + ICFBamTimestampType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTokenDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTokenDef ret = new CFBamBuffTokenDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTokenDef.CLASS_CODE)[" + ICFBamTokenDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTokenType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTokenType ret = new CFBamBuffTokenType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTokenType.CLASS_CODE)[" + ICFBamTokenType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt16Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt16Def ret = new CFBamBuffUInt16Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt16Def.CLASS_CODE)[" + ICFBamUInt16Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt16Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt16Type ret = new CFBamBuffUInt16Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt16Type.CLASS_CODE)[" + ICFBamUInt16Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt32Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt32Def ret = new CFBamBuffUInt32Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt32Def.CLASS_CODE)[" + ICFBamUInt32Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt32Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt32Type ret = new CFBamBuffUInt32Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt32Type.CLASS_CODE)[" + ICFBamUInt32Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt64Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt64Def ret = new CFBamBuffUInt64Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt64Def.CLASS_CODE)[" + ICFBamUInt64Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt64Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt64Type ret = new CFBamBuffUInt64Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt64Type.CLASS_CODE)[" + ICFBamUInt64Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuidDef ret = new CFBamBuffUuidDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidDef.CLASS_CODE)[" + ICFBamUuidDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Def.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuid6Def ret = new CFBamBuffUuid6Def();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Def.CLASS_CODE)[" + ICFBamUuid6Def.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuidType ret = new CFBamBuffUuidType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidType.CLASS_CODE)[" + ICFBamUuidType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Type.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuid6Type ret = new CFBamBuffUuid6Type();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Type.CLASS_CODE)[" + ICFBamUuid6Type.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamBlobCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamBlobCol ret = new CFBamBuffBlobCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamBlobCol.CLASS_CODE)[" + ICFBamBlobCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamBoolCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamBoolCol ret = new CFBamBuffBoolCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamBoolCol.CLASS_CODE)[" + ICFBamBoolCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDateCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDateCol ret = new CFBamBuffDateCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDateCol.CLASS_CODE)[" + ICFBamDateCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamDoubleCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamDoubleCol ret = new CFBamBuffDoubleCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamDoubleCol.CLASS_CODE)[" + ICFBamDoubleCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamEnumDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamEnumDef ret = new CFBamBuffEnumDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamEnumDef.CLASS_CODE)[" + ICFBamEnumDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamEnumType.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamEnumType ret = new CFBamBuffEnumType();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamEnumType.CLASS_CODE)[" + ICFBamEnumType.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamFloatCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamFloatCol ret = new CFBamBuffFloatCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamFloatCol.CLASS_CODE)[" + ICFBamFloatCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamId16Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamId16Gen ret = new CFBamBuffId16Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamId16Gen.CLASS_CODE)[" + ICFBamId16Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamId32Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamId32Gen ret = new CFBamBuffId32Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamId32Gen.CLASS_CODE)[" + ICFBamId32Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamId64Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamId64Gen ret = new CFBamBuffId64Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamId64Gen.CLASS_CODE)[" + ICFBamId64Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt16Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt16Col ret = new CFBamBuffInt16Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt16Col.CLASS_CODE)[" + ICFBamInt16Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt32Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt32Col ret = new CFBamBuffInt32Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt32Col.CLASS_CODE)[" + ICFBamInt32Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt64Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamInt64Col ret = new CFBamBuffInt64Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamInt64Col.CLASS_CODE)[" + ICFBamInt64Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokenCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNmTokenCol ret = new CFBamBuffNmTokenCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokenCol.CLASS_CODE)[" + ICFBamNmTokenCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokensCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNmTokensCol ret = new CFBamBuffNmTokensCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNmTokensCol.CLASS_CODE)[" + ICFBamNmTokensCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamNumberCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamNumberCol ret = new CFBamBuffNumberCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamNumberCol.CLASS_CODE)[" + ICFBamNumberCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamStringCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamStringCol ret = new CFBamBuffStringCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamStringCol.CLASS_CODE)[" + ICFBamStringCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZDateCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZDateCol ret = new CFBamBuffTZDateCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZDateCol.CLASS_CODE)[" + ICFBamTZDateCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimeCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZTimeCol ret = new CFBamBuffTZTimeCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimeCol.CLASS_CODE)[" + ICFBamTZTimeCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimestampCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTZTimestampCol ret = new CFBamBuffTZTimestampCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTZTimestampCol.CLASS_CODE)[" + ICFBamTZTimestampCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTextCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTextCol ret = new CFBamBuffTextCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTextCol.CLASS_CODE)[" + ICFBamTextCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimeCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTimeCol ret = new CFBamBuffTimeCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimeCol.CLASS_CODE)[" + ICFBamTimeCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimestampCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTimestampCol ret = new CFBamBuffTimestampCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTimestampCol.CLASS_CODE)[" + ICFBamTimestampCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamTokenCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamTokenCol ret = new CFBamBuffTokenCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamTokenCol.CLASS_CODE)[" + ICFBamTokenCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt16Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt16Col ret = new CFBamBuffUInt16Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt16Col.CLASS_CODE)[" + ICFBamUInt16Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt32Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt32Col ret = new CFBamBuffUInt32Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt32Col.CLASS_CODE)[" + ICFBamUInt32Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt64Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUInt64Col ret = new CFBamBuffUInt64Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUInt64Col.CLASS_CODE)[" + ICFBamUInt64Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidCol.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuidCol ret = new CFBamBuffUuidCol();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidCol.CLASS_CODE)[" + ICFBamUuidCol.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Col.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuid6Col ret = new CFBamBuffUuid6Col();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Col.CLASS_CODE)[" + ICFBamUuid6Col.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidGen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuidGen ret = new CFBamBuffUuidGen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuidGen.CLASS_CODE)[" + ICFBamUuidGen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Gen.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamUuid6Gen ret = new CFBamBuffUuid6Gen();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamUuid6Gen.CLASS_CODE)[" + ICFBamUuid6Gen.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamRoleDef.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamRoleDef ret = new CFBamBuffRoleDef();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamRoleDef.CLASS_CODE)[" + ICFBamRoleDef.CLASS_CODE + "]");
		}
	
		entry = ICFBamSchema.getClassMapByBackingClassCode(ICFBamSchemaRole.CLASS_CODE);
		if (entry != null) {
			entry.setBackingRecConstructor( new BackingRecConstructor() {
				@Override
				public Object instantiate() {
					ICFBamSchemaRole ret = new CFBamBuffSchemaRole();
					return(ret);
				}
			});
		}
		else {
			throw new CFLibNullArgumentException(CFBamBuffSchema.class, "wireRecConstructors", 0, "ICFBamSchema.getClassMapByBackingClassCode(ICFBamSchemaRole.CLASS_CODE)[" + ICFBamSchemaRole.CLASS_CODE + "]");
		}
	
	}

	@Override
	public void wireTableTableInstances() {
		throw new CFLibMustOverrideException(getClass(), "wireTableTableInstances");
	}

	@Override		
	public ICFSecSchema getCFSecSchema() {
		return( ICFSecSchema.getBackingCFSec() );
	}

	@Override
	public void setCFSecSchema(ICFSecSchema schema) {
		ICFSecSchema.setBackingCFSec(schema);
		schema.wireRecConstructors();
	}

	@Override		
	public ICFIntSchema getCFIntSchema() {
		return( ICFIntSchema.getBackingCFInt() );
	}

	@Override
	public void setCFIntSchema(ICFIntSchema schema) {
		ICFIntSchema.setBackingCFInt(schema);
		schema.wireRecConstructors();
	}

	@Override		
	public ICFBamSchema getCFBamSchema() {
		return( ICFBamSchema.getBackingCFBam() );
	}

	@Override
	public void setCFBamSchema(ICFBamSchema schema) {
		ICFBamSchema.setBackingCFBam(schema);
		schema.wireRecConstructors();
	}

	public static CFBamBuffHooksSchema getBuffHooksSchema() {
		return( cfbamBuffHooksSchema );
	}

	public static void setBuffHooksSchema(CFBamBuffHooksSchema buffHooksSchema) {
		cfbamBuffHooksSchema = buffHooksSchema;
	}

	@Override
	public ICFSecFactory getCFSecFactory() {
		ICFSecSchema sch = ICFSecSchema.getBackingCFSec();
		if (sch == null) {
			return null;
		}
		else {
			return(sch.getCFSecFactory());
		}
	}
	
	@Override
	public CFSecBuffFactoryService getCFSecBuffFactory() {
		ICFSecSchema sch = ICFSecSchema.getBackingCFSec();
		if (sch == null) {
			return null;
		}
		else {
			return((CFSecBuffFactoryService)(sch.getCFSecBuffFactory()));
		}
	}

	@Override
	public ICFIntFactory getCFIntFactory() {
		ICFIntSchema sch = ICFIntSchema.getBackingCFInt();
		if (sch == null) {
			return null;
		}
		else {
			return(sch.getCFIntFactory());
		}
	}
	
	@Override
	public CFIntBuffFactoryService getCFIntBuffFactory() {
		ICFIntSchema sch = ICFIntSchema.getBackingCFInt();
		if (sch == null) {
			return null;
		}
		else {
			return((CFIntBuffFactoryService)(sch.getCFIntBuffFactory()));
		}
	}

	@Override
	public ICFBamFactory getCFBamFactory() {
		return(CFBamBuffSchema.getBuffHooksSchema().getFactoryService());
	}

	@Override
	public CFBamBuffFactoryService getCFBamBuffFactory() {
		return((CFBamBuffFactoryService)(CFBamBuffSchema.getBuffHooksSchema().getFactoryService()));
	}

	public CFBamBuffSchema() {

	tableAtom = null; // new CFBamBuffAtomTable();
	tableBlobCol = null; // new CFBamBuffBlobColTable();
	tableBlobDef = null; // new CFBamBuffBlobDefTable();
	tableBlobType = null; // new CFBamBuffBlobTypeTable();
	tableBoolCol = null; // new CFBamBuffBoolColTable();
	tableBoolDef = null; // new CFBamBuffBoolDefTable();
	tableBoolType = null; // new CFBamBuffBoolTypeTable();
	tableChain = null; // new CFBamBuffChainTable();
	tableClearDep = null; // new CFBamBuffClearDepTable();
	tableClearSubDep1 = null; // new CFBamBuffClearSubDep1Table();
	tableClearSubDep2 = null; // new CFBamBuffClearSubDep2Table();
	tableClearSubDep3 = null; // new CFBamBuffClearSubDep3Table();
	tableClearTopDep = null; // new CFBamBuffClearTopDepTable();
	tableDateCol = null; // new CFBamBuffDateColTable();
	tableDateDef = null; // new CFBamBuffDateDefTable();
	tableDateType = null; // new CFBamBuffDateTypeTable();
	tableDbKeyHash128Col = null; // new CFBamBuffDbKeyHash128ColTable();
	tableDbKeyHash128Def = null; // new CFBamBuffDbKeyHash128DefTable();
	tableDbKeyHash128Gen = null; // new CFBamBuffDbKeyHash128GenTable();
	tableDbKeyHash128Type = null; // new CFBamBuffDbKeyHash128TypeTable();
	tableDbKeyHash160Col = null; // new CFBamBuffDbKeyHash160ColTable();
	tableDbKeyHash160Def = null; // new CFBamBuffDbKeyHash160DefTable();
	tableDbKeyHash160Gen = null; // new CFBamBuffDbKeyHash160GenTable();
	tableDbKeyHash160Type = null; // new CFBamBuffDbKeyHash160TypeTable();
	tableDbKeyHash224Col = null; // new CFBamBuffDbKeyHash224ColTable();
	tableDbKeyHash224Def = null; // new CFBamBuffDbKeyHash224DefTable();
	tableDbKeyHash224Gen = null; // new CFBamBuffDbKeyHash224GenTable();
	tableDbKeyHash224Type = null; // new CFBamBuffDbKeyHash224TypeTable();
	tableDbKeyHash256Col = null; // new CFBamBuffDbKeyHash256ColTable();
	tableDbKeyHash256Def = null; // new CFBamBuffDbKeyHash256DefTable();
	tableDbKeyHash256Gen = null; // new CFBamBuffDbKeyHash256GenTable();
	tableDbKeyHash256Type = null; // new CFBamBuffDbKeyHash256TypeTable();
	tableDbKeyHash384Col = null; // new CFBamBuffDbKeyHash384ColTable();
	tableDbKeyHash384Def = null; // new CFBamBuffDbKeyHash384DefTable();
	tableDbKeyHash384Gen = null; // new CFBamBuffDbKeyHash384GenTable();
	tableDbKeyHash384Type = null; // new CFBamBuffDbKeyHash384TypeTable();
	tableDbKeyHash512Col = null; // new CFBamBuffDbKeyHash512ColTable();
	tableDbKeyHash512Def = null; // new CFBamBuffDbKeyHash512DefTable();
	tableDbKeyHash512Gen = null; // new CFBamBuffDbKeyHash512GenTable();
	tableDbKeyHash512Type = null; // new CFBamBuffDbKeyHash512TypeTable();
	tableDelDep = null; // new CFBamBuffDelDepTable();
	tableDelSubDep1 = null; // new CFBamBuffDelSubDep1Table();
	tableDelSubDep2 = null; // new CFBamBuffDelSubDep2Table();
	tableDelSubDep3 = null; // new CFBamBuffDelSubDep3Table();
	tableDelTopDep = null; // new CFBamBuffDelTopDepTable();
	tableDoubleCol = null; // new CFBamBuffDoubleColTable();
	tableDoubleDef = null; // new CFBamBuffDoubleDefTable();
	tableDoubleType = null; // new CFBamBuffDoubleTypeTable();
	tableEnumDef = null; // new CFBamBuffEnumDefTable();
	tableEnumTag = null; // new CFBamBuffEnumTagTable();
	tableEnumType = null; // new CFBamBuffEnumTypeTable();
	tableFloatCol = null; // new CFBamBuffFloatColTable();
	tableFloatDef = null; // new CFBamBuffFloatDefTable();
	tableFloatType = null; // new CFBamBuffFloatTypeTable();
	tableId16Gen = null; // new CFBamBuffId16GenTable();
	tableId32Gen = null; // new CFBamBuffId32GenTable();
	tableId64Gen = null; // new CFBamBuffId64GenTable();
	tableIndex = null; // new CFBamBuffIndexTable();
	tableIndexCol = null; // new CFBamBuffIndexColTable();
	tableIndexTweak = null; // new CFBamBuffIndexTweakTable();
	tableInt16Col = null; // new CFBamBuffInt16ColTable();
	tableInt16Def = null; // new CFBamBuffInt16DefTable();
	tableInt16Type = null; // new CFBamBuffInt16TypeTable();
	tableInt32Col = null; // new CFBamBuffInt32ColTable();
	tableInt32Def = null; // new CFBamBuffInt32DefTable();
	tableInt32Type = null; // new CFBamBuffInt32TypeTable();
	tableInt64Col = null; // new CFBamBuffInt64ColTable();
	tableInt64Def = null; // new CFBamBuffInt64DefTable();
	tableInt64Type = null; // new CFBamBuffInt64TypeTable();
	tableNmTokenCol = null; // new CFBamBuffNmTokenColTable();
	tableNmTokenDef = null; // new CFBamBuffNmTokenDefTable();
	tableNmTokenType = null; // new CFBamBuffNmTokenTypeTable();
	tableNmTokensCol = null; // new CFBamBuffNmTokensColTable();
	tableNmTokensDef = null; // new CFBamBuffNmTokensDefTable();
	tableNmTokensType = null; // new CFBamBuffNmTokensTypeTable();
	tableNumberCol = null; // new CFBamBuffNumberColTable();
	tableNumberDef = null; // new CFBamBuffNumberDefTable();
	tableNumberType = null; // new CFBamBuffNumberTypeTable();
	tableParam = null; // new CFBamBuffParamTable();
	tablePopDep = null; // new CFBamBuffPopDepTable();
	tablePopSubDep1 = null; // new CFBamBuffPopSubDep1Table();
	tablePopSubDep2 = null; // new CFBamBuffPopSubDep2Table();
	tablePopSubDep3 = null; // new CFBamBuffPopSubDep3Table();
	tablePopTopDep = null; // new CFBamBuffPopTopDepTable();
	tableRelation = null; // new CFBamBuffRelationTable();
	tableRelationCol = null; // new CFBamBuffRelationColTable();
	tableRoleDef = null; // new CFBamBuffRoleDefTable();
	tableSchemaDef = null; // new CFBamBuffSchemaDefTable();
	tableSchemaRef = null; // new CFBamBuffSchemaRefTable();
	tableSchemaRole = null; // new CFBamBuffSchemaRoleTable();
	tableSchemaTweak = null; // new CFBamBuffSchemaTweakTable();
	tableScope = null; // new CFBamBuffScopeTable();
	tableServerListFunc = null; // new CFBamBuffServerListFuncTable();
	tableServerMethod = null; // new CFBamBuffServerMethodTable();
	tableServerObjFunc = null; // new CFBamBuffServerObjFuncTable();
	tableServerProc = null; // new CFBamBuffServerProcTable();
	tableStringCol = null; // new CFBamBuffStringColTable();
	tableStringDef = null; // new CFBamBuffStringDefTable();
	tableStringType = null; // new CFBamBuffStringTypeTable();
	tableTZDateCol = null; // new CFBamBuffTZDateColTable();
	tableTZDateDef = null; // new CFBamBuffTZDateDefTable();
	tableTZDateType = null; // new CFBamBuffTZDateTypeTable();
	tableTZTimeCol = null; // new CFBamBuffTZTimeColTable();
	tableTZTimeDef = null; // new CFBamBuffTZTimeDefTable();
	tableTZTimeType = null; // new CFBamBuffTZTimeTypeTable();
	tableTZTimestampCol = null; // new CFBamBuffTZTimestampColTable();
	tableTZTimestampDef = null; // new CFBamBuffTZTimestampDefTable();
	tableTZTimestampType = null; // new CFBamBuffTZTimestampTypeTable();
	tableTable = null; // new CFBamBuffTableTable();
	tableTableCol = null; // new CFBamBuffTableColTable();
	tableTableTweak = null; // new CFBamBuffTableTweakTable();
	tableTextCol = null; // new CFBamBuffTextColTable();
	tableTextDef = null; // new CFBamBuffTextDefTable();
	tableTextType = null; // new CFBamBuffTextTypeTable();
	tableTimeCol = null; // new CFBamBuffTimeColTable();
	tableTimeDef = null; // new CFBamBuffTimeDefTable();
	tableTimeType = null; // new CFBamBuffTimeTypeTable();
	tableTimestampCol = null; // new CFBamBuffTimestampColTable();
	tableTimestampDef = null; // new CFBamBuffTimestampDefTable();
	tableTimestampType = null; // new CFBamBuffTimestampTypeTable();
	tableTokenCol = null; // new CFBamBuffTokenColTable();
	tableTokenDef = null; // new CFBamBuffTokenDefTable();
	tableTokenType = null; // new CFBamBuffTokenTypeTable();
	tableTweak = null; // new CFBamBuffTweakTable();
	tableUInt16Col = null; // new CFBamBuffUInt16ColTable();
	tableUInt16Def = null; // new CFBamBuffUInt16DefTable();
	tableUInt16Type = null; // new CFBamBuffUInt16TypeTable();
	tableUInt32Col = null; // new CFBamBuffUInt32ColTable();
	tableUInt32Def = null; // new CFBamBuffUInt32DefTable();
	tableUInt32Type = null; // new CFBamBuffUInt32TypeTable();
	tableUInt64Col = null; // new CFBamBuffUInt64ColTable();
	tableUInt64Def = null; // new CFBamBuffUInt64DefTable();
	tableUInt64Type = null; // new CFBamBuffUInt64TypeTable();
	tableUuid6Col = null; // new CFBamBuffUuid6ColTable();
	tableUuid6Def = null; // new CFBamBuffUuid6DefTable();
	tableUuid6Gen = null; // new CFBamBuffUuid6GenTable();
	tableUuid6Type = null; // new CFBamBuffUuid6TypeTable();
	tableUuidCol = null; // new CFBamBuffUuidColTable();
	tableUuidDef = null; // new CFBamBuffUuidDefTable();
	tableUuidGen = null; // new CFBamBuffUuidGenTable();
	tableUuidType = null; // new CFBamBuffUuidTypeTable();
	tableValue = null; // new CFBamBuffValueTable();
	}

	public ICFBamSchema newSchema() {
		throw new CFLibMustOverrideException( getClass(), "newSchema" );
	}

	/**
	 *	Get the next ISOCcyIdGen identifier.
	 *
	 *	@return	The next ISOCcyIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public short nextISOCcyIdGen() {
		throw new CFLibMustOverrideException(getClass(), "nextISOCcyIdGen");
	}

	/**
	 *	Get the next ISOCtryIdGen identifier.
	 *
	 *	@return	The next ISOCtryIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public short nextISOCtryIdGen() {
		throw new CFLibMustOverrideException(getClass(), "nextISOCtryIdGen");
	}

	/**
	 *	Get the next ISOLangIdGen identifier.
	 *
	 *	@return	The next ISOLangIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public short nextISOLangIdGen() {
		throw new CFLibMustOverrideException(getClass(), "nextISOLangIdGen");
	}

	/**
	 *	Get the next ISOTZoneIdGen identifier.
	 *
	 *	@return	The next ISOTZoneIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public short nextISOTZoneIdGen() {
		throw new CFLibMustOverrideException(getClass(), "nextISOTZoneIdGen");
	}

	/**
	 *	Get the next TableInfoIdGen identifier.
	 *
	 *	@return	The next TableInfoIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public int nextTableInfoIdGen() {
		throw new CFLibMustOverrideException(getClass(), "nextTableInfoIdGen");
	}

	/**
	 *	Get the next MimeTypeIdGen identifier.
	 *
	 *	@return	The next MimeTypeIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public int nextMimeTypeIdGen() {
		throw new CFLibMustOverrideException(getClass(), "nextMimeTypeIdGen");
	}

	/**
	 *	Get the next URLProtocolIdGen identifier.
	 *
	 *	@return	The next URLProtocolIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public int nextURLProtocolIdGen() {
		throw new CFLibMustOverrideException(getClass(), "nextURLProtocolIdGen");
	}

	/**
	 *	Get the next ClusterIdGen identifier.
	 *
	 *	@return	The next ClusterIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextClusterIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next SecSessionIdGen identifier.
	 *
	 *	@return	The next SecSessionIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecSessionIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next SecUserIdGen identifier.
	 *
	 *	@return	The next SecUserIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecUserIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next TenantIdGen identifier.
	 *
	 *	@return	The next TenantIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextTenantIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next SecSysGrpIdGen identifier.
	 *
	 *	@return	The next SecSysGrpIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecSysGrpIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next SecClusGrpIdGen identifier.
	 *
	 *	@return	The next SecClusGrpIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecClusGrpIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next SecClusRoleIdGen identifier.
	 *
	 *	@return	The next SecClusRoleIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecClusRoleIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next SecTentGrpIdGen identifier.
	 *
	 *	@return	The next SecTentGrpIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecTentGrpIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next SecTentRoleIdGen identifier.
	 *
	 *	@return	The next SecTentRoleIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecTentRoleIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next MajorVersionIdGen identifier.
	 *
	 *	@return	The next MajorVersionIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextMajorVersionIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next MinorVersionIdGen identifier.
	 *
	 *	@return	The next MinorVersionIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextMinorVersionIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next SubProjectIdGen identifier.
	 *
	 *	@return	The next SubProjectIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSubProjectIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next TldIdGen identifier.
	 *
	 *	@return	The next TldIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextTldIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next TopDomainIdGen identifier.
	 *
	 *	@return	The next TopDomainIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextTopDomainIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next TopProjectIdGen identifier.
	 *
	 *	@return	The next TopProjectIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextTopProjectIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next LicenseIdGen identifier.
	 *
	 *	@return	The next LicenseIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextLicenseIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next ChainIdGen identifier.
	 *
	 *	@return	The next ChainIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextChainIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next EnumTagIdGen identifier.
	 *
	 *	@return	The next EnumTagIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextEnumTagIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next IndexColIdGen identifier.
	 *
	 *	@return	The next IndexColIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextIndexColIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next ParamIdGen identifier.
	 *
	 *	@return	The next ParamIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextParamIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next RelationColIdGen identifier.
	 *
	 *	@return	The next RelationColIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextRelationColIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next TweakIdGen identifier.
	 *
	 *	@return	The next TweakIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextTweakIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next ScopeIdGen identifier.
	 *
	 *	@return	The next ScopeIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextScopeIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next ValueIdGen identifier.
	 *
	 *	@return	The next ValueIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextValueIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	/**
	 *	Get the next RoleIdGen identifier.
	 *
	 *	@return	The next RoleIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextRoleIdGen() {
		return( new CFLibDbKeyHash256(0) );
	}

	public ICFBamAtomTable getTableAtom() {
		return( tableAtom );
	}

	public void setTableAtom( ICFBamAtomTable value ) {
		tableAtom = value;
	}

	public ICFBamBlobColTable getTableBlobCol() {
		return( tableBlobCol );
	}

	public void setTableBlobCol( ICFBamBlobColTable value ) {
		tableBlobCol = value;
	}

	public ICFBamBlobDefTable getTableBlobDef() {
		return( tableBlobDef );
	}

	public void setTableBlobDef( ICFBamBlobDefTable value ) {
		tableBlobDef = value;
	}

	public ICFBamBlobTypeTable getTableBlobType() {
		return( tableBlobType );
	}

	public void setTableBlobType( ICFBamBlobTypeTable value ) {
		tableBlobType = value;
	}

	public ICFBamBoolColTable getTableBoolCol() {
		return( tableBoolCol );
	}

	public void setTableBoolCol( ICFBamBoolColTable value ) {
		tableBoolCol = value;
	}

	public ICFBamBoolDefTable getTableBoolDef() {
		return( tableBoolDef );
	}

	public void setTableBoolDef( ICFBamBoolDefTable value ) {
		tableBoolDef = value;
	}

	public ICFBamBoolTypeTable getTableBoolType() {
		return( tableBoolType );
	}

	public void setTableBoolType( ICFBamBoolTypeTable value ) {
		tableBoolType = value;
	}

	public ICFBamChainTable getTableChain() {
		return( tableChain );
	}

	public void setTableChain( ICFBamChainTable value ) {
		tableChain = value;
	}

	public ICFBamClearDepTable getTableClearDep() {
		return( tableClearDep );
	}

	public void setTableClearDep( ICFBamClearDepTable value ) {
		tableClearDep = value;
	}

	public ICFBamClearSubDep1Table getTableClearSubDep1() {
		return( tableClearSubDep1 );
	}

	public void setTableClearSubDep1( ICFBamClearSubDep1Table value ) {
		tableClearSubDep1 = value;
	}

	public ICFBamClearSubDep2Table getTableClearSubDep2() {
		return( tableClearSubDep2 );
	}

	public void setTableClearSubDep2( ICFBamClearSubDep2Table value ) {
		tableClearSubDep2 = value;
	}

	public ICFBamClearSubDep3Table getTableClearSubDep3() {
		return( tableClearSubDep3 );
	}

	public void setTableClearSubDep3( ICFBamClearSubDep3Table value ) {
		tableClearSubDep3 = value;
	}

	public ICFBamClearTopDepTable getTableClearTopDep() {
		return( tableClearTopDep );
	}

	public void setTableClearTopDep( ICFBamClearTopDepTable value ) {
		tableClearTopDep = value;
	}

	public ICFSecClusterTable getTableCluster() {
		return( tableCluster );
	}

	public void setTableCluster( ICFSecClusterTable value ) {
		tableCluster = value;
	}

	public ICFBamDateColTable getTableDateCol() {
		return( tableDateCol );
	}

	public void setTableDateCol( ICFBamDateColTable value ) {
		tableDateCol = value;
	}

	public ICFBamDateDefTable getTableDateDef() {
		return( tableDateDef );
	}

	public void setTableDateDef( ICFBamDateDefTable value ) {
		tableDateDef = value;
	}

	public ICFBamDateTypeTable getTableDateType() {
		return( tableDateType );
	}

	public void setTableDateType( ICFBamDateTypeTable value ) {
		tableDateType = value;
	}

	public ICFBamDbKeyHash128ColTable getTableDbKeyHash128Col() {
		return( tableDbKeyHash128Col );
	}

	public void setTableDbKeyHash128Col( ICFBamDbKeyHash128ColTable value ) {
		tableDbKeyHash128Col = value;
	}

	public ICFBamDbKeyHash128DefTable getTableDbKeyHash128Def() {
		return( tableDbKeyHash128Def );
	}

	public void setTableDbKeyHash128Def( ICFBamDbKeyHash128DefTable value ) {
		tableDbKeyHash128Def = value;
	}

	public ICFBamDbKeyHash128GenTable getTableDbKeyHash128Gen() {
		return( tableDbKeyHash128Gen );
	}

	public void setTableDbKeyHash128Gen( ICFBamDbKeyHash128GenTable value ) {
		tableDbKeyHash128Gen = value;
	}

	public ICFBamDbKeyHash128TypeTable getTableDbKeyHash128Type() {
		return( tableDbKeyHash128Type );
	}

	public void setTableDbKeyHash128Type( ICFBamDbKeyHash128TypeTable value ) {
		tableDbKeyHash128Type = value;
	}

	public ICFBamDbKeyHash160ColTable getTableDbKeyHash160Col() {
		return( tableDbKeyHash160Col );
	}

	public void setTableDbKeyHash160Col( ICFBamDbKeyHash160ColTable value ) {
		tableDbKeyHash160Col = value;
	}

	public ICFBamDbKeyHash160DefTable getTableDbKeyHash160Def() {
		return( tableDbKeyHash160Def );
	}

	public void setTableDbKeyHash160Def( ICFBamDbKeyHash160DefTable value ) {
		tableDbKeyHash160Def = value;
	}

	public ICFBamDbKeyHash160GenTable getTableDbKeyHash160Gen() {
		return( tableDbKeyHash160Gen );
	}

	public void setTableDbKeyHash160Gen( ICFBamDbKeyHash160GenTable value ) {
		tableDbKeyHash160Gen = value;
	}

	public ICFBamDbKeyHash160TypeTable getTableDbKeyHash160Type() {
		return( tableDbKeyHash160Type );
	}

	public void setTableDbKeyHash160Type( ICFBamDbKeyHash160TypeTable value ) {
		tableDbKeyHash160Type = value;
	}

	public ICFBamDbKeyHash224ColTable getTableDbKeyHash224Col() {
		return( tableDbKeyHash224Col );
	}

	public void setTableDbKeyHash224Col( ICFBamDbKeyHash224ColTable value ) {
		tableDbKeyHash224Col = value;
	}

	public ICFBamDbKeyHash224DefTable getTableDbKeyHash224Def() {
		return( tableDbKeyHash224Def );
	}

	public void setTableDbKeyHash224Def( ICFBamDbKeyHash224DefTable value ) {
		tableDbKeyHash224Def = value;
	}

	public ICFBamDbKeyHash224GenTable getTableDbKeyHash224Gen() {
		return( tableDbKeyHash224Gen );
	}

	public void setTableDbKeyHash224Gen( ICFBamDbKeyHash224GenTable value ) {
		tableDbKeyHash224Gen = value;
	}

	public ICFBamDbKeyHash224TypeTable getTableDbKeyHash224Type() {
		return( tableDbKeyHash224Type );
	}

	public void setTableDbKeyHash224Type( ICFBamDbKeyHash224TypeTable value ) {
		tableDbKeyHash224Type = value;
	}

	public ICFBamDbKeyHash256ColTable getTableDbKeyHash256Col() {
		return( tableDbKeyHash256Col );
	}

	public void setTableDbKeyHash256Col( ICFBamDbKeyHash256ColTable value ) {
		tableDbKeyHash256Col = value;
	}

	public ICFBamDbKeyHash256DefTable getTableDbKeyHash256Def() {
		return( tableDbKeyHash256Def );
	}

	public void setTableDbKeyHash256Def( ICFBamDbKeyHash256DefTable value ) {
		tableDbKeyHash256Def = value;
	}

	public ICFBamDbKeyHash256GenTable getTableDbKeyHash256Gen() {
		return( tableDbKeyHash256Gen );
	}

	public void setTableDbKeyHash256Gen( ICFBamDbKeyHash256GenTable value ) {
		tableDbKeyHash256Gen = value;
	}

	public ICFBamDbKeyHash256TypeTable getTableDbKeyHash256Type() {
		return( tableDbKeyHash256Type );
	}

	public void setTableDbKeyHash256Type( ICFBamDbKeyHash256TypeTable value ) {
		tableDbKeyHash256Type = value;
	}

	public ICFBamDbKeyHash384ColTable getTableDbKeyHash384Col() {
		return( tableDbKeyHash384Col );
	}

	public void setTableDbKeyHash384Col( ICFBamDbKeyHash384ColTable value ) {
		tableDbKeyHash384Col = value;
	}

	public ICFBamDbKeyHash384DefTable getTableDbKeyHash384Def() {
		return( tableDbKeyHash384Def );
	}

	public void setTableDbKeyHash384Def( ICFBamDbKeyHash384DefTable value ) {
		tableDbKeyHash384Def = value;
	}

	public ICFBamDbKeyHash384GenTable getTableDbKeyHash384Gen() {
		return( tableDbKeyHash384Gen );
	}

	public void setTableDbKeyHash384Gen( ICFBamDbKeyHash384GenTable value ) {
		tableDbKeyHash384Gen = value;
	}

	public ICFBamDbKeyHash384TypeTable getTableDbKeyHash384Type() {
		return( tableDbKeyHash384Type );
	}

	public void setTableDbKeyHash384Type( ICFBamDbKeyHash384TypeTable value ) {
		tableDbKeyHash384Type = value;
	}

	public ICFBamDbKeyHash512ColTable getTableDbKeyHash512Col() {
		return( tableDbKeyHash512Col );
	}

	public void setTableDbKeyHash512Col( ICFBamDbKeyHash512ColTable value ) {
		tableDbKeyHash512Col = value;
	}

	public ICFBamDbKeyHash512DefTable getTableDbKeyHash512Def() {
		return( tableDbKeyHash512Def );
	}

	public void setTableDbKeyHash512Def( ICFBamDbKeyHash512DefTable value ) {
		tableDbKeyHash512Def = value;
	}

	public ICFBamDbKeyHash512GenTable getTableDbKeyHash512Gen() {
		return( tableDbKeyHash512Gen );
	}

	public void setTableDbKeyHash512Gen( ICFBamDbKeyHash512GenTable value ) {
		tableDbKeyHash512Gen = value;
	}

	public ICFBamDbKeyHash512TypeTable getTableDbKeyHash512Type() {
		return( tableDbKeyHash512Type );
	}

	public void setTableDbKeyHash512Type( ICFBamDbKeyHash512TypeTable value ) {
		tableDbKeyHash512Type = value;
	}

	public ICFBamDelDepTable getTableDelDep() {
		return( tableDelDep );
	}

	public void setTableDelDep( ICFBamDelDepTable value ) {
		tableDelDep = value;
	}

	public ICFBamDelSubDep1Table getTableDelSubDep1() {
		return( tableDelSubDep1 );
	}

	public void setTableDelSubDep1( ICFBamDelSubDep1Table value ) {
		tableDelSubDep1 = value;
	}

	public ICFBamDelSubDep2Table getTableDelSubDep2() {
		return( tableDelSubDep2 );
	}

	public void setTableDelSubDep2( ICFBamDelSubDep2Table value ) {
		tableDelSubDep2 = value;
	}

	public ICFBamDelSubDep3Table getTableDelSubDep3() {
		return( tableDelSubDep3 );
	}

	public void setTableDelSubDep3( ICFBamDelSubDep3Table value ) {
		tableDelSubDep3 = value;
	}

	public ICFBamDelTopDepTable getTableDelTopDep() {
		return( tableDelTopDep );
	}

	public void setTableDelTopDep( ICFBamDelTopDepTable value ) {
		tableDelTopDep = value;
	}

	public ICFBamDoubleColTable getTableDoubleCol() {
		return( tableDoubleCol );
	}

	public void setTableDoubleCol( ICFBamDoubleColTable value ) {
		tableDoubleCol = value;
	}

	public ICFBamDoubleDefTable getTableDoubleDef() {
		return( tableDoubleDef );
	}

	public void setTableDoubleDef( ICFBamDoubleDefTable value ) {
		tableDoubleDef = value;
	}

	public ICFBamDoubleTypeTable getTableDoubleType() {
		return( tableDoubleType );
	}

	public void setTableDoubleType( ICFBamDoubleTypeTable value ) {
		tableDoubleType = value;
	}

	public ICFBamEnumDefTable getTableEnumDef() {
		return( tableEnumDef );
	}

	public void setTableEnumDef( ICFBamEnumDefTable value ) {
		tableEnumDef = value;
	}

	public ICFBamEnumTagTable getTableEnumTag() {
		return( tableEnumTag );
	}

	public void setTableEnumTag( ICFBamEnumTagTable value ) {
		tableEnumTag = value;
	}

	public ICFBamEnumTypeTable getTableEnumType() {
		return( tableEnumType );
	}

	public void setTableEnumType( ICFBamEnumTypeTable value ) {
		tableEnumType = value;
	}

	public ICFBamFloatColTable getTableFloatCol() {
		return( tableFloatCol );
	}

	public void setTableFloatCol( ICFBamFloatColTable value ) {
		tableFloatCol = value;
	}

	public ICFBamFloatDefTable getTableFloatDef() {
		return( tableFloatDef );
	}

	public void setTableFloatDef( ICFBamFloatDefTable value ) {
		tableFloatDef = value;
	}

	public ICFBamFloatTypeTable getTableFloatType() {
		return( tableFloatType );
	}

	public void setTableFloatType( ICFBamFloatTypeTable value ) {
		tableFloatType = value;
	}

	public ICFSecISOCcyTable getTableISOCcy() {
		return( tableISOCcy );
	}

	public void setTableISOCcy( ICFSecISOCcyTable value ) {
		tableISOCcy = value;
	}

	public ICFSecISOCtryTable getTableISOCtry() {
		return( tableISOCtry );
	}

	public void setTableISOCtry( ICFSecISOCtryTable value ) {
		tableISOCtry = value;
	}

	public ICFSecISOCtryCcyTable getTableISOCtryCcy() {
		return( tableISOCtryCcy );
	}

	public void setTableISOCtryCcy( ICFSecISOCtryCcyTable value ) {
		tableISOCtryCcy = value;
	}

	public ICFSecISOCtryLangTable getTableISOCtryLang() {
		return( tableISOCtryLang );
	}

	public void setTableISOCtryLang( ICFSecISOCtryLangTable value ) {
		tableISOCtryLang = value;
	}

	public ICFSecISOLangTable getTableISOLang() {
		return( tableISOLang );
	}

	public void setTableISOLang( ICFSecISOLangTable value ) {
		tableISOLang = value;
	}

	public ICFSecISOTZoneTable getTableISOTZone() {
		return( tableISOTZone );
	}

	public void setTableISOTZone( ICFSecISOTZoneTable value ) {
		tableISOTZone = value;
	}

	public ICFBamId16GenTable getTableId16Gen() {
		return( tableId16Gen );
	}

	public void setTableId16Gen( ICFBamId16GenTable value ) {
		tableId16Gen = value;
	}

	public ICFBamId32GenTable getTableId32Gen() {
		return( tableId32Gen );
	}

	public void setTableId32Gen( ICFBamId32GenTable value ) {
		tableId32Gen = value;
	}

	public ICFBamId64GenTable getTableId64Gen() {
		return( tableId64Gen );
	}

	public void setTableId64Gen( ICFBamId64GenTable value ) {
		tableId64Gen = value;
	}

	public ICFBamIndexTable getTableIndex() {
		return( tableIndex );
	}

	public void setTableIndex( ICFBamIndexTable value ) {
		tableIndex = value;
	}

	public ICFBamIndexColTable getTableIndexCol() {
		return( tableIndexCol );
	}

	public void setTableIndexCol( ICFBamIndexColTable value ) {
		tableIndexCol = value;
	}

	public ICFBamIndexTweakTable getTableIndexTweak() {
		return( tableIndexTweak );
	}

	public void setTableIndexTweak( ICFBamIndexTweakTable value ) {
		tableIndexTweak = value;
	}

	public ICFBamInt16ColTable getTableInt16Col() {
		return( tableInt16Col );
	}

	public void setTableInt16Col( ICFBamInt16ColTable value ) {
		tableInt16Col = value;
	}

	public ICFBamInt16DefTable getTableInt16Def() {
		return( tableInt16Def );
	}

	public void setTableInt16Def( ICFBamInt16DefTable value ) {
		tableInt16Def = value;
	}

	public ICFBamInt16TypeTable getTableInt16Type() {
		return( tableInt16Type );
	}

	public void setTableInt16Type( ICFBamInt16TypeTable value ) {
		tableInt16Type = value;
	}

	public ICFBamInt32ColTable getTableInt32Col() {
		return( tableInt32Col );
	}

	public void setTableInt32Col( ICFBamInt32ColTable value ) {
		tableInt32Col = value;
	}

	public ICFBamInt32DefTable getTableInt32Def() {
		return( tableInt32Def );
	}

	public void setTableInt32Def( ICFBamInt32DefTable value ) {
		tableInt32Def = value;
	}

	public ICFBamInt32TypeTable getTableInt32Type() {
		return( tableInt32Type );
	}

	public void setTableInt32Type( ICFBamInt32TypeTable value ) {
		tableInt32Type = value;
	}

	public ICFBamInt64ColTable getTableInt64Col() {
		return( tableInt64Col );
	}

	public void setTableInt64Col( ICFBamInt64ColTable value ) {
		tableInt64Col = value;
	}

	public ICFBamInt64DefTable getTableInt64Def() {
		return( tableInt64Def );
	}

	public void setTableInt64Def( ICFBamInt64DefTable value ) {
		tableInt64Def = value;
	}

	public ICFBamInt64TypeTable getTableInt64Type() {
		return( tableInt64Type );
	}

	public void setTableInt64Type( ICFBamInt64TypeTable value ) {
		tableInt64Type = value;
	}

	public ICFIntLicenseTable getTableLicense() {
		return( tableLicense );
	}

	public void setTableLicense( ICFIntLicenseTable value ) {
		tableLicense = value;
	}

	public ICFIntMajorVersionTable getTableMajorVersion() {
		return( tableMajorVersion );
	}

	public void setTableMajorVersion( ICFIntMajorVersionTable value ) {
		tableMajorVersion = value;
	}

	public ICFIntMimeTypeTable getTableMimeType() {
		return( tableMimeType );
	}

	public void setTableMimeType( ICFIntMimeTypeTable value ) {
		tableMimeType = value;
	}

	public ICFIntMinorVersionTable getTableMinorVersion() {
		return( tableMinorVersion );
	}

	public void setTableMinorVersion( ICFIntMinorVersionTable value ) {
		tableMinorVersion = value;
	}

	public ICFBamNmTokenColTable getTableNmTokenCol() {
		return( tableNmTokenCol );
	}

	public void setTableNmTokenCol( ICFBamNmTokenColTable value ) {
		tableNmTokenCol = value;
	}

	public ICFBamNmTokenDefTable getTableNmTokenDef() {
		return( tableNmTokenDef );
	}

	public void setTableNmTokenDef( ICFBamNmTokenDefTable value ) {
		tableNmTokenDef = value;
	}

	public ICFBamNmTokenTypeTable getTableNmTokenType() {
		return( tableNmTokenType );
	}

	public void setTableNmTokenType( ICFBamNmTokenTypeTable value ) {
		tableNmTokenType = value;
	}

	public ICFBamNmTokensColTable getTableNmTokensCol() {
		return( tableNmTokensCol );
	}

	public void setTableNmTokensCol( ICFBamNmTokensColTable value ) {
		tableNmTokensCol = value;
	}

	public ICFBamNmTokensDefTable getTableNmTokensDef() {
		return( tableNmTokensDef );
	}

	public void setTableNmTokensDef( ICFBamNmTokensDefTable value ) {
		tableNmTokensDef = value;
	}

	public ICFBamNmTokensTypeTable getTableNmTokensType() {
		return( tableNmTokensType );
	}

	public void setTableNmTokensType( ICFBamNmTokensTypeTable value ) {
		tableNmTokensType = value;
	}

	public ICFBamNumberColTable getTableNumberCol() {
		return( tableNumberCol );
	}

	public void setTableNumberCol( ICFBamNumberColTable value ) {
		tableNumberCol = value;
	}

	public ICFBamNumberDefTable getTableNumberDef() {
		return( tableNumberDef );
	}

	public void setTableNumberDef( ICFBamNumberDefTable value ) {
		tableNumberDef = value;
	}

	public ICFBamNumberTypeTable getTableNumberType() {
		return( tableNumberType );
	}

	public void setTableNumberType( ICFBamNumberTypeTable value ) {
		tableNumberType = value;
	}

	public ICFBamParamTable getTableParam() {
		return( tableParam );
	}

	public void setTableParam( ICFBamParamTable value ) {
		tableParam = value;
	}

	public ICFBamPopDepTable getTablePopDep() {
		return( tablePopDep );
	}

	public void setTablePopDep( ICFBamPopDepTable value ) {
		tablePopDep = value;
	}

	public ICFBamPopSubDep1Table getTablePopSubDep1() {
		return( tablePopSubDep1 );
	}

	public void setTablePopSubDep1( ICFBamPopSubDep1Table value ) {
		tablePopSubDep1 = value;
	}

	public ICFBamPopSubDep2Table getTablePopSubDep2() {
		return( tablePopSubDep2 );
	}

	public void setTablePopSubDep2( ICFBamPopSubDep2Table value ) {
		tablePopSubDep2 = value;
	}

	public ICFBamPopSubDep3Table getTablePopSubDep3() {
		return( tablePopSubDep3 );
	}

	public void setTablePopSubDep3( ICFBamPopSubDep3Table value ) {
		tablePopSubDep3 = value;
	}

	public ICFBamPopTopDepTable getTablePopTopDep() {
		return( tablePopTopDep );
	}

	public void setTablePopTopDep( ICFBamPopTopDepTable value ) {
		tablePopTopDep = value;
	}

	public ICFBamRelationTable getTableRelation() {
		return( tableRelation );
	}

	public void setTableRelation( ICFBamRelationTable value ) {
		tableRelation = value;
	}

	public ICFBamRelationColTable getTableRelationCol() {
		return( tableRelationCol );
	}

	public void setTableRelationCol( ICFBamRelationColTable value ) {
		tableRelationCol = value;
	}

	public ICFBamRoleDefTable getTableRoleDef() {
		return( tableRoleDef );
	}

	public void setTableRoleDef( ICFBamRoleDefTable value ) {
		tableRoleDef = value;
	}

	public ICFBamSchemaDefTable getTableSchemaDef() {
		return( tableSchemaDef );
	}

	public void setTableSchemaDef( ICFBamSchemaDefTable value ) {
		tableSchemaDef = value;
	}

	public ICFBamSchemaRefTable getTableSchemaRef() {
		return( tableSchemaRef );
	}

	public void setTableSchemaRef( ICFBamSchemaRefTable value ) {
		tableSchemaRef = value;
	}

	public ICFBamSchemaRoleTable getTableSchemaRole() {
		return( tableSchemaRole );
	}

	public void setTableSchemaRole( ICFBamSchemaRoleTable value ) {
		tableSchemaRole = value;
	}

	public ICFBamSchemaTweakTable getTableSchemaTweak() {
		return( tableSchemaTweak );
	}

	public void setTableSchemaTweak( ICFBamSchemaTweakTable value ) {
		tableSchemaTweak = value;
	}

	public ICFBamScopeTable getTableScope() {
		return( tableScope );
	}

	public void setTableScope( ICFBamScopeTable value ) {
		tableScope = value;
	}

	public ICFSecSecClusGrpTable getTableSecClusGrp() {
		return( tableSecClusGrp );
	}

	public void setTableSecClusGrp( ICFSecSecClusGrpTable value ) {
		tableSecClusGrp = value;
	}

	public ICFSecSecClusGrpMembTable getTableSecClusGrpMemb() {
		return( tableSecClusGrpMemb );
	}

	public void setTableSecClusGrpMemb( ICFSecSecClusGrpMembTable value ) {
		tableSecClusGrpMemb = value;
	}

	public ICFSecSecClusRoleTable getTableSecClusRole() {
		return( tableSecClusRole );
	}

	public void setTableSecClusRole( ICFSecSecClusRoleTable value ) {
		tableSecClusRole = value;
	}

	public ICFSecSecClusRoleMembTable getTableSecClusRoleMemb() {
		return( tableSecClusRoleMemb );
	}

	public void setTableSecClusRoleMemb( ICFSecSecClusRoleMembTable value ) {
		tableSecClusRoleMemb = value;
	}

	public ICFSecSecSessionTable getTableSecSession() {
		return( tableSecSession );
	}

	public void setTableSecSession( ICFSecSecSessionTable value ) {
		tableSecSession = value;
	}

	public ICFSecSecSysGrpTable getTableSecSysGrp() {
		return( tableSecSysGrp );
	}

	public void setTableSecSysGrp( ICFSecSecSysGrpTable value ) {
		tableSecSysGrp = value;
	}

	public ICFSecSecSysGrpIncTable getTableSecSysGrpInc() {
		return( tableSecSysGrpInc );
	}

	public void setTableSecSysGrpInc( ICFSecSecSysGrpIncTable value ) {
		tableSecSysGrpInc = value;
	}

	public ICFSecSecSysGrpMembTable getTableSecSysGrpMemb() {
		return( tableSecSysGrpMemb );
	}

	public void setTableSecSysGrpMemb( ICFSecSecSysGrpMembTable value ) {
		tableSecSysGrpMemb = value;
	}

	public ICFSecSecSysRoleTable getTableSecSysRole() {
		return( tableSecSysRole );
	}

	public void setTableSecSysRole( ICFSecSecSysRoleTable value ) {
		tableSecSysRole = value;
	}

	public ICFSecSecSysRoleEnablesTable getTableSecSysRoleEnables() {
		return( tableSecSysRoleEnables );
	}

	public void setTableSecSysRoleEnables( ICFSecSecSysRoleEnablesTable value ) {
		tableSecSysRoleEnables = value;
	}

	public ICFSecSecSysRoleMembTable getTableSecSysRoleMemb() {
		return( tableSecSysRoleMemb );
	}

	public void setTableSecSysRoleMemb( ICFSecSecSysRoleMembTable value ) {
		tableSecSysRoleMemb = value;
	}

	public ICFSecSecTentGrpTable getTableSecTentGrp() {
		return( tableSecTentGrp );
	}

	public void setTableSecTentGrp( ICFSecSecTentGrpTable value ) {
		tableSecTentGrp = value;
	}

	public ICFSecSecTentGrpMembTable getTableSecTentGrpMemb() {
		return( tableSecTentGrpMemb );
	}

	public void setTableSecTentGrpMemb( ICFSecSecTentGrpMembTable value ) {
		tableSecTentGrpMemb = value;
	}

	public ICFSecSecTentRoleTable getTableSecTentRole() {
		return( tableSecTentRole );
	}

	public void setTableSecTentRole( ICFSecSecTentRoleTable value ) {
		tableSecTentRole = value;
	}

	public ICFSecSecTentRoleMembTable getTableSecTentRoleMemb() {
		return( tableSecTentRoleMemb );
	}

	public void setTableSecTentRoleMemb( ICFSecSecTentRoleMembTable value ) {
		tableSecTentRoleMemb = value;
	}

	public ICFSecSecUserTable getTableSecUser() {
		return( tableSecUser );
	}

	public void setTableSecUser( ICFSecSecUserTable value ) {
		tableSecUser = value;
	}

	public ICFSecSecUserEMConfTable getTableSecUserEMConf() {
		return( tableSecUserEMConf );
	}

	public void setTableSecUserEMConf( ICFSecSecUserEMConfTable value ) {
		tableSecUserEMConf = value;
	}

	public ICFSecSecUserPWHistoryTable getTableSecUserPWHistory() {
		return( tableSecUserPWHistory );
	}

	public void setTableSecUserPWHistory( ICFSecSecUserPWHistoryTable value ) {
		tableSecUserPWHistory = value;
	}

	public ICFSecSecUserPWResetTable getTableSecUserPWReset() {
		return( tableSecUserPWReset );
	}

	public void setTableSecUserPWReset( ICFSecSecUserPWResetTable value ) {
		tableSecUserPWReset = value;
	}

	public ICFSecSecUserPasswordTable getTableSecUserPassword() {
		return( tableSecUserPassword );
	}

	public void setTableSecUserPassword( ICFSecSecUserPasswordTable value ) {
		tableSecUserPassword = value;
	}

	public ICFBamServerListFuncTable getTableServerListFunc() {
		return( tableServerListFunc );
	}

	public void setTableServerListFunc( ICFBamServerListFuncTable value ) {
		tableServerListFunc = value;
	}

	public ICFBamServerMethodTable getTableServerMethod() {
		return( tableServerMethod );
	}

	public void setTableServerMethod( ICFBamServerMethodTable value ) {
		tableServerMethod = value;
	}

	public ICFBamServerObjFuncTable getTableServerObjFunc() {
		return( tableServerObjFunc );
	}

	public void setTableServerObjFunc( ICFBamServerObjFuncTable value ) {
		tableServerObjFunc = value;
	}

	public ICFBamServerProcTable getTableServerProc() {
		return( tableServerProc );
	}

	public void setTableServerProc( ICFBamServerProcTable value ) {
		tableServerProc = value;
	}

	public ICFBamStringColTable getTableStringCol() {
		return( tableStringCol );
	}

	public void setTableStringCol( ICFBamStringColTable value ) {
		tableStringCol = value;
	}

	public ICFBamStringDefTable getTableStringDef() {
		return( tableStringDef );
	}

	public void setTableStringDef( ICFBamStringDefTable value ) {
		tableStringDef = value;
	}

	public ICFBamStringTypeTable getTableStringType() {
		return( tableStringType );
	}

	public void setTableStringType( ICFBamStringTypeTable value ) {
		tableStringType = value;
	}

	public ICFIntSubProjectTable getTableSubProject() {
		return( tableSubProject );
	}

	public void setTableSubProject( ICFIntSubProjectTable value ) {
		tableSubProject = value;
	}

	public ICFSecSysClusterTable getTableSysCluster() {
		return( tableSysCluster );
	}

	public void setTableSysCluster( ICFSecSysClusterTable value ) {
		tableSysCluster = value;
	}

	public ICFBamTZDateColTable getTableTZDateCol() {
		return( tableTZDateCol );
	}

	public void setTableTZDateCol( ICFBamTZDateColTable value ) {
		tableTZDateCol = value;
	}

	public ICFBamTZDateDefTable getTableTZDateDef() {
		return( tableTZDateDef );
	}

	public void setTableTZDateDef( ICFBamTZDateDefTable value ) {
		tableTZDateDef = value;
	}

	public ICFBamTZDateTypeTable getTableTZDateType() {
		return( tableTZDateType );
	}

	public void setTableTZDateType( ICFBamTZDateTypeTable value ) {
		tableTZDateType = value;
	}

	public ICFBamTZTimeColTable getTableTZTimeCol() {
		return( tableTZTimeCol );
	}

	public void setTableTZTimeCol( ICFBamTZTimeColTable value ) {
		tableTZTimeCol = value;
	}

	public ICFBamTZTimeDefTable getTableTZTimeDef() {
		return( tableTZTimeDef );
	}

	public void setTableTZTimeDef( ICFBamTZTimeDefTable value ) {
		tableTZTimeDef = value;
	}

	public ICFBamTZTimeTypeTable getTableTZTimeType() {
		return( tableTZTimeType );
	}

	public void setTableTZTimeType( ICFBamTZTimeTypeTable value ) {
		tableTZTimeType = value;
	}

	public ICFBamTZTimestampColTable getTableTZTimestampCol() {
		return( tableTZTimestampCol );
	}

	public void setTableTZTimestampCol( ICFBamTZTimestampColTable value ) {
		tableTZTimestampCol = value;
	}

	public ICFBamTZTimestampDefTable getTableTZTimestampDef() {
		return( tableTZTimestampDef );
	}

	public void setTableTZTimestampDef( ICFBamTZTimestampDefTable value ) {
		tableTZTimestampDef = value;
	}

	public ICFBamTZTimestampTypeTable getTableTZTimestampType() {
		return( tableTZTimestampType );
	}

	public void setTableTZTimestampType( ICFBamTZTimestampTypeTable value ) {
		tableTZTimestampType = value;
	}

	public ICFBamTableTable getTableTable() {
		return( tableTable );
	}

	public void setTableTable( ICFBamTableTable value ) {
		tableTable = value;
	}

	public ICFBamTableColTable getTableTableCol() {
		return( tableTableCol );
	}

	public void setTableTableCol( ICFBamTableColTable value ) {
		tableTableCol = value;
	}

	public ICFSecTableInfoTable getTableTableInfo() {
		return( tableTableInfo );
	}

	public void setTableTableInfo( ICFSecTableInfoTable value ) {
		tableTableInfo = value;
	}

	public ICFBamTableTweakTable getTableTableTweak() {
		return( tableTableTweak );
	}

	public void setTableTableTweak( ICFBamTableTweakTable value ) {
		tableTableTweak = value;
	}

	public ICFSecTenantTable getTableTenant() {
		return( tableTenant );
	}

	public void setTableTenant( ICFSecTenantTable value ) {
		tableTenant = value;
	}

	public ICFBamTextColTable getTableTextCol() {
		return( tableTextCol );
	}

	public void setTableTextCol( ICFBamTextColTable value ) {
		tableTextCol = value;
	}

	public ICFBamTextDefTable getTableTextDef() {
		return( tableTextDef );
	}

	public void setTableTextDef( ICFBamTextDefTable value ) {
		tableTextDef = value;
	}

	public ICFBamTextTypeTable getTableTextType() {
		return( tableTextType );
	}

	public void setTableTextType( ICFBamTextTypeTable value ) {
		tableTextType = value;
	}

	public ICFBamTimeColTable getTableTimeCol() {
		return( tableTimeCol );
	}

	public void setTableTimeCol( ICFBamTimeColTable value ) {
		tableTimeCol = value;
	}

	public ICFBamTimeDefTable getTableTimeDef() {
		return( tableTimeDef );
	}

	public void setTableTimeDef( ICFBamTimeDefTable value ) {
		tableTimeDef = value;
	}

	public ICFBamTimeTypeTable getTableTimeType() {
		return( tableTimeType );
	}

	public void setTableTimeType( ICFBamTimeTypeTable value ) {
		tableTimeType = value;
	}

	public ICFBamTimestampColTable getTableTimestampCol() {
		return( tableTimestampCol );
	}

	public void setTableTimestampCol( ICFBamTimestampColTable value ) {
		tableTimestampCol = value;
	}

	public ICFBamTimestampDefTable getTableTimestampDef() {
		return( tableTimestampDef );
	}

	public void setTableTimestampDef( ICFBamTimestampDefTable value ) {
		tableTimestampDef = value;
	}

	public ICFBamTimestampTypeTable getTableTimestampType() {
		return( tableTimestampType );
	}

	public void setTableTimestampType( ICFBamTimestampTypeTable value ) {
		tableTimestampType = value;
	}

	public ICFIntTldTable getTableTld() {
		return( tableTld );
	}

	public void setTableTld( ICFIntTldTable value ) {
		tableTld = value;
	}

	public ICFBamTokenColTable getTableTokenCol() {
		return( tableTokenCol );
	}

	public void setTableTokenCol( ICFBamTokenColTable value ) {
		tableTokenCol = value;
	}

	public ICFBamTokenDefTable getTableTokenDef() {
		return( tableTokenDef );
	}

	public void setTableTokenDef( ICFBamTokenDefTable value ) {
		tableTokenDef = value;
	}

	public ICFBamTokenTypeTable getTableTokenType() {
		return( tableTokenType );
	}

	public void setTableTokenType( ICFBamTokenTypeTable value ) {
		tableTokenType = value;
	}

	public ICFIntTopDomainTable getTableTopDomain() {
		return( tableTopDomain );
	}

	public void setTableTopDomain( ICFIntTopDomainTable value ) {
		tableTopDomain = value;
	}

	public ICFIntTopProjectTable getTableTopProject() {
		return( tableTopProject );
	}

	public void setTableTopProject( ICFIntTopProjectTable value ) {
		tableTopProject = value;
	}

	public ICFBamTweakTable getTableTweak() {
		return( tableTweak );
	}

	public void setTableTweak( ICFBamTweakTable value ) {
		tableTweak = value;
	}

	public ICFBamUInt16ColTable getTableUInt16Col() {
		return( tableUInt16Col );
	}

	public void setTableUInt16Col( ICFBamUInt16ColTable value ) {
		tableUInt16Col = value;
	}

	public ICFBamUInt16DefTable getTableUInt16Def() {
		return( tableUInt16Def );
	}

	public void setTableUInt16Def( ICFBamUInt16DefTable value ) {
		tableUInt16Def = value;
	}

	public ICFBamUInt16TypeTable getTableUInt16Type() {
		return( tableUInt16Type );
	}

	public void setTableUInt16Type( ICFBamUInt16TypeTable value ) {
		tableUInt16Type = value;
	}

	public ICFBamUInt32ColTable getTableUInt32Col() {
		return( tableUInt32Col );
	}

	public void setTableUInt32Col( ICFBamUInt32ColTable value ) {
		tableUInt32Col = value;
	}

	public ICFBamUInt32DefTable getTableUInt32Def() {
		return( tableUInt32Def );
	}

	public void setTableUInt32Def( ICFBamUInt32DefTable value ) {
		tableUInt32Def = value;
	}

	public ICFBamUInt32TypeTable getTableUInt32Type() {
		return( tableUInt32Type );
	}

	public void setTableUInt32Type( ICFBamUInt32TypeTable value ) {
		tableUInt32Type = value;
	}

	public ICFBamUInt64ColTable getTableUInt64Col() {
		return( tableUInt64Col );
	}

	public void setTableUInt64Col( ICFBamUInt64ColTable value ) {
		tableUInt64Col = value;
	}

	public ICFBamUInt64DefTable getTableUInt64Def() {
		return( tableUInt64Def );
	}

	public void setTableUInt64Def( ICFBamUInt64DefTable value ) {
		tableUInt64Def = value;
	}

	public ICFBamUInt64TypeTable getTableUInt64Type() {
		return( tableUInt64Type );
	}

	public void setTableUInt64Type( ICFBamUInt64TypeTable value ) {
		tableUInt64Type = value;
	}

	public ICFIntURLProtocolTable getTableURLProtocol() {
		return( tableURLProtocol );
	}

	public void setTableURLProtocol( ICFIntURLProtocolTable value ) {
		tableURLProtocol = value;
	}

	public ICFBamUuid6ColTable getTableUuid6Col() {
		return( tableUuid6Col );
	}

	public void setTableUuid6Col( ICFBamUuid6ColTable value ) {
		tableUuid6Col = value;
	}

	public ICFBamUuid6DefTable getTableUuid6Def() {
		return( tableUuid6Def );
	}

	public void setTableUuid6Def( ICFBamUuid6DefTable value ) {
		tableUuid6Def = value;
	}

	public ICFBamUuid6GenTable getTableUuid6Gen() {
		return( tableUuid6Gen );
	}

	public void setTableUuid6Gen( ICFBamUuid6GenTable value ) {
		tableUuid6Gen = value;
	}

	public ICFBamUuid6TypeTable getTableUuid6Type() {
		return( tableUuid6Type );
	}

	public void setTableUuid6Type( ICFBamUuid6TypeTable value ) {
		tableUuid6Type = value;
	}

	public ICFBamUuidColTable getTableUuidCol() {
		return( tableUuidCol );
	}

	public void setTableUuidCol( ICFBamUuidColTable value ) {
		tableUuidCol = value;
	}

	public ICFBamUuidDefTable getTableUuidDef() {
		return( tableUuidDef );
	}

	public void setTableUuidDef( ICFBamUuidDefTable value ) {
		tableUuidDef = value;
	}

	public ICFBamUuidGenTable getTableUuidGen() {
		return( tableUuidGen );
	}

	public void setTableUuidGen( ICFBamUuidGenTable value ) {
		tableUuidGen = value;
	}

	public ICFBamUuidTypeTable getTableUuidType() {
		return( tableUuidType );
	}

	public void setTableUuidType( ICFBamUuidTypeTable value ) {
		tableUuidType = value;
	}

	public ICFBamValueTable getTableValue() {
		return( tableValue );
	}

	public void setTableValue( ICFBamValueTable value ) {
		tableValue = value;
	}

	public static String xmlEncodeString( String val ) {
		StringBuffer buff = new StringBuffer();
		int len = val.length();
		for( int idx = 0; idx < len; idx ++ ) {
			char c = val.charAt( idx );
			switch( c ) {
				case '&':
					buff.append( "&amp;" );
					break;
				case '<':
					buff.append( "&lt;" );
					break;
				case '>':
					buff.append( "&gt;" );
					break;
				case '"':
					buff.append( "&quot;" );
					break;
				case '\'':
					buff.append( "&apos;" );
					break;
				default:
					buff.append( c );
					break;
			}
		}
		return( buff.toString() );
	}

	public void bootstrapSchema(CFSecPubTableData tableData[]) {
		ICFSecSchema.getBackingCFSec().bootstrapSchema(tableData);
	}
	
	public void bootstrapAllTablesSecurity(CFLibDbKeyHash256 clusterId, CFLibDbKeyHash256 tenantId, CFSecPubTableData tableData[]) {
		ICFSecSchema.getBackingCFSec().bootstrapAllTablesSecurity(clusterId, tenantId, tableData);
	}
}
