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

@Service("cfbam31BuffFactoryService")
public class CFBamBuffFactoryService
	implements ICFBamFactory
{

	@Autowired
	@Qualifier("cfbam31BuffAtomFactoryService")
	protected CFBamBuffAtomFactoryService atomFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffBlobColFactoryService")
	protected CFBamBuffBlobColFactoryService blobcolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffBlobDefFactoryService")
	protected CFBamBuffBlobDefFactoryService blobdefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffBlobTypeFactoryService")
	protected CFBamBuffBlobTypeFactoryService blobtypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffBoolColFactoryService")
	protected CFBamBuffBoolColFactoryService boolcolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffBoolDefFactoryService")
	protected CFBamBuffBoolDefFactoryService booldefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffBoolTypeFactoryService")
	protected CFBamBuffBoolTypeFactoryService booltypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffChainFactoryService")
	protected CFBamBuffChainFactoryService chainFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffClearDepFactoryService")
	protected CFBamBuffClearDepFactoryService cleardepFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffClearSubDep1FactoryService")
	protected CFBamBuffClearSubDep1FactoryService clearsubdep1FactoryService;

	@Autowired
	@Qualifier("cfbam31BuffClearSubDep2FactoryService")
	protected CFBamBuffClearSubDep2FactoryService clearsubdep2FactoryService;

	@Autowired
	@Qualifier("cfbam31BuffClearSubDep3FactoryService")
	protected CFBamBuffClearSubDep3FactoryService clearsubdep3FactoryService;

	@Autowired
	@Qualifier("cfbam31BuffClearTopDepFactoryService")
	protected CFBamBuffClearTopDepFactoryService cleartopdepFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDateColFactoryService")
	protected CFBamBuffDateColFactoryService datecolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDateDefFactoryService")
	protected CFBamBuffDateDefFactoryService datedefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDateTypeFactoryService")
	protected CFBamBuffDateTypeFactoryService datetypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash128ColFactoryService")
	protected CFBamBuffDbKeyHash128ColFactoryService dbkeyhash128colFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash128DefFactoryService")
	protected CFBamBuffDbKeyHash128DefFactoryService dbkeyhash128defFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash128GenFactoryService")
	protected CFBamBuffDbKeyHash128GenFactoryService dbkeyhash128genFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash128TypeFactoryService")
	protected CFBamBuffDbKeyHash128TypeFactoryService dbkeyhash128typeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash160ColFactoryService")
	protected CFBamBuffDbKeyHash160ColFactoryService dbkeyhash160colFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash160DefFactoryService")
	protected CFBamBuffDbKeyHash160DefFactoryService dbkeyhash160defFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash160GenFactoryService")
	protected CFBamBuffDbKeyHash160GenFactoryService dbkeyhash160genFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash160TypeFactoryService")
	protected CFBamBuffDbKeyHash160TypeFactoryService dbkeyhash160typeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash224ColFactoryService")
	protected CFBamBuffDbKeyHash224ColFactoryService dbkeyhash224colFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash224DefFactoryService")
	protected CFBamBuffDbKeyHash224DefFactoryService dbkeyhash224defFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash224GenFactoryService")
	protected CFBamBuffDbKeyHash224GenFactoryService dbkeyhash224genFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash224TypeFactoryService")
	protected CFBamBuffDbKeyHash224TypeFactoryService dbkeyhash224typeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash256ColFactoryService")
	protected CFBamBuffDbKeyHash256ColFactoryService dbkeyhash256colFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash256DefFactoryService")
	protected CFBamBuffDbKeyHash256DefFactoryService dbkeyhash256defFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash256GenFactoryService")
	protected CFBamBuffDbKeyHash256GenFactoryService dbkeyhash256genFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash256TypeFactoryService")
	protected CFBamBuffDbKeyHash256TypeFactoryService dbkeyhash256typeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash384ColFactoryService")
	protected CFBamBuffDbKeyHash384ColFactoryService dbkeyhash384colFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash384DefFactoryService")
	protected CFBamBuffDbKeyHash384DefFactoryService dbkeyhash384defFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash384GenFactoryService")
	protected CFBamBuffDbKeyHash384GenFactoryService dbkeyhash384genFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash384TypeFactoryService")
	protected CFBamBuffDbKeyHash384TypeFactoryService dbkeyhash384typeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash512ColFactoryService")
	protected CFBamBuffDbKeyHash512ColFactoryService dbkeyhash512colFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash512DefFactoryService")
	protected CFBamBuffDbKeyHash512DefFactoryService dbkeyhash512defFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash512GenFactoryService")
	protected CFBamBuffDbKeyHash512GenFactoryService dbkeyhash512genFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDbKeyHash512TypeFactoryService")
	protected CFBamBuffDbKeyHash512TypeFactoryService dbkeyhash512typeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDelDepFactoryService")
	protected CFBamBuffDelDepFactoryService deldepFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDelSubDep1FactoryService")
	protected CFBamBuffDelSubDep1FactoryService delsubdep1FactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDelSubDep2FactoryService")
	protected CFBamBuffDelSubDep2FactoryService delsubdep2FactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDelSubDep3FactoryService")
	protected CFBamBuffDelSubDep3FactoryService delsubdep3FactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDelTopDepFactoryService")
	protected CFBamBuffDelTopDepFactoryService deltopdepFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDoubleColFactoryService")
	protected CFBamBuffDoubleColFactoryService doublecolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDoubleDefFactoryService")
	protected CFBamBuffDoubleDefFactoryService doubledefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffDoubleTypeFactoryService")
	protected CFBamBuffDoubleTypeFactoryService doubletypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffEnumDefFactoryService")
	protected CFBamBuffEnumDefFactoryService enumdefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffEnumTagFactoryService")
	protected CFBamBuffEnumTagFactoryService enumtagFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffEnumTypeFactoryService")
	protected CFBamBuffEnumTypeFactoryService enumtypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffFloatColFactoryService")
	protected CFBamBuffFloatColFactoryService floatcolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffFloatDefFactoryService")
	protected CFBamBuffFloatDefFactoryService floatdefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffFloatTypeFactoryService")
	protected CFBamBuffFloatTypeFactoryService floattypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffId16GenFactoryService")
	protected CFBamBuffId16GenFactoryService id16genFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffId32GenFactoryService")
	protected CFBamBuffId32GenFactoryService id32genFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffId64GenFactoryService")
	protected CFBamBuffId64GenFactoryService id64genFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffIndexFactoryService")
	protected CFBamBuffIndexFactoryService indexFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffIndexColFactoryService")
	protected CFBamBuffIndexColFactoryService indexcolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffIndexTweakFactoryService")
	protected CFBamBuffIndexTweakFactoryService indextweakFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffInt16ColFactoryService")
	protected CFBamBuffInt16ColFactoryService int16colFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffInt16DefFactoryService")
	protected CFBamBuffInt16DefFactoryService int16defFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffInt16TypeFactoryService")
	protected CFBamBuffInt16TypeFactoryService int16typeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffInt32ColFactoryService")
	protected CFBamBuffInt32ColFactoryService int32colFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffInt32DefFactoryService")
	protected CFBamBuffInt32DefFactoryService int32defFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffInt32TypeFactoryService")
	protected CFBamBuffInt32TypeFactoryService int32typeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffInt64ColFactoryService")
	protected CFBamBuffInt64ColFactoryService int64colFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffInt64DefFactoryService")
	protected CFBamBuffInt64DefFactoryService int64defFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffInt64TypeFactoryService")
	protected CFBamBuffInt64TypeFactoryService int64typeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffNmTokenColFactoryService")
	protected CFBamBuffNmTokenColFactoryService nmtokencolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffNmTokenDefFactoryService")
	protected CFBamBuffNmTokenDefFactoryService nmtokendefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffNmTokenTypeFactoryService")
	protected CFBamBuffNmTokenTypeFactoryService nmtokentypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffNmTokensColFactoryService")
	protected CFBamBuffNmTokensColFactoryService nmtokenscolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffNmTokensDefFactoryService")
	protected CFBamBuffNmTokensDefFactoryService nmtokensdefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffNmTokensTypeFactoryService")
	protected CFBamBuffNmTokensTypeFactoryService nmtokenstypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffNumberColFactoryService")
	protected CFBamBuffNumberColFactoryService numbercolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffNumberDefFactoryService")
	protected CFBamBuffNumberDefFactoryService numberdefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffNumberTypeFactoryService")
	protected CFBamBuffNumberTypeFactoryService numbertypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffParamFactoryService")
	protected CFBamBuffParamFactoryService paramFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffPopDepFactoryService")
	protected CFBamBuffPopDepFactoryService popdepFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffPopSubDep1FactoryService")
	protected CFBamBuffPopSubDep1FactoryService popsubdep1FactoryService;

	@Autowired
	@Qualifier("cfbam31BuffPopSubDep2FactoryService")
	protected CFBamBuffPopSubDep2FactoryService popsubdep2FactoryService;

	@Autowired
	@Qualifier("cfbam31BuffPopSubDep3FactoryService")
	protected CFBamBuffPopSubDep3FactoryService popsubdep3FactoryService;

	@Autowired
	@Qualifier("cfbam31BuffPopTopDepFactoryService")
	protected CFBamBuffPopTopDepFactoryService poptopdepFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffRelationFactoryService")
	protected CFBamBuffRelationFactoryService relationFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffRelationColFactoryService")
	protected CFBamBuffRelationColFactoryService relationcolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffRoleDefFactoryService")
	protected CFBamBuffRoleDefFactoryService roledefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffSchemaDefFactoryService")
	protected CFBamBuffSchemaDefFactoryService schemadefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffSchemaRefFactoryService")
	protected CFBamBuffSchemaRefFactoryService schemarefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffSchemaRoleFactoryService")
	protected CFBamBuffSchemaRoleFactoryService schemaroleFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffSchemaTweakFactoryService")
	protected CFBamBuffSchemaTweakFactoryService schematweakFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffScopeFactoryService")
	protected CFBamBuffScopeFactoryService scopeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffServerListFuncFactoryService")
	protected CFBamBuffServerListFuncFactoryService serverlistfuncFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffServerMethodFactoryService")
	protected CFBamBuffServerMethodFactoryService servermethodFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffServerObjFuncFactoryService")
	protected CFBamBuffServerObjFuncFactoryService serverobjfuncFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffServerProcFactoryService")
	protected CFBamBuffServerProcFactoryService serverprocFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffStringColFactoryService")
	protected CFBamBuffStringColFactoryService stringcolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffStringDefFactoryService")
	protected CFBamBuffStringDefFactoryService stringdefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffStringTypeFactoryService")
	protected CFBamBuffStringTypeFactoryService stringtypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTZDateColFactoryService")
	protected CFBamBuffTZDateColFactoryService tzdatecolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTZDateDefFactoryService")
	protected CFBamBuffTZDateDefFactoryService tzdatedefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTZDateTypeFactoryService")
	protected CFBamBuffTZDateTypeFactoryService tzdatetypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTZTimeColFactoryService")
	protected CFBamBuffTZTimeColFactoryService tztimecolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTZTimeDefFactoryService")
	protected CFBamBuffTZTimeDefFactoryService tztimedefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTZTimeTypeFactoryService")
	protected CFBamBuffTZTimeTypeFactoryService tztimetypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTZTimestampColFactoryService")
	protected CFBamBuffTZTimestampColFactoryService tztimestampcolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTZTimestampDefFactoryService")
	protected CFBamBuffTZTimestampDefFactoryService tztimestampdefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTZTimestampTypeFactoryService")
	protected CFBamBuffTZTimestampTypeFactoryService tztimestamptypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTableFactoryService")
	protected CFBamBuffTableFactoryService tableFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTableColFactoryService")
	protected CFBamBuffTableColFactoryService tablecolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTableTweakFactoryService")
	protected CFBamBuffTableTweakFactoryService tabletweakFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTextColFactoryService")
	protected CFBamBuffTextColFactoryService textcolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTextDefFactoryService")
	protected CFBamBuffTextDefFactoryService textdefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTextTypeFactoryService")
	protected CFBamBuffTextTypeFactoryService texttypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTimeColFactoryService")
	protected CFBamBuffTimeColFactoryService timecolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTimeDefFactoryService")
	protected CFBamBuffTimeDefFactoryService timedefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTimeTypeFactoryService")
	protected CFBamBuffTimeTypeFactoryService timetypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTimestampColFactoryService")
	protected CFBamBuffTimestampColFactoryService timestampcolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTimestampDefFactoryService")
	protected CFBamBuffTimestampDefFactoryService timestampdefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTimestampTypeFactoryService")
	protected CFBamBuffTimestampTypeFactoryService timestamptypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTokenColFactoryService")
	protected CFBamBuffTokenColFactoryService tokencolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTokenDefFactoryService")
	protected CFBamBuffTokenDefFactoryService tokendefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTokenTypeFactoryService")
	protected CFBamBuffTokenTypeFactoryService tokentypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffTweakFactoryService")
	protected CFBamBuffTweakFactoryService tweakFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUInt16ColFactoryService")
	protected CFBamBuffUInt16ColFactoryService uint16colFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUInt16DefFactoryService")
	protected CFBamBuffUInt16DefFactoryService uint16defFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUInt16TypeFactoryService")
	protected CFBamBuffUInt16TypeFactoryService uint16typeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUInt32ColFactoryService")
	protected CFBamBuffUInt32ColFactoryService uint32colFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUInt32DefFactoryService")
	protected CFBamBuffUInt32DefFactoryService uint32defFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUInt32TypeFactoryService")
	protected CFBamBuffUInt32TypeFactoryService uint32typeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUInt64ColFactoryService")
	protected CFBamBuffUInt64ColFactoryService uint64colFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUInt64DefFactoryService")
	protected CFBamBuffUInt64DefFactoryService uint64defFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUInt64TypeFactoryService")
	protected CFBamBuffUInt64TypeFactoryService uint64typeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUuid6ColFactoryService")
	protected CFBamBuffUuid6ColFactoryService uuid6colFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUuid6DefFactoryService")
	protected CFBamBuffUuid6DefFactoryService uuid6defFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUuid6GenFactoryService")
	protected CFBamBuffUuid6GenFactoryService uuid6genFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUuid6TypeFactoryService")
	protected CFBamBuffUuid6TypeFactoryService uuid6typeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUuidColFactoryService")
	protected CFBamBuffUuidColFactoryService uuidcolFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUuidDefFactoryService")
	protected CFBamBuffUuidDefFactoryService uuiddefFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUuidGenFactoryService")
	protected CFBamBuffUuidGenFactoryService uuidgenFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffUuidTypeFactoryService")
	protected CFBamBuffUuidTypeFactoryService uuidtypeFactoryService;

	@Autowired
	@Qualifier("cfbam31BuffValueFactoryService")
	protected CFBamBuffValueFactoryService valueFactoryService;


	public CFBamBuffFactoryService() { }

	@Override
	public ICFBamAtomFactory getFactoryAtom() {
		return(atomFactoryService);
	}

	public CFBamBuffAtomFactoryService getAtomFactoryService() {
		return(atomFactoryService);
	}

	@Override
	public ICFBamBlobColFactory getFactoryBlobCol() {
		return(blobcolFactoryService);
	}

	public CFBamBuffBlobColFactoryService getBlobColFactoryService() {
		return(blobcolFactoryService);
	}

	@Override
	public ICFBamBlobDefFactory getFactoryBlobDef() {
		return(blobdefFactoryService);
	}

	public CFBamBuffBlobDefFactoryService getBlobDefFactoryService() {
		return(blobdefFactoryService);
	}

	@Override
	public ICFBamBlobTypeFactory getFactoryBlobType() {
		return(blobtypeFactoryService);
	}

	public CFBamBuffBlobTypeFactoryService getBlobTypeFactoryService() {
		return(blobtypeFactoryService);
	}

	@Override
	public ICFBamBoolColFactory getFactoryBoolCol() {
		return(boolcolFactoryService);
	}

	public CFBamBuffBoolColFactoryService getBoolColFactoryService() {
		return(boolcolFactoryService);
	}

	@Override
	public ICFBamBoolDefFactory getFactoryBoolDef() {
		return(booldefFactoryService);
	}

	public CFBamBuffBoolDefFactoryService getBoolDefFactoryService() {
		return(booldefFactoryService);
	}

	@Override
	public ICFBamBoolTypeFactory getFactoryBoolType() {
		return(booltypeFactoryService);
	}

	public CFBamBuffBoolTypeFactoryService getBoolTypeFactoryService() {
		return(booltypeFactoryService);
	}

	@Override
	public ICFBamChainFactory getFactoryChain() {
		return(chainFactoryService);
	}

	public CFBamBuffChainFactoryService getChainFactoryService() {
		return(chainFactoryService);
	}

	@Override
	public ICFBamClearDepFactory getFactoryClearDep() {
		return(cleardepFactoryService);
	}

	public CFBamBuffClearDepFactoryService getClearDepFactoryService() {
		return(cleardepFactoryService);
	}

	@Override
	public ICFBamClearSubDep1Factory getFactoryClearSubDep1() {
		return(clearsubdep1FactoryService);
	}

	public CFBamBuffClearSubDep1FactoryService getClearSubDep1FactoryService() {
		return(clearsubdep1FactoryService);
	}

	@Override
	public ICFBamClearSubDep2Factory getFactoryClearSubDep2() {
		return(clearsubdep2FactoryService);
	}

	public CFBamBuffClearSubDep2FactoryService getClearSubDep2FactoryService() {
		return(clearsubdep2FactoryService);
	}

	@Override
	public ICFBamClearSubDep3Factory getFactoryClearSubDep3() {
		return(clearsubdep3FactoryService);
	}

	public CFBamBuffClearSubDep3FactoryService getClearSubDep3FactoryService() {
		return(clearsubdep3FactoryService);
	}

	@Override
	public ICFBamClearTopDepFactory getFactoryClearTopDep() {
		return(cleartopdepFactoryService);
	}

	public CFBamBuffClearTopDepFactoryService getClearTopDepFactoryService() {
		return(cleartopdepFactoryService);
	}

	@Override
	public ICFBamDateColFactory getFactoryDateCol() {
		return(datecolFactoryService);
	}

	public CFBamBuffDateColFactoryService getDateColFactoryService() {
		return(datecolFactoryService);
	}

	@Override
	public ICFBamDateDefFactory getFactoryDateDef() {
		return(datedefFactoryService);
	}

	public CFBamBuffDateDefFactoryService getDateDefFactoryService() {
		return(datedefFactoryService);
	}

	@Override
	public ICFBamDateTypeFactory getFactoryDateType() {
		return(datetypeFactoryService);
	}

	public CFBamBuffDateTypeFactoryService getDateTypeFactoryService() {
		return(datetypeFactoryService);
	}

	@Override
	public ICFBamDbKeyHash128ColFactory getFactoryDbKeyHash128Col() {
		return(dbkeyhash128colFactoryService);
	}

	public CFBamBuffDbKeyHash128ColFactoryService getDbKeyHash128ColFactoryService() {
		return(dbkeyhash128colFactoryService);
	}

	@Override
	public ICFBamDbKeyHash128DefFactory getFactoryDbKeyHash128Def() {
		return(dbkeyhash128defFactoryService);
	}

	public CFBamBuffDbKeyHash128DefFactoryService getDbKeyHash128DefFactoryService() {
		return(dbkeyhash128defFactoryService);
	}

	@Override
	public ICFBamDbKeyHash128GenFactory getFactoryDbKeyHash128Gen() {
		return(dbkeyhash128genFactoryService);
	}

	public CFBamBuffDbKeyHash128GenFactoryService getDbKeyHash128GenFactoryService() {
		return(dbkeyhash128genFactoryService);
	}

	@Override
	public ICFBamDbKeyHash128TypeFactory getFactoryDbKeyHash128Type() {
		return(dbkeyhash128typeFactoryService);
	}

	public CFBamBuffDbKeyHash128TypeFactoryService getDbKeyHash128TypeFactoryService() {
		return(dbkeyhash128typeFactoryService);
	}

	@Override
	public ICFBamDbKeyHash160ColFactory getFactoryDbKeyHash160Col() {
		return(dbkeyhash160colFactoryService);
	}

	public CFBamBuffDbKeyHash160ColFactoryService getDbKeyHash160ColFactoryService() {
		return(dbkeyhash160colFactoryService);
	}

	@Override
	public ICFBamDbKeyHash160DefFactory getFactoryDbKeyHash160Def() {
		return(dbkeyhash160defFactoryService);
	}

	public CFBamBuffDbKeyHash160DefFactoryService getDbKeyHash160DefFactoryService() {
		return(dbkeyhash160defFactoryService);
	}

	@Override
	public ICFBamDbKeyHash160GenFactory getFactoryDbKeyHash160Gen() {
		return(dbkeyhash160genFactoryService);
	}

	public CFBamBuffDbKeyHash160GenFactoryService getDbKeyHash160GenFactoryService() {
		return(dbkeyhash160genFactoryService);
	}

	@Override
	public ICFBamDbKeyHash160TypeFactory getFactoryDbKeyHash160Type() {
		return(dbkeyhash160typeFactoryService);
	}

	public CFBamBuffDbKeyHash160TypeFactoryService getDbKeyHash160TypeFactoryService() {
		return(dbkeyhash160typeFactoryService);
	}

	@Override
	public ICFBamDbKeyHash224ColFactory getFactoryDbKeyHash224Col() {
		return(dbkeyhash224colFactoryService);
	}

	public CFBamBuffDbKeyHash224ColFactoryService getDbKeyHash224ColFactoryService() {
		return(dbkeyhash224colFactoryService);
	}

	@Override
	public ICFBamDbKeyHash224DefFactory getFactoryDbKeyHash224Def() {
		return(dbkeyhash224defFactoryService);
	}

	public CFBamBuffDbKeyHash224DefFactoryService getDbKeyHash224DefFactoryService() {
		return(dbkeyhash224defFactoryService);
	}

	@Override
	public ICFBamDbKeyHash224GenFactory getFactoryDbKeyHash224Gen() {
		return(dbkeyhash224genFactoryService);
	}

	public CFBamBuffDbKeyHash224GenFactoryService getDbKeyHash224GenFactoryService() {
		return(dbkeyhash224genFactoryService);
	}

	@Override
	public ICFBamDbKeyHash224TypeFactory getFactoryDbKeyHash224Type() {
		return(dbkeyhash224typeFactoryService);
	}

	public CFBamBuffDbKeyHash224TypeFactoryService getDbKeyHash224TypeFactoryService() {
		return(dbkeyhash224typeFactoryService);
	}

	@Override
	public ICFBamDbKeyHash256ColFactory getFactoryDbKeyHash256Col() {
		return(dbkeyhash256colFactoryService);
	}

	public CFBamBuffDbKeyHash256ColFactoryService getDbKeyHash256ColFactoryService() {
		return(dbkeyhash256colFactoryService);
	}

	@Override
	public ICFBamDbKeyHash256DefFactory getFactoryDbKeyHash256Def() {
		return(dbkeyhash256defFactoryService);
	}

	public CFBamBuffDbKeyHash256DefFactoryService getDbKeyHash256DefFactoryService() {
		return(dbkeyhash256defFactoryService);
	}

	@Override
	public ICFBamDbKeyHash256GenFactory getFactoryDbKeyHash256Gen() {
		return(dbkeyhash256genFactoryService);
	}

	public CFBamBuffDbKeyHash256GenFactoryService getDbKeyHash256GenFactoryService() {
		return(dbkeyhash256genFactoryService);
	}

	@Override
	public ICFBamDbKeyHash256TypeFactory getFactoryDbKeyHash256Type() {
		return(dbkeyhash256typeFactoryService);
	}

	public CFBamBuffDbKeyHash256TypeFactoryService getDbKeyHash256TypeFactoryService() {
		return(dbkeyhash256typeFactoryService);
	}

	@Override
	public ICFBamDbKeyHash384ColFactory getFactoryDbKeyHash384Col() {
		return(dbkeyhash384colFactoryService);
	}

	public CFBamBuffDbKeyHash384ColFactoryService getDbKeyHash384ColFactoryService() {
		return(dbkeyhash384colFactoryService);
	}

	@Override
	public ICFBamDbKeyHash384DefFactory getFactoryDbKeyHash384Def() {
		return(dbkeyhash384defFactoryService);
	}

	public CFBamBuffDbKeyHash384DefFactoryService getDbKeyHash384DefFactoryService() {
		return(dbkeyhash384defFactoryService);
	}

	@Override
	public ICFBamDbKeyHash384GenFactory getFactoryDbKeyHash384Gen() {
		return(dbkeyhash384genFactoryService);
	}

	public CFBamBuffDbKeyHash384GenFactoryService getDbKeyHash384GenFactoryService() {
		return(dbkeyhash384genFactoryService);
	}

	@Override
	public ICFBamDbKeyHash384TypeFactory getFactoryDbKeyHash384Type() {
		return(dbkeyhash384typeFactoryService);
	}

	public CFBamBuffDbKeyHash384TypeFactoryService getDbKeyHash384TypeFactoryService() {
		return(dbkeyhash384typeFactoryService);
	}

	@Override
	public ICFBamDbKeyHash512ColFactory getFactoryDbKeyHash512Col() {
		return(dbkeyhash512colFactoryService);
	}

	public CFBamBuffDbKeyHash512ColFactoryService getDbKeyHash512ColFactoryService() {
		return(dbkeyhash512colFactoryService);
	}

	@Override
	public ICFBamDbKeyHash512DefFactory getFactoryDbKeyHash512Def() {
		return(dbkeyhash512defFactoryService);
	}

	public CFBamBuffDbKeyHash512DefFactoryService getDbKeyHash512DefFactoryService() {
		return(dbkeyhash512defFactoryService);
	}

	@Override
	public ICFBamDbKeyHash512GenFactory getFactoryDbKeyHash512Gen() {
		return(dbkeyhash512genFactoryService);
	}

	public CFBamBuffDbKeyHash512GenFactoryService getDbKeyHash512GenFactoryService() {
		return(dbkeyhash512genFactoryService);
	}

	@Override
	public ICFBamDbKeyHash512TypeFactory getFactoryDbKeyHash512Type() {
		return(dbkeyhash512typeFactoryService);
	}

	public CFBamBuffDbKeyHash512TypeFactoryService getDbKeyHash512TypeFactoryService() {
		return(dbkeyhash512typeFactoryService);
	}

	@Override
	public ICFBamDelDepFactory getFactoryDelDep() {
		return(deldepFactoryService);
	}

	public CFBamBuffDelDepFactoryService getDelDepFactoryService() {
		return(deldepFactoryService);
	}

	@Override
	public ICFBamDelSubDep1Factory getFactoryDelSubDep1() {
		return(delsubdep1FactoryService);
	}

	public CFBamBuffDelSubDep1FactoryService getDelSubDep1FactoryService() {
		return(delsubdep1FactoryService);
	}

	@Override
	public ICFBamDelSubDep2Factory getFactoryDelSubDep2() {
		return(delsubdep2FactoryService);
	}

	public CFBamBuffDelSubDep2FactoryService getDelSubDep2FactoryService() {
		return(delsubdep2FactoryService);
	}

	@Override
	public ICFBamDelSubDep3Factory getFactoryDelSubDep3() {
		return(delsubdep3FactoryService);
	}

	public CFBamBuffDelSubDep3FactoryService getDelSubDep3FactoryService() {
		return(delsubdep3FactoryService);
	}

	@Override
	public ICFBamDelTopDepFactory getFactoryDelTopDep() {
		return(deltopdepFactoryService);
	}

	public CFBamBuffDelTopDepFactoryService getDelTopDepFactoryService() {
		return(deltopdepFactoryService);
	}

	@Override
	public ICFBamDoubleColFactory getFactoryDoubleCol() {
		return(doublecolFactoryService);
	}

	public CFBamBuffDoubleColFactoryService getDoubleColFactoryService() {
		return(doublecolFactoryService);
	}

	@Override
	public ICFBamDoubleDefFactory getFactoryDoubleDef() {
		return(doubledefFactoryService);
	}

	public CFBamBuffDoubleDefFactoryService getDoubleDefFactoryService() {
		return(doubledefFactoryService);
	}

	@Override
	public ICFBamDoubleTypeFactory getFactoryDoubleType() {
		return(doubletypeFactoryService);
	}

	public CFBamBuffDoubleTypeFactoryService getDoubleTypeFactoryService() {
		return(doubletypeFactoryService);
	}

	@Override
	public ICFBamEnumDefFactory getFactoryEnumDef() {
		return(enumdefFactoryService);
	}

	public CFBamBuffEnumDefFactoryService getEnumDefFactoryService() {
		return(enumdefFactoryService);
	}

	@Override
	public ICFBamEnumTagFactory getFactoryEnumTag() {
		return(enumtagFactoryService);
	}

	public CFBamBuffEnumTagFactoryService getEnumTagFactoryService() {
		return(enumtagFactoryService);
	}

	@Override
	public ICFBamEnumTypeFactory getFactoryEnumType() {
		return(enumtypeFactoryService);
	}

	public CFBamBuffEnumTypeFactoryService getEnumTypeFactoryService() {
		return(enumtypeFactoryService);
	}

	@Override
	public ICFBamFloatColFactory getFactoryFloatCol() {
		return(floatcolFactoryService);
	}

	public CFBamBuffFloatColFactoryService getFloatColFactoryService() {
		return(floatcolFactoryService);
	}

	@Override
	public ICFBamFloatDefFactory getFactoryFloatDef() {
		return(floatdefFactoryService);
	}

	public CFBamBuffFloatDefFactoryService getFloatDefFactoryService() {
		return(floatdefFactoryService);
	}

	@Override
	public ICFBamFloatTypeFactory getFactoryFloatType() {
		return(floattypeFactoryService);
	}

	public CFBamBuffFloatTypeFactoryService getFloatTypeFactoryService() {
		return(floattypeFactoryService);
	}

	@Override
	public ICFBamId16GenFactory getFactoryId16Gen() {
		return(id16genFactoryService);
	}

	public CFBamBuffId16GenFactoryService getId16GenFactoryService() {
		return(id16genFactoryService);
	}

	@Override
	public ICFBamId32GenFactory getFactoryId32Gen() {
		return(id32genFactoryService);
	}

	public CFBamBuffId32GenFactoryService getId32GenFactoryService() {
		return(id32genFactoryService);
	}

	@Override
	public ICFBamId64GenFactory getFactoryId64Gen() {
		return(id64genFactoryService);
	}

	public CFBamBuffId64GenFactoryService getId64GenFactoryService() {
		return(id64genFactoryService);
	}

	@Override
	public ICFBamIndexFactory getFactoryIndex() {
		return(indexFactoryService);
	}

	public CFBamBuffIndexFactoryService getIndexFactoryService() {
		return(indexFactoryService);
	}

	@Override
	public ICFBamIndexColFactory getFactoryIndexCol() {
		return(indexcolFactoryService);
	}

	public CFBamBuffIndexColFactoryService getIndexColFactoryService() {
		return(indexcolFactoryService);
	}

	@Override
	public ICFBamIndexTweakFactory getFactoryIndexTweak() {
		return(indextweakFactoryService);
	}

	public CFBamBuffIndexTweakFactoryService getIndexTweakFactoryService() {
		return(indextweakFactoryService);
	}

	@Override
	public ICFBamInt16ColFactory getFactoryInt16Col() {
		return(int16colFactoryService);
	}

	public CFBamBuffInt16ColFactoryService getInt16ColFactoryService() {
		return(int16colFactoryService);
	}

	@Override
	public ICFBamInt16DefFactory getFactoryInt16Def() {
		return(int16defFactoryService);
	}

	public CFBamBuffInt16DefFactoryService getInt16DefFactoryService() {
		return(int16defFactoryService);
	}

	@Override
	public ICFBamInt16TypeFactory getFactoryInt16Type() {
		return(int16typeFactoryService);
	}

	public CFBamBuffInt16TypeFactoryService getInt16TypeFactoryService() {
		return(int16typeFactoryService);
	}

	@Override
	public ICFBamInt32ColFactory getFactoryInt32Col() {
		return(int32colFactoryService);
	}

	public CFBamBuffInt32ColFactoryService getInt32ColFactoryService() {
		return(int32colFactoryService);
	}

	@Override
	public ICFBamInt32DefFactory getFactoryInt32Def() {
		return(int32defFactoryService);
	}

	public CFBamBuffInt32DefFactoryService getInt32DefFactoryService() {
		return(int32defFactoryService);
	}

	@Override
	public ICFBamInt32TypeFactory getFactoryInt32Type() {
		return(int32typeFactoryService);
	}

	public CFBamBuffInt32TypeFactoryService getInt32TypeFactoryService() {
		return(int32typeFactoryService);
	}

	@Override
	public ICFBamInt64ColFactory getFactoryInt64Col() {
		return(int64colFactoryService);
	}

	public CFBamBuffInt64ColFactoryService getInt64ColFactoryService() {
		return(int64colFactoryService);
	}

	@Override
	public ICFBamInt64DefFactory getFactoryInt64Def() {
		return(int64defFactoryService);
	}

	public CFBamBuffInt64DefFactoryService getInt64DefFactoryService() {
		return(int64defFactoryService);
	}

	@Override
	public ICFBamInt64TypeFactory getFactoryInt64Type() {
		return(int64typeFactoryService);
	}

	public CFBamBuffInt64TypeFactoryService getInt64TypeFactoryService() {
		return(int64typeFactoryService);
	}

	@Override
	public ICFBamNmTokenColFactory getFactoryNmTokenCol() {
		return(nmtokencolFactoryService);
	}

	public CFBamBuffNmTokenColFactoryService getNmTokenColFactoryService() {
		return(nmtokencolFactoryService);
	}

	@Override
	public ICFBamNmTokenDefFactory getFactoryNmTokenDef() {
		return(nmtokendefFactoryService);
	}

	public CFBamBuffNmTokenDefFactoryService getNmTokenDefFactoryService() {
		return(nmtokendefFactoryService);
	}

	@Override
	public ICFBamNmTokenTypeFactory getFactoryNmTokenType() {
		return(nmtokentypeFactoryService);
	}

	public CFBamBuffNmTokenTypeFactoryService getNmTokenTypeFactoryService() {
		return(nmtokentypeFactoryService);
	}

	@Override
	public ICFBamNmTokensColFactory getFactoryNmTokensCol() {
		return(nmtokenscolFactoryService);
	}

	public CFBamBuffNmTokensColFactoryService getNmTokensColFactoryService() {
		return(nmtokenscolFactoryService);
	}

	@Override
	public ICFBamNmTokensDefFactory getFactoryNmTokensDef() {
		return(nmtokensdefFactoryService);
	}

	public CFBamBuffNmTokensDefFactoryService getNmTokensDefFactoryService() {
		return(nmtokensdefFactoryService);
	}

	@Override
	public ICFBamNmTokensTypeFactory getFactoryNmTokensType() {
		return(nmtokenstypeFactoryService);
	}

	public CFBamBuffNmTokensTypeFactoryService getNmTokensTypeFactoryService() {
		return(nmtokenstypeFactoryService);
	}

	@Override
	public ICFBamNumberColFactory getFactoryNumberCol() {
		return(numbercolFactoryService);
	}

	public CFBamBuffNumberColFactoryService getNumberColFactoryService() {
		return(numbercolFactoryService);
	}

	@Override
	public ICFBamNumberDefFactory getFactoryNumberDef() {
		return(numberdefFactoryService);
	}

	public CFBamBuffNumberDefFactoryService getNumberDefFactoryService() {
		return(numberdefFactoryService);
	}

	@Override
	public ICFBamNumberTypeFactory getFactoryNumberType() {
		return(numbertypeFactoryService);
	}

	public CFBamBuffNumberTypeFactoryService getNumberTypeFactoryService() {
		return(numbertypeFactoryService);
	}

	@Override
	public ICFBamParamFactory getFactoryParam() {
		return(paramFactoryService);
	}

	public CFBamBuffParamFactoryService getParamFactoryService() {
		return(paramFactoryService);
	}

	@Override
	public ICFBamPopDepFactory getFactoryPopDep() {
		return(popdepFactoryService);
	}

	public CFBamBuffPopDepFactoryService getPopDepFactoryService() {
		return(popdepFactoryService);
	}

	@Override
	public ICFBamPopSubDep1Factory getFactoryPopSubDep1() {
		return(popsubdep1FactoryService);
	}

	public CFBamBuffPopSubDep1FactoryService getPopSubDep1FactoryService() {
		return(popsubdep1FactoryService);
	}

	@Override
	public ICFBamPopSubDep2Factory getFactoryPopSubDep2() {
		return(popsubdep2FactoryService);
	}

	public CFBamBuffPopSubDep2FactoryService getPopSubDep2FactoryService() {
		return(popsubdep2FactoryService);
	}

	@Override
	public ICFBamPopSubDep3Factory getFactoryPopSubDep3() {
		return(popsubdep3FactoryService);
	}

	public CFBamBuffPopSubDep3FactoryService getPopSubDep3FactoryService() {
		return(popsubdep3FactoryService);
	}

	@Override
	public ICFBamPopTopDepFactory getFactoryPopTopDep() {
		return(poptopdepFactoryService);
	}

	public CFBamBuffPopTopDepFactoryService getPopTopDepFactoryService() {
		return(poptopdepFactoryService);
	}

	@Override
	public ICFBamRelationFactory getFactoryRelation() {
		return(relationFactoryService);
	}

	public CFBamBuffRelationFactoryService getRelationFactoryService() {
		return(relationFactoryService);
	}

	@Override
	public ICFBamRelationColFactory getFactoryRelationCol() {
		return(relationcolFactoryService);
	}

	public CFBamBuffRelationColFactoryService getRelationColFactoryService() {
		return(relationcolFactoryService);
	}

	@Override
	public ICFBamRoleDefFactory getFactoryRoleDef() {
		return(roledefFactoryService);
	}

	public CFBamBuffRoleDefFactoryService getRoleDefFactoryService() {
		return(roledefFactoryService);
	}

	@Override
	public ICFBamSchemaDefFactory getFactorySchemaDef() {
		return(schemadefFactoryService);
	}

	public CFBamBuffSchemaDefFactoryService getSchemaDefFactoryService() {
		return(schemadefFactoryService);
	}

	@Override
	public ICFBamSchemaRefFactory getFactorySchemaRef() {
		return(schemarefFactoryService);
	}

	public CFBamBuffSchemaRefFactoryService getSchemaRefFactoryService() {
		return(schemarefFactoryService);
	}

	@Override
	public ICFBamSchemaRoleFactory getFactorySchemaRole() {
		return(schemaroleFactoryService);
	}

	public CFBamBuffSchemaRoleFactoryService getSchemaRoleFactoryService() {
		return(schemaroleFactoryService);
	}

	@Override
	public ICFBamSchemaTweakFactory getFactorySchemaTweak() {
		return(schematweakFactoryService);
	}

	public CFBamBuffSchemaTweakFactoryService getSchemaTweakFactoryService() {
		return(schematweakFactoryService);
	}

	@Override
	public ICFBamScopeFactory getFactoryScope() {
		return(scopeFactoryService);
	}

	public CFBamBuffScopeFactoryService getScopeFactoryService() {
		return(scopeFactoryService);
	}

	@Override
	public ICFBamServerListFuncFactory getFactoryServerListFunc() {
		return(serverlistfuncFactoryService);
	}

	public CFBamBuffServerListFuncFactoryService getServerListFuncFactoryService() {
		return(serverlistfuncFactoryService);
	}

	@Override
	public ICFBamServerMethodFactory getFactoryServerMethod() {
		return(servermethodFactoryService);
	}

	public CFBamBuffServerMethodFactoryService getServerMethodFactoryService() {
		return(servermethodFactoryService);
	}

	@Override
	public ICFBamServerObjFuncFactory getFactoryServerObjFunc() {
		return(serverobjfuncFactoryService);
	}

	public CFBamBuffServerObjFuncFactoryService getServerObjFuncFactoryService() {
		return(serverobjfuncFactoryService);
	}

	@Override
	public ICFBamServerProcFactory getFactoryServerProc() {
		return(serverprocFactoryService);
	}

	public CFBamBuffServerProcFactoryService getServerProcFactoryService() {
		return(serverprocFactoryService);
	}

	@Override
	public ICFBamStringColFactory getFactoryStringCol() {
		return(stringcolFactoryService);
	}

	public CFBamBuffStringColFactoryService getStringColFactoryService() {
		return(stringcolFactoryService);
	}

	@Override
	public ICFBamStringDefFactory getFactoryStringDef() {
		return(stringdefFactoryService);
	}

	public CFBamBuffStringDefFactoryService getStringDefFactoryService() {
		return(stringdefFactoryService);
	}

	@Override
	public ICFBamStringTypeFactory getFactoryStringType() {
		return(stringtypeFactoryService);
	}

	public CFBamBuffStringTypeFactoryService getStringTypeFactoryService() {
		return(stringtypeFactoryService);
	}

	@Override
	public ICFBamTZDateColFactory getFactoryTZDateCol() {
		return(tzdatecolFactoryService);
	}

	public CFBamBuffTZDateColFactoryService getTZDateColFactoryService() {
		return(tzdatecolFactoryService);
	}

	@Override
	public ICFBamTZDateDefFactory getFactoryTZDateDef() {
		return(tzdatedefFactoryService);
	}

	public CFBamBuffTZDateDefFactoryService getTZDateDefFactoryService() {
		return(tzdatedefFactoryService);
	}

	@Override
	public ICFBamTZDateTypeFactory getFactoryTZDateType() {
		return(tzdatetypeFactoryService);
	}

	public CFBamBuffTZDateTypeFactoryService getTZDateTypeFactoryService() {
		return(tzdatetypeFactoryService);
	}

	@Override
	public ICFBamTZTimeColFactory getFactoryTZTimeCol() {
		return(tztimecolFactoryService);
	}

	public CFBamBuffTZTimeColFactoryService getTZTimeColFactoryService() {
		return(tztimecolFactoryService);
	}

	@Override
	public ICFBamTZTimeDefFactory getFactoryTZTimeDef() {
		return(tztimedefFactoryService);
	}

	public CFBamBuffTZTimeDefFactoryService getTZTimeDefFactoryService() {
		return(tztimedefFactoryService);
	}

	@Override
	public ICFBamTZTimeTypeFactory getFactoryTZTimeType() {
		return(tztimetypeFactoryService);
	}

	public CFBamBuffTZTimeTypeFactoryService getTZTimeTypeFactoryService() {
		return(tztimetypeFactoryService);
	}

	@Override
	public ICFBamTZTimestampColFactory getFactoryTZTimestampCol() {
		return(tztimestampcolFactoryService);
	}

	public CFBamBuffTZTimestampColFactoryService getTZTimestampColFactoryService() {
		return(tztimestampcolFactoryService);
	}

	@Override
	public ICFBamTZTimestampDefFactory getFactoryTZTimestampDef() {
		return(tztimestampdefFactoryService);
	}

	public CFBamBuffTZTimestampDefFactoryService getTZTimestampDefFactoryService() {
		return(tztimestampdefFactoryService);
	}

	@Override
	public ICFBamTZTimestampTypeFactory getFactoryTZTimestampType() {
		return(tztimestamptypeFactoryService);
	}

	public CFBamBuffTZTimestampTypeFactoryService getTZTimestampTypeFactoryService() {
		return(tztimestamptypeFactoryService);
	}

	@Override
	public ICFBamTableFactory getFactoryTable() {
		return(tableFactoryService);
	}

	public CFBamBuffTableFactoryService getTableFactoryService() {
		return(tableFactoryService);
	}

	@Override
	public ICFBamTableColFactory getFactoryTableCol() {
		return(tablecolFactoryService);
	}

	public CFBamBuffTableColFactoryService getTableColFactoryService() {
		return(tablecolFactoryService);
	}

	@Override
	public ICFBamTableTweakFactory getFactoryTableTweak() {
		return(tabletweakFactoryService);
	}

	public CFBamBuffTableTweakFactoryService getTableTweakFactoryService() {
		return(tabletweakFactoryService);
	}

	@Override
	public ICFBamTextColFactory getFactoryTextCol() {
		return(textcolFactoryService);
	}

	public CFBamBuffTextColFactoryService getTextColFactoryService() {
		return(textcolFactoryService);
	}

	@Override
	public ICFBamTextDefFactory getFactoryTextDef() {
		return(textdefFactoryService);
	}

	public CFBamBuffTextDefFactoryService getTextDefFactoryService() {
		return(textdefFactoryService);
	}

	@Override
	public ICFBamTextTypeFactory getFactoryTextType() {
		return(texttypeFactoryService);
	}

	public CFBamBuffTextTypeFactoryService getTextTypeFactoryService() {
		return(texttypeFactoryService);
	}

	@Override
	public ICFBamTimeColFactory getFactoryTimeCol() {
		return(timecolFactoryService);
	}

	public CFBamBuffTimeColFactoryService getTimeColFactoryService() {
		return(timecolFactoryService);
	}

	@Override
	public ICFBamTimeDefFactory getFactoryTimeDef() {
		return(timedefFactoryService);
	}

	public CFBamBuffTimeDefFactoryService getTimeDefFactoryService() {
		return(timedefFactoryService);
	}

	@Override
	public ICFBamTimeTypeFactory getFactoryTimeType() {
		return(timetypeFactoryService);
	}

	public CFBamBuffTimeTypeFactoryService getTimeTypeFactoryService() {
		return(timetypeFactoryService);
	}

	@Override
	public ICFBamTimestampColFactory getFactoryTimestampCol() {
		return(timestampcolFactoryService);
	}

	public CFBamBuffTimestampColFactoryService getTimestampColFactoryService() {
		return(timestampcolFactoryService);
	}

	@Override
	public ICFBamTimestampDefFactory getFactoryTimestampDef() {
		return(timestampdefFactoryService);
	}

	public CFBamBuffTimestampDefFactoryService getTimestampDefFactoryService() {
		return(timestampdefFactoryService);
	}

	@Override
	public ICFBamTimestampTypeFactory getFactoryTimestampType() {
		return(timestamptypeFactoryService);
	}

	public CFBamBuffTimestampTypeFactoryService getTimestampTypeFactoryService() {
		return(timestamptypeFactoryService);
	}

	@Override
	public ICFBamTokenColFactory getFactoryTokenCol() {
		return(tokencolFactoryService);
	}

	public CFBamBuffTokenColFactoryService getTokenColFactoryService() {
		return(tokencolFactoryService);
	}

	@Override
	public ICFBamTokenDefFactory getFactoryTokenDef() {
		return(tokendefFactoryService);
	}

	public CFBamBuffTokenDefFactoryService getTokenDefFactoryService() {
		return(tokendefFactoryService);
	}

	@Override
	public ICFBamTokenTypeFactory getFactoryTokenType() {
		return(tokentypeFactoryService);
	}

	public CFBamBuffTokenTypeFactoryService getTokenTypeFactoryService() {
		return(tokentypeFactoryService);
	}

	@Override
	public ICFBamTweakFactory getFactoryTweak() {
		return(tweakFactoryService);
	}

	public CFBamBuffTweakFactoryService getTweakFactoryService() {
		return(tweakFactoryService);
	}

	@Override
	public ICFBamUInt16ColFactory getFactoryUInt16Col() {
		return(uint16colFactoryService);
	}

	public CFBamBuffUInt16ColFactoryService getUInt16ColFactoryService() {
		return(uint16colFactoryService);
	}

	@Override
	public ICFBamUInt16DefFactory getFactoryUInt16Def() {
		return(uint16defFactoryService);
	}

	public CFBamBuffUInt16DefFactoryService getUInt16DefFactoryService() {
		return(uint16defFactoryService);
	}

	@Override
	public ICFBamUInt16TypeFactory getFactoryUInt16Type() {
		return(uint16typeFactoryService);
	}

	public CFBamBuffUInt16TypeFactoryService getUInt16TypeFactoryService() {
		return(uint16typeFactoryService);
	}

	@Override
	public ICFBamUInt32ColFactory getFactoryUInt32Col() {
		return(uint32colFactoryService);
	}

	public CFBamBuffUInt32ColFactoryService getUInt32ColFactoryService() {
		return(uint32colFactoryService);
	}

	@Override
	public ICFBamUInt32DefFactory getFactoryUInt32Def() {
		return(uint32defFactoryService);
	}

	public CFBamBuffUInt32DefFactoryService getUInt32DefFactoryService() {
		return(uint32defFactoryService);
	}

	@Override
	public ICFBamUInt32TypeFactory getFactoryUInt32Type() {
		return(uint32typeFactoryService);
	}

	public CFBamBuffUInt32TypeFactoryService getUInt32TypeFactoryService() {
		return(uint32typeFactoryService);
	}

	@Override
	public ICFBamUInt64ColFactory getFactoryUInt64Col() {
		return(uint64colFactoryService);
	}

	public CFBamBuffUInt64ColFactoryService getUInt64ColFactoryService() {
		return(uint64colFactoryService);
	}

	@Override
	public ICFBamUInt64DefFactory getFactoryUInt64Def() {
		return(uint64defFactoryService);
	}

	public CFBamBuffUInt64DefFactoryService getUInt64DefFactoryService() {
		return(uint64defFactoryService);
	}

	@Override
	public ICFBamUInt64TypeFactory getFactoryUInt64Type() {
		return(uint64typeFactoryService);
	}

	public CFBamBuffUInt64TypeFactoryService getUInt64TypeFactoryService() {
		return(uint64typeFactoryService);
	}

	@Override
	public ICFBamUuid6ColFactory getFactoryUuid6Col() {
		return(uuid6colFactoryService);
	}

	public CFBamBuffUuid6ColFactoryService getUuid6ColFactoryService() {
		return(uuid6colFactoryService);
	}

	@Override
	public ICFBamUuid6DefFactory getFactoryUuid6Def() {
		return(uuid6defFactoryService);
	}

	public CFBamBuffUuid6DefFactoryService getUuid6DefFactoryService() {
		return(uuid6defFactoryService);
	}

	@Override
	public ICFBamUuid6GenFactory getFactoryUuid6Gen() {
		return(uuid6genFactoryService);
	}

	public CFBamBuffUuid6GenFactoryService getUuid6GenFactoryService() {
		return(uuid6genFactoryService);
	}

	@Override
	public ICFBamUuid6TypeFactory getFactoryUuid6Type() {
		return(uuid6typeFactoryService);
	}

	public CFBamBuffUuid6TypeFactoryService getUuid6TypeFactoryService() {
		return(uuid6typeFactoryService);
	}

	@Override
	public ICFBamUuidColFactory getFactoryUuidCol() {
		return(uuidcolFactoryService);
	}

	public CFBamBuffUuidColFactoryService getUuidColFactoryService() {
		return(uuidcolFactoryService);
	}

	@Override
	public ICFBamUuidDefFactory getFactoryUuidDef() {
		return(uuiddefFactoryService);
	}

	public CFBamBuffUuidDefFactoryService getUuidDefFactoryService() {
		return(uuiddefFactoryService);
	}

	@Override
	public ICFBamUuidGenFactory getFactoryUuidGen() {
		return(uuidgenFactoryService);
	}

	public CFBamBuffUuidGenFactoryService getUuidGenFactoryService() {
		return(uuidgenFactoryService);
	}

	@Override
	public ICFBamUuidTypeFactory getFactoryUuidType() {
		return(uuidtypeFactoryService);
	}

	public CFBamBuffUuidTypeFactoryService getUuidTypeFactoryService() {
		return(uuidtypeFactoryService);
	}

	@Override
	public ICFBamValueFactory getFactoryValue() {
		return(valueFactoryService);
	}

	public CFBamBuffValueFactoryService getValueFactoryService() {
		return(valueFactoryService);
	}

}
