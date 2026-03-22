// Description: Java 25 Schema Object implementation for CFBam.

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

public class CFBamSchemaObj
	implements ICFBamSchemaObj
{
	public static String SCHEMA_NAME = "CFBam";
	public static String SCHEMA_DBNAME = "CFBam31";
	protected ICFSecAuthorization authorization = null;
	protected String secClusterName = "system";
	protected String secTenantName = "system";
	protected String secUserName = "system";
	protected ICFSecClusterObj secCluster = null;
	protected CFLibDbKeyHash256 secClusterId = null;
	protected ICFSecTenantObj secTenant = null;
	protected CFLibDbKeyHash256 secTenantId = null;
	protected ICFSecSecUserObj secUser = null;
	protected CFLibDbKeyHash256 secSessionUserId = null;
	protected ICFSecSecSessionObj secSession = null;
	protected CFLibDbKeyHash256 secSessionSessionId = null;
	protected String schemaDbName = SCHEMA_DBNAME;
	protected String lowerDbSchemaName = SCHEMA_DBNAME.toLowerCase();

	protected ICFSecSchema cfsecBackingStore;
	protected ICFIntSchema cfintBackingStore;
	protected ICFBamSchema cfbamBackingStore;
	protected ICFBamAtomTableObj atomTableObj;
	protected ICFBamBlobColTableObj blobColTableObj;
	protected ICFBamBlobDefTableObj blobDefTableObj;
	protected ICFBamBlobTypeTableObj blobTypeTableObj;
	protected ICFBamBoolColTableObj boolColTableObj;
	protected ICFBamBoolDefTableObj boolDefTableObj;
	protected ICFBamBoolTypeTableObj boolTypeTableObj;
	protected ICFBamChainTableObj chainTableObj;
	protected ICFBamClearDepTableObj clearDepTableObj;
	protected ICFBamClearSubDep1TableObj clearSubDep1TableObj;
	protected ICFBamClearSubDep2TableObj clearSubDep2TableObj;
	protected ICFBamClearSubDep3TableObj clearSubDep3TableObj;
	protected ICFBamClearTopDepTableObj clearTopDepTableObj;
	protected ICFBamClusterTableObj clusterTableObj;
	protected ICFBamDateColTableObj dateColTableObj;
	protected ICFBamDateDefTableObj dateDefTableObj;
	protected ICFBamDateTypeTableObj dateTypeTableObj;
	protected ICFBamDbKeyHash128ColTableObj dbKeyHash128ColTableObj;
	protected ICFBamDbKeyHash128DefTableObj dbKeyHash128DefTableObj;
	protected ICFBamDbKeyHash128GenTableObj dbKeyHash128GenTableObj;
	protected ICFBamDbKeyHash128TypeTableObj dbKeyHash128TypeTableObj;
	protected ICFBamDbKeyHash160ColTableObj dbKeyHash160ColTableObj;
	protected ICFBamDbKeyHash160DefTableObj dbKeyHash160DefTableObj;
	protected ICFBamDbKeyHash160GenTableObj dbKeyHash160GenTableObj;
	protected ICFBamDbKeyHash160TypeTableObj dbKeyHash160TypeTableObj;
	protected ICFBamDbKeyHash224ColTableObj dbKeyHash224ColTableObj;
	protected ICFBamDbKeyHash224DefTableObj dbKeyHash224DefTableObj;
	protected ICFBamDbKeyHash224GenTableObj dbKeyHash224GenTableObj;
	protected ICFBamDbKeyHash224TypeTableObj dbKeyHash224TypeTableObj;
	protected ICFBamDbKeyHash256ColTableObj dbKeyHash256ColTableObj;
	protected ICFBamDbKeyHash256DefTableObj dbKeyHash256DefTableObj;
	protected ICFBamDbKeyHash256GenTableObj dbKeyHash256GenTableObj;
	protected ICFBamDbKeyHash256TypeTableObj dbKeyHash256TypeTableObj;
	protected ICFBamDbKeyHash384ColTableObj dbKeyHash384ColTableObj;
	protected ICFBamDbKeyHash384DefTableObj dbKeyHash384DefTableObj;
	protected ICFBamDbKeyHash384GenTableObj dbKeyHash384GenTableObj;
	protected ICFBamDbKeyHash384TypeTableObj dbKeyHash384TypeTableObj;
	protected ICFBamDbKeyHash512ColTableObj dbKeyHash512ColTableObj;
	protected ICFBamDbKeyHash512DefTableObj dbKeyHash512DefTableObj;
	protected ICFBamDbKeyHash512GenTableObj dbKeyHash512GenTableObj;
	protected ICFBamDbKeyHash512TypeTableObj dbKeyHash512TypeTableObj;
	protected ICFBamDelDepTableObj delDepTableObj;
	protected ICFBamDelSubDep1TableObj delSubDep1TableObj;
	protected ICFBamDelSubDep2TableObj delSubDep2TableObj;
	protected ICFBamDelSubDep3TableObj delSubDep3TableObj;
	protected ICFBamDelTopDepTableObj delTopDepTableObj;
	protected ICFBamDoubleColTableObj doubleColTableObj;
	protected ICFBamDoubleDefTableObj doubleDefTableObj;
	protected ICFBamDoubleTypeTableObj doubleTypeTableObj;
	protected ICFBamEnumDefTableObj enumDefTableObj;
	protected ICFBamEnumTagTableObj enumTagTableObj;
	protected ICFBamEnumTypeTableObj enumTypeTableObj;
	protected ICFBamFloatColTableObj floatColTableObj;
	protected ICFBamFloatDefTableObj floatDefTableObj;
	protected ICFBamFloatTypeTableObj floatTypeTableObj;
	protected ICFBamISOCcyTableObj iSOCcyTableObj;
	protected ICFBamISOCtryTableObj iSOCtryTableObj;
	protected ICFBamISOCtryCcyTableObj iSOCtryCcyTableObj;
	protected ICFBamISOCtryLangTableObj iSOCtryLangTableObj;
	protected ICFBamISOLangTableObj iSOLangTableObj;
	protected ICFBamISOTZoneTableObj iSOTZoneTableObj;
	protected ICFBamId16GenTableObj id16GenTableObj;
	protected ICFBamId32GenTableObj id32GenTableObj;
	protected ICFBamId64GenTableObj id64GenTableObj;
	protected ICFBamIndexTableObj indexTableObj;
	protected ICFBamIndexColTableObj indexColTableObj;
	protected ICFBamInt16ColTableObj int16ColTableObj;
	protected ICFBamInt16DefTableObj int16DefTableObj;
	protected ICFBamInt16TypeTableObj int16TypeTableObj;
	protected ICFBamInt32ColTableObj int32ColTableObj;
	protected ICFBamInt32DefTableObj int32DefTableObj;
	protected ICFBamInt32TypeTableObj int32TypeTableObj;
	protected ICFBamInt64ColTableObj int64ColTableObj;
	protected ICFBamInt64DefTableObj int64DefTableObj;
	protected ICFBamInt64TypeTableObj int64TypeTableObj;
	protected ICFBamLicenseTableObj licenseTableObj;
	protected ICFBamMajorVersionTableObj majorVersionTableObj;
	protected ICFBamMimeTypeTableObj mimeTypeTableObj;
	protected ICFBamMinorVersionTableObj minorVersionTableObj;
	protected ICFBamNmTokenColTableObj nmTokenColTableObj;
	protected ICFBamNmTokenDefTableObj nmTokenDefTableObj;
	protected ICFBamNmTokenTypeTableObj nmTokenTypeTableObj;
	protected ICFBamNmTokensColTableObj nmTokensColTableObj;
	protected ICFBamNmTokensDefTableObj nmTokensDefTableObj;
	protected ICFBamNmTokensTypeTableObj nmTokensTypeTableObj;
	protected ICFBamNumberColTableObj numberColTableObj;
	protected ICFBamNumberDefTableObj numberDefTableObj;
	protected ICFBamNumberTypeTableObj numberTypeTableObj;
	protected ICFBamParamTableObj paramTableObj;
	protected ICFBamPopDepTableObj popDepTableObj;
	protected ICFBamPopSubDep1TableObj popSubDep1TableObj;
	protected ICFBamPopSubDep2TableObj popSubDep2TableObj;
	protected ICFBamPopSubDep3TableObj popSubDep3TableObj;
	protected ICFBamPopTopDepTableObj popTopDepTableObj;
	protected ICFBamRelationTableObj relationTableObj;
	protected ICFBamRelationColTableObj relationColTableObj;
	protected ICFBamSchemaDefTableObj schemaDefTableObj;
	protected ICFBamSchemaRefTableObj schemaRefTableObj;
	protected ICFBamScopeTableObj scopeTableObj;
	protected ICFBamSecClusGrpTableObj secClusGrpTableObj;
	protected ICFBamSecClusGrpIncTableObj secClusGrpIncTableObj;
	protected ICFBamSecClusGrpMembTableObj secClusGrpMembTableObj;
	protected ICFBamSecSessionTableObj secSessionTableObj;
	protected ICFBamSecSysGrpTableObj secSysGrpTableObj;
	protected ICFBamSecSysGrpIncTableObj secSysGrpIncTableObj;
	protected ICFBamSecSysGrpMembTableObj secSysGrpMembTableObj;
	protected ICFBamSecTentGrpTableObj secTentGrpTableObj;
	protected ICFBamSecTentGrpIncTableObj secTentGrpIncTableObj;
	protected ICFBamSecTentGrpMembTableObj secTentGrpMembTableObj;
	protected ICFBamSecUserTableObj secUserTableObj;
	protected ICFBamSecUserPWHistoryTableObj secUserPWHistoryTableObj;
	protected ICFBamSecUserPasswordTableObj secUserPasswordTableObj;
	protected ICFBamServerListFuncTableObj serverListFuncTableObj;
	protected ICFBamServerMethodTableObj serverMethodTableObj;
	protected ICFBamServerObjFuncTableObj serverObjFuncTableObj;
	protected ICFBamServerProcTableObj serverProcTableObj;
	protected ICFBamStringColTableObj stringColTableObj;
	protected ICFBamStringDefTableObj stringDefTableObj;
	protected ICFBamStringTypeTableObj stringTypeTableObj;
	protected ICFBamSubProjectTableObj subProjectTableObj;
	protected ICFBamSysClusterTableObj sysClusterTableObj;
	protected ICFBamTZDateColTableObj tZDateColTableObj;
	protected ICFBamTZDateDefTableObj tZDateDefTableObj;
	protected ICFBamTZDateTypeTableObj tZDateTypeTableObj;
	protected ICFBamTZTimeColTableObj tZTimeColTableObj;
	protected ICFBamTZTimeDefTableObj tZTimeDefTableObj;
	protected ICFBamTZTimeTypeTableObj tZTimeTypeTableObj;
	protected ICFBamTZTimestampColTableObj tZTimestampColTableObj;
	protected ICFBamTZTimestampDefTableObj tZTimestampDefTableObj;
	protected ICFBamTZTimestampTypeTableObj tZTimestampTypeTableObj;
	protected ICFBamTableTableObj tableTableObj;
	protected ICFBamTableColTableObj tableColTableObj;
	protected ICFBamTenantTableObj tenantTableObj;
	protected ICFBamTextColTableObj textColTableObj;
	protected ICFBamTextDefTableObj textDefTableObj;
	protected ICFBamTextTypeTableObj textTypeTableObj;
	protected ICFBamTimeColTableObj timeColTableObj;
	protected ICFBamTimeDefTableObj timeDefTableObj;
	protected ICFBamTimeTypeTableObj timeTypeTableObj;
	protected ICFBamTimestampColTableObj timestampColTableObj;
	protected ICFBamTimestampDefTableObj timestampDefTableObj;
	protected ICFBamTimestampTypeTableObj timestampTypeTableObj;
	protected ICFBamTldTableObj tldTableObj;
	protected ICFBamTokenColTableObj tokenColTableObj;
	protected ICFBamTokenDefTableObj tokenDefTableObj;
	protected ICFBamTokenTypeTableObj tokenTypeTableObj;
	protected ICFBamTopDomainTableObj topDomainTableObj;
	protected ICFBamTopProjectTableObj topProjectTableObj;
	protected ICFBamUInt16ColTableObj uInt16ColTableObj;
	protected ICFBamUInt16DefTableObj uInt16DefTableObj;
	protected ICFBamUInt16TypeTableObj uInt16TypeTableObj;
	protected ICFBamUInt32ColTableObj uInt32ColTableObj;
	protected ICFBamUInt32DefTableObj uInt32DefTableObj;
	protected ICFBamUInt32TypeTableObj uInt32TypeTableObj;
	protected ICFBamUInt64ColTableObj uInt64ColTableObj;
	protected ICFBamUInt64DefTableObj uInt64DefTableObj;
	protected ICFBamUInt64TypeTableObj uInt64TypeTableObj;
	protected ICFBamURLProtocolTableObj uRLProtocolTableObj;
	protected ICFBamUuid6ColTableObj uuid6ColTableObj;
	protected ICFBamUuid6DefTableObj uuid6DefTableObj;
	protected ICFBamUuid6GenTableObj uuid6GenTableObj;
	protected ICFBamUuid6TypeTableObj uuid6TypeTableObj;
	protected ICFBamUuidColTableObj uuidColTableObj;
	protected ICFBamUuidDefTableObj uuidDefTableObj;
	protected ICFBamUuidGenTableObj uuidGenTableObj;
	protected ICFBamUuidTypeTableObj uuidTypeTableObj;
	protected ICFBamValueTableObj valueTableObj;

	public CFBamSchemaObj() {

		cfsecBackingStore = null;
		cfintBackingStore = null;
		cfbamBackingStore = null;
		atomTableObj = new CFBamAtomTableObj( this );
		blobColTableObj = new CFBamBlobColTableObj( this );
		blobDefTableObj = new CFBamBlobDefTableObj( this );
		blobTypeTableObj = new CFBamBlobTypeTableObj( this );
		boolColTableObj = new CFBamBoolColTableObj( this );
		boolDefTableObj = new CFBamBoolDefTableObj( this );
		boolTypeTableObj = new CFBamBoolTypeTableObj( this );
		chainTableObj = new CFBamChainTableObj( this );
		clearDepTableObj = new CFBamClearDepTableObj( this );
		clearSubDep1TableObj = new CFBamClearSubDep1TableObj( this );
		clearSubDep2TableObj = new CFBamClearSubDep2TableObj( this );
		clearSubDep3TableObj = new CFBamClearSubDep3TableObj( this );
		clearTopDepTableObj = new CFBamClearTopDepTableObj( this );
		clusterTableObj = new CFBamClusterTableObj( this );
		dateColTableObj = new CFBamDateColTableObj( this );
		dateDefTableObj = new CFBamDateDefTableObj( this );
		dateTypeTableObj = new CFBamDateTypeTableObj( this );
		dbKeyHash128ColTableObj = new CFBamDbKeyHash128ColTableObj( this );
		dbKeyHash128DefTableObj = new CFBamDbKeyHash128DefTableObj( this );
		dbKeyHash128GenTableObj = new CFBamDbKeyHash128GenTableObj( this );
		dbKeyHash128TypeTableObj = new CFBamDbKeyHash128TypeTableObj( this );
		dbKeyHash160ColTableObj = new CFBamDbKeyHash160ColTableObj( this );
		dbKeyHash160DefTableObj = new CFBamDbKeyHash160DefTableObj( this );
		dbKeyHash160GenTableObj = new CFBamDbKeyHash160GenTableObj( this );
		dbKeyHash160TypeTableObj = new CFBamDbKeyHash160TypeTableObj( this );
		dbKeyHash224ColTableObj = new CFBamDbKeyHash224ColTableObj( this );
		dbKeyHash224DefTableObj = new CFBamDbKeyHash224DefTableObj( this );
		dbKeyHash224GenTableObj = new CFBamDbKeyHash224GenTableObj( this );
		dbKeyHash224TypeTableObj = new CFBamDbKeyHash224TypeTableObj( this );
		dbKeyHash256ColTableObj = new CFBamDbKeyHash256ColTableObj( this );
		dbKeyHash256DefTableObj = new CFBamDbKeyHash256DefTableObj( this );
		dbKeyHash256GenTableObj = new CFBamDbKeyHash256GenTableObj( this );
		dbKeyHash256TypeTableObj = new CFBamDbKeyHash256TypeTableObj( this );
		dbKeyHash384ColTableObj = new CFBamDbKeyHash384ColTableObj( this );
		dbKeyHash384DefTableObj = new CFBamDbKeyHash384DefTableObj( this );
		dbKeyHash384GenTableObj = new CFBamDbKeyHash384GenTableObj( this );
		dbKeyHash384TypeTableObj = new CFBamDbKeyHash384TypeTableObj( this );
		dbKeyHash512ColTableObj = new CFBamDbKeyHash512ColTableObj( this );
		dbKeyHash512DefTableObj = new CFBamDbKeyHash512DefTableObj( this );
		dbKeyHash512GenTableObj = new CFBamDbKeyHash512GenTableObj( this );
		dbKeyHash512TypeTableObj = new CFBamDbKeyHash512TypeTableObj( this );
		delDepTableObj = new CFBamDelDepTableObj( this );
		delSubDep1TableObj = new CFBamDelSubDep1TableObj( this );
		delSubDep2TableObj = new CFBamDelSubDep2TableObj( this );
		delSubDep3TableObj = new CFBamDelSubDep3TableObj( this );
		delTopDepTableObj = new CFBamDelTopDepTableObj( this );
		doubleColTableObj = new CFBamDoubleColTableObj( this );
		doubleDefTableObj = new CFBamDoubleDefTableObj( this );
		doubleTypeTableObj = new CFBamDoubleTypeTableObj( this );
		enumDefTableObj = new CFBamEnumDefTableObj( this );
		enumTagTableObj = new CFBamEnumTagTableObj( this );
		enumTypeTableObj = new CFBamEnumTypeTableObj( this );
		floatColTableObj = new CFBamFloatColTableObj( this );
		floatDefTableObj = new CFBamFloatDefTableObj( this );
		floatTypeTableObj = new CFBamFloatTypeTableObj( this );
		iSOCcyTableObj = new CFBamISOCcyTableObj( this );
		iSOCtryTableObj = new CFBamISOCtryTableObj( this );
		iSOCtryCcyTableObj = new CFBamISOCtryCcyTableObj( this );
		iSOCtryLangTableObj = new CFBamISOCtryLangTableObj( this );
		iSOLangTableObj = new CFBamISOLangTableObj( this );
		iSOTZoneTableObj = new CFBamISOTZoneTableObj( this );
		id16GenTableObj = new CFBamId16GenTableObj( this );
		id32GenTableObj = new CFBamId32GenTableObj( this );
		id64GenTableObj = new CFBamId64GenTableObj( this );
		indexTableObj = new CFBamIndexTableObj( this );
		indexColTableObj = new CFBamIndexColTableObj( this );
		int16ColTableObj = new CFBamInt16ColTableObj( this );
		int16DefTableObj = new CFBamInt16DefTableObj( this );
		int16TypeTableObj = new CFBamInt16TypeTableObj( this );
		int32ColTableObj = new CFBamInt32ColTableObj( this );
		int32DefTableObj = new CFBamInt32DefTableObj( this );
		int32TypeTableObj = new CFBamInt32TypeTableObj( this );
		int64ColTableObj = new CFBamInt64ColTableObj( this );
		int64DefTableObj = new CFBamInt64DefTableObj( this );
		int64TypeTableObj = new CFBamInt64TypeTableObj( this );
		licenseTableObj = new CFBamLicenseTableObj( this );
		majorVersionTableObj = new CFBamMajorVersionTableObj( this );
		mimeTypeTableObj = new CFBamMimeTypeTableObj( this );
		minorVersionTableObj = new CFBamMinorVersionTableObj( this );
		nmTokenColTableObj = new CFBamNmTokenColTableObj( this );
		nmTokenDefTableObj = new CFBamNmTokenDefTableObj( this );
		nmTokenTypeTableObj = new CFBamNmTokenTypeTableObj( this );
		nmTokensColTableObj = new CFBamNmTokensColTableObj( this );
		nmTokensDefTableObj = new CFBamNmTokensDefTableObj( this );
		nmTokensTypeTableObj = new CFBamNmTokensTypeTableObj( this );
		numberColTableObj = new CFBamNumberColTableObj( this );
		numberDefTableObj = new CFBamNumberDefTableObj( this );
		numberTypeTableObj = new CFBamNumberTypeTableObj( this );
		paramTableObj = new CFBamParamTableObj( this );
		popDepTableObj = new CFBamPopDepTableObj( this );
		popSubDep1TableObj = new CFBamPopSubDep1TableObj( this );
		popSubDep2TableObj = new CFBamPopSubDep2TableObj( this );
		popSubDep3TableObj = new CFBamPopSubDep3TableObj( this );
		popTopDepTableObj = new CFBamPopTopDepTableObj( this );
		relationTableObj = new CFBamRelationTableObj( this );
		relationColTableObj = new CFBamRelationColTableObj( this );
		schemaDefTableObj = new CFBamSchemaDefTableObj( this );
		schemaRefTableObj = new CFBamSchemaRefTableObj( this );
		scopeTableObj = new CFBamScopeTableObj( this );
		secClusGrpTableObj = new CFBamSecClusGrpTableObj( this );
		secClusGrpIncTableObj = new CFBamSecClusGrpIncTableObj( this );
		secClusGrpMembTableObj = new CFBamSecClusGrpMembTableObj( this );
		secSessionTableObj = new CFBamSecSessionTableObj( this );
		secSysGrpTableObj = new CFBamSecSysGrpTableObj( this );
		secSysGrpIncTableObj = new CFBamSecSysGrpIncTableObj( this );
		secSysGrpMembTableObj = new CFBamSecSysGrpMembTableObj( this );
		secTentGrpTableObj = new CFBamSecTentGrpTableObj( this );
		secTentGrpIncTableObj = new CFBamSecTentGrpIncTableObj( this );
		secTentGrpMembTableObj = new CFBamSecTentGrpMembTableObj( this );
		secUserTableObj = new CFBamSecUserTableObj( this );
		secUserPWHistoryTableObj = new CFBamSecUserPWHistoryTableObj( this );
		secUserPasswordTableObj = new CFBamSecUserPasswordTableObj( this );
		serverListFuncTableObj = new CFBamServerListFuncTableObj( this );
		serverMethodTableObj = new CFBamServerMethodTableObj( this );
		serverObjFuncTableObj = new CFBamServerObjFuncTableObj( this );
		serverProcTableObj = new CFBamServerProcTableObj( this );
		stringColTableObj = new CFBamStringColTableObj( this );
		stringDefTableObj = new CFBamStringDefTableObj( this );
		stringTypeTableObj = new CFBamStringTypeTableObj( this );
		subProjectTableObj = new CFBamSubProjectTableObj( this );
		sysClusterTableObj = new CFBamSysClusterTableObj( this );
		tZDateColTableObj = new CFBamTZDateColTableObj( this );
		tZDateDefTableObj = new CFBamTZDateDefTableObj( this );
		tZDateTypeTableObj = new CFBamTZDateTypeTableObj( this );
		tZTimeColTableObj = new CFBamTZTimeColTableObj( this );
		tZTimeDefTableObj = new CFBamTZTimeDefTableObj( this );
		tZTimeTypeTableObj = new CFBamTZTimeTypeTableObj( this );
		tZTimestampColTableObj = new CFBamTZTimestampColTableObj( this );
		tZTimestampDefTableObj = new CFBamTZTimestampDefTableObj( this );
		tZTimestampTypeTableObj = new CFBamTZTimestampTypeTableObj( this );
		tableTableObj = new CFBamTableTableObj( this );
		tableColTableObj = new CFBamTableColTableObj( this );
		tenantTableObj = new CFBamTenantTableObj( this );
		textColTableObj = new CFBamTextColTableObj( this );
		textDefTableObj = new CFBamTextDefTableObj( this );
		textTypeTableObj = new CFBamTextTypeTableObj( this );
		timeColTableObj = new CFBamTimeColTableObj( this );
		timeDefTableObj = new CFBamTimeDefTableObj( this );
		timeTypeTableObj = new CFBamTimeTypeTableObj( this );
		timestampColTableObj = new CFBamTimestampColTableObj( this );
		timestampDefTableObj = new CFBamTimestampDefTableObj( this );
		timestampTypeTableObj = new CFBamTimestampTypeTableObj( this );
		tldTableObj = new CFBamTldTableObj( this );
		tokenColTableObj = new CFBamTokenColTableObj( this );
		tokenDefTableObj = new CFBamTokenDefTableObj( this );
		tokenTypeTableObj = new CFBamTokenTypeTableObj( this );
		topDomainTableObj = new CFBamTopDomainTableObj( this );
		topProjectTableObj = new CFBamTopProjectTableObj( this );
		uInt16ColTableObj = new CFBamUInt16ColTableObj( this );
		uInt16DefTableObj = new CFBamUInt16DefTableObj( this );
		uInt16TypeTableObj = new CFBamUInt16TypeTableObj( this );
		uInt32ColTableObj = new CFBamUInt32ColTableObj( this );
		uInt32DefTableObj = new CFBamUInt32DefTableObj( this );
		uInt32TypeTableObj = new CFBamUInt32TypeTableObj( this );
		uInt64ColTableObj = new CFBamUInt64ColTableObj( this );
		uInt64DefTableObj = new CFBamUInt64DefTableObj( this );
		uInt64TypeTableObj = new CFBamUInt64TypeTableObj( this );
		uRLProtocolTableObj = new CFBamURLProtocolTableObj( this );
		uuid6ColTableObj = new CFBamUuid6ColTableObj( this );
		uuid6DefTableObj = new CFBamUuid6DefTableObj( this );
		uuid6GenTableObj = new CFBamUuid6GenTableObj( this );
		uuid6TypeTableObj = new CFBamUuid6TypeTableObj( this );
		uuidColTableObj = new CFBamUuidColTableObj( this );
		uuidDefTableObj = new CFBamUuidDefTableObj( this );
		uuidGenTableObj = new CFBamUuidGenTableObj( this );
		uuidTypeTableObj = new CFBamUuidTypeTableObj( this );
		valueTableObj = new CFBamValueTableObj( this );
		}

	public void setSecClusterName( String value ) {
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			throw new CFLibNullArgumentException( getClass(),
				"setClusterName",
				1,
				"value" );
		}
		secClusterName = value;
		secCluster = null;
	}

	public String getSecClusterName() {
		return( secClusterName );
	}

	public ICFSecClusterObj getSecCluster() {
		if( secCluster == null ) {
			if( authorization != null ) {
				secCluster = getClusterTableObj().readClusterByIdIdx( authorization.getSecClusterId() );
			}
			else {
				secCluster = getClusterTableObj().readClusterByUDomNameIdx( secClusterName );
				if( secCluster == null && secClusterId != null && !secClusterId.isNull() ) {
					secCluster = getClusterTableObj().readClusterByIdIdx( secClusterId );
				}
			}
			if( secCluster != null ) {
				secClusterName = secCluster.getRequiredFullDomName();
				secClusterId = secCluster.getRequiredId();
				if( authorization != null ) {
					authorization.setSecCluster( secCluster );
				}
			}
		}
		return( secCluster );
	}

	public void setSecCluster( ICFSecClusterObj value ) {
		secCluster = value;
		if( secCluster == null ) {
			return;
		}
		secClusterId = secCluster.getRequiredId();
		secClusterName = secCluster.getRequiredFullDomName();
		if( authorization != null ) {
			authorization.setSecCluster( secCluster );
		}
	}

	public CFLibDbKeyHash256 getSecClusterId() {
		return( secClusterId );
	}

	public void setSecTenantName( String value ) {
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			throw new CFLibNullArgumentException( getClass(),
				"setTenantName",
				1,
				"value" );
		}
		secTenantName = value;
		secTenant = null;
	}

	public String getSecTenantName() {
		return( secTenantName );
	}

	public ICFSecTenantObj getSecTenant() {
		if( secTenant == null ) {
			if( authorization != null ) {
				secTenant = getTenantTableObj().readTenantByIdIdx( authorization.getSecTenantId() );
			}
			else {
				secTenant = getTenantTableObj().readTenantByUNameIdx( getSecCluster().getRequiredId(), secTenantName );
				if( ( secTenant == null && secTenantId != null && !secTenantId.isNull() ) ) {
					secTenant = getTenantTableObj().readTenantByIdIdx( secTenantId );
				}
			}
			if( secTenant != null ) {
				secTenantName = secTenant.getRequiredTenantName();
				secTenantId = secTenant.getRequiredId();
				if( authorization != null ) {
					authorization.setSecTenant( secTenant );
				}
			}
		}
		return( secTenant );
	}

	public void setSecTenant( ICFSecTenantObj value ) {
		secTenant = value;
		if( secTenant == null ) {
			return;
		}
		secTenantId = secTenant.getRequiredId();
		secTenantName = secTenant.getRequiredTenantName();
		if( authorization != null ) {
			authorization.setSecTenant( secTenant );
		}
	}

	public CFLibDbKeyHash256 getSecTenantId() {
		return( secTenantId );
	}

	public void setSecUserName( String value ) {
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			throw new CFLibNullArgumentException( getClass(),
				"setUserName",
				1,
				"value" );
		}
		secUserName = value;
		secUser = null;
	}

	public String getSecUserName() {
		return( secUserName );
	}

	public ICFSecSecUserObj getSecUser() {
		if( secUser == null ) {
			if( authorization != null ) {
				secUser = getSecUserTableObj().readSecUserByIdIdx( authorization.getSecUserId() );
			}
			else {
				secUser = getSecUserTableObj().readSecUserByULoginIdx( secUserName );
				if( ( secUser == null ) && ( secSessionUserId != null ) ) {
					secUser = getSecUserTableObj().readSecUserByIdIdx( secSessionUserId );
				}
			}
			if( secUser != null ) {
				secUserName = secUser.getRequiredLoginId();
				secSessionUserId = secUser.getRequiredSecUserId();
			}
		}
		return( secUser );
	}

	public void setSecUser( ICFSecSecUserObj value ) {
		secUser = value;
		if( secUser != null ) {
			secUserName = secUser.getRequiredLoginId();
			secSessionUserId = secUser.getRequiredSecUserId();
		}
	}
	public ICFSecSecSessionObj getSecSession() {
		if( secSession == null ) {
			if( authorization != null ) {
				secSession = getSecSessionTableObj().readSecSessionByIdIdx( authorization.getSecSessionId() );
			}
			else if( secSessionSessionId != null ) {
				secSession = getSecSessionTableObj().readSecSessionByIdIdx( secSessionSessionId );
			}
			if( secSession != null ) {
				secSessionSessionId = secSession.getRequiredSecSessionId();
				secSessionUserId = secSession.getRequiredSecUserId();
			}
		}
		return( secSession );
	}

	public void setSecSession( ICFSecSecSessionObj value ) {
		secSession = value;
		if( secSession == null ) {
			return;
		}
		secSessionSessionId = secSession.getRequiredSecSessionId();
		secSessionUserId = secSession.getRequiredSecUserId();
		if( authorization != null ) {
			authorization.setSecSession( secSession );
		}
	}

	public void setSecSessionId( CFLibDbKeyHash256 value ) {
		secSessionSessionId = value;
	}

	public CFLibDbKeyHash256 getSecSessionSessionId() {
		return( secSessionSessionId );
	}

	public CFLibDbKeyHash256 getSecSessionUserId() {
		return( secSessionUserId );
	}

	public ICFSecAuthorization getAuthorization() {
		return( authorization );
	}

	public void setAuthorization( ICFSecAuthorization value ) {
		authorization = value;
	}

	@Override
	public ICFSecSchema getCFSecBackingStore() {
		return( cfsecBackingStore );
	}

	@Override
	public void setCFSecBackingStore(ICFSecSchema cfsecBackingStore) {
		if (cfsecBackingStore == null) {
			throw new CFLibNullArgumentException(getClass(), "setCFSecBackingStore", 1, "cfsecBackingStore");
		}
		this.cfsecBackingStore = cfsecBackingStore;
	}

	@Override
	public ICFIntSchema getCFIntBackingStore() {
		return( cfintBackingStore );
	}

	@Override
	public void setCFIntBackingStore(ICFIntSchema cfintBackingStore) {
		if (cfintBackingStore == null) {
			throw new CFLibNullArgumentException(getClass(), "setCFIntBackingStore", 1, "cfintBackingStore");
		}
		this.cfintBackingStore = cfintBackingStore;
	}

	@Override
	public ICFBamSchema getCFBamBackingStore() {
		return( cfbamBackingStore );
	}

	@Override
	public void setCFBamBackingStore(ICFBamSchema cfbamBackingStore) {
		if (cfbamBackingStore == null) {
			throw new CFLibNullArgumentException(getClass(), "setCFBamBackingStore", 1, "cfbamBackingStore");
		}
		this.cfbamBackingStore = cfbamBackingStore;
	}

	public String getSchemaName() {
		return( SCHEMA_NAME );
	}

	public void minimizeMemory() {
		if( atomTableObj != null ) {
			atomTableObj.minimizeMemory();
		}
		if( blobColTableObj != null ) {
			blobColTableObj.minimizeMemory();
		}
		if( blobDefTableObj != null ) {
			blobDefTableObj.minimizeMemory();
		}
		if( blobTypeTableObj != null ) {
			blobTypeTableObj.minimizeMemory();
		}
		if( boolColTableObj != null ) {
			boolColTableObj.minimizeMemory();
		}
		if( boolDefTableObj != null ) {
			boolDefTableObj.minimizeMemory();
		}
		if( boolTypeTableObj != null ) {
			boolTypeTableObj.minimizeMemory();
		}
		if( chainTableObj != null ) {
			chainTableObj.minimizeMemory();
		}
		if( clearDepTableObj != null ) {
			clearDepTableObj.minimizeMemory();
		}
		if( clearSubDep1TableObj != null ) {
			clearSubDep1TableObj.minimizeMemory();
		}
		if( clearSubDep2TableObj != null ) {
			clearSubDep2TableObj.minimizeMemory();
		}
		if( clearSubDep3TableObj != null ) {
			clearSubDep3TableObj.minimizeMemory();
		}
		if( clearTopDepTableObj != null ) {
			clearTopDepTableObj.minimizeMemory();
		}
		if( clusterTableObj != null ) {
			clusterTableObj.minimizeMemory();
		}
		if( dateColTableObj != null ) {
			dateColTableObj.minimizeMemory();
		}
		if( dateDefTableObj != null ) {
			dateDefTableObj.minimizeMemory();
		}
		if( dateTypeTableObj != null ) {
			dateTypeTableObj.minimizeMemory();
		}
		if( dbKeyHash128ColTableObj != null ) {
			dbKeyHash128ColTableObj.minimizeMemory();
		}
		if( dbKeyHash128DefTableObj != null ) {
			dbKeyHash128DefTableObj.minimizeMemory();
		}
		if( dbKeyHash128GenTableObj != null ) {
			dbKeyHash128GenTableObj.minimizeMemory();
		}
		if( dbKeyHash128TypeTableObj != null ) {
			dbKeyHash128TypeTableObj.minimizeMemory();
		}
		if( dbKeyHash160ColTableObj != null ) {
			dbKeyHash160ColTableObj.minimizeMemory();
		}
		if( dbKeyHash160DefTableObj != null ) {
			dbKeyHash160DefTableObj.minimizeMemory();
		}
		if( dbKeyHash160GenTableObj != null ) {
			dbKeyHash160GenTableObj.minimizeMemory();
		}
		if( dbKeyHash160TypeTableObj != null ) {
			dbKeyHash160TypeTableObj.minimizeMemory();
		}
		if( dbKeyHash224ColTableObj != null ) {
			dbKeyHash224ColTableObj.minimizeMemory();
		}
		if( dbKeyHash224DefTableObj != null ) {
			dbKeyHash224DefTableObj.minimizeMemory();
		}
		if( dbKeyHash224GenTableObj != null ) {
			dbKeyHash224GenTableObj.minimizeMemory();
		}
		if( dbKeyHash224TypeTableObj != null ) {
			dbKeyHash224TypeTableObj.minimizeMemory();
		}
		if( dbKeyHash256ColTableObj != null ) {
			dbKeyHash256ColTableObj.minimizeMemory();
		}
		if( dbKeyHash256DefTableObj != null ) {
			dbKeyHash256DefTableObj.minimizeMemory();
		}
		if( dbKeyHash256GenTableObj != null ) {
			dbKeyHash256GenTableObj.minimizeMemory();
		}
		if( dbKeyHash256TypeTableObj != null ) {
			dbKeyHash256TypeTableObj.minimizeMemory();
		}
		if( dbKeyHash384ColTableObj != null ) {
			dbKeyHash384ColTableObj.minimizeMemory();
		}
		if( dbKeyHash384DefTableObj != null ) {
			dbKeyHash384DefTableObj.minimizeMemory();
		}
		if( dbKeyHash384GenTableObj != null ) {
			dbKeyHash384GenTableObj.minimizeMemory();
		}
		if( dbKeyHash384TypeTableObj != null ) {
			dbKeyHash384TypeTableObj.minimizeMemory();
		}
		if( dbKeyHash512ColTableObj != null ) {
			dbKeyHash512ColTableObj.minimizeMemory();
		}
		if( dbKeyHash512DefTableObj != null ) {
			dbKeyHash512DefTableObj.minimizeMemory();
		}
		if( dbKeyHash512GenTableObj != null ) {
			dbKeyHash512GenTableObj.minimizeMemory();
		}
		if( dbKeyHash512TypeTableObj != null ) {
			dbKeyHash512TypeTableObj.minimizeMemory();
		}
		if( delDepTableObj != null ) {
			delDepTableObj.minimizeMemory();
		}
		if( delSubDep1TableObj != null ) {
			delSubDep1TableObj.minimizeMemory();
		}
		if( delSubDep2TableObj != null ) {
			delSubDep2TableObj.minimizeMemory();
		}
		if( delSubDep3TableObj != null ) {
			delSubDep3TableObj.minimizeMemory();
		}
		if( delTopDepTableObj != null ) {
			delTopDepTableObj.minimizeMemory();
		}
		if( doubleColTableObj != null ) {
			doubleColTableObj.minimizeMemory();
		}
		if( doubleDefTableObj != null ) {
			doubleDefTableObj.minimizeMemory();
		}
		if( doubleTypeTableObj != null ) {
			doubleTypeTableObj.minimizeMemory();
		}
		if( enumDefTableObj != null ) {
			enumDefTableObj.minimizeMemory();
		}
		if( enumTagTableObj != null ) {
			enumTagTableObj.minimizeMemory();
		}
		if( enumTypeTableObj != null ) {
			enumTypeTableObj.minimizeMemory();
		}
		if( floatColTableObj != null ) {
			floatColTableObj.minimizeMemory();
		}
		if( floatDefTableObj != null ) {
			floatDefTableObj.minimizeMemory();
		}
		if( floatTypeTableObj != null ) {
			floatTypeTableObj.minimizeMemory();
		}
		if( iSOCcyTableObj != null ) {
			iSOCcyTableObj.minimizeMemory();
		}
		if( iSOCtryTableObj != null ) {
			iSOCtryTableObj.minimizeMemory();
		}
		if( iSOCtryCcyTableObj != null ) {
			iSOCtryCcyTableObj.minimizeMemory();
		}
		if( iSOCtryLangTableObj != null ) {
			iSOCtryLangTableObj.minimizeMemory();
		}
		if( iSOLangTableObj != null ) {
			iSOLangTableObj.minimizeMemory();
		}
		if( iSOTZoneTableObj != null ) {
			iSOTZoneTableObj.minimizeMemory();
		}
		if( id16GenTableObj != null ) {
			id16GenTableObj.minimizeMemory();
		}
		if( id32GenTableObj != null ) {
			id32GenTableObj.minimizeMemory();
		}
		if( id64GenTableObj != null ) {
			id64GenTableObj.minimizeMemory();
		}
		if( indexTableObj != null ) {
			indexTableObj.minimizeMemory();
		}
		if( indexColTableObj != null ) {
			indexColTableObj.minimizeMemory();
		}
		if( int16ColTableObj != null ) {
			int16ColTableObj.minimizeMemory();
		}
		if( int16DefTableObj != null ) {
			int16DefTableObj.minimizeMemory();
		}
		if( int16TypeTableObj != null ) {
			int16TypeTableObj.minimizeMemory();
		}
		if( int32ColTableObj != null ) {
			int32ColTableObj.minimizeMemory();
		}
		if( int32DefTableObj != null ) {
			int32DefTableObj.minimizeMemory();
		}
		if( int32TypeTableObj != null ) {
			int32TypeTableObj.minimizeMemory();
		}
		if( int64ColTableObj != null ) {
			int64ColTableObj.minimizeMemory();
		}
		if( int64DefTableObj != null ) {
			int64DefTableObj.minimizeMemory();
		}
		if( int64TypeTableObj != null ) {
			int64TypeTableObj.minimizeMemory();
		}
		if( licenseTableObj != null ) {
			licenseTableObj.minimizeMemory();
		}
		if( majorVersionTableObj != null ) {
			majorVersionTableObj.minimizeMemory();
		}
		if( mimeTypeTableObj != null ) {
			mimeTypeTableObj.minimizeMemory();
		}
		if( minorVersionTableObj != null ) {
			minorVersionTableObj.minimizeMemory();
		}
		if( nmTokenColTableObj != null ) {
			nmTokenColTableObj.minimizeMemory();
		}
		if( nmTokenDefTableObj != null ) {
			nmTokenDefTableObj.minimizeMemory();
		}
		if( nmTokenTypeTableObj != null ) {
			nmTokenTypeTableObj.minimizeMemory();
		}
		if( nmTokensColTableObj != null ) {
			nmTokensColTableObj.minimizeMemory();
		}
		if( nmTokensDefTableObj != null ) {
			nmTokensDefTableObj.minimizeMemory();
		}
		if( nmTokensTypeTableObj != null ) {
			nmTokensTypeTableObj.minimizeMemory();
		}
		if( numberColTableObj != null ) {
			numberColTableObj.minimizeMemory();
		}
		if( numberDefTableObj != null ) {
			numberDefTableObj.minimizeMemory();
		}
		if( numberTypeTableObj != null ) {
			numberTypeTableObj.minimizeMemory();
		}
		if( paramTableObj != null ) {
			paramTableObj.minimizeMemory();
		}
		if( popDepTableObj != null ) {
			popDepTableObj.minimizeMemory();
		}
		if( popSubDep1TableObj != null ) {
			popSubDep1TableObj.minimizeMemory();
		}
		if( popSubDep2TableObj != null ) {
			popSubDep2TableObj.minimizeMemory();
		}
		if( popSubDep3TableObj != null ) {
			popSubDep3TableObj.minimizeMemory();
		}
		if( popTopDepTableObj != null ) {
			popTopDepTableObj.minimizeMemory();
		}
		if( relationTableObj != null ) {
			relationTableObj.minimizeMemory();
		}
		if( relationColTableObj != null ) {
			relationColTableObj.minimizeMemory();
		}
		if( schemaDefTableObj != null ) {
			schemaDefTableObj.minimizeMemory();
		}
		if( schemaRefTableObj != null ) {
			schemaRefTableObj.minimizeMemory();
		}
		if( scopeTableObj != null ) {
			scopeTableObj.minimizeMemory();
		}
		if( secClusGrpTableObj != null ) {
			secClusGrpTableObj.minimizeMemory();
		}
		if( secClusGrpIncTableObj != null ) {
			secClusGrpIncTableObj.minimizeMemory();
		}
		if( secClusGrpMembTableObj != null ) {
			secClusGrpMembTableObj.minimizeMemory();
		}
		if( secSessionTableObj != null ) {
			secSessionTableObj.minimizeMemory();
		}
		if( secSysGrpTableObj != null ) {
			secSysGrpTableObj.minimizeMemory();
		}
		if( secSysGrpIncTableObj != null ) {
			secSysGrpIncTableObj.minimizeMemory();
		}
		if( secSysGrpMembTableObj != null ) {
			secSysGrpMembTableObj.minimizeMemory();
		}
		if( secTentGrpTableObj != null ) {
			secTentGrpTableObj.minimizeMemory();
		}
		if( secTentGrpIncTableObj != null ) {
			secTentGrpIncTableObj.minimizeMemory();
		}
		if( secTentGrpMembTableObj != null ) {
			secTentGrpMembTableObj.minimizeMemory();
		}
		if( secUserTableObj != null ) {
			secUserTableObj.minimizeMemory();
		}
		if( secUserPWHistoryTableObj != null ) {
			secUserPWHistoryTableObj.minimizeMemory();
		}
		if( secUserPasswordTableObj != null ) {
			secUserPasswordTableObj.minimizeMemory();
		}
		if( serverListFuncTableObj != null ) {
			serverListFuncTableObj.minimizeMemory();
		}
		if( serverMethodTableObj != null ) {
			serverMethodTableObj.minimizeMemory();
		}
		if( serverObjFuncTableObj != null ) {
			serverObjFuncTableObj.minimizeMemory();
		}
		if( serverProcTableObj != null ) {
			serverProcTableObj.minimizeMemory();
		}
		if( stringColTableObj != null ) {
			stringColTableObj.minimizeMemory();
		}
		if( stringDefTableObj != null ) {
			stringDefTableObj.minimizeMemory();
		}
		if( stringTypeTableObj != null ) {
			stringTypeTableObj.minimizeMemory();
		}
		if( subProjectTableObj != null ) {
			subProjectTableObj.minimizeMemory();
		}
		if( sysClusterTableObj != null ) {
			sysClusterTableObj.minimizeMemory();
		}
		if( tZDateColTableObj != null ) {
			tZDateColTableObj.minimizeMemory();
		}
		if( tZDateDefTableObj != null ) {
			tZDateDefTableObj.minimizeMemory();
		}
		if( tZDateTypeTableObj != null ) {
			tZDateTypeTableObj.minimizeMemory();
		}
		if( tZTimeColTableObj != null ) {
			tZTimeColTableObj.minimizeMemory();
		}
		if( tZTimeDefTableObj != null ) {
			tZTimeDefTableObj.minimizeMemory();
		}
		if( tZTimeTypeTableObj != null ) {
			tZTimeTypeTableObj.minimizeMemory();
		}
		if( tZTimestampColTableObj != null ) {
			tZTimestampColTableObj.minimizeMemory();
		}
		if( tZTimestampDefTableObj != null ) {
			tZTimestampDefTableObj.minimizeMemory();
		}
		if( tZTimestampTypeTableObj != null ) {
			tZTimestampTypeTableObj.minimizeMemory();
		}
		if( tableTableObj != null ) {
			tableTableObj.minimizeMemory();
		}
		if( tableColTableObj != null ) {
			tableColTableObj.minimizeMemory();
		}
		if( tenantTableObj != null ) {
			tenantTableObj.minimizeMemory();
		}
		if( textColTableObj != null ) {
			textColTableObj.minimizeMemory();
		}
		if( textDefTableObj != null ) {
			textDefTableObj.minimizeMemory();
		}
		if( textTypeTableObj != null ) {
			textTypeTableObj.minimizeMemory();
		}
		if( timeColTableObj != null ) {
			timeColTableObj.minimizeMemory();
		}
		if( timeDefTableObj != null ) {
			timeDefTableObj.minimizeMemory();
		}
		if( timeTypeTableObj != null ) {
			timeTypeTableObj.minimizeMemory();
		}
		if( timestampColTableObj != null ) {
			timestampColTableObj.minimizeMemory();
		}
		if( timestampDefTableObj != null ) {
			timestampDefTableObj.minimizeMemory();
		}
		if( timestampTypeTableObj != null ) {
			timestampTypeTableObj.minimizeMemory();
		}
		if( tldTableObj != null ) {
			tldTableObj.minimizeMemory();
		}
		if( tokenColTableObj != null ) {
			tokenColTableObj.minimizeMemory();
		}
		if( tokenDefTableObj != null ) {
			tokenDefTableObj.minimizeMemory();
		}
		if( tokenTypeTableObj != null ) {
			tokenTypeTableObj.minimizeMemory();
		}
		if( topDomainTableObj != null ) {
			topDomainTableObj.minimizeMemory();
		}
		if( topProjectTableObj != null ) {
			topProjectTableObj.minimizeMemory();
		}
		if( uInt16ColTableObj != null ) {
			uInt16ColTableObj.minimizeMemory();
		}
		if( uInt16DefTableObj != null ) {
			uInt16DefTableObj.minimizeMemory();
		}
		if( uInt16TypeTableObj != null ) {
			uInt16TypeTableObj.minimizeMemory();
		}
		if( uInt32ColTableObj != null ) {
			uInt32ColTableObj.minimizeMemory();
		}
		if( uInt32DefTableObj != null ) {
			uInt32DefTableObj.minimizeMemory();
		}
		if( uInt32TypeTableObj != null ) {
			uInt32TypeTableObj.minimizeMemory();
		}
		if( uInt64ColTableObj != null ) {
			uInt64ColTableObj.minimizeMemory();
		}
		if( uInt64DefTableObj != null ) {
			uInt64DefTableObj.minimizeMemory();
		}
		if( uInt64TypeTableObj != null ) {
			uInt64TypeTableObj.minimizeMemory();
		}
		if( uRLProtocolTableObj != null ) {
			uRLProtocolTableObj.minimizeMemory();
		}
		if( uuid6ColTableObj != null ) {
			uuid6ColTableObj.minimizeMemory();
		}
		if( uuid6DefTableObj != null ) {
			uuid6DefTableObj.minimizeMemory();
		}
		if( uuid6GenTableObj != null ) {
			uuid6GenTableObj.minimizeMemory();
		}
		if( uuid6TypeTableObj != null ) {
			uuid6TypeTableObj.minimizeMemory();
		}
		if( uuidColTableObj != null ) {
			uuidColTableObj.minimizeMemory();
		}
		if( uuidDefTableObj != null ) {
			uuidDefTableObj.minimizeMemory();
		}
		if( uuidGenTableObj != null ) {
			uuidGenTableObj.minimizeMemory();
		}
		if( uuidTypeTableObj != null ) {
			uuidTypeTableObj.minimizeMemory();
		}
		if( valueTableObj != null ) {
			valueTableObj.minimizeMemory();
		}
	}

	public void logout() {
		if( authorization == null ) {
			return;
		}
		setAuthorization( null );
		CFLibDbKeyHash256 secSessionId = authorization.getSecSessionId();
		if( secSessionId != null ) {
			ICFSecSecSessionObj secSession = getSecSessionTableObj().readSecSessionByIdIdx( secSessionId );
			if( secSession != null ) {
				if( secSession.getOptionalFinish() == null ) {
					ICFSecSecSessionEditObj editSecSession = secSession.beginEdit();
					editSecSession.setOptionalFinish( LocalDateTime.now() );
					editSecSession.update();
					editSecSession = null;
				}
			}
		}
	}

	public ICFBamAtomTableObj getAtomTableObj() {
		return( atomTableObj );
	}

	public ICFBamBlobColTableObj getBlobColTableObj() {
		return( blobColTableObj );
	}

	public ICFBamBlobDefTableObj getBlobDefTableObj() {
		return( blobDefTableObj );
	}

	public ICFBamBlobTypeTableObj getBlobTypeTableObj() {
		return( blobTypeTableObj );
	}

	public ICFBamBoolColTableObj getBoolColTableObj() {
		return( boolColTableObj );
	}

	public ICFBamBoolDefTableObj getBoolDefTableObj() {
		return( boolDefTableObj );
	}

	public ICFBamBoolTypeTableObj getBoolTypeTableObj() {
		return( boolTypeTableObj );
	}

	public ICFBamChainTableObj getChainTableObj() {
		return( chainTableObj );
	}

	public ICFBamClearDepTableObj getClearDepTableObj() {
		return( clearDepTableObj );
	}

	public ICFBamClearSubDep1TableObj getClearSubDep1TableObj() {
		return( clearSubDep1TableObj );
	}

	public ICFBamClearSubDep2TableObj getClearSubDep2TableObj() {
		return( clearSubDep2TableObj );
	}

	public ICFBamClearSubDep3TableObj getClearSubDep3TableObj() {
		return( clearSubDep3TableObj );
	}

	public ICFBamClearTopDepTableObj getClearTopDepTableObj() {
		return( clearTopDepTableObj );
	}

	public ICFBamClusterTableObj getClusterTableObj() {
		return( clusterTableObj );
	}

	public ICFBamDateColTableObj getDateColTableObj() {
		return( dateColTableObj );
	}

	public ICFBamDateDefTableObj getDateDefTableObj() {
		return( dateDefTableObj );
	}

	public ICFBamDateTypeTableObj getDateTypeTableObj() {
		return( dateTypeTableObj );
	}

	public ICFBamDbKeyHash128ColTableObj getDbKeyHash128ColTableObj() {
		return( dbKeyHash128ColTableObj );
	}

	public ICFBamDbKeyHash128DefTableObj getDbKeyHash128DefTableObj() {
		return( dbKeyHash128DefTableObj );
	}

	public ICFBamDbKeyHash128GenTableObj getDbKeyHash128GenTableObj() {
		return( dbKeyHash128GenTableObj );
	}

	public ICFBamDbKeyHash128TypeTableObj getDbKeyHash128TypeTableObj() {
		return( dbKeyHash128TypeTableObj );
	}

	public ICFBamDbKeyHash160ColTableObj getDbKeyHash160ColTableObj() {
		return( dbKeyHash160ColTableObj );
	}

	public ICFBamDbKeyHash160DefTableObj getDbKeyHash160DefTableObj() {
		return( dbKeyHash160DefTableObj );
	}

	public ICFBamDbKeyHash160GenTableObj getDbKeyHash160GenTableObj() {
		return( dbKeyHash160GenTableObj );
	}

	public ICFBamDbKeyHash160TypeTableObj getDbKeyHash160TypeTableObj() {
		return( dbKeyHash160TypeTableObj );
	}

	public ICFBamDbKeyHash224ColTableObj getDbKeyHash224ColTableObj() {
		return( dbKeyHash224ColTableObj );
	}

	public ICFBamDbKeyHash224DefTableObj getDbKeyHash224DefTableObj() {
		return( dbKeyHash224DefTableObj );
	}

	public ICFBamDbKeyHash224GenTableObj getDbKeyHash224GenTableObj() {
		return( dbKeyHash224GenTableObj );
	}

	public ICFBamDbKeyHash224TypeTableObj getDbKeyHash224TypeTableObj() {
		return( dbKeyHash224TypeTableObj );
	}

	public ICFBamDbKeyHash256ColTableObj getDbKeyHash256ColTableObj() {
		return( dbKeyHash256ColTableObj );
	}

	public ICFBamDbKeyHash256DefTableObj getDbKeyHash256DefTableObj() {
		return( dbKeyHash256DefTableObj );
	}

	public ICFBamDbKeyHash256GenTableObj getDbKeyHash256GenTableObj() {
		return( dbKeyHash256GenTableObj );
	}

	public ICFBamDbKeyHash256TypeTableObj getDbKeyHash256TypeTableObj() {
		return( dbKeyHash256TypeTableObj );
	}

	public ICFBamDbKeyHash384ColTableObj getDbKeyHash384ColTableObj() {
		return( dbKeyHash384ColTableObj );
	}

	public ICFBamDbKeyHash384DefTableObj getDbKeyHash384DefTableObj() {
		return( dbKeyHash384DefTableObj );
	}

	public ICFBamDbKeyHash384GenTableObj getDbKeyHash384GenTableObj() {
		return( dbKeyHash384GenTableObj );
	}

	public ICFBamDbKeyHash384TypeTableObj getDbKeyHash384TypeTableObj() {
		return( dbKeyHash384TypeTableObj );
	}

	public ICFBamDbKeyHash512ColTableObj getDbKeyHash512ColTableObj() {
		return( dbKeyHash512ColTableObj );
	}

	public ICFBamDbKeyHash512DefTableObj getDbKeyHash512DefTableObj() {
		return( dbKeyHash512DefTableObj );
	}

	public ICFBamDbKeyHash512GenTableObj getDbKeyHash512GenTableObj() {
		return( dbKeyHash512GenTableObj );
	}

	public ICFBamDbKeyHash512TypeTableObj getDbKeyHash512TypeTableObj() {
		return( dbKeyHash512TypeTableObj );
	}

	public ICFBamDelDepTableObj getDelDepTableObj() {
		return( delDepTableObj );
	}

	public ICFBamDelSubDep1TableObj getDelSubDep1TableObj() {
		return( delSubDep1TableObj );
	}

	public ICFBamDelSubDep2TableObj getDelSubDep2TableObj() {
		return( delSubDep2TableObj );
	}

	public ICFBamDelSubDep3TableObj getDelSubDep3TableObj() {
		return( delSubDep3TableObj );
	}

	public ICFBamDelTopDepTableObj getDelTopDepTableObj() {
		return( delTopDepTableObj );
	}

	public ICFBamDoubleColTableObj getDoubleColTableObj() {
		return( doubleColTableObj );
	}

	public ICFBamDoubleDefTableObj getDoubleDefTableObj() {
		return( doubleDefTableObj );
	}

	public ICFBamDoubleTypeTableObj getDoubleTypeTableObj() {
		return( doubleTypeTableObj );
	}

	public ICFBamEnumDefTableObj getEnumDefTableObj() {
		return( enumDefTableObj );
	}

	public ICFBamEnumTagTableObj getEnumTagTableObj() {
		return( enumTagTableObj );
	}

	public ICFBamEnumTypeTableObj getEnumTypeTableObj() {
		return( enumTypeTableObj );
	}

	public ICFBamFloatColTableObj getFloatColTableObj() {
		return( floatColTableObj );
	}

	public ICFBamFloatDefTableObj getFloatDefTableObj() {
		return( floatDefTableObj );
	}

	public ICFBamFloatTypeTableObj getFloatTypeTableObj() {
		return( floatTypeTableObj );
	}

	public ICFBamISOCcyTableObj getISOCcyTableObj() {
		return( iSOCcyTableObj );
	}

	public ICFBamISOCtryTableObj getISOCtryTableObj() {
		return( iSOCtryTableObj );
	}

	public ICFBamISOCtryCcyTableObj getISOCtryCcyTableObj() {
		return( iSOCtryCcyTableObj );
	}

	public ICFBamISOCtryLangTableObj getISOCtryLangTableObj() {
		return( iSOCtryLangTableObj );
	}

	public ICFBamISOLangTableObj getISOLangTableObj() {
		return( iSOLangTableObj );
	}

	public ICFBamISOTZoneTableObj getISOTZoneTableObj() {
		return( iSOTZoneTableObj );
	}

	public ICFBamId16GenTableObj getId16GenTableObj() {
		return( id16GenTableObj );
	}

	public ICFBamId32GenTableObj getId32GenTableObj() {
		return( id32GenTableObj );
	}

	public ICFBamId64GenTableObj getId64GenTableObj() {
		return( id64GenTableObj );
	}

	public ICFBamIndexTableObj getIndexTableObj() {
		return( indexTableObj );
	}

	public ICFBamIndexColTableObj getIndexColTableObj() {
		return( indexColTableObj );
	}

	public ICFBamInt16ColTableObj getInt16ColTableObj() {
		return( int16ColTableObj );
	}

	public ICFBamInt16DefTableObj getInt16DefTableObj() {
		return( int16DefTableObj );
	}

	public ICFBamInt16TypeTableObj getInt16TypeTableObj() {
		return( int16TypeTableObj );
	}

	public ICFBamInt32ColTableObj getInt32ColTableObj() {
		return( int32ColTableObj );
	}

	public ICFBamInt32DefTableObj getInt32DefTableObj() {
		return( int32DefTableObj );
	}

	public ICFBamInt32TypeTableObj getInt32TypeTableObj() {
		return( int32TypeTableObj );
	}

	public ICFBamInt64ColTableObj getInt64ColTableObj() {
		return( int64ColTableObj );
	}

	public ICFBamInt64DefTableObj getInt64DefTableObj() {
		return( int64DefTableObj );
	}

	public ICFBamInt64TypeTableObj getInt64TypeTableObj() {
		return( int64TypeTableObj );
	}

	public ICFBamLicenseTableObj getLicenseTableObj() {
		return( licenseTableObj );
	}

	public ICFBamMajorVersionTableObj getMajorVersionTableObj() {
		return( majorVersionTableObj );
	}

	public ICFBamMimeTypeTableObj getMimeTypeTableObj() {
		return( mimeTypeTableObj );
	}

	public ICFBamMinorVersionTableObj getMinorVersionTableObj() {
		return( minorVersionTableObj );
	}

	public ICFBamNmTokenColTableObj getNmTokenColTableObj() {
		return( nmTokenColTableObj );
	}

	public ICFBamNmTokenDefTableObj getNmTokenDefTableObj() {
		return( nmTokenDefTableObj );
	}

	public ICFBamNmTokenTypeTableObj getNmTokenTypeTableObj() {
		return( nmTokenTypeTableObj );
	}

	public ICFBamNmTokensColTableObj getNmTokensColTableObj() {
		return( nmTokensColTableObj );
	}

	public ICFBamNmTokensDefTableObj getNmTokensDefTableObj() {
		return( nmTokensDefTableObj );
	}

	public ICFBamNmTokensTypeTableObj getNmTokensTypeTableObj() {
		return( nmTokensTypeTableObj );
	}

	public ICFBamNumberColTableObj getNumberColTableObj() {
		return( numberColTableObj );
	}

	public ICFBamNumberDefTableObj getNumberDefTableObj() {
		return( numberDefTableObj );
	}

	public ICFBamNumberTypeTableObj getNumberTypeTableObj() {
		return( numberTypeTableObj );
	}

	public ICFBamParamTableObj getParamTableObj() {
		return( paramTableObj );
	}

	public ICFBamPopDepTableObj getPopDepTableObj() {
		return( popDepTableObj );
	}

	public ICFBamPopSubDep1TableObj getPopSubDep1TableObj() {
		return( popSubDep1TableObj );
	}

	public ICFBamPopSubDep2TableObj getPopSubDep2TableObj() {
		return( popSubDep2TableObj );
	}

	public ICFBamPopSubDep3TableObj getPopSubDep3TableObj() {
		return( popSubDep3TableObj );
	}

	public ICFBamPopTopDepTableObj getPopTopDepTableObj() {
		return( popTopDepTableObj );
	}

	public ICFBamRelationTableObj getRelationTableObj() {
		return( relationTableObj );
	}

	public ICFBamRelationColTableObj getRelationColTableObj() {
		return( relationColTableObj );
	}

	public ICFBamSchemaDefTableObj getSchemaDefTableObj() {
		return( schemaDefTableObj );
	}

	public ICFBamSchemaRefTableObj getSchemaRefTableObj() {
		return( schemaRefTableObj );
	}

	public ICFBamScopeTableObj getScopeTableObj() {
		return( scopeTableObj );
	}

	public ICFBamSecClusGrpTableObj getSecClusGrpTableObj() {
		return( secClusGrpTableObj );
	}

	public ICFBamSecClusGrpIncTableObj getSecClusGrpIncTableObj() {
		return( secClusGrpIncTableObj );
	}

	public ICFBamSecClusGrpMembTableObj getSecClusGrpMembTableObj() {
		return( secClusGrpMembTableObj );
	}

	public ICFBamSecSessionTableObj getSecSessionTableObj() {
		return( secSessionTableObj );
	}

	public ICFBamSecSysGrpTableObj getSecSysGrpTableObj() {
		return( secSysGrpTableObj );
	}

	public ICFBamSecSysGrpIncTableObj getSecSysGrpIncTableObj() {
		return( secSysGrpIncTableObj );
	}

	public ICFBamSecSysGrpMembTableObj getSecSysGrpMembTableObj() {
		return( secSysGrpMembTableObj );
	}

	public ICFBamSecTentGrpTableObj getSecTentGrpTableObj() {
		return( secTentGrpTableObj );
	}

	public ICFBamSecTentGrpIncTableObj getSecTentGrpIncTableObj() {
		return( secTentGrpIncTableObj );
	}

	public ICFBamSecTentGrpMembTableObj getSecTentGrpMembTableObj() {
		return( secTentGrpMembTableObj );
	}

	public ICFBamSecUserTableObj getSecUserTableObj() {
		return( secUserTableObj );
	}

	public ICFBamSecUserPWHistoryTableObj getSecUserPWHistoryTableObj() {
		return( secUserPWHistoryTableObj );
	}

	public ICFBamSecUserPasswordTableObj getSecUserPasswordTableObj() {
		return( secUserPasswordTableObj );
	}

	public ICFBamServerListFuncTableObj getServerListFuncTableObj() {
		return( serverListFuncTableObj );
	}

	public ICFBamServerMethodTableObj getServerMethodTableObj() {
		return( serverMethodTableObj );
	}

	public ICFBamServerObjFuncTableObj getServerObjFuncTableObj() {
		return( serverObjFuncTableObj );
	}

	public ICFBamServerProcTableObj getServerProcTableObj() {
		return( serverProcTableObj );
	}

	public ICFBamStringColTableObj getStringColTableObj() {
		return( stringColTableObj );
	}

	public ICFBamStringDefTableObj getStringDefTableObj() {
		return( stringDefTableObj );
	}

	public ICFBamStringTypeTableObj getStringTypeTableObj() {
		return( stringTypeTableObj );
	}

	public ICFBamSubProjectTableObj getSubProjectTableObj() {
		return( subProjectTableObj );
	}

	public ICFBamSysClusterTableObj getSysClusterTableObj() {
		return( sysClusterTableObj );
	}

	public ICFBamTZDateColTableObj getTZDateColTableObj() {
		return( tZDateColTableObj );
	}

	public ICFBamTZDateDefTableObj getTZDateDefTableObj() {
		return( tZDateDefTableObj );
	}

	public ICFBamTZDateTypeTableObj getTZDateTypeTableObj() {
		return( tZDateTypeTableObj );
	}

	public ICFBamTZTimeColTableObj getTZTimeColTableObj() {
		return( tZTimeColTableObj );
	}

	public ICFBamTZTimeDefTableObj getTZTimeDefTableObj() {
		return( tZTimeDefTableObj );
	}

	public ICFBamTZTimeTypeTableObj getTZTimeTypeTableObj() {
		return( tZTimeTypeTableObj );
	}

	public ICFBamTZTimestampColTableObj getTZTimestampColTableObj() {
		return( tZTimestampColTableObj );
	}

	public ICFBamTZTimestampDefTableObj getTZTimestampDefTableObj() {
		return( tZTimestampDefTableObj );
	}

	public ICFBamTZTimestampTypeTableObj getTZTimestampTypeTableObj() {
		return( tZTimestampTypeTableObj );
	}

	public ICFBamTableTableObj getTableTableObj() {
		return( tableTableObj );
	}

	public ICFBamTableColTableObj getTableColTableObj() {
		return( tableColTableObj );
	}

	public ICFBamTenantTableObj getTenantTableObj() {
		return( tenantTableObj );
	}

	public ICFBamTextColTableObj getTextColTableObj() {
		return( textColTableObj );
	}

	public ICFBamTextDefTableObj getTextDefTableObj() {
		return( textDefTableObj );
	}

	public ICFBamTextTypeTableObj getTextTypeTableObj() {
		return( textTypeTableObj );
	}

	public ICFBamTimeColTableObj getTimeColTableObj() {
		return( timeColTableObj );
	}

	public ICFBamTimeDefTableObj getTimeDefTableObj() {
		return( timeDefTableObj );
	}

	public ICFBamTimeTypeTableObj getTimeTypeTableObj() {
		return( timeTypeTableObj );
	}

	public ICFBamTimestampColTableObj getTimestampColTableObj() {
		return( timestampColTableObj );
	}

	public ICFBamTimestampDefTableObj getTimestampDefTableObj() {
		return( timestampDefTableObj );
	}

	public ICFBamTimestampTypeTableObj getTimestampTypeTableObj() {
		return( timestampTypeTableObj );
	}

	public ICFBamTldTableObj getTldTableObj() {
		return( tldTableObj );
	}

	public ICFBamTokenColTableObj getTokenColTableObj() {
		return( tokenColTableObj );
	}

	public ICFBamTokenDefTableObj getTokenDefTableObj() {
		return( tokenDefTableObj );
	}

	public ICFBamTokenTypeTableObj getTokenTypeTableObj() {
		return( tokenTypeTableObj );
	}

	public ICFBamTopDomainTableObj getTopDomainTableObj() {
		return( topDomainTableObj );
	}

	public ICFBamTopProjectTableObj getTopProjectTableObj() {
		return( topProjectTableObj );
	}

	public ICFBamUInt16ColTableObj getUInt16ColTableObj() {
		return( uInt16ColTableObj );
	}

	public ICFBamUInt16DefTableObj getUInt16DefTableObj() {
		return( uInt16DefTableObj );
	}

	public ICFBamUInt16TypeTableObj getUInt16TypeTableObj() {
		return( uInt16TypeTableObj );
	}

	public ICFBamUInt32ColTableObj getUInt32ColTableObj() {
		return( uInt32ColTableObj );
	}

	public ICFBamUInt32DefTableObj getUInt32DefTableObj() {
		return( uInt32DefTableObj );
	}

	public ICFBamUInt32TypeTableObj getUInt32TypeTableObj() {
		return( uInt32TypeTableObj );
	}

	public ICFBamUInt64ColTableObj getUInt64ColTableObj() {
		return( uInt64ColTableObj );
	}

	public ICFBamUInt64DefTableObj getUInt64DefTableObj() {
		return( uInt64DefTableObj );
	}

	public ICFBamUInt64TypeTableObj getUInt64TypeTableObj() {
		return( uInt64TypeTableObj );
	}

	public ICFBamURLProtocolTableObj getURLProtocolTableObj() {
		return( uRLProtocolTableObj );
	}

	public ICFBamUuid6ColTableObj getUuid6ColTableObj() {
		return( uuid6ColTableObj );
	}

	public ICFBamUuid6DefTableObj getUuid6DefTableObj() {
		return( uuid6DefTableObj );
	}

	public ICFBamUuid6GenTableObj getUuid6GenTableObj() {
		return( uuid6GenTableObj );
	}

	public ICFBamUuid6TypeTableObj getUuid6TypeTableObj() {
		return( uuid6TypeTableObj );
	}

	public ICFBamUuidColTableObj getUuidColTableObj() {
		return( uuidColTableObj );
	}

	public ICFBamUuidDefTableObj getUuidDefTableObj() {
		return( uuidDefTableObj );
	}

	public ICFBamUuidGenTableObj getUuidGenTableObj() {
		return( uuidGenTableObj );
	}

	public ICFBamUuidTypeTableObj getUuidTypeTableObj() {
		return( uuidTypeTableObj );
	}

	public ICFBamValueTableObj getValueTableObj() {
		return( valueTableObj );
	}
}
