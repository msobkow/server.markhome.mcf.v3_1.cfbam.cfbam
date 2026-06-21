
// Description: Java 25 Factory service implementation for Atom buffers

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
 *	Java 25 Factory service implementation for Atom buffers.
 */
@Service("cfbam31BuffAtomFactoryService")
public class CFBamBuffAtomFactoryService
	implements ICFBamAtomFactory
{
	public CFBamBuffAtomFactoryService() {
	}

	@Override
	public ICFBamAtom newRec() {
		ICFBamAtom rec =
			new CFBamBuffAtom();
		return( rec );
	}

	public CFBamBuffAtom ensureRec(ICFBamAtom rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffAtom) {
			return ((CFBamBuffAtom)rec);
		}
		else {	
			switch (rec.getClassCode()) {
				case ICFBamAtom.CLASS_CODE: {
					CFBamBuffAtom mapped = new CFBamBuffAtom();
					mapped.set(rec);
					return(mapped); }
				case ICFBamBlobDef.CLASS_CODE: {
					CFBamBuffBlobDef mapped = new CFBamBuffBlobDef();
					mapped.set((ICFBamBlobDef)rec);
					return(mapped); }
				case ICFBamBlobType.CLASS_CODE: {
					CFBamBuffBlobType mapped = new CFBamBuffBlobType();
					mapped.set((ICFBamBlobType)rec);
					return(mapped); }
				case ICFBamBlobCol.CLASS_CODE: {
					CFBamBuffBlobCol mapped = new CFBamBuffBlobCol();
					mapped.set((ICFBamBlobCol)rec);
					return(mapped); }
				case ICFBamBoolDef.CLASS_CODE: {
					CFBamBuffBoolDef mapped = new CFBamBuffBoolDef();
					mapped.set((ICFBamBoolDef)rec);
					return(mapped); }
				case ICFBamBoolType.CLASS_CODE: {
					CFBamBuffBoolType mapped = new CFBamBuffBoolType();
					mapped.set((ICFBamBoolType)rec);
					return(mapped); }
				case ICFBamBoolCol.CLASS_CODE: {
					CFBamBuffBoolCol mapped = new CFBamBuffBoolCol();
					mapped.set((ICFBamBoolCol)rec);
					return(mapped); }
				case ICFBamDateDef.CLASS_CODE: {
					CFBamBuffDateDef mapped = new CFBamBuffDateDef();
					mapped.set((ICFBamDateDef)rec);
					return(mapped); }
				case ICFBamDateType.CLASS_CODE: {
					CFBamBuffDateType mapped = new CFBamBuffDateType();
					mapped.set((ICFBamDateType)rec);
					return(mapped); }
				case ICFBamDateCol.CLASS_CODE: {
					CFBamBuffDateCol mapped = new CFBamBuffDateCol();
					mapped.set((ICFBamDateCol)rec);
					return(mapped); }
				case ICFBamDoubleDef.CLASS_CODE: {
					CFBamBuffDoubleDef mapped = new CFBamBuffDoubleDef();
					mapped.set((ICFBamDoubleDef)rec);
					return(mapped); }
				case ICFBamDoubleType.CLASS_CODE: {
					CFBamBuffDoubleType mapped = new CFBamBuffDoubleType();
					mapped.set((ICFBamDoubleType)rec);
					return(mapped); }
				case ICFBamDoubleCol.CLASS_CODE: {
					CFBamBuffDoubleCol mapped = new CFBamBuffDoubleCol();
					mapped.set((ICFBamDoubleCol)rec);
					return(mapped); }
				case ICFBamFloatDef.CLASS_CODE: {
					CFBamBuffFloatDef mapped = new CFBamBuffFloatDef();
					mapped.set((ICFBamFloatDef)rec);
					return(mapped); }
				case ICFBamFloatType.CLASS_CODE: {
					CFBamBuffFloatType mapped = new CFBamBuffFloatType();
					mapped.set((ICFBamFloatType)rec);
					return(mapped); }
				case ICFBamFloatCol.CLASS_CODE: {
					CFBamBuffFloatCol mapped = new CFBamBuffFloatCol();
					mapped.set((ICFBamFloatCol)rec);
					return(mapped); }
				case ICFBamInt16Def.CLASS_CODE: {
					CFBamBuffInt16Def mapped = new CFBamBuffInt16Def();
					mapped.set((ICFBamInt16Def)rec);
					return(mapped); }
				case ICFBamInt16Type.CLASS_CODE: {
					CFBamBuffInt16Type mapped = new CFBamBuffInt16Type();
					mapped.set((ICFBamInt16Type)rec);
					return(mapped); }
				case ICFBamId16Gen.CLASS_CODE: {
					CFBamBuffId16Gen mapped = new CFBamBuffId16Gen();
					mapped.set((ICFBamId16Gen)rec);
					return(mapped); }
				case ICFBamEnumDef.CLASS_CODE: {
					CFBamBuffEnumDef mapped = new CFBamBuffEnumDef();
					mapped.set((ICFBamEnumDef)rec);
					return(mapped); }
				case ICFBamEnumType.CLASS_CODE: {
					CFBamBuffEnumType mapped = new CFBamBuffEnumType();
					mapped.set((ICFBamEnumType)rec);
					return(mapped); }
				case ICFBamInt16Col.CLASS_CODE: {
					CFBamBuffInt16Col mapped = new CFBamBuffInt16Col();
					mapped.set((ICFBamInt16Col)rec);
					return(mapped); }
				case ICFBamInt32Def.CLASS_CODE: {
					CFBamBuffInt32Def mapped = new CFBamBuffInt32Def();
					mapped.set((ICFBamInt32Def)rec);
					return(mapped); }
				case ICFBamInt32Type.CLASS_CODE: {
					CFBamBuffInt32Type mapped = new CFBamBuffInt32Type();
					mapped.set((ICFBamInt32Type)rec);
					return(mapped); }
				case ICFBamId32Gen.CLASS_CODE: {
					CFBamBuffId32Gen mapped = new CFBamBuffId32Gen();
					mapped.set((ICFBamId32Gen)rec);
					return(mapped); }
				case ICFBamInt32Col.CLASS_CODE: {
					CFBamBuffInt32Col mapped = new CFBamBuffInt32Col();
					mapped.set((ICFBamInt32Col)rec);
					return(mapped); }
				case ICFBamInt64Def.CLASS_CODE: {
					CFBamBuffInt64Def mapped = new CFBamBuffInt64Def();
					mapped.set((ICFBamInt64Def)rec);
					return(mapped); }
				case ICFBamInt64Type.CLASS_CODE: {
					CFBamBuffInt64Type mapped = new CFBamBuffInt64Type();
					mapped.set((ICFBamInt64Type)rec);
					return(mapped); }
				case ICFBamId64Gen.CLASS_CODE: {
					CFBamBuffId64Gen mapped = new CFBamBuffId64Gen();
					mapped.set((ICFBamId64Gen)rec);
					return(mapped); }
				case ICFBamInt64Col.CLASS_CODE: {
					CFBamBuffInt64Col mapped = new CFBamBuffInt64Col();
					mapped.set((ICFBamInt64Col)rec);
					return(mapped); }
				case ICFBamNmTokenDef.CLASS_CODE: {
					CFBamBuffNmTokenDef mapped = new CFBamBuffNmTokenDef();
					mapped.set((ICFBamNmTokenDef)rec);
					return(mapped); }
				case ICFBamNmTokenType.CLASS_CODE: {
					CFBamBuffNmTokenType mapped = new CFBamBuffNmTokenType();
					mapped.set((ICFBamNmTokenType)rec);
					return(mapped); }
				case ICFBamNmTokenCol.CLASS_CODE: {
					CFBamBuffNmTokenCol mapped = new CFBamBuffNmTokenCol();
					mapped.set((ICFBamNmTokenCol)rec);
					return(mapped); }
				case ICFBamNmTokensDef.CLASS_CODE: {
					CFBamBuffNmTokensDef mapped = new CFBamBuffNmTokensDef();
					mapped.set((ICFBamNmTokensDef)rec);
					return(mapped); }
				case ICFBamNmTokensType.CLASS_CODE: {
					CFBamBuffNmTokensType mapped = new CFBamBuffNmTokensType();
					mapped.set((ICFBamNmTokensType)rec);
					return(mapped); }
				case ICFBamNmTokensCol.CLASS_CODE: {
					CFBamBuffNmTokensCol mapped = new CFBamBuffNmTokensCol();
					mapped.set((ICFBamNmTokensCol)rec);
					return(mapped); }
				case ICFBamNumberDef.CLASS_CODE: {
					CFBamBuffNumberDef mapped = new CFBamBuffNumberDef();
					mapped.set((ICFBamNumberDef)rec);
					return(mapped); }
				case ICFBamNumberType.CLASS_CODE: {
					CFBamBuffNumberType mapped = new CFBamBuffNumberType();
					mapped.set((ICFBamNumberType)rec);
					return(mapped); }
				case ICFBamNumberCol.CLASS_CODE: {
					CFBamBuffNumberCol mapped = new CFBamBuffNumberCol();
					mapped.set((ICFBamNumberCol)rec);
					return(mapped); }
				case ICFBamDbKeyHash128Def.CLASS_CODE: {
					CFBamBuffDbKeyHash128Def mapped = new CFBamBuffDbKeyHash128Def();
					mapped.set((ICFBamDbKeyHash128Def)rec);
					return(mapped); }
				case ICFBamDbKeyHash128Col.CLASS_CODE: {
					CFBamBuffDbKeyHash128Col mapped = new CFBamBuffDbKeyHash128Col();
					mapped.set((ICFBamDbKeyHash128Col)rec);
					return(mapped); }
				case ICFBamDbKeyHash128Type.CLASS_CODE: {
					CFBamBuffDbKeyHash128Type mapped = new CFBamBuffDbKeyHash128Type();
					mapped.set((ICFBamDbKeyHash128Type)rec);
					return(mapped); }
				case ICFBamDbKeyHash128Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash128Gen mapped = new CFBamBuffDbKeyHash128Gen();
					mapped.set((ICFBamDbKeyHash128Gen)rec);
					return(mapped); }
				case ICFBamDbKeyHash160Def.CLASS_CODE: {
					CFBamBuffDbKeyHash160Def mapped = new CFBamBuffDbKeyHash160Def();
					mapped.set((ICFBamDbKeyHash160Def)rec);
					return(mapped); }
				case ICFBamDbKeyHash160Col.CLASS_CODE: {
					CFBamBuffDbKeyHash160Col mapped = new CFBamBuffDbKeyHash160Col();
					mapped.set((ICFBamDbKeyHash160Col)rec);
					return(mapped); }
				case ICFBamDbKeyHash160Type.CLASS_CODE: {
					CFBamBuffDbKeyHash160Type mapped = new CFBamBuffDbKeyHash160Type();
					mapped.set((ICFBamDbKeyHash160Type)rec);
					return(mapped); }
				case ICFBamDbKeyHash160Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash160Gen mapped = new CFBamBuffDbKeyHash160Gen();
					mapped.set((ICFBamDbKeyHash160Gen)rec);
					return(mapped); }
				case ICFBamDbKeyHash224Def.CLASS_CODE: {
					CFBamBuffDbKeyHash224Def mapped = new CFBamBuffDbKeyHash224Def();
					mapped.set((ICFBamDbKeyHash224Def)rec);
					return(mapped); }
				case ICFBamDbKeyHash224Col.CLASS_CODE: {
					CFBamBuffDbKeyHash224Col mapped = new CFBamBuffDbKeyHash224Col();
					mapped.set((ICFBamDbKeyHash224Col)rec);
					return(mapped); }
				case ICFBamDbKeyHash224Type.CLASS_CODE: {
					CFBamBuffDbKeyHash224Type mapped = new CFBamBuffDbKeyHash224Type();
					mapped.set((ICFBamDbKeyHash224Type)rec);
					return(mapped); }
				case ICFBamDbKeyHash224Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash224Gen mapped = new CFBamBuffDbKeyHash224Gen();
					mapped.set((ICFBamDbKeyHash224Gen)rec);
					return(mapped); }
				case ICFBamDbKeyHash256Def.CLASS_CODE: {
					CFBamBuffDbKeyHash256Def mapped = new CFBamBuffDbKeyHash256Def();
					mapped.set((ICFBamDbKeyHash256Def)rec);
					return(mapped); }
				case ICFBamDbKeyHash256Col.CLASS_CODE: {
					CFBamBuffDbKeyHash256Col mapped = new CFBamBuffDbKeyHash256Col();
					mapped.set((ICFBamDbKeyHash256Col)rec);
					return(mapped); }
				case ICFBamDbKeyHash256Type.CLASS_CODE: {
					CFBamBuffDbKeyHash256Type mapped = new CFBamBuffDbKeyHash256Type();
					mapped.set((ICFBamDbKeyHash256Type)rec);
					return(mapped); }
				case ICFBamDbKeyHash256Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash256Gen mapped = new CFBamBuffDbKeyHash256Gen();
					mapped.set((ICFBamDbKeyHash256Gen)rec);
					return(mapped); }
				case ICFBamDbKeyHash384Def.CLASS_CODE: {
					CFBamBuffDbKeyHash384Def mapped = new CFBamBuffDbKeyHash384Def();
					mapped.set((ICFBamDbKeyHash384Def)rec);
					return(mapped); }
				case ICFBamDbKeyHash384Col.CLASS_CODE: {
					CFBamBuffDbKeyHash384Col mapped = new CFBamBuffDbKeyHash384Col();
					mapped.set((ICFBamDbKeyHash384Col)rec);
					return(mapped); }
				case ICFBamDbKeyHash384Type.CLASS_CODE: {
					CFBamBuffDbKeyHash384Type mapped = new CFBamBuffDbKeyHash384Type();
					mapped.set((ICFBamDbKeyHash384Type)rec);
					return(mapped); }
				case ICFBamDbKeyHash384Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash384Gen mapped = new CFBamBuffDbKeyHash384Gen();
					mapped.set((ICFBamDbKeyHash384Gen)rec);
					return(mapped); }
				case ICFBamDbKeyHash512Def.CLASS_CODE: {
					CFBamBuffDbKeyHash512Def mapped = new CFBamBuffDbKeyHash512Def();
					mapped.set((ICFBamDbKeyHash512Def)rec);
					return(mapped); }
				case ICFBamDbKeyHash512Col.CLASS_CODE: {
					CFBamBuffDbKeyHash512Col mapped = new CFBamBuffDbKeyHash512Col();
					mapped.set((ICFBamDbKeyHash512Col)rec);
					return(mapped); }
				case ICFBamDbKeyHash512Type.CLASS_CODE: {
					CFBamBuffDbKeyHash512Type mapped = new CFBamBuffDbKeyHash512Type();
					mapped.set((ICFBamDbKeyHash512Type)rec);
					return(mapped); }
				case ICFBamDbKeyHash512Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash512Gen mapped = new CFBamBuffDbKeyHash512Gen();
					mapped.set((ICFBamDbKeyHash512Gen)rec);
					return(mapped); }
				case ICFBamStringDef.CLASS_CODE: {
					CFBamBuffStringDef mapped = new CFBamBuffStringDef();
					mapped.set((ICFBamStringDef)rec);
					return(mapped); }
				case ICFBamStringType.CLASS_CODE: {
					CFBamBuffStringType mapped = new CFBamBuffStringType();
					mapped.set((ICFBamStringType)rec);
					return(mapped); }
				case ICFBamStringCol.CLASS_CODE: {
					CFBamBuffStringCol mapped = new CFBamBuffStringCol();
					mapped.set((ICFBamStringCol)rec);
					return(mapped); }
				case ICFBamTZDateDef.CLASS_CODE: {
					CFBamBuffTZDateDef mapped = new CFBamBuffTZDateDef();
					mapped.set((ICFBamTZDateDef)rec);
					return(mapped); }
				case ICFBamTZDateType.CLASS_CODE: {
					CFBamBuffTZDateType mapped = new CFBamBuffTZDateType();
					mapped.set((ICFBamTZDateType)rec);
					return(mapped); }
				case ICFBamTZDateCol.CLASS_CODE: {
					CFBamBuffTZDateCol mapped = new CFBamBuffTZDateCol();
					mapped.set((ICFBamTZDateCol)rec);
					return(mapped); }
				case ICFBamTZTimeDef.CLASS_CODE: {
					CFBamBuffTZTimeDef mapped = new CFBamBuffTZTimeDef();
					mapped.set((ICFBamTZTimeDef)rec);
					return(mapped); }
				case ICFBamTZTimeType.CLASS_CODE: {
					CFBamBuffTZTimeType mapped = new CFBamBuffTZTimeType();
					mapped.set((ICFBamTZTimeType)rec);
					return(mapped); }
				case ICFBamTZTimeCol.CLASS_CODE: {
					CFBamBuffTZTimeCol mapped = new CFBamBuffTZTimeCol();
					mapped.set((ICFBamTZTimeCol)rec);
					return(mapped); }
				case ICFBamTZTimestampDef.CLASS_CODE: {
					CFBamBuffTZTimestampDef mapped = new CFBamBuffTZTimestampDef();
					mapped.set((ICFBamTZTimestampDef)rec);
					return(mapped); }
				case ICFBamTZTimestampType.CLASS_CODE: {
					CFBamBuffTZTimestampType mapped = new CFBamBuffTZTimestampType();
					mapped.set((ICFBamTZTimestampType)rec);
					return(mapped); }
				case ICFBamTZTimestampCol.CLASS_CODE: {
					CFBamBuffTZTimestampCol mapped = new CFBamBuffTZTimestampCol();
					mapped.set((ICFBamTZTimestampCol)rec);
					return(mapped); }
				case ICFBamTextDef.CLASS_CODE: {
					CFBamBuffTextDef mapped = new CFBamBuffTextDef();
					mapped.set((ICFBamTextDef)rec);
					return(mapped); }
				case ICFBamTextType.CLASS_CODE: {
					CFBamBuffTextType mapped = new CFBamBuffTextType();
					mapped.set((ICFBamTextType)rec);
					return(mapped); }
				case ICFBamTextCol.CLASS_CODE: {
					CFBamBuffTextCol mapped = new CFBamBuffTextCol();
					mapped.set((ICFBamTextCol)rec);
					return(mapped); }
				case ICFBamTimeDef.CLASS_CODE: {
					CFBamBuffTimeDef mapped = new CFBamBuffTimeDef();
					mapped.set((ICFBamTimeDef)rec);
					return(mapped); }
				case ICFBamTimeType.CLASS_CODE: {
					CFBamBuffTimeType mapped = new CFBamBuffTimeType();
					mapped.set((ICFBamTimeType)rec);
					return(mapped); }
				case ICFBamTimeCol.CLASS_CODE: {
					CFBamBuffTimeCol mapped = new CFBamBuffTimeCol();
					mapped.set((ICFBamTimeCol)rec);
					return(mapped); }
				case ICFBamTimestampDef.CLASS_CODE: {
					CFBamBuffTimestampDef mapped = new CFBamBuffTimestampDef();
					mapped.set((ICFBamTimestampDef)rec);
					return(mapped); }
				case ICFBamTimestampType.CLASS_CODE: {
					CFBamBuffTimestampType mapped = new CFBamBuffTimestampType();
					mapped.set((ICFBamTimestampType)rec);
					return(mapped); }
				case ICFBamTimestampCol.CLASS_CODE: {
					CFBamBuffTimestampCol mapped = new CFBamBuffTimestampCol();
					mapped.set((ICFBamTimestampCol)rec);
					return(mapped); }
				case ICFBamTokenDef.CLASS_CODE: {
					CFBamBuffTokenDef mapped = new CFBamBuffTokenDef();
					mapped.set((ICFBamTokenDef)rec);
					return(mapped); }
				case ICFBamTokenType.CLASS_CODE: {
					CFBamBuffTokenType mapped = new CFBamBuffTokenType();
					mapped.set((ICFBamTokenType)rec);
					return(mapped); }
				case ICFBamTokenCol.CLASS_CODE: {
					CFBamBuffTokenCol mapped = new CFBamBuffTokenCol();
					mapped.set((ICFBamTokenCol)rec);
					return(mapped); }
				case ICFBamUInt16Def.CLASS_CODE: {
					CFBamBuffUInt16Def mapped = new CFBamBuffUInt16Def();
					mapped.set((ICFBamUInt16Def)rec);
					return(mapped); }
				case ICFBamUInt16Type.CLASS_CODE: {
					CFBamBuffUInt16Type mapped = new CFBamBuffUInt16Type();
					mapped.set((ICFBamUInt16Type)rec);
					return(mapped); }
				case ICFBamUInt16Col.CLASS_CODE: {
					CFBamBuffUInt16Col mapped = new CFBamBuffUInt16Col();
					mapped.set((ICFBamUInt16Col)rec);
					return(mapped); }
				case ICFBamUInt32Def.CLASS_CODE: {
					CFBamBuffUInt32Def mapped = new CFBamBuffUInt32Def();
					mapped.set((ICFBamUInt32Def)rec);
					return(mapped); }
				case ICFBamUInt32Type.CLASS_CODE: {
					CFBamBuffUInt32Type mapped = new CFBamBuffUInt32Type();
					mapped.set((ICFBamUInt32Type)rec);
					return(mapped); }
				case ICFBamUInt32Col.CLASS_CODE: {
					CFBamBuffUInt32Col mapped = new CFBamBuffUInt32Col();
					mapped.set((ICFBamUInt32Col)rec);
					return(mapped); }
				case ICFBamUInt64Def.CLASS_CODE: {
					CFBamBuffUInt64Def mapped = new CFBamBuffUInt64Def();
					mapped.set((ICFBamUInt64Def)rec);
					return(mapped); }
				case ICFBamUInt64Type.CLASS_CODE: {
					CFBamBuffUInt64Type mapped = new CFBamBuffUInt64Type();
					mapped.set((ICFBamUInt64Type)rec);
					return(mapped); }
				case ICFBamUInt64Col.CLASS_CODE: {
					CFBamBuffUInt64Col mapped = new CFBamBuffUInt64Col();
					mapped.set((ICFBamUInt64Col)rec);
					return(mapped); }
				case ICFBamUuidDef.CLASS_CODE: {
					CFBamBuffUuidDef mapped = new CFBamBuffUuidDef();
					mapped.set((ICFBamUuidDef)rec);
					return(mapped); }
				case ICFBamUuidType.CLASS_CODE: {
					CFBamBuffUuidType mapped = new CFBamBuffUuidType();
					mapped.set((ICFBamUuidType)rec);
					return(mapped); }
				case ICFBamUuidGen.CLASS_CODE: {
					CFBamBuffUuidGen mapped = new CFBamBuffUuidGen();
					mapped.set((ICFBamUuidGen)rec);
					return(mapped); }
				case ICFBamUuidCol.CLASS_CODE: {
					CFBamBuffUuidCol mapped = new CFBamBuffUuidCol();
					mapped.set((ICFBamUuidCol)rec);
					return(mapped); }
				case ICFBamUuid6Def.CLASS_CODE: {
					CFBamBuffUuid6Def mapped = new CFBamBuffUuid6Def();
					mapped.set((ICFBamUuid6Def)rec);
					return(mapped); }
				case ICFBamUuid6Type.CLASS_CODE: {
					CFBamBuffUuid6Type mapped = new CFBamBuffUuid6Type();
					mapped.set((ICFBamUuid6Type)rec);
					return(mapped); }
				case ICFBamUuid6Gen.CLASS_CODE: {
					CFBamBuffUuid6Gen mapped = new CFBamBuffUuid6Gen();
					mapped.set((ICFBamUuid6Gen)rec);
					return(mapped); }
				case ICFBamUuid6Col.CLASS_CODE: {
					CFBamBuffUuid6Col mapped = new CFBamBuffUuid6Col();
					mapped.set((ICFBamUuid6Col)rec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureRec",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamAtom",
						"Unsupported class code " + rec.getClassCode() + " is not a derivative of CFBamAtom");
			}
		}
	}

	@Override
	public ICFBamAtomH newHRec() {
		ICFBamAtomH hrec =
			new CFBamBuffAtomH();
		return( hrec );
	}

	public CFBamBuffAtomH ensureHRec(ICFBamAtomH hrec) {
		if( hrec == null ) {
			return( null );
		}
		else if (hrec instanceof CFBamBuffAtomH) {
			return ((CFBamBuffAtomH)hrec);
		}
		else {	
			switch (hrec.getClassCode()) {
				case ICFBamAtom.CLASS_CODE: {
					CFBamBuffAtomH mapped = new CFBamBuffAtomH();
					mapped.set(hrec);
					return(mapped); }
				case ICFBamBlobDef.CLASS_CODE: {
					CFBamBuffBlobDefH mapped = new CFBamBuffBlobDefH();
					mapped.set((ICFBamBlobDefH)hrec);
					return(mapped); }
				case ICFBamBlobType.CLASS_CODE: {
					CFBamBuffBlobTypeH mapped = new CFBamBuffBlobTypeH();
					mapped.set((ICFBamBlobTypeH)hrec);
					return(mapped); }
				case ICFBamBlobCol.CLASS_CODE: {
					CFBamBuffBlobColH mapped = new CFBamBuffBlobColH();
					mapped.set((ICFBamBlobColH)hrec);
					return(mapped); }
				case ICFBamBoolDef.CLASS_CODE: {
					CFBamBuffBoolDefH mapped = new CFBamBuffBoolDefH();
					mapped.set((ICFBamBoolDefH)hrec);
					return(mapped); }
				case ICFBamBoolType.CLASS_CODE: {
					CFBamBuffBoolTypeH mapped = new CFBamBuffBoolTypeH();
					mapped.set((ICFBamBoolTypeH)hrec);
					return(mapped); }
				case ICFBamBoolCol.CLASS_CODE: {
					CFBamBuffBoolColH mapped = new CFBamBuffBoolColH();
					mapped.set((ICFBamBoolColH)hrec);
					return(mapped); }
				case ICFBamDateDef.CLASS_CODE: {
					CFBamBuffDateDefH mapped = new CFBamBuffDateDefH();
					mapped.set((ICFBamDateDefH)hrec);
					return(mapped); }
				case ICFBamDateType.CLASS_CODE: {
					CFBamBuffDateTypeH mapped = new CFBamBuffDateTypeH();
					mapped.set((ICFBamDateTypeH)hrec);
					return(mapped); }
				case ICFBamDateCol.CLASS_CODE: {
					CFBamBuffDateColH mapped = new CFBamBuffDateColH();
					mapped.set((ICFBamDateColH)hrec);
					return(mapped); }
				case ICFBamDoubleDef.CLASS_CODE: {
					CFBamBuffDoubleDefH mapped = new CFBamBuffDoubleDefH();
					mapped.set((ICFBamDoubleDefH)hrec);
					return(mapped); }
				case ICFBamDoubleType.CLASS_CODE: {
					CFBamBuffDoubleTypeH mapped = new CFBamBuffDoubleTypeH();
					mapped.set((ICFBamDoubleTypeH)hrec);
					return(mapped); }
				case ICFBamDoubleCol.CLASS_CODE: {
					CFBamBuffDoubleColH mapped = new CFBamBuffDoubleColH();
					mapped.set((ICFBamDoubleColH)hrec);
					return(mapped); }
				case ICFBamFloatDef.CLASS_CODE: {
					CFBamBuffFloatDefH mapped = new CFBamBuffFloatDefH();
					mapped.set((ICFBamFloatDefH)hrec);
					return(mapped); }
				case ICFBamFloatType.CLASS_CODE: {
					CFBamBuffFloatTypeH mapped = new CFBamBuffFloatTypeH();
					mapped.set((ICFBamFloatTypeH)hrec);
					return(mapped); }
				case ICFBamFloatCol.CLASS_CODE: {
					CFBamBuffFloatColH mapped = new CFBamBuffFloatColH();
					mapped.set((ICFBamFloatColH)hrec);
					return(mapped); }
				case ICFBamInt16Def.CLASS_CODE: {
					CFBamBuffInt16DefH mapped = new CFBamBuffInt16DefH();
					mapped.set((ICFBamInt16DefH)hrec);
					return(mapped); }
				case ICFBamInt16Type.CLASS_CODE: {
					CFBamBuffInt16TypeH mapped = new CFBamBuffInt16TypeH();
					mapped.set((ICFBamInt16TypeH)hrec);
					return(mapped); }
				case ICFBamId16Gen.CLASS_CODE: {
					CFBamBuffId16GenH mapped = new CFBamBuffId16GenH();
					mapped.set((ICFBamId16GenH)hrec);
					return(mapped); }
				case ICFBamEnumDef.CLASS_CODE: {
					CFBamBuffEnumDefH mapped = new CFBamBuffEnumDefH();
					mapped.set((ICFBamEnumDefH)hrec);
					return(mapped); }
				case ICFBamEnumType.CLASS_CODE: {
					CFBamBuffEnumTypeH mapped = new CFBamBuffEnumTypeH();
					mapped.set((ICFBamEnumTypeH)hrec);
					return(mapped); }
				case ICFBamInt16Col.CLASS_CODE: {
					CFBamBuffInt16ColH mapped = new CFBamBuffInt16ColH();
					mapped.set((ICFBamInt16ColH)hrec);
					return(mapped); }
				case ICFBamInt32Def.CLASS_CODE: {
					CFBamBuffInt32DefH mapped = new CFBamBuffInt32DefH();
					mapped.set((ICFBamInt32DefH)hrec);
					return(mapped); }
				case ICFBamInt32Type.CLASS_CODE: {
					CFBamBuffInt32TypeH mapped = new CFBamBuffInt32TypeH();
					mapped.set((ICFBamInt32TypeH)hrec);
					return(mapped); }
				case ICFBamId32Gen.CLASS_CODE: {
					CFBamBuffId32GenH mapped = new CFBamBuffId32GenH();
					mapped.set((ICFBamId32GenH)hrec);
					return(mapped); }
				case ICFBamInt32Col.CLASS_CODE: {
					CFBamBuffInt32ColH mapped = new CFBamBuffInt32ColH();
					mapped.set((ICFBamInt32ColH)hrec);
					return(mapped); }
				case ICFBamInt64Def.CLASS_CODE: {
					CFBamBuffInt64DefH mapped = new CFBamBuffInt64DefH();
					mapped.set((ICFBamInt64DefH)hrec);
					return(mapped); }
				case ICFBamInt64Type.CLASS_CODE: {
					CFBamBuffInt64TypeH mapped = new CFBamBuffInt64TypeH();
					mapped.set((ICFBamInt64TypeH)hrec);
					return(mapped); }
				case ICFBamId64Gen.CLASS_CODE: {
					CFBamBuffId64GenH mapped = new CFBamBuffId64GenH();
					mapped.set((ICFBamId64GenH)hrec);
					return(mapped); }
				case ICFBamInt64Col.CLASS_CODE: {
					CFBamBuffInt64ColH mapped = new CFBamBuffInt64ColH();
					mapped.set((ICFBamInt64ColH)hrec);
					return(mapped); }
				case ICFBamNmTokenDef.CLASS_CODE: {
					CFBamBuffNmTokenDefH mapped = new CFBamBuffNmTokenDefH();
					mapped.set((ICFBamNmTokenDefH)hrec);
					return(mapped); }
				case ICFBamNmTokenType.CLASS_CODE: {
					CFBamBuffNmTokenTypeH mapped = new CFBamBuffNmTokenTypeH();
					mapped.set((ICFBamNmTokenTypeH)hrec);
					return(mapped); }
				case ICFBamNmTokenCol.CLASS_CODE: {
					CFBamBuffNmTokenColH mapped = new CFBamBuffNmTokenColH();
					mapped.set((ICFBamNmTokenColH)hrec);
					return(mapped); }
				case ICFBamNmTokensDef.CLASS_CODE: {
					CFBamBuffNmTokensDefH mapped = new CFBamBuffNmTokensDefH();
					mapped.set((ICFBamNmTokensDefH)hrec);
					return(mapped); }
				case ICFBamNmTokensType.CLASS_CODE: {
					CFBamBuffNmTokensTypeH mapped = new CFBamBuffNmTokensTypeH();
					mapped.set((ICFBamNmTokensTypeH)hrec);
					return(mapped); }
				case ICFBamNmTokensCol.CLASS_CODE: {
					CFBamBuffNmTokensColH mapped = new CFBamBuffNmTokensColH();
					mapped.set((ICFBamNmTokensColH)hrec);
					return(mapped); }
				case ICFBamNumberDef.CLASS_CODE: {
					CFBamBuffNumberDefH mapped = new CFBamBuffNumberDefH();
					mapped.set((ICFBamNumberDefH)hrec);
					return(mapped); }
				case ICFBamNumberType.CLASS_CODE: {
					CFBamBuffNumberTypeH mapped = new CFBamBuffNumberTypeH();
					mapped.set((ICFBamNumberTypeH)hrec);
					return(mapped); }
				case ICFBamNumberCol.CLASS_CODE: {
					CFBamBuffNumberColH mapped = new CFBamBuffNumberColH();
					mapped.set((ICFBamNumberColH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash128Def.CLASS_CODE: {
					CFBamBuffDbKeyHash128DefH mapped = new CFBamBuffDbKeyHash128DefH();
					mapped.set((ICFBamDbKeyHash128DefH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash128Col.CLASS_CODE: {
					CFBamBuffDbKeyHash128ColH mapped = new CFBamBuffDbKeyHash128ColH();
					mapped.set((ICFBamDbKeyHash128ColH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash128Type.CLASS_CODE: {
					CFBamBuffDbKeyHash128TypeH mapped = new CFBamBuffDbKeyHash128TypeH();
					mapped.set((ICFBamDbKeyHash128TypeH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash128Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash128GenH mapped = new CFBamBuffDbKeyHash128GenH();
					mapped.set((ICFBamDbKeyHash128GenH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash160Def.CLASS_CODE: {
					CFBamBuffDbKeyHash160DefH mapped = new CFBamBuffDbKeyHash160DefH();
					mapped.set((ICFBamDbKeyHash160DefH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash160Col.CLASS_CODE: {
					CFBamBuffDbKeyHash160ColH mapped = new CFBamBuffDbKeyHash160ColH();
					mapped.set((ICFBamDbKeyHash160ColH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash160Type.CLASS_CODE: {
					CFBamBuffDbKeyHash160TypeH mapped = new CFBamBuffDbKeyHash160TypeH();
					mapped.set((ICFBamDbKeyHash160TypeH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash160Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash160GenH mapped = new CFBamBuffDbKeyHash160GenH();
					mapped.set((ICFBamDbKeyHash160GenH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash224Def.CLASS_CODE: {
					CFBamBuffDbKeyHash224DefH mapped = new CFBamBuffDbKeyHash224DefH();
					mapped.set((ICFBamDbKeyHash224DefH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash224Col.CLASS_CODE: {
					CFBamBuffDbKeyHash224ColH mapped = new CFBamBuffDbKeyHash224ColH();
					mapped.set((ICFBamDbKeyHash224ColH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash224Type.CLASS_CODE: {
					CFBamBuffDbKeyHash224TypeH mapped = new CFBamBuffDbKeyHash224TypeH();
					mapped.set((ICFBamDbKeyHash224TypeH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash224Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash224GenH mapped = new CFBamBuffDbKeyHash224GenH();
					mapped.set((ICFBamDbKeyHash224GenH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash256Def.CLASS_CODE: {
					CFBamBuffDbKeyHash256DefH mapped = new CFBamBuffDbKeyHash256DefH();
					mapped.set((ICFBamDbKeyHash256DefH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash256Col.CLASS_CODE: {
					CFBamBuffDbKeyHash256ColH mapped = new CFBamBuffDbKeyHash256ColH();
					mapped.set((ICFBamDbKeyHash256ColH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash256Type.CLASS_CODE: {
					CFBamBuffDbKeyHash256TypeH mapped = new CFBamBuffDbKeyHash256TypeH();
					mapped.set((ICFBamDbKeyHash256TypeH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash256Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash256GenH mapped = new CFBamBuffDbKeyHash256GenH();
					mapped.set((ICFBamDbKeyHash256GenH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash384Def.CLASS_CODE: {
					CFBamBuffDbKeyHash384DefH mapped = new CFBamBuffDbKeyHash384DefH();
					mapped.set((ICFBamDbKeyHash384DefH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash384Col.CLASS_CODE: {
					CFBamBuffDbKeyHash384ColH mapped = new CFBamBuffDbKeyHash384ColH();
					mapped.set((ICFBamDbKeyHash384ColH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash384Type.CLASS_CODE: {
					CFBamBuffDbKeyHash384TypeH mapped = new CFBamBuffDbKeyHash384TypeH();
					mapped.set((ICFBamDbKeyHash384TypeH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash384Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash384GenH mapped = new CFBamBuffDbKeyHash384GenH();
					mapped.set((ICFBamDbKeyHash384GenH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash512Def.CLASS_CODE: {
					CFBamBuffDbKeyHash512DefH mapped = new CFBamBuffDbKeyHash512DefH();
					mapped.set((ICFBamDbKeyHash512DefH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash512Col.CLASS_CODE: {
					CFBamBuffDbKeyHash512ColH mapped = new CFBamBuffDbKeyHash512ColH();
					mapped.set((ICFBamDbKeyHash512ColH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash512Type.CLASS_CODE: {
					CFBamBuffDbKeyHash512TypeH mapped = new CFBamBuffDbKeyHash512TypeH();
					mapped.set((ICFBamDbKeyHash512TypeH)hrec);
					return(mapped); }
				case ICFBamDbKeyHash512Gen.CLASS_CODE: {
					CFBamBuffDbKeyHash512GenH mapped = new CFBamBuffDbKeyHash512GenH();
					mapped.set((ICFBamDbKeyHash512GenH)hrec);
					return(mapped); }
				case ICFBamStringDef.CLASS_CODE: {
					CFBamBuffStringDefH mapped = new CFBamBuffStringDefH();
					mapped.set((ICFBamStringDefH)hrec);
					return(mapped); }
				case ICFBamStringType.CLASS_CODE: {
					CFBamBuffStringTypeH mapped = new CFBamBuffStringTypeH();
					mapped.set((ICFBamStringTypeH)hrec);
					return(mapped); }
				case ICFBamStringCol.CLASS_CODE: {
					CFBamBuffStringColH mapped = new CFBamBuffStringColH();
					mapped.set((ICFBamStringColH)hrec);
					return(mapped); }
				case ICFBamTZDateDef.CLASS_CODE: {
					CFBamBuffTZDateDefH mapped = new CFBamBuffTZDateDefH();
					mapped.set((ICFBamTZDateDefH)hrec);
					return(mapped); }
				case ICFBamTZDateType.CLASS_CODE: {
					CFBamBuffTZDateTypeH mapped = new CFBamBuffTZDateTypeH();
					mapped.set((ICFBamTZDateTypeH)hrec);
					return(mapped); }
				case ICFBamTZDateCol.CLASS_CODE: {
					CFBamBuffTZDateColH mapped = new CFBamBuffTZDateColH();
					mapped.set((ICFBamTZDateColH)hrec);
					return(mapped); }
				case ICFBamTZTimeDef.CLASS_CODE: {
					CFBamBuffTZTimeDefH mapped = new CFBamBuffTZTimeDefH();
					mapped.set((ICFBamTZTimeDefH)hrec);
					return(mapped); }
				case ICFBamTZTimeType.CLASS_CODE: {
					CFBamBuffTZTimeTypeH mapped = new CFBamBuffTZTimeTypeH();
					mapped.set((ICFBamTZTimeTypeH)hrec);
					return(mapped); }
				case ICFBamTZTimeCol.CLASS_CODE: {
					CFBamBuffTZTimeColH mapped = new CFBamBuffTZTimeColH();
					mapped.set((ICFBamTZTimeColH)hrec);
					return(mapped); }
				case ICFBamTZTimestampDef.CLASS_CODE: {
					CFBamBuffTZTimestampDefH mapped = new CFBamBuffTZTimestampDefH();
					mapped.set((ICFBamTZTimestampDefH)hrec);
					return(mapped); }
				case ICFBamTZTimestampType.CLASS_CODE: {
					CFBamBuffTZTimestampTypeH mapped = new CFBamBuffTZTimestampTypeH();
					mapped.set((ICFBamTZTimestampTypeH)hrec);
					return(mapped); }
				case ICFBamTZTimestampCol.CLASS_CODE: {
					CFBamBuffTZTimestampColH mapped = new CFBamBuffTZTimestampColH();
					mapped.set((ICFBamTZTimestampColH)hrec);
					return(mapped); }
				case ICFBamTextDef.CLASS_CODE: {
					CFBamBuffTextDefH mapped = new CFBamBuffTextDefH();
					mapped.set((ICFBamTextDefH)hrec);
					return(mapped); }
				case ICFBamTextType.CLASS_CODE: {
					CFBamBuffTextTypeH mapped = new CFBamBuffTextTypeH();
					mapped.set((ICFBamTextTypeH)hrec);
					return(mapped); }
				case ICFBamTextCol.CLASS_CODE: {
					CFBamBuffTextColH mapped = new CFBamBuffTextColH();
					mapped.set((ICFBamTextColH)hrec);
					return(mapped); }
				case ICFBamTimeDef.CLASS_CODE: {
					CFBamBuffTimeDefH mapped = new CFBamBuffTimeDefH();
					mapped.set((ICFBamTimeDefH)hrec);
					return(mapped); }
				case ICFBamTimeType.CLASS_CODE: {
					CFBamBuffTimeTypeH mapped = new CFBamBuffTimeTypeH();
					mapped.set((ICFBamTimeTypeH)hrec);
					return(mapped); }
				case ICFBamTimeCol.CLASS_CODE: {
					CFBamBuffTimeColH mapped = new CFBamBuffTimeColH();
					mapped.set((ICFBamTimeColH)hrec);
					return(mapped); }
				case ICFBamTimestampDef.CLASS_CODE: {
					CFBamBuffTimestampDefH mapped = new CFBamBuffTimestampDefH();
					mapped.set((ICFBamTimestampDefH)hrec);
					return(mapped); }
				case ICFBamTimestampType.CLASS_CODE: {
					CFBamBuffTimestampTypeH mapped = new CFBamBuffTimestampTypeH();
					mapped.set((ICFBamTimestampTypeH)hrec);
					return(mapped); }
				case ICFBamTimestampCol.CLASS_CODE: {
					CFBamBuffTimestampColH mapped = new CFBamBuffTimestampColH();
					mapped.set((ICFBamTimestampColH)hrec);
					return(mapped); }
				case ICFBamTokenDef.CLASS_CODE: {
					CFBamBuffTokenDefH mapped = new CFBamBuffTokenDefH();
					mapped.set((ICFBamTokenDefH)hrec);
					return(mapped); }
				case ICFBamTokenType.CLASS_CODE: {
					CFBamBuffTokenTypeH mapped = new CFBamBuffTokenTypeH();
					mapped.set((ICFBamTokenTypeH)hrec);
					return(mapped); }
				case ICFBamTokenCol.CLASS_CODE: {
					CFBamBuffTokenColH mapped = new CFBamBuffTokenColH();
					mapped.set((ICFBamTokenColH)hrec);
					return(mapped); }
				case ICFBamUInt16Def.CLASS_CODE: {
					CFBamBuffUInt16DefH mapped = new CFBamBuffUInt16DefH();
					mapped.set((ICFBamUInt16DefH)hrec);
					return(mapped); }
				case ICFBamUInt16Type.CLASS_CODE: {
					CFBamBuffUInt16TypeH mapped = new CFBamBuffUInt16TypeH();
					mapped.set((ICFBamUInt16TypeH)hrec);
					return(mapped); }
				case ICFBamUInt16Col.CLASS_CODE: {
					CFBamBuffUInt16ColH mapped = new CFBamBuffUInt16ColH();
					mapped.set((ICFBamUInt16ColH)hrec);
					return(mapped); }
				case ICFBamUInt32Def.CLASS_CODE: {
					CFBamBuffUInt32DefH mapped = new CFBamBuffUInt32DefH();
					mapped.set((ICFBamUInt32DefH)hrec);
					return(mapped); }
				case ICFBamUInt32Type.CLASS_CODE: {
					CFBamBuffUInt32TypeH mapped = new CFBamBuffUInt32TypeH();
					mapped.set((ICFBamUInt32TypeH)hrec);
					return(mapped); }
				case ICFBamUInt32Col.CLASS_CODE: {
					CFBamBuffUInt32ColH mapped = new CFBamBuffUInt32ColH();
					mapped.set((ICFBamUInt32ColH)hrec);
					return(mapped); }
				case ICFBamUInt64Def.CLASS_CODE: {
					CFBamBuffUInt64DefH mapped = new CFBamBuffUInt64DefH();
					mapped.set((ICFBamUInt64DefH)hrec);
					return(mapped); }
				case ICFBamUInt64Type.CLASS_CODE: {
					CFBamBuffUInt64TypeH mapped = new CFBamBuffUInt64TypeH();
					mapped.set((ICFBamUInt64TypeH)hrec);
					return(mapped); }
				case ICFBamUInt64Col.CLASS_CODE: {
					CFBamBuffUInt64ColH mapped = new CFBamBuffUInt64ColH();
					mapped.set((ICFBamUInt64ColH)hrec);
					return(mapped); }
				case ICFBamUuidDef.CLASS_CODE: {
					CFBamBuffUuidDefH mapped = new CFBamBuffUuidDefH();
					mapped.set((ICFBamUuidDefH)hrec);
					return(mapped); }
				case ICFBamUuidType.CLASS_CODE: {
					CFBamBuffUuidTypeH mapped = new CFBamBuffUuidTypeH();
					mapped.set((ICFBamUuidTypeH)hrec);
					return(mapped); }
				case ICFBamUuidGen.CLASS_CODE: {
					CFBamBuffUuidGenH mapped = new CFBamBuffUuidGenH();
					mapped.set((ICFBamUuidGenH)hrec);
					return(mapped); }
				case ICFBamUuidCol.CLASS_CODE: {
					CFBamBuffUuidColH mapped = new CFBamBuffUuidColH();
					mapped.set((ICFBamUuidColH)hrec);
					return(mapped); }
				case ICFBamUuid6Def.CLASS_CODE: {
					CFBamBuffUuid6DefH mapped = new CFBamBuffUuid6DefH();
					mapped.set((ICFBamUuid6DefH)hrec);
					return(mapped); }
				case ICFBamUuid6Type.CLASS_CODE: {
					CFBamBuffUuid6TypeH mapped = new CFBamBuffUuid6TypeH();
					mapped.set((ICFBamUuid6TypeH)hrec);
					return(mapped); }
				case ICFBamUuid6Gen.CLASS_CODE: {
					CFBamBuffUuid6GenH mapped = new CFBamBuffUuid6GenH();
					mapped.set((ICFBamUuid6GenH)hrec);
					return(mapped); }
				case ICFBamUuid6Col.CLASS_CODE: {
					CFBamBuffUuid6ColH mapped = new CFBamBuffUuid6ColH();
					mapped.set((ICFBamUuid6ColH)hrec);
					return(mapped); }
				default:
					throw new CFLibUnsupportedClassException(getClass(), "ensureHRec",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamAtom",
						"Unsupported class code " + hrec.getClassCode() + " is not a derivative of CFBamAtom");
			}
		}
	}
}
