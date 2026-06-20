// Description: Java 25 interface for a CFBam schema.

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

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.math.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.*;
import java.util.*;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import java.util.concurrent.atomic.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;

public interface ICFBamSchema
extends ICFSecSchema,
	ICFIntSchema
{
	public static final String SCHEMA_NAME = "CFBam";
	public static final String DBSCHEMA_NAME = "CFBam31";
	static final AtomicReference<ApplicationContext> arApplicationContext = new AtomicReference<>(null);
	public static final CFSecTableData TABLE_DATA[] = {new CFSecTableData("CFBam", "Chain", null, true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "EnumTag", null, true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "IndexCol", null, true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Param", null, true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "RelationCol", null, true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "RoleDef", null, true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "SchemaRole", "RoleDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Scope", null, true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "SchemaDef", "Scope", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "SchemaRef", "Scope", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "ServerMethod", "Scope", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "ServerObjFunc", "ServerMethod", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "ServerProc", "ServerMethod", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "ServerListFunc", "ServerMethod", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Table", "Scope", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "ClearDep", "Scope", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "ClearSubDep1", "ClearDep", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "ClearSubDep2", "ClearDep", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "ClearSubDep3", "ClearDep", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "ClearTopDep", "ClearDep", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DelDep", "Scope", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DelSubDep1", "DelDep", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DelSubDep2", "DelDep", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DelSubDep3", "DelDep", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DelTopDep", "DelDep", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Index", "Scope", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "PopDep", "Scope", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "PopSubDep1", "PopDep", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "PopSubDep2", "PopDep", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "PopSubDep3", "PopDep", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "PopTopDep", "PopDep", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Relation", "Scope", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Tweak", null, true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TableTweak", "Tweak", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "SchemaTweak", "Tweak", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "IndexTweak", "Tweak", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Value", null, true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Atom", "Value", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "BlobDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "BlobType", "BlobDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "BlobCol", "BlobDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "BoolDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "BoolType", "BoolDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "BoolCol", "BoolDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DateDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DateType", "DateDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DateCol", "DateDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DoubleDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DoubleType", "DoubleDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DoubleCol", "DoubleDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "FloatDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "FloatType", "FloatDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "FloatCol", "FloatDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Int16Def", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Int16Type", "Int16Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Id16Gen", "Int16Type", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "EnumDef", "Int16Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "EnumType", "EnumDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Int16Col", "Int16Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Int32Def", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Int32Type", "Int32Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Id32Gen", "Int32Type", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Int32Col", "Int32Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Int64Def", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Int64Type", "Int64Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Id64Gen", "Int64Type", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Int64Col", "Int64Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "NmTokenDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "NmTokenType", "NmTokenDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "NmTokenCol", "NmTokenDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "NmTokensDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "NmTokensType", "NmTokensDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "NmTokensCol", "NmTokensDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "NumberDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "NumberType", "NumberDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "NumberCol", "NumberDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash128Def", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash128Col", "DbKeyHash128Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash128Type", "DbKeyHash128Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash128Gen", "DbKeyHash128Type", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash160Def", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash160Col", "DbKeyHash160Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash160Type", "DbKeyHash160Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash160Gen", "DbKeyHash160Type", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash224Def", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash224Col", "DbKeyHash224Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash224Type", "DbKeyHash224Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash224Gen", "DbKeyHash224Type", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash256Def", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash256Col", "DbKeyHash256Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash256Type", "DbKeyHash256Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash256Gen", "DbKeyHash256Type", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash384Def", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash384Col", "DbKeyHash384Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash384Type", "DbKeyHash384Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash384Gen", "DbKeyHash384Type", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash512Def", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash512Col", "DbKeyHash512Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash512Type", "DbKeyHash512Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "DbKeyHash512Gen", "DbKeyHash512Type", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "StringDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "StringType", "StringDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "StringCol", "StringDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TZDateDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TZDateType", "TZDateDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TZDateCol", "TZDateDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TZTimeDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TZTimeType", "TZTimeDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TZTimeCol", "TZTimeDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TZTimestampDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TZTimestampType", "TZTimestampDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TZTimestampCol", "TZTimestampDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TextDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TextType", "TextDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TextCol", "TextDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TimeDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TimeType", "TimeDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TimeCol", "TimeDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TimestampDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TimestampType", "TimestampDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TimestampCol", "TimestampDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TokenDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TokenType", "TokenDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TokenCol", "TokenDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "UInt16Def", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "UInt16Type", "UInt16Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "UInt16Col", "UInt16Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "UInt32Def", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "UInt32Type", "UInt32Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "UInt32Col", "UInt32Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "UInt64Def", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "UInt64Type", "UInt64Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "UInt64Col", "UInt64Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "UuidDef", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "UuidType", "UuidDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "UuidGen", "UuidType", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "UuidCol", "UuidDef", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Uuid6Def", "Atom", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Uuid6Type", "Uuid6Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Uuid6Gen", "Uuid6Type", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "Uuid6Col", "Uuid6Def", true, false, "Tenant", "Public"),
		new CFSecTableData("CFBam", "TableCol", "Value", true, false, "Tenant", "Public")};
	public static final AtomicReference<CFSecTableData[]> consolidatedTableData = new AtomicReference<>(null);
	public static final CFSecRoleInfo ROLE_INFO[] = {};
	public static final AtomicReference<CFSecRoleInfo[]> consolidatedRoleInfo = new AtomicReference<>(null);

	public static CFSecTableData[] getTableData() {
		return TABLE_DATA;
	}

	public static CFSecTableData[] getConsolidatedTableData() {
		if (consolidatedTableData.get() == null) {
			ArrayList<CFSecTableData> lst = new ArrayList<>();
			for( CFSecTableData data: ICFSecSchema.getTableData()) {
				lst.add(data);
			}
			for( CFSecTableData data: ICFIntSchema.getTableData()) {
				lst.add(data);
			}
			for( CFSecTableData data: TABLE_DATA) {
				lst.add(data);
			}
			CFSecTableData arr[] = new CFSecTableData[lst.size()];
			int idx = 0;
			for(CFSecTableData data: lst) {
				arr[idx++] = data;
			}
			consolidatedTableData.compareAndSet(null, arr);
		}
		return(consolidatedTableData.get());
	}

	public static CFSecRoleInfo[] getRoleInfo() {
		return ROLE_INFO;
	}

	public static CFSecRoleInfo[] getConsolidatedRoleInfo() {
		if (consolidatedRoleInfo.get() == null) {
			ArrayList<CFSecRoleInfo> lst = new ArrayList<>();
			for( CFSecRoleInfo info: ICFSecSchema.getRoleInfo()) {
				lst.add(info);
			}
			for( CFSecRoleInfo info: ICFIntSchema.getRoleInfo()) {
				lst.add(info);
			}
			for( CFSecRoleInfo info: ROLE_INFO) {
				lst.add(info);
			}
			// Dependency order is the natural order of role info comparison
			lst.sort(new CFSecRoleInfoDependencyComparator());
			CFSecRoleInfo arr[] = new CFSecRoleInfo[lst.size()];
			int idx = 0;
			for(CFSecRoleInfo info: lst) {
				arr[idx++] = info;
			}
			consolidatedRoleInfo.compareAndSet(null, arr);
		}
		return(consolidatedRoleInfo.get());
	}

	public default void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		arApplicationContext.compareAndSet(arApplicationContext.get(), applicationContext);
	}

	public static ApplicationContext getApplicationContext() {
		return( arApplicationContext.get() );
	}

		final static ArrayList<ICFSecSchema.ClassMapEntry> entries = new ArrayList<>();
		final static HashMap<Integer,ICFSecSchema.ClassMapEntry> mapBackingClassCodeToEntry = new HashMap<>();
		final static HashMap<Integer,ICFSecSchema.ClassMapEntry> mapRuntimeClassCodeToEntry = new HashMap<>();
		final static AtomicReference<ICFBamSchema> backingCFBam = new AtomicReference<>();
	public enum LoaderBehaviourEnum {
		Insert,
		Update,
		Replace
	};

	static HashMap<String,LoaderBehaviourEnum> lookupLoaderBehaviourEnum = new HashMap<String,LoaderBehaviourEnum>();

	public static LoaderBehaviourEnum parseLoaderBehaviourEnum( String value ) {
		LoaderBehaviourEnum retval = parseLoaderBehaviourEnum( ICFBamSchema.class.getName(), value );
		return( retval );
	}

	public static LoaderBehaviourEnum parseLoaderBehaviourEnum( String fieldOrClassName, String value ) {
		final String S_ProcName = "parseLoaderBehaviourEnum";
		if( lookupLoaderBehaviourEnum.isEmpty() ) {
			lookupLoaderBehaviourEnum.put( "Insert", LoaderBehaviourEnum.Insert );
			lookupLoaderBehaviourEnum.put( "Update", LoaderBehaviourEnum.Update );
			lookupLoaderBehaviourEnum.put( "Replace", LoaderBehaviourEnum.Replace );
		}
		LoaderBehaviourEnum retval;
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			retval = null;
		}
		else {
			retval = lookupLoaderBehaviourEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	static HashMap<Integer,LoaderBehaviourEnum> lookupOrdinalLoaderBehaviourEnum = new HashMap<Integer,LoaderBehaviourEnum>();

	public static LoaderBehaviourEnum ordinalToLoaderBehaviourEnum( String fieldOrClassName, Short value ) {
		LoaderBehaviourEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToLoaderBehaviourEnum( fieldOrClassName, Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static LoaderBehaviourEnum ordinalToLoaderBehaviourEnum( Short value ) {
		LoaderBehaviourEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToLoaderBehaviourEnum( Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static LoaderBehaviourEnum ordinalToLoaderBehaviourEnum( Integer value ) {
		LoaderBehaviourEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToLoaderBehaviourEnum( ICFBamSchema.class.getName(), Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static LoaderBehaviourEnum ordinalToLoaderBehaviourEnum( String fieldOrClassName, Integer value ) {
		final String S_ProcName = "ordinalToLoaderBehaviourEnum";
		if( lookupOrdinalLoaderBehaviourEnum.isEmpty() ) {
			lookupOrdinalLoaderBehaviourEnum.put( Integer.valueOf( LoaderBehaviourEnum.Insert.ordinal() ), LoaderBehaviourEnum.Insert );
			lookupOrdinalLoaderBehaviourEnum.put( Integer.valueOf( LoaderBehaviourEnum.Update.ordinal() ), LoaderBehaviourEnum.Update );
			lookupOrdinalLoaderBehaviourEnum.put( Integer.valueOf( LoaderBehaviourEnum.Replace.ordinal() ), LoaderBehaviourEnum.Replace );
		}
		LoaderBehaviourEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = lookupOrdinalLoaderBehaviourEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	public enum RelationTypeEnum {
		Unknown,
		Lookup,
		Superclass,
		Container,
		Components,
		Owner,
		Parent,
		Children
	};

	static HashMap<String,RelationTypeEnum> lookupRelationTypeEnum = new HashMap<String,RelationTypeEnum>();

	public static RelationTypeEnum parseRelationTypeEnum( String value ) {
		RelationTypeEnum retval = parseRelationTypeEnum( ICFBamSchema.class.getName(), value );
		return( retval );
	}

	public static RelationTypeEnum parseRelationTypeEnum( String fieldOrClassName, String value ) {
		final String S_ProcName = "parseRelationTypeEnum";
		if( lookupRelationTypeEnum.isEmpty() ) {
			lookupRelationTypeEnum.put( "Unknown", RelationTypeEnum.Unknown );
			lookupRelationTypeEnum.put( "Lookup", RelationTypeEnum.Lookup );
			lookupRelationTypeEnum.put( "Superclass", RelationTypeEnum.Superclass );
			lookupRelationTypeEnum.put( "Container", RelationTypeEnum.Container );
			lookupRelationTypeEnum.put( "Components", RelationTypeEnum.Components );
			lookupRelationTypeEnum.put( "Owner", RelationTypeEnum.Owner );
			lookupRelationTypeEnum.put( "Parent", RelationTypeEnum.Parent );
			lookupRelationTypeEnum.put( "Children", RelationTypeEnum.Children );
		}
		RelationTypeEnum retval;
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			retval = null;
		}
		else {
			retval = lookupRelationTypeEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	static HashMap<Integer,RelationTypeEnum> lookupOrdinalRelationTypeEnum = new HashMap<Integer,RelationTypeEnum>();

	public static RelationTypeEnum ordinalToRelationTypeEnum( String fieldOrClassName, Short value ) {
		RelationTypeEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToRelationTypeEnum( fieldOrClassName, Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static RelationTypeEnum ordinalToRelationTypeEnum( Short value ) {
		RelationTypeEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToRelationTypeEnum( Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static RelationTypeEnum ordinalToRelationTypeEnum( Integer value ) {
		RelationTypeEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToRelationTypeEnum( ICFBamSchema.class.getName(), Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static RelationTypeEnum ordinalToRelationTypeEnum( String fieldOrClassName, Integer value ) {
		final String S_ProcName = "ordinalToRelationTypeEnum";
		if( lookupOrdinalRelationTypeEnum.isEmpty() ) {
			lookupOrdinalRelationTypeEnum.put( Integer.valueOf( RelationTypeEnum.Unknown.ordinal() ), RelationTypeEnum.Unknown );
			lookupOrdinalRelationTypeEnum.put( Integer.valueOf( RelationTypeEnum.Lookup.ordinal() ), RelationTypeEnum.Lookup );
			lookupOrdinalRelationTypeEnum.put( Integer.valueOf( RelationTypeEnum.Superclass.ordinal() ), RelationTypeEnum.Superclass );
			lookupOrdinalRelationTypeEnum.put( Integer.valueOf( RelationTypeEnum.Container.ordinal() ), RelationTypeEnum.Container );
			lookupOrdinalRelationTypeEnum.put( Integer.valueOf( RelationTypeEnum.Components.ordinal() ), RelationTypeEnum.Components );
			lookupOrdinalRelationTypeEnum.put( Integer.valueOf( RelationTypeEnum.Owner.ordinal() ), RelationTypeEnum.Owner );
			lookupOrdinalRelationTypeEnum.put( Integer.valueOf( RelationTypeEnum.Parent.ordinal() ), RelationTypeEnum.Parent );
			lookupOrdinalRelationTypeEnum.put( Integer.valueOf( RelationTypeEnum.Children.ordinal() ), RelationTypeEnum.Children );
		}
		RelationTypeEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = lookupOrdinalRelationTypeEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	public enum CodeVisibilityEnum {
		Public,
		Protected,
		Private
	};

	static HashMap<String,CodeVisibilityEnum> lookupCodeVisibilityEnum = new HashMap<String,CodeVisibilityEnum>();

	public static CodeVisibilityEnum parseCodeVisibilityEnum( String value ) {
		CodeVisibilityEnum retval = parseCodeVisibilityEnum( ICFBamSchema.class.getName(), value );
		return( retval );
	}

	public static CodeVisibilityEnum parseCodeVisibilityEnum( String fieldOrClassName, String value ) {
		final String S_ProcName = "parseCodeVisibilityEnum";
		if( lookupCodeVisibilityEnum.isEmpty() ) {
			lookupCodeVisibilityEnum.put( "Public", CodeVisibilityEnum.Public );
			lookupCodeVisibilityEnum.put( "Protected", CodeVisibilityEnum.Protected );
			lookupCodeVisibilityEnum.put( "Private", CodeVisibilityEnum.Private );
		}
		CodeVisibilityEnum retval;
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			retval = null;
		}
		else {
			retval = lookupCodeVisibilityEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	static HashMap<Integer,CodeVisibilityEnum> lookupOrdinalCodeVisibilityEnum = new HashMap<Integer,CodeVisibilityEnum>();

	public static CodeVisibilityEnum ordinalToCodeVisibilityEnum( String fieldOrClassName, Short value ) {
		CodeVisibilityEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToCodeVisibilityEnum( fieldOrClassName, Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static CodeVisibilityEnum ordinalToCodeVisibilityEnum( Short value ) {
		CodeVisibilityEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToCodeVisibilityEnum( Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static CodeVisibilityEnum ordinalToCodeVisibilityEnum( Integer value ) {
		CodeVisibilityEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToCodeVisibilityEnum( ICFBamSchema.class.getName(), Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static CodeVisibilityEnum ordinalToCodeVisibilityEnum( String fieldOrClassName, Integer value ) {
		final String S_ProcName = "ordinalToCodeVisibilityEnum";
		if( lookupOrdinalCodeVisibilityEnum.isEmpty() ) {
			lookupOrdinalCodeVisibilityEnum.put( Integer.valueOf( CodeVisibilityEnum.Public.ordinal() ), CodeVisibilityEnum.Public );
			lookupOrdinalCodeVisibilityEnum.put( Integer.valueOf( CodeVisibilityEnum.Protected.ordinal() ), CodeVisibilityEnum.Protected );
			lookupOrdinalCodeVisibilityEnum.put( Integer.valueOf( CodeVisibilityEnum.Private.ordinal() ), CodeVisibilityEnum.Private );
		}
		CodeVisibilityEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = lookupOrdinalCodeVisibilityEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	public enum SecScopeEnum {
		None,
		System,
		Cluster,
		Tenant,
		SystemGroup,
		ClusterGroup,
		TenantGroup
	};

	static HashMap<String,SecScopeEnum> lookupSecScopeEnum = new HashMap<String,SecScopeEnum>();

	public static SecScopeEnum parseSecScopeEnum( String value ) {
		SecScopeEnum retval = parseSecScopeEnum( ICFBamSchema.class.getName(), value );
		return( retval );
	}

	public static SecScopeEnum parseSecScopeEnum( String fieldOrClassName, String value ) {
		final String S_ProcName = "parseSecScopeEnum";
		if( lookupSecScopeEnum.isEmpty() ) {
			lookupSecScopeEnum.put( "None", SecScopeEnum.None );
			lookupSecScopeEnum.put( "System", SecScopeEnum.System );
			lookupSecScopeEnum.put( "Cluster", SecScopeEnum.Cluster );
			lookupSecScopeEnum.put( "Tenant", SecScopeEnum.Tenant );
			lookupSecScopeEnum.put( "SystemGroup", SecScopeEnum.SystemGroup );
			lookupSecScopeEnum.put( "ClusterGroup", SecScopeEnum.ClusterGroup );
			lookupSecScopeEnum.put( "TenantGroup", SecScopeEnum.TenantGroup );
		}
		SecScopeEnum retval;
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			retval = null;
		}
		else {
			retval = lookupSecScopeEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	static HashMap<Integer,SecScopeEnum> lookupOrdinalSecScopeEnum = new HashMap<Integer,SecScopeEnum>();

	public static SecScopeEnum ordinalToSecScopeEnum( String fieldOrClassName, Short value ) {
		SecScopeEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToSecScopeEnum( fieldOrClassName, Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static SecScopeEnum ordinalToSecScopeEnum( Short value ) {
		SecScopeEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToSecScopeEnum( Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static SecScopeEnum ordinalToSecScopeEnum( Integer value ) {
		SecScopeEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToSecScopeEnum( ICFBamSchema.class.getName(), Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static SecScopeEnum ordinalToSecScopeEnum( String fieldOrClassName, Integer value ) {
		final String S_ProcName = "ordinalToSecScopeEnum";
		if( lookupOrdinalSecScopeEnum.isEmpty() ) {
			lookupOrdinalSecScopeEnum.put( Integer.valueOf( SecScopeEnum.None.ordinal() ), SecScopeEnum.None );
			lookupOrdinalSecScopeEnum.put( Integer.valueOf( SecScopeEnum.System.ordinal() ), SecScopeEnum.System );
			lookupOrdinalSecScopeEnum.put( Integer.valueOf( SecScopeEnum.Cluster.ordinal() ), SecScopeEnum.Cluster );
			lookupOrdinalSecScopeEnum.put( Integer.valueOf( SecScopeEnum.Tenant.ordinal() ), SecScopeEnum.Tenant );
			lookupOrdinalSecScopeEnum.put( Integer.valueOf( SecScopeEnum.SystemGroup.ordinal() ), SecScopeEnum.SystemGroup );
			lookupOrdinalSecScopeEnum.put( Integer.valueOf( SecScopeEnum.ClusterGroup.ordinal() ), SecScopeEnum.ClusterGroup );
			lookupOrdinalSecScopeEnum.put( Integer.valueOf( SecScopeEnum.TenantGroup.ordinal() ), SecScopeEnum.TenantGroup );
		}
		SecScopeEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = lookupOrdinalSecScopeEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	public enum RoleScopeEnum {
		SysRole,
		ClusRole,
		TentRole
	};

	static HashMap<String,RoleScopeEnum> lookupRoleScopeEnum = new HashMap<String,RoleScopeEnum>();

	public static RoleScopeEnum parseRoleScopeEnum( String value ) {
		RoleScopeEnum retval = parseRoleScopeEnum( ICFBamSchema.class.getName(), value );
		return( retval );
	}

	public static RoleScopeEnum parseRoleScopeEnum( String fieldOrClassName, String value ) {
		final String S_ProcName = "parseRoleScopeEnum";
		if( lookupRoleScopeEnum.isEmpty() ) {
			lookupRoleScopeEnum.put( "SysRole", RoleScopeEnum.SysRole );
			lookupRoleScopeEnum.put( "ClusRole", RoleScopeEnum.ClusRole );
			lookupRoleScopeEnum.put( "TentRole", RoleScopeEnum.TentRole );
		}
		RoleScopeEnum retval;
		if( ( value == null ) || ( value.length() <= 0 ) ) {
			retval = null;
		}
		else {
			retval = lookupRoleScopeEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

	static HashMap<Integer,RoleScopeEnum> lookupOrdinalRoleScopeEnum = new HashMap<Integer,RoleScopeEnum>();

	public static RoleScopeEnum ordinalToRoleScopeEnum( String fieldOrClassName, Short value ) {
		RoleScopeEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToRoleScopeEnum( fieldOrClassName, Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static RoleScopeEnum ordinalToRoleScopeEnum( Short value ) {
		RoleScopeEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToRoleScopeEnum( Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static RoleScopeEnum ordinalToRoleScopeEnum( Integer value ) {
		RoleScopeEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = ordinalToRoleScopeEnum( ICFBamSchema.class.getName(), Integer.valueOf( value.shortValue() ) );
		}
		return( retval );
	}

	public static RoleScopeEnum ordinalToRoleScopeEnum( String fieldOrClassName, Integer value ) {
		final String S_ProcName = "ordinalToRoleScopeEnum";
		if( lookupOrdinalRoleScopeEnum.isEmpty() ) {
			lookupOrdinalRoleScopeEnum.put( Integer.valueOf( RoleScopeEnum.SysRole.ordinal() ), RoleScopeEnum.SysRole );
			lookupOrdinalRoleScopeEnum.put( Integer.valueOf( RoleScopeEnum.ClusRole.ordinal() ), RoleScopeEnum.ClusRole );
			lookupOrdinalRoleScopeEnum.put( Integer.valueOf( RoleScopeEnum.TentRole.ordinal() ), RoleScopeEnum.TentRole );
		}
		RoleScopeEnum retval;
		if( value == null ) {
			retval = null;
		}
		else {
			retval = lookupOrdinalRoleScopeEnum.get( value );
			if( retval == null ) {
				throw new CFLibInvalidArgumentException( fieldOrClassName,
					fieldOrClassName,
					S_ProcName,
					2,
					"value",
					"Invalid enum limb argument " + value,
					"Invalid enum limb argument " + value);
			}
		}
		return( retval );
	}

		public static ICFBamSchema getBackingCFBam() {
			return( ICFBamSchema.backingCFBam.get() );
		}
		
		public static void setBackingCFBam(ICFBamSchema backingSchema) {
			ICFBamSchema.backingCFBam.set(backingSchema);
		}
		
		public ICFBamSchema getCFBamSchema();
		public void setCFBamSchema(ICFBamSchema schema);
		
		public static int doInitClassMapEntries(int value) {
			value = ICFSecSchema.doInitClassMapEntries(value);
			value = ICFIntSchema.doInitClassMapEntries(value);
			if (ICFBamSchema.entries.isEmpty()) {
				ICFSecSchema.ClassMapEntry entry;
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Scope", ICFBamScope.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "SchemaDef", ICFBamSchemaDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "SchemaRef", ICFBamSchemaRef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "ServerMethod", ICFBamServerMethod.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "ServerObjFunc", ICFBamServerObjFunc.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "ServerProc", ICFBamServerProc.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Table", ICFBamTable.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Tweak", ICFBamTweak.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TableTweak", ICFBamTableTweak.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "SchemaTweak", ICFBamSchemaTweak.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "IndexTweak", ICFBamIndexTweak.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Value", ICFBamValue.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Atom", ICFBamAtom.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "BlobDef", ICFBamBlobDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "BlobType", ICFBamBlobType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "BoolDef", ICFBamBoolDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "BoolType", ICFBamBoolType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Chain", ICFBamChain.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "ClearDep", ICFBamClearDep.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "ClearSubDep1", ICFBamClearSubDep1.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "ClearSubDep2", ICFBamClearSubDep2.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "ClearSubDep3", ICFBamClearSubDep3.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "ClearTopDep", ICFBamClearTopDep.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DateDef", ICFBamDateDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DateType", ICFBamDateType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DelDep", ICFBamDelDep.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DelSubDep1", ICFBamDelSubDep1.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DelSubDep2", ICFBamDelSubDep2.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DelSubDep3", ICFBamDelSubDep3.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DelTopDep", ICFBamDelTopDep.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DoubleDef", ICFBamDoubleDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DoubleType", ICFBamDoubleType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "EnumTag", ICFBamEnumTag.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "FloatDef", ICFBamFloatDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "FloatType", ICFBamFloatType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Index", ICFBamIndex.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "IndexCol", ICFBamIndexCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Int16Def", ICFBamInt16Def.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Int16Type", ICFBamInt16Type.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Int32Def", ICFBamInt32Def.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Int32Type", ICFBamInt32Type.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Int64Def", ICFBamInt64Def.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Int64Type", ICFBamInt64Type.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "NmTokenDef", ICFBamNmTokenDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "NmTokenType", ICFBamNmTokenType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "NmTokensDef", ICFBamNmTokensDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "NmTokensType", ICFBamNmTokensType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "NumberDef", ICFBamNumberDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "NumberType", ICFBamNumberType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Param", ICFBamParam.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "PopDep", ICFBamPopDep.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "PopSubDep1", ICFBamPopSubDep1.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "PopSubDep2", ICFBamPopSubDep2.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "PopSubDep3", ICFBamPopSubDep3.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "PopTopDep", ICFBamPopTopDep.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Relation", ICFBamRelation.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "RelationCol", ICFBamRelationCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "ServerListFunc", ICFBamServerListFunc.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash128Def", ICFBamDbKeyHash128Def.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash128Col", ICFBamDbKeyHash128Col.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash128Type", ICFBamDbKeyHash128Type.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash128Gen", ICFBamDbKeyHash128Gen.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash160Def", ICFBamDbKeyHash160Def.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash160Col", ICFBamDbKeyHash160Col.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash160Type", ICFBamDbKeyHash160Type.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash160Gen", ICFBamDbKeyHash160Gen.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash224Def", ICFBamDbKeyHash224Def.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash224Col", ICFBamDbKeyHash224Col.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash224Type", ICFBamDbKeyHash224Type.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash224Gen", ICFBamDbKeyHash224Gen.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash256Def", ICFBamDbKeyHash256Def.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash256Col", ICFBamDbKeyHash256Col.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash256Type", ICFBamDbKeyHash256Type.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash256Gen", ICFBamDbKeyHash256Gen.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash384Def", ICFBamDbKeyHash384Def.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash384Col", ICFBamDbKeyHash384Col.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash384Type", ICFBamDbKeyHash384Type.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash384Gen", ICFBamDbKeyHash384Gen.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash512Def", ICFBamDbKeyHash512Def.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash512Col", ICFBamDbKeyHash512Col.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash512Type", ICFBamDbKeyHash512Type.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DbKeyHash512Gen", ICFBamDbKeyHash512Gen.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "StringDef", ICFBamStringDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "StringType", ICFBamStringType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TZDateDef", ICFBamTZDateDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TZDateType", ICFBamTZDateType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TZTimeDef", ICFBamTZTimeDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TZTimeType", ICFBamTZTimeType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TZTimestampDef", ICFBamTZTimestampDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TZTimestampType", ICFBamTZTimestampType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TableCol", ICFBamTableCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TextDef", ICFBamTextDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TextType", ICFBamTextType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TimeDef", ICFBamTimeDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TimeType", ICFBamTimeType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TimestampDef", ICFBamTimestampDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TimestampType", ICFBamTimestampType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TokenDef", ICFBamTokenDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TokenType", ICFBamTokenType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "UInt16Def", ICFBamUInt16Def.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "UInt16Type", ICFBamUInt16Type.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "UInt32Def", ICFBamUInt32Def.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "UInt32Type", ICFBamUInt32Type.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "UInt64Def", ICFBamUInt64Def.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "UInt64Type", ICFBamUInt64Type.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "UuidDef", ICFBamUuidDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Uuid6Def", ICFBamUuid6Def.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "UuidType", ICFBamUuidType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Uuid6Type", ICFBamUuid6Type.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "BlobCol", ICFBamBlobCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "BoolCol", ICFBamBoolCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DateCol", ICFBamDateCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "DoubleCol", ICFBamDoubleCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "EnumDef", ICFBamEnumDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "EnumType", ICFBamEnumType.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "FloatCol", ICFBamFloatCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Id16Gen", ICFBamId16Gen.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Id32Gen", ICFBamId32Gen.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Id64Gen", ICFBamId64Gen.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Int16Col", ICFBamInt16Col.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Int32Col", ICFBamInt32Col.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Int64Col", ICFBamInt64Col.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "NmTokenCol", ICFBamNmTokenCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "NmTokensCol", ICFBamNmTokensCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "NumberCol", ICFBamNumberCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "StringCol", ICFBamStringCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TZDateCol", ICFBamTZDateCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TZTimeCol", ICFBamTZTimeCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TZTimestampCol", ICFBamTZTimestampCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TextCol", ICFBamTextCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TimeCol", ICFBamTimeCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TimestampCol", ICFBamTimestampCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "TokenCol", ICFBamTokenCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "UInt16Col", ICFBamUInt16Col.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "UInt32Col", ICFBamUInt32Col.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "UInt64Col", ICFBamUInt64Col.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "UuidCol", ICFBamUuidCol.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Uuid6Col", ICFBamUuid6Col.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "UuidGen", ICFBamUuidGen.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "Uuid6Gen", ICFBamUuid6Gen.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "RoleDef", ICFBamRoleDef.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				entry = new ICFSecSchema.ClassMapEntry(ICFBamSchema.SCHEMA_NAME, "SchemaRole", ICFBamSchemaRole.CLASS_CODE);
				ICFBamSchema.entries.add(entry);
				for( ICFSecSchema.ClassMapEntry cur: ICFBamSchema.entries) {
					cur.setRuntimeClassCode(value++);
				}
				ICFBamSchema.mapBackingClassCodeToEntry.clear();
				ICFBamSchema.mapRuntimeClassCodeToEntry.clear();
				for( ICFSecSchema.ClassMapEntry cur: ICFBamSchema.entries) {
					ICFBamSchema.mapBackingClassCodeToEntry.put(cur.getBackingClassCode(), cur);
					ICFBamSchema.mapRuntimeClassCodeToEntry.put(cur.getRuntimeClassCode(), cur);
				}
			}
			return(value);
		}
		
		public static ICFSecSchema.ClassMapEntry getClassMapByBackingClassCode(int code) {
			ICFSecSchema.ClassMapEntry entry;
			entry = ICFSecSchema.mapBackingClassCodeToEntry.get(code);
			if (entry != null) {
				return( entry );
			}
			entry = ICFIntSchema.mapBackingClassCodeToEntry.get(code);
			if (entry != null) {
				return( entry );
			}
			entry = ICFBamSchema.mapBackingClassCodeToEntry.get(code);
			if (entry != null) {
				return( entry );
			}
			return( null );
		}
		
		public static ICFSecSchema.ClassMapEntry getClassMapByRuntimeClassCode(int code) {
			ICFSecSchema.ClassMapEntry entry;
			entry = ICFSecSchema.mapRuntimeClassCodeToEntry.get(code);
			if (entry != null) {
				return( entry );
			}
			entry = ICFIntSchema.mapRuntimeClassCodeToEntry.get(code);
			if (entry != null) {
				return( entry );
			}
			entry = ICFBamSchema.mapRuntimeClassCodeToEntry.get(code);
			if (entry != null) {
				return( entry );
			}
			return( null );
		}
		
		public int initClassMapEntries(int value);
		public void wireRecConstructors();
		public void wireTableTableInstances();

	/**
	 *	Allocate a new schema instance.
	 *
	 *	@return	A new ICFSecSchema instance.
	 */
	public ICFSecSchema newSchema();

	/**
	 *	Get the next ISOCcyIdGen identifier.
	 *
	 *	@return	The next ISOCcyIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public short nextISOCcyIdGen();

	/**
	 *	Get the next ISOCtryIdGen identifier.
	 *
	 *	@return	The next ISOCtryIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public short nextISOCtryIdGen();

	/**
	 *	Get the next ISOLangIdGen identifier.
	 *
	 *	@return	The next ISOLangIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public short nextISOLangIdGen();

	/**
	 *	Get the next ISOTZoneIdGen identifier.
	 *
	 *	@return	The next ISOTZoneIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public short nextISOTZoneIdGen();

	/**
	 *	Get the next TableInfoIdGen identifier.
	 *
	 *	@return	The next TableInfoIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public int nextTableInfoIdGen();

	/**
	 *	Get the next MimeTypeIdGen identifier.
	 *
	 *	@return	The next MimeTypeIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public int nextMimeTypeIdGen();

	/**
	 *	Get the next URLProtocolIdGen identifier.
	 *
	 *	@return	The next URLProtocolIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public int nextURLProtocolIdGen();

	/**
	 *	Get the next ClusterIdGen identifier.
	 *
	 *	@return	The next ClusterIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextClusterIdGen();

	/**
	 *	Get the next SecSessionIdGen identifier.
	 *
	 *	@return	The next SecSessionIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecSessionIdGen();

	/**
	 *	Get the next SecUserIdGen identifier.
	 *
	 *	@return	The next SecUserIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecUserIdGen();

	/**
	 *	Get the next TenantIdGen identifier.
	 *
	 *	@return	The next TenantIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextTenantIdGen();

	/**
	 *	Get the next SecSysGrpIdGen identifier.
	 *
	 *	@return	The next SecSysGrpIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecSysGrpIdGen();

	/**
	 *	Get the next SecClusGrpIdGen identifier.
	 *
	 *	@return	The next SecClusGrpIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecClusGrpIdGen();

	/**
	 *	Get the next SecClusRoleIdGen identifier.
	 *
	 *	@return	The next SecClusRoleIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecClusRoleIdGen();

	/**
	 *	Get the next SecTentGrpIdGen identifier.
	 *
	 *	@return	The next SecTentGrpIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecTentGrpIdGen();

	/**
	 *	Get the next SecTentRoleIdGen identifier.
	 *
	 *	@return	The next SecTentRoleIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSecTentRoleIdGen();

	/**
	 *	Get the next MajorVersionIdGen identifier.
	 *
	 *	@return	The next MajorVersionIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextMajorVersionIdGen();

	/**
	 *	Get the next MinorVersionIdGen identifier.
	 *
	 *	@return	The next MinorVersionIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextMinorVersionIdGen();

	/**
	 *	Get the next SubProjectIdGen identifier.
	 *
	 *	@return	The next SubProjectIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextSubProjectIdGen();

	/**
	 *	Get the next TldIdGen identifier.
	 *
	 *	@return	The next TldIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextTldIdGen();

	/**
	 *	Get the next TopDomainIdGen identifier.
	 *
	 *	@return	The next TopDomainIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextTopDomainIdGen();

	/**
	 *	Get the next TopProjectIdGen identifier.
	 *
	 *	@return	The next TopProjectIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextTopProjectIdGen();

	/**
	 *	Get the next LicenseIdGen identifier.
	 *
	 *	@return	The next LicenseIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextLicenseIdGen();

	/**
	 *	Get the next ChainIdGen identifier.
	 *
	 *	@return	The next ChainIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextChainIdGen();

	/**
	 *	Get the next EnumTagIdGen identifier.
	 *
	 *	@return	The next EnumTagIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextEnumTagIdGen();

	/**
	 *	Get the next IndexColIdGen identifier.
	 *
	 *	@return	The next IndexColIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextIndexColIdGen();

	/**
	 *	Get the next ParamIdGen identifier.
	 *
	 *	@return	The next ParamIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextParamIdGen();

	/**
	 *	Get the next RelationColIdGen identifier.
	 *
	 *	@return	The next RelationColIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextRelationColIdGen();

	/**
	 *	Get the next TweakIdGen identifier.
	 *
	 *	@return	The next TweakIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextTweakIdGen();

	/**
	 *	Get the next ScopeIdGen identifier.
	 *
	 *	@return	The next ScopeIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextScopeIdGen();

	/**
	 *	Get the next ValueIdGen identifier.
	 *
	 *	@return	The next ValueIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextValueIdGen();

	/**
	 *	Get the next RoleIdGen identifier.
	 *
	 *	@return	The next RoleIdGen identifier.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFLibDbKeyHash256 nextRoleIdGen();

	/**
	 *	Get the factory for CFSec data objects.
	 */
	public ICFSecFactory getCFSecFactory();

	/**
	 *	Get the factory for CFInt data objects.
	 */
	public ICFIntFactory getCFIntFactory();

	/**
	 *	Get the factory for CFBam data objects.
	 */
	public ICFBamFactory getCFBamFactory();

	/**
	 *	Get the Atom Table interface for the schema.
	 *
	 *	@return	The Atom Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamAtomTable getTableAtom();

	/**
	 *	Get the BlobCol Table interface for the schema.
	 *
	 *	@return	The BlobCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBlobColTable getTableBlobCol();

	/**
	 *	Get the BlobDef Table interface for the schema.
	 *
	 *	@return	The BlobDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBlobDefTable getTableBlobDef();

	/**
	 *	Get the BlobType Table interface for the schema.
	 *
	 *	@return	The BlobType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBlobTypeTable getTableBlobType();

	/**
	 *	Get the BoolCol Table interface for the schema.
	 *
	 *	@return	The BoolCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBoolColTable getTableBoolCol();

	/**
	 *	Get the BoolDef Table interface for the schema.
	 *
	 *	@return	The BoolDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBoolDefTable getTableBoolDef();

	/**
	 *	Get the BoolType Table interface for the schema.
	 *
	 *	@return	The BoolType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBoolTypeTable getTableBoolType();

	/**
	 *	Get the Chain Table interface for the schema.
	 *
	 *	@return	The Chain Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamChainTable getTableChain();

	/**
	 *	Get the ClearDep Table interface for the schema.
	 *
	 *	@return	The ClearDep Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearDepTable getTableClearDep();

	/**
	 *	Get the ClearSubDep1 Table interface for the schema.
	 *
	 *	@return	The ClearSubDep1 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearSubDep1Table getTableClearSubDep1();

	/**
	 *	Get the ClearSubDep2 Table interface for the schema.
	 *
	 *	@return	The ClearSubDep2 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearSubDep2Table getTableClearSubDep2();

	/**
	 *	Get the ClearSubDep3 Table interface for the schema.
	 *
	 *	@return	The ClearSubDep3 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearSubDep3Table getTableClearSubDep3();

	/**
	 *	Get the ClearTopDep Table interface for the schema.
	 *
	 *	@return	The ClearTopDep Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearTopDepTable getTableClearTopDep();

	/**
	 *	Get the Cluster Table interface for the schema.
	 *
	 *	@return	The Cluster Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecClusterTable getTableCluster();

	/**
	 *	Get the DateCol Table interface for the schema.
	 *
	 *	@return	The DateCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDateColTable getTableDateCol();

	/**
	 *	Get the DateDef Table interface for the schema.
	 *
	 *	@return	The DateDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDateDefTable getTableDateDef();

	/**
	 *	Get the DateType Table interface for the schema.
	 *
	 *	@return	The DateType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDateTypeTable getTableDateType();

	/**
	 *	Get the DbKeyHash128Col Table interface for the schema.
	 *
	 *	@return	The DbKeyHash128Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash128ColTable getTableDbKeyHash128Col();

	/**
	 *	Get the DbKeyHash128Def Table interface for the schema.
	 *
	 *	@return	The DbKeyHash128Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash128DefTable getTableDbKeyHash128Def();

	/**
	 *	Get the DbKeyHash128Gen Table interface for the schema.
	 *
	 *	@return	The DbKeyHash128Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash128GenTable getTableDbKeyHash128Gen();

	/**
	 *	Get the DbKeyHash128Type Table interface for the schema.
	 *
	 *	@return	The DbKeyHash128Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash128TypeTable getTableDbKeyHash128Type();

	/**
	 *	Get the DbKeyHash160Col Table interface for the schema.
	 *
	 *	@return	The DbKeyHash160Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash160ColTable getTableDbKeyHash160Col();

	/**
	 *	Get the DbKeyHash160Def Table interface for the schema.
	 *
	 *	@return	The DbKeyHash160Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash160DefTable getTableDbKeyHash160Def();

	/**
	 *	Get the DbKeyHash160Gen Table interface for the schema.
	 *
	 *	@return	The DbKeyHash160Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash160GenTable getTableDbKeyHash160Gen();

	/**
	 *	Get the DbKeyHash160Type Table interface for the schema.
	 *
	 *	@return	The DbKeyHash160Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash160TypeTable getTableDbKeyHash160Type();

	/**
	 *	Get the DbKeyHash224Col Table interface for the schema.
	 *
	 *	@return	The DbKeyHash224Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash224ColTable getTableDbKeyHash224Col();

	/**
	 *	Get the DbKeyHash224Def Table interface for the schema.
	 *
	 *	@return	The DbKeyHash224Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash224DefTable getTableDbKeyHash224Def();

	/**
	 *	Get the DbKeyHash224Gen Table interface for the schema.
	 *
	 *	@return	The DbKeyHash224Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash224GenTable getTableDbKeyHash224Gen();

	/**
	 *	Get the DbKeyHash224Type Table interface for the schema.
	 *
	 *	@return	The DbKeyHash224Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash224TypeTable getTableDbKeyHash224Type();

	/**
	 *	Get the DbKeyHash256Col Table interface for the schema.
	 *
	 *	@return	The DbKeyHash256Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash256ColTable getTableDbKeyHash256Col();

	/**
	 *	Get the DbKeyHash256Def Table interface for the schema.
	 *
	 *	@return	The DbKeyHash256Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash256DefTable getTableDbKeyHash256Def();

	/**
	 *	Get the DbKeyHash256Gen Table interface for the schema.
	 *
	 *	@return	The DbKeyHash256Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash256GenTable getTableDbKeyHash256Gen();

	/**
	 *	Get the DbKeyHash256Type Table interface for the schema.
	 *
	 *	@return	The DbKeyHash256Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash256TypeTable getTableDbKeyHash256Type();

	/**
	 *	Get the DbKeyHash384Col Table interface for the schema.
	 *
	 *	@return	The DbKeyHash384Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash384ColTable getTableDbKeyHash384Col();

	/**
	 *	Get the DbKeyHash384Def Table interface for the schema.
	 *
	 *	@return	The DbKeyHash384Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash384DefTable getTableDbKeyHash384Def();

	/**
	 *	Get the DbKeyHash384Gen Table interface for the schema.
	 *
	 *	@return	The DbKeyHash384Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash384GenTable getTableDbKeyHash384Gen();

	/**
	 *	Get the DbKeyHash384Type Table interface for the schema.
	 *
	 *	@return	The DbKeyHash384Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash384TypeTable getTableDbKeyHash384Type();

	/**
	 *	Get the DbKeyHash512Col Table interface for the schema.
	 *
	 *	@return	The DbKeyHash512Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash512ColTable getTableDbKeyHash512Col();

	/**
	 *	Get the DbKeyHash512Def Table interface for the schema.
	 *
	 *	@return	The DbKeyHash512Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash512DefTable getTableDbKeyHash512Def();

	/**
	 *	Get the DbKeyHash512Gen Table interface for the schema.
	 *
	 *	@return	The DbKeyHash512Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash512GenTable getTableDbKeyHash512Gen();

	/**
	 *	Get the DbKeyHash512Type Table interface for the schema.
	 *
	 *	@return	The DbKeyHash512Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash512TypeTable getTableDbKeyHash512Type();

	/**
	 *	Get the DelDep Table interface for the schema.
	 *
	 *	@return	The DelDep Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelDepTable getTableDelDep();

	/**
	 *	Get the DelSubDep1 Table interface for the schema.
	 *
	 *	@return	The DelSubDep1 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelSubDep1Table getTableDelSubDep1();

	/**
	 *	Get the DelSubDep2 Table interface for the schema.
	 *
	 *	@return	The DelSubDep2 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelSubDep2Table getTableDelSubDep2();

	/**
	 *	Get the DelSubDep3 Table interface for the schema.
	 *
	 *	@return	The DelSubDep3 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelSubDep3Table getTableDelSubDep3();

	/**
	 *	Get the DelTopDep Table interface for the schema.
	 *
	 *	@return	The DelTopDep Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelTopDepTable getTableDelTopDep();

	/**
	 *	Get the DoubleCol Table interface for the schema.
	 *
	 *	@return	The DoubleCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDoubleColTable getTableDoubleCol();

	/**
	 *	Get the DoubleDef Table interface for the schema.
	 *
	 *	@return	The DoubleDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDoubleDefTable getTableDoubleDef();

	/**
	 *	Get the DoubleType Table interface for the schema.
	 *
	 *	@return	The DoubleType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDoubleTypeTable getTableDoubleType();

	/**
	 *	Get the EnumDef Table interface for the schema.
	 *
	 *	@return	The EnumDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamEnumDefTable getTableEnumDef();

	/**
	 *	Get the EnumTag Table interface for the schema.
	 *
	 *	@return	The EnumTag Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamEnumTagTable getTableEnumTag();

	/**
	 *	Get the EnumType Table interface for the schema.
	 *
	 *	@return	The EnumType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamEnumTypeTable getTableEnumType();

	/**
	 *	Get the FloatCol Table interface for the schema.
	 *
	 *	@return	The FloatCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamFloatColTable getTableFloatCol();

	/**
	 *	Get the FloatDef Table interface for the schema.
	 *
	 *	@return	The FloatDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamFloatDefTable getTableFloatDef();

	/**
	 *	Get the FloatType Table interface for the schema.
	 *
	 *	@return	The FloatType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamFloatTypeTable getTableFloatType();

	/**
	 *	Get the ISOCcy Table interface for the schema.
	 *
	 *	@return	The ISOCcy Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCcyTable getTableISOCcy();

	/**
	 *	Get the ISOCtry Table interface for the schema.
	 *
	 *	@return	The ISOCtry Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCtryTable getTableISOCtry();

	/**
	 *	Get the ISOCtryCcy Table interface for the schema.
	 *
	 *	@return	The ISOCtryCcy Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCtryCcyTable getTableISOCtryCcy();

	/**
	 *	Get the ISOCtryLang Table interface for the schema.
	 *
	 *	@return	The ISOCtryLang Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCtryLangTable getTableISOCtryLang();

	/**
	 *	Get the ISOLang Table interface for the schema.
	 *
	 *	@return	The ISOLang Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOLangTable getTableISOLang();

	/**
	 *	Get the ISOTZone Table interface for the schema.
	 *
	 *	@return	The ISOTZone Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOTZoneTable getTableISOTZone();

	/**
	 *	Get the Id16Gen Table interface for the schema.
	 *
	 *	@return	The Id16Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamId16GenTable getTableId16Gen();

	/**
	 *	Get the Id32Gen Table interface for the schema.
	 *
	 *	@return	The Id32Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamId32GenTable getTableId32Gen();

	/**
	 *	Get the Id64Gen Table interface for the schema.
	 *
	 *	@return	The Id64Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamId64GenTable getTableId64Gen();

	/**
	 *	Get the Index Table interface for the schema.
	 *
	 *	@return	The Index Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamIndexTable getTableIndex();

	/**
	 *	Get the IndexCol Table interface for the schema.
	 *
	 *	@return	The IndexCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamIndexColTable getTableIndexCol();

	/**
	 *	Get the IndexTweak Table interface for the schema.
	 *
	 *	@return	The IndexTweak Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamIndexTweakTable getTableIndexTweak();

	/**
	 *	Get the Int16Col Table interface for the schema.
	 *
	 *	@return	The Int16Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt16ColTable getTableInt16Col();

	/**
	 *	Get the Int16Def Table interface for the schema.
	 *
	 *	@return	The Int16Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt16DefTable getTableInt16Def();

	/**
	 *	Get the Int16Type Table interface for the schema.
	 *
	 *	@return	The Int16Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt16TypeTable getTableInt16Type();

	/**
	 *	Get the Int32Col Table interface for the schema.
	 *
	 *	@return	The Int32Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt32ColTable getTableInt32Col();

	/**
	 *	Get the Int32Def Table interface for the schema.
	 *
	 *	@return	The Int32Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt32DefTable getTableInt32Def();

	/**
	 *	Get the Int32Type Table interface for the schema.
	 *
	 *	@return	The Int32Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt32TypeTable getTableInt32Type();

	/**
	 *	Get the Int64Col Table interface for the schema.
	 *
	 *	@return	The Int64Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt64ColTable getTableInt64Col();

	/**
	 *	Get the Int64Def Table interface for the schema.
	 *
	 *	@return	The Int64Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt64DefTable getTableInt64Def();

	/**
	 *	Get the Int64Type Table interface for the schema.
	 *
	 *	@return	The Int64Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt64TypeTable getTableInt64Type();

	/**
	 *	Get the License Table interface for the schema.
	 *
	 *	@return	The License Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntLicenseTable getTableLicense();

	/**
	 *	Get the MajorVersion Table interface for the schema.
	 *
	 *	@return	The MajorVersion Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntMajorVersionTable getTableMajorVersion();

	/**
	 *	Get the MimeType Table interface for the schema.
	 *
	 *	@return	The MimeType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntMimeTypeTable getTableMimeType();

	/**
	 *	Get the MinorVersion Table interface for the schema.
	 *
	 *	@return	The MinorVersion Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntMinorVersionTable getTableMinorVersion();

	/**
	 *	Get the NmTokenCol Table interface for the schema.
	 *
	 *	@return	The NmTokenCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokenColTable getTableNmTokenCol();

	/**
	 *	Get the NmTokenDef Table interface for the schema.
	 *
	 *	@return	The NmTokenDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokenDefTable getTableNmTokenDef();

	/**
	 *	Get the NmTokenType Table interface for the schema.
	 *
	 *	@return	The NmTokenType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokenTypeTable getTableNmTokenType();

	/**
	 *	Get the NmTokensCol Table interface for the schema.
	 *
	 *	@return	The NmTokensCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokensColTable getTableNmTokensCol();

	/**
	 *	Get the NmTokensDef Table interface for the schema.
	 *
	 *	@return	The NmTokensDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokensDefTable getTableNmTokensDef();

	/**
	 *	Get the NmTokensType Table interface for the schema.
	 *
	 *	@return	The NmTokensType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokensTypeTable getTableNmTokensType();

	/**
	 *	Get the NumberCol Table interface for the schema.
	 *
	 *	@return	The NumberCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNumberColTable getTableNumberCol();

	/**
	 *	Get the NumberDef Table interface for the schema.
	 *
	 *	@return	The NumberDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNumberDefTable getTableNumberDef();

	/**
	 *	Get the NumberType Table interface for the schema.
	 *
	 *	@return	The NumberType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNumberTypeTable getTableNumberType();

	/**
	 *	Get the Param Table interface for the schema.
	 *
	 *	@return	The Param Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamParamTable getTableParam();

	/**
	 *	Get the PopDep Table interface for the schema.
	 *
	 *	@return	The PopDep Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopDepTable getTablePopDep();

	/**
	 *	Get the PopSubDep1 Table interface for the schema.
	 *
	 *	@return	The PopSubDep1 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopSubDep1Table getTablePopSubDep1();

	/**
	 *	Get the PopSubDep2 Table interface for the schema.
	 *
	 *	@return	The PopSubDep2 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopSubDep2Table getTablePopSubDep2();

	/**
	 *	Get the PopSubDep3 Table interface for the schema.
	 *
	 *	@return	The PopSubDep3 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopSubDep3Table getTablePopSubDep3();

	/**
	 *	Get the PopTopDep Table interface for the schema.
	 *
	 *	@return	The PopTopDep Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopTopDepTable getTablePopTopDep();

	/**
	 *	Get the Relation Table interface for the schema.
	 *
	 *	@return	The Relation Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamRelationTable getTableRelation();

	/**
	 *	Get the RelationCol Table interface for the schema.
	 *
	 *	@return	The RelationCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamRelationColTable getTableRelationCol();

	/**
	 *	Get the RoleDef Table interface for the schema.
	 *
	 *	@return	The RoleDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamRoleDefTable getTableRoleDef();

	/**
	 *	Get the SchemaDef Table interface for the schema.
	 *
	 *	@return	The SchemaDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamSchemaDefTable getTableSchemaDef();

	/**
	 *	Get the SchemaRef Table interface for the schema.
	 *
	 *	@return	The SchemaRef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamSchemaRefTable getTableSchemaRef();

	/**
	 *	Get the SchemaRole Table interface for the schema.
	 *
	 *	@return	The SchemaRole Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamSchemaRoleTable getTableSchemaRole();

	/**
	 *	Get the SchemaTweak Table interface for the schema.
	 *
	 *	@return	The SchemaTweak Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamSchemaTweakTable getTableSchemaTweak();

	/**
	 *	Get the Scope Table interface for the schema.
	 *
	 *	@return	The Scope Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamScopeTable getTableScope();

	/**
	 *	Get the SecClusGrp Table interface for the schema.
	 *
	 *	@return	The SecClusGrp Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusGrpTable getTableSecClusGrp();

	/**
	 *	Get the SecClusGrpMemb Table interface for the schema.
	 *
	 *	@return	The SecClusGrpMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusGrpMembTable getTableSecClusGrpMemb();

	/**
	 *	Get the SecClusRole Table interface for the schema.
	 *
	 *	@return	The SecClusRole Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusRoleTable getTableSecClusRole();

	/**
	 *	Get the SecClusRoleMemb Table interface for the schema.
	 *
	 *	@return	The SecClusRoleMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusRoleMembTable getTableSecClusRoleMemb();

	/**
	 *	Get the SecSession Table interface for the schema.
	 *
	 *	@return	The SecSession Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSessionTable getTableSecSession();

	/**
	 *	Get the SecSysGrp Table interface for the schema.
	 *
	 *	@return	The SecSysGrp Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysGrpTable getTableSecSysGrp();

	/**
	 *	Get the SecSysGrpInc Table interface for the schema.
	 *
	 *	@return	The SecSysGrpInc Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysGrpIncTable getTableSecSysGrpInc();

	/**
	 *	Get the SecSysGrpMemb Table interface for the schema.
	 *
	 *	@return	The SecSysGrpMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysGrpMembTable getTableSecSysGrpMemb();

	/**
	 *	Get the SecSysRole Table interface for the schema.
	 *
	 *	@return	The SecSysRole Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysRoleTable getTableSecSysRole();

	/**
	 *	Get the SecSysRoleEnables Table interface for the schema.
	 *
	 *	@return	The SecSysRoleEnables Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysRoleEnablesTable getTableSecSysRoleEnables();

	/**
	 *	Get the SecSysRoleMemb Table interface for the schema.
	 *
	 *	@return	The SecSysRoleMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysRoleMembTable getTableSecSysRoleMemb();

	/**
	 *	Get the SecTentGrp Table interface for the schema.
	 *
	 *	@return	The SecTentGrp Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentGrpTable getTableSecTentGrp();

	/**
	 *	Get the SecTentGrpMemb Table interface for the schema.
	 *
	 *	@return	The SecTentGrpMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentGrpMembTable getTableSecTentGrpMemb();

	/**
	 *	Get the SecTentRole Table interface for the schema.
	 *
	 *	@return	The SecTentRole Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentRoleTable getTableSecTentRole();

	/**
	 *	Get the SecTentRoleMemb Table interface for the schema.
	 *
	 *	@return	The SecTentRoleMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentRoleMembTable getTableSecTentRoleMemb();

	/**
	 *	Get the SecUser Table interface for the schema.
	 *
	 *	@return	The SecUser Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserTable getTableSecUser();

	/**
	 *	Get the SecUserEMConf Table interface for the schema.
	 *
	 *	@return	The SecUserEMConf Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserEMConfTable getTableSecUserEMConf();

	/**
	 *	Get the SecUserPWHistory Table interface for the schema.
	 *
	 *	@return	The SecUserPWHistory Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserPWHistoryTable getTableSecUserPWHistory();

	/**
	 *	Get the SecUserPWReset Table interface for the schema.
	 *
	 *	@return	The SecUserPWReset Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserPWResetTable getTableSecUserPWReset();

	/**
	 *	Get the SecUserPassword Table interface for the schema.
	 *
	 *	@return	The SecUserPassword Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserPasswordTable getTableSecUserPassword();

	/**
	 *	Get the ServerListFunc Table interface for the schema.
	 *
	 *	@return	The ServerListFunc Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamServerListFuncTable getTableServerListFunc();

	/**
	 *	Get the ServerMethod Table interface for the schema.
	 *
	 *	@return	The ServerMethod Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamServerMethodTable getTableServerMethod();

	/**
	 *	Get the ServerObjFunc Table interface for the schema.
	 *
	 *	@return	The ServerObjFunc Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamServerObjFuncTable getTableServerObjFunc();

	/**
	 *	Get the ServerProc Table interface for the schema.
	 *
	 *	@return	The ServerProc Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamServerProcTable getTableServerProc();

	/**
	 *	Get the StringCol Table interface for the schema.
	 *
	 *	@return	The StringCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamStringColTable getTableStringCol();

	/**
	 *	Get the StringDef Table interface for the schema.
	 *
	 *	@return	The StringDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamStringDefTable getTableStringDef();

	/**
	 *	Get the StringType Table interface for the schema.
	 *
	 *	@return	The StringType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamStringTypeTable getTableStringType();

	/**
	 *	Get the SubProject Table interface for the schema.
	 *
	 *	@return	The SubProject Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntSubProjectTable getTableSubProject();

	/**
	 *	Get the SysCluster Table interface for the schema.
	 *
	 *	@return	The SysCluster Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSysClusterTable getTableSysCluster();

	/**
	 *	Get the TZDateCol Table interface for the schema.
	 *
	 *	@return	The TZDateCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZDateColTable getTableTZDateCol();

	/**
	 *	Get the TZDateDef Table interface for the schema.
	 *
	 *	@return	The TZDateDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZDateDefTable getTableTZDateDef();

	/**
	 *	Get the TZDateType Table interface for the schema.
	 *
	 *	@return	The TZDateType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZDateTypeTable getTableTZDateType();

	/**
	 *	Get the TZTimeCol Table interface for the schema.
	 *
	 *	@return	The TZTimeCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimeColTable getTableTZTimeCol();

	/**
	 *	Get the TZTimeDef Table interface for the schema.
	 *
	 *	@return	The TZTimeDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimeDefTable getTableTZTimeDef();

	/**
	 *	Get the TZTimeType Table interface for the schema.
	 *
	 *	@return	The TZTimeType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimeTypeTable getTableTZTimeType();

	/**
	 *	Get the TZTimestampCol Table interface for the schema.
	 *
	 *	@return	The TZTimestampCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimestampColTable getTableTZTimestampCol();

	/**
	 *	Get the TZTimestampDef Table interface for the schema.
	 *
	 *	@return	The TZTimestampDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimestampDefTable getTableTZTimestampDef();

	/**
	 *	Get the TZTimestampType Table interface for the schema.
	 *
	 *	@return	The TZTimestampType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimestampTypeTable getTableTZTimestampType();

	/**
	 *	Get the Table Table interface for the schema.
	 *
	 *	@return	The Table Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTableTable getTableTable();

	/**
	 *	Get the TableCol Table interface for the schema.
	 *
	 *	@return	The TableCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTableColTable getTableTableCol();

	/**
	 *	Get the TableInfo Table interface for the schema.
	 *
	 *	@return	The TableInfo Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecTableInfoTable getTableTableInfo();

	/**
	 *	Get the TableTweak Table interface for the schema.
	 *
	 *	@return	The TableTweak Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTableTweakTable getTableTableTweak();

	/**
	 *	Get the Tenant Table interface for the schema.
	 *
	 *	@return	The Tenant Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecTenantTable getTableTenant();

	/**
	 *	Get the TextCol Table interface for the schema.
	 *
	 *	@return	The TextCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTextColTable getTableTextCol();

	/**
	 *	Get the TextDef Table interface for the schema.
	 *
	 *	@return	The TextDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTextDefTable getTableTextDef();

	/**
	 *	Get the TextType Table interface for the schema.
	 *
	 *	@return	The TextType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTextTypeTable getTableTextType();

	/**
	 *	Get the TimeCol Table interface for the schema.
	 *
	 *	@return	The TimeCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimeColTable getTableTimeCol();

	/**
	 *	Get the TimeDef Table interface for the schema.
	 *
	 *	@return	The TimeDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimeDefTable getTableTimeDef();

	/**
	 *	Get the TimeType Table interface for the schema.
	 *
	 *	@return	The TimeType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimeTypeTable getTableTimeType();

	/**
	 *	Get the TimestampCol Table interface for the schema.
	 *
	 *	@return	The TimestampCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimestampColTable getTableTimestampCol();

	/**
	 *	Get the TimestampDef Table interface for the schema.
	 *
	 *	@return	The TimestampDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimestampDefTable getTableTimestampDef();

	/**
	 *	Get the TimestampType Table interface for the schema.
	 *
	 *	@return	The TimestampType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimestampTypeTable getTableTimestampType();

	/**
	 *	Get the Tld Table interface for the schema.
	 *
	 *	@return	The Tld Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntTldTable getTableTld();

	/**
	 *	Get the TokenCol Table interface for the schema.
	 *
	 *	@return	The TokenCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTokenColTable getTableTokenCol();

	/**
	 *	Get the TokenDef Table interface for the schema.
	 *
	 *	@return	The TokenDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTokenDefTable getTableTokenDef();

	/**
	 *	Get the TokenType Table interface for the schema.
	 *
	 *	@return	The TokenType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTokenTypeTable getTableTokenType();

	/**
	 *	Get the TopDomain Table interface for the schema.
	 *
	 *	@return	The TopDomain Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntTopDomainTable getTableTopDomain();

	/**
	 *	Get the TopProject Table interface for the schema.
	 *
	 *	@return	The TopProject Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntTopProjectTable getTableTopProject();

	/**
	 *	Get the Tweak Table interface for the schema.
	 *
	 *	@return	The Tweak Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTweakTable getTableTweak();

	/**
	 *	Get the UInt16Col Table interface for the schema.
	 *
	 *	@return	The UInt16Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt16ColTable getTableUInt16Col();

	/**
	 *	Get the UInt16Def Table interface for the schema.
	 *
	 *	@return	The UInt16Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt16DefTable getTableUInt16Def();

	/**
	 *	Get the UInt16Type Table interface for the schema.
	 *
	 *	@return	The UInt16Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt16TypeTable getTableUInt16Type();

	/**
	 *	Get the UInt32Col Table interface for the schema.
	 *
	 *	@return	The UInt32Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt32ColTable getTableUInt32Col();

	/**
	 *	Get the UInt32Def Table interface for the schema.
	 *
	 *	@return	The UInt32Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt32DefTable getTableUInt32Def();

	/**
	 *	Get the UInt32Type Table interface for the schema.
	 *
	 *	@return	The UInt32Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt32TypeTable getTableUInt32Type();

	/**
	 *	Get the UInt64Col Table interface for the schema.
	 *
	 *	@return	The UInt64Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt64ColTable getTableUInt64Col();

	/**
	 *	Get the UInt64Def Table interface for the schema.
	 *
	 *	@return	The UInt64Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt64DefTable getTableUInt64Def();

	/**
	 *	Get the UInt64Type Table interface for the schema.
	 *
	 *	@return	The UInt64Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt64TypeTable getTableUInt64Type();

	/**
	 *	Get the URLProtocol Table interface for the schema.
	 *
	 *	@return	The URLProtocol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntURLProtocolTable getTableURLProtocol();

	/**
	 *	Get the Uuid6Col Table interface for the schema.
	 *
	 *	@return	The Uuid6Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuid6ColTable getTableUuid6Col();

	/**
	 *	Get the Uuid6Def Table interface for the schema.
	 *
	 *	@return	The Uuid6Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuid6DefTable getTableUuid6Def();

	/**
	 *	Get the Uuid6Gen Table interface for the schema.
	 *
	 *	@return	The Uuid6Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuid6GenTable getTableUuid6Gen();

	/**
	 *	Get the Uuid6Type Table interface for the schema.
	 *
	 *	@return	The Uuid6Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuid6TypeTable getTableUuid6Type();

	/**
	 *	Get the UuidCol Table interface for the schema.
	 *
	 *	@return	The UuidCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuidColTable getTableUuidCol();

	/**
	 *	Get the UuidDef Table interface for the schema.
	 *
	 *	@return	The UuidDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuidDefTable getTableUuidDef();

	/**
	 *	Get the UuidGen Table interface for the schema.
	 *
	 *	@return	The UuidGen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuidGenTable getTableUuidGen();

	/**
	 *	Get the UuidType Table interface for the schema.
	 *
	 *	@return	The UuidType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuidTypeTable getTableUuidType();

	/**
	 *	Get the Value Table interface for the schema.
	 *
	 *	@return	The Value Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamValueTable getTableValue();

	/**
	 *	Get the Table Permissions interface for the schema.
	 *
	 *	@return	The Table Permissions interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	//public static ICFSecTablePerms getTablePerms();

	/**
	 *	Get the Table Permissions interface cast to this schema's implementation.
	 *
	 *	@return The Table Permissions interface for this schema.
	 */
	//public static ICFSecTablePerms getCFSecTablePerms();

	/**
	 *	Set the Table Permissions interface for the schema.  All fractal subclasses of
	 *	the ICFSecTablePerms implement at least that interface plus their own
	 *	accessors.
	 *
	 *	@param	value	The Table Permissions interface to be used by the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	//public static void setTablePerms( ICFSecTablePerms value );

	public void bootstrapSchema(CFSecTableData tableData[]);
	public void bootstrapAllTablesSecurity(CFLibDbKeyHash256 clusterId, CFLibDbKeyHash256 tenantId, CFSecTableData tableData[]);
}
