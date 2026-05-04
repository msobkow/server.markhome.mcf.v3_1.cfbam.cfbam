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
	public static final CFSecTableInfo TABLE_INFO[] = {new CFSecTableInfo("Atom", true, false, "Tenant"),
		new CFSecTableInfo("BlobCol", true, false, "Tenant"),
		new CFSecTableInfo("BlobDef", true, false, "Tenant"),
		new CFSecTableInfo("BlobType", true, false, "Tenant"),
		new CFSecTableInfo("BoolCol", true, false, "Tenant"),
		new CFSecTableInfo("BoolDef", true, false, "Tenant"),
		new CFSecTableInfo("BoolType", true, false, "Tenant"),
		new CFSecTableInfo("Chain", true, false, "Tenant"),
		new CFSecTableInfo("ClearDep", true, false, "Tenant"),
		new CFSecTableInfo("ClearSubDep1", true, false, "Tenant"),
		new CFSecTableInfo("ClearSubDep2", true, false, "Tenant"),
		new CFSecTableInfo("ClearSubDep3", true, false, "Tenant"),
		new CFSecTableInfo("ClearTopDep", true, false, "Tenant"),
		new CFSecTableInfo("DateCol", true, false, "Tenant"),
		new CFSecTableInfo("DateDef", true, false, "Tenant"),
		new CFSecTableInfo("DateType", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash128Col", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash128Def", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash128Gen", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash128Type", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash160Col", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash160Def", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash160Gen", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash160Type", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash224Col", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash224Def", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash224Gen", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash224Type", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash256Col", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash256Def", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash256Gen", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash256Type", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash384Col", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash384Def", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash384Gen", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash384Type", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash512Col", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash512Def", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash512Gen", true, false, "Tenant"),
		new CFSecTableInfo("DbKeyHash512Type", true, false, "Tenant"),
		new CFSecTableInfo("DelDep", true, false, "Tenant"),
		new CFSecTableInfo("DelSubDep1", true, false, "Tenant"),
		new CFSecTableInfo("DelSubDep2", true, false, "Tenant"),
		new CFSecTableInfo("DelSubDep3", true, false, "Tenant"),
		new CFSecTableInfo("DelTopDep", true, false, "Tenant"),
		new CFSecTableInfo("DoubleCol", true, false, "Tenant"),
		new CFSecTableInfo("DoubleDef", true, false, "Tenant"),
		new CFSecTableInfo("DoubleType", true, false, "Tenant"),
		new CFSecTableInfo("EnumDef", true, false, "Tenant"),
		new CFSecTableInfo("EnumTag", true, false, "Tenant"),
		new CFSecTableInfo("EnumType", true, false, "Tenant"),
		new CFSecTableInfo("FloatCol", true, false, "Tenant"),
		new CFSecTableInfo("FloatDef", true, false, "Tenant"),
		new CFSecTableInfo("FloatType", true, false, "Tenant"),
		new CFSecTableInfo("Id16Gen", true, false, "Tenant"),
		new CFSecTableInfo("Id32Gen", true, false, "Tenant"),
		new CFSecTableInfo("Id64Gen", true, false, "Tenant"),
		new CFSecTableInfo("Index", true, false, "Tenant"),
		new CFSecTableInfo("IndexCol", true, false, "Tenant"),
		new CFSecTableInfo("IndexTweak", true, false, "Tenant"),
		new CFSecTableInfo("Int16Col", true, false, "Tenant"),
		new CFSecTableInfo("Int16Def", true, false, "Tenant"),
		new CFSecTableInfo("Int16Type", true, false, "Tenant"),
		new CFSecTableInfo("Int32Col", true, false, "Tenant"),
		new CFSecTableInfo("Int32Def", true, false, "Tenant"),
		new CFSecTableInfo("Int32Type", true, false, "Tenant"),
		new CFSecTableInfo("Int64Col", true, false, "Tenant"),
		new CFSecTableInfo("Int64Def", true, false, "Tenant"),
		new CFSecTableInfo("Int64Type", true, false, "Tenant"),
		new CFSecTableInfo("NmTokenCol", true, false, "Tenant"),
		new CFSecTableInfo("NmTokenDef", true, false, "Tenant"),
		new CFSecTableInfo("NmTokenType", true, false, "Tenant"),
		new CFSecTableInfo("NmTokensCol", true, false, "Tenant"),
		new CFSecTableInfo("NmTokensDef", true, false, "Tenant"),
		new CFSecTableInfo("NmTokensType", true, false, "Tenant"),
		new CFSecTableInfo("NumberCol", true, false, "Tenant"),
		new CFSecTableInfo("NumberDef", true, false, "Tenant"),
		new CFSecTableInfo("NumberType", true, false, "Tenant"),
		new CFSecTableInfo("Param", true, false, "Tenant"),
		new CFSecTableInfo("PopDep", true, false, "Tenant"),
		new CFSecTableInfo("PopSubDep1", true, false, "Tenant"),
		new CFSecTableInfo("PopSubDep2", true, false, "Tenant"),
		new CFSecTableInfo("PopSubDep3", true, false, "Tenant"),
		new CFSecTableInfo("PopTopDep", true, false, "Tenant"),
		new CFSecTableInfo("Relation", true, false, "Tenant"),
		new CFSecTableInfo("RelationCol", true, false, "Tenant"),
		new CFSecTableInfo("RoleDef", true, false, "Tenant"),
		new CFSecTableInfo("SchemaDef", true, false, "Tenant"),
		new CFSecTableInfo("SchemaRef", true, false, "Tenant"),
		new CFSecTableInfo("SchemaRole", true, false, "Tenant"),
		new CFSecTableInfo("SchemaTweak", true, false, "Tenant"),
		new CFSecTableInfo("Scope", true, false, "Tenant"),
		new CFSecTableInfo("ServerListFunc", true, false, "Tenant"),
		new CFSecTableInfo("ServerMethod", true, false, "Tenant"),
		new CFSecTableInfo("ServerObjFunc", true, false, "Tenant"),
		new CFSecTableInfo("ServerProc", true, false, "Tenant"),
		new CFSecTableInfo("StringCol", true, false, "Tenant"),
		new CFSecTableInfo("StringDef", true, false, "Tenant"),
		new CFSecTableInfo("StringType", true, false, "Tenant"),
		new CFSecTableInfo("TZDateCol", true, false, "Tenant"),
		new CFSecTableInfo("TZDateDef", true, false, "Tenant"),
		new CFSecTableInfo("TZDateType", true, false, "Tenant"),
		new CFSecTableInfo("TZTimeCol", true, false, "Tenant"),
		new CFSecTableInfo("TZTimeDef", true, false, "Tenant"),
		new CFSecTableInfo("TZTimeType", true, false, "Tenant"),
		new CFSecTableInfo("TZTimestampCol", true, false, "Tenant"),
		new CFSecTableInfo("TZTimestampDef", true, false, "Tenant"),
		new CFSecTableInfo("TZTimestampType", true, false, "Tenant"),
		new CFSecTableInfo("Table", true, false, "Tenant"),
		new CFSecTableInfo("TableCol", true, false, "Tenant"),
		new CFSecTableInfo("TableTweak", true, false, "Tenant"),
		new CFSecTableInfo("TextCol", true, false, "Tenant"),
		new CFSecTableInfo("TextDef", true, false, "Tenant"),
		new CFSecTableInfo("TextType", true, false, "Tenant"),
		new CFSecTableInfo("TimeCol", true, false, "Tenant"),
		new CFSecTableInfo("TimeDef", true, false, "Tenant"),
		new CFSecTableInfo("TimeType", true, false, "Tenant"),
		new CFSecTableInfo("TimestampCol", true, false, "Tenant"),
		new CFSecTableInfo("TimestampDef", true, false, "Tenant"),
		new CFSecTableInfo("TimestampType", true, false, "Tenant"),
		new CFSecTableInfo("TokenCol", true, false, "Tenant"),
		new CFSecTableInfo("TokenDef", true, false, "Tenant"),
		new CFSecTableInfo("TokenType", true, false, "Tenant"),
		new CFSecTableInfo("Tweak", true, false, "Tenant"),
		new CFSecTableInfo("UInt16Col", true, false, "Tenant"),
		new CFSecTableInfo("UInt16Def", true, false, "Tenant"),
		new CFSecTableInfo("UInt16Type", true, false, "Tenant"),
		new CFSecTableInfo("UInt32Col", true, false, "Tenant"),
		new CFSecTableInfo("UInt32Def", true, false, "Tenant"),
		new CFSecTableInfo("UInt32Type", true, false, "Tenant"),
		new CFSecTableInfo("UInt64Col", true, false, "Tenant"),
		new CFSecTableInfo("UInt64Def", true, false, "Tenant"),
		new CFSecTableInfo("UInt64Type", true, false, "Tenant"),
		new CFSecTableInfo("Uuid6Col", true, false, "Tenant"),
		new CFSecTableInfo("Uuid6Def", true, false, "Tenant"),
		new CFSecTableInfo("Uuid6Gen", true, false, "Tenant"),
		new CFSecTableInfo("Uuid6Type", true, false, "Tenant"),
		new CFSecTableInfo("UuidCol", true, false, "Tenant"),
		new CFSecTableInfo("UuidDef", true, false, "Tenant"),
		new CFSecTableInfo("UuidGen", true, false, "Tenant"),
		new CFSecTableInfo("UuidType", true, false, "Tenant"),
		new CFSecTableInfo("Value", true, false, "Tenant")};
	public static final AtomicReference<CFSecTableInfo[]> consolidatedTableInfo = new AtomicReference<>(null);
	public static final CFSecRoleInfo ROLE_INFO[] = {};
	public static final AtomicReference<CFSecRoleInfo[]> consolidatedRoleInfo = new AtomicReference<>(null);

	public static CFSecTableInfo[] getTableInfo() {
		return TABLE_INFO;
	}

	public static CFSecTableInfo[] getConsolidatedTableInfo() {
		if (consolidatedTableInfo.get() == null) {
			ArrayList<CFSecTableInfo> lst = new ArrayList<>();
			for( CFSecTableInfo info: ICFSecSchema.getTableInfo()) {
				lst.add(info);
			}
			for( CFSecTableInfo info: ICFIntSchema.getTableInfo()) {
				lst.add(info);
			}
			for( CFSecTableInfo info: TABLE_INFO) {
				lst.add(info);
			}
			lst.sort((o1, o2) -> {
				return o1.getTableName().compareTo(o2.getTableName());
			});
			CFSecTableInfo arr[] = new CFSecTableInfo[lst.size()];
			int idx = 0;
			for(CFSecTableInfo info: lst) {
				arr[idx++] = info;
			}
			consolidatedTableInfo.compareAndSet(null, arr);
		}
		return(consolidatedTableInfo.get());
	}

	public static CFSecRoleInfo[] getRoleInfo() {
		return ROLE_INFO;
	}

	public static CFSecRoleInfo[] getConsolidatedRoleInfo() {
		if (consolidatedRoleInfo.get() == null) {
			// The ROLE_INFO entries are in dependency order, so do not sort them any other way
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
	 *	Get the Atom Table interface for the schema.
	 *
	 *	@return	The Atom Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamAtomTable getTableAtom();

	/**
	 *	Get the Atom Factory interface for the schema.
	 *
	 *	@return	The Atom Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamAtomFactory getFactoryAtom();

	/**
	 *	Get the BlobCol Table interface for the schema.
	 *
	 *	@return	The BlobCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBlobColTable getTableBlobCol();

	/**
	 *	Get the BlobCol Factory interface for the schema.
	 *
	 *	@return	The BlobCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBlobColFactory getFactoryBlobCol();

	/**
	 *	Get the BlobDef Table interface for the schema.
	 *
	 *	@return	The BlobDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBlobDefTable getTableBlobDef();

	/**
	 *	Get the BlobDef Factory interface for the schema.
	 *
	 *	@return	The BlobDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBlobDefFactory getFactoryBlobDef();

	/**
	 *	Get the BlobType Table interface for the schema.
	 *
	 *	@return	The BlobType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBlobTypeTable getTableBlobType();

	/**
	 *	Get the BlobType Factory interface for the schema.
	 *
	 *	@return	The BlobType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBlobTypeFactory getFactoryBlobType();

	/**
	 *	Get the BoolCol Table interface for the schema.
	 *
	 *	@return	The BoolCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBoolColTable getTableBoolCol();

	/**
	 *	Get the BoolCol Factory interface for the schema.
	 *
	 *	@return	The BoolCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBoolColFactory getFactoryBoolCol();

	/**
	 *	Get the BoolDef Table interface for the schema.
	 *
	 *	@return	The BoolDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBoolDefTable getTableBoolDef();

	/**
	 *	Get the BoolDef Factory interface for the schema.
	 *
	 *	@return	The BoolDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBoolDefFactory getFactoryBoolDef();

	/**
	 *	Get the BoolType Table interface for the schema.
	 *
	 *	@return	The BoolType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBoolTypeTable getTableBoolType();

	/**
	 *	Get the BoolType Factory interface for the schema.
	 *
	 *	@return	The BoolType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamBoolTypeFactory getFactoryBoolType();

	/**
	 *	Get the Chain Table interface for the schema.
	 *
	 *	@return	The Chain Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamChainTable getTableChain();

	/**
	 *	Get the Chain Factory interface for the schema.
	 *
	 *	@return	The Chain Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamChainFactory getFactoryChain();

	/**
	 *	Get the ClearDep Table interface for the schema.
	 *
	 *	@return	The ClearDep Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearDepTable getTableClearDep();

	/**
	 *	Get the ClearDep Factory interface for the schema.
	 *
	 *	@return	The ClearDep Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearDepFactory getFactoryClearDep();

	/**
	 *	Get the ClearSubDep1 Table interface for the schema.
	 *
	 *	@return	The ClearSubDep1 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearSubDep1Table getTableClearSubDep1();

	/**
	 *	Get the ClearSubDep1 Factory interface for the schema.
	 *
	 *	@return	The ClearSubDep1 Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearSubDep1Factory getFactoryClearSubDep1();

	/**
	 *	Get the ClearSubDep2 Table interface for the schema.
	 *
	 *	@return	The ClearSubDep2 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearSubDep2Table getTableClearSubDep2();

	/**
	 *	Get the ClearSubDep2 Factory interface for the schema.
	 *
	 *	@return	The ClearSubDep2 Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearSubDep2Factory getFactoryClearSubDep2();

	/**
	 *	Get the ClearSubDep3 Table interface for the schema.
	 *
	 *	@return	The ClearSubDep3 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearSubDep3Table getTableClearSubDep3();

	/**
	 *	Get the ClearSubDep3 Factory interface for the schema.
	 *
	 *	@return	The ClearSubDep3 Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearSubDep3Factory getFactoryClearSubDep3();

	/**
	 *	Get the ClearTopDep Table interface for the schema.
	 *
	 *	@return	The ClearTopDep Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearTopDepTable getTableClearTopDep();

	/**
	 *	Get the ClearTopDep Factory interface for the schema.
	 *
	 *	@return	The ClearTopDep Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamClearTopDepFactory getFactoryClearTopDep();

	/**
	 *	Get the Cluster Table interface for the schema.
	 *
	 *	@return	The Cluster Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecClusterTable getTableCluster();

	/**
	 *	Get the Cluster Factory interface for the schema.
	 *
	 *	@return	The Cluster Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecClusterFactory getFactoryCluster();

	/**
	 *	Get the DateCol Table interface for the schema.
	 *
	 *	@return	The DateCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDateColTable getTableDateCol();

	/**
	 *	Get the DateCol Factory interface for the schema.
	 *
	 *	@return	The DateCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDateColFactory getFactoryDateCol();

	/**
	 *	Get the DateDef Table interface for the schema.
	 *
	 *	@return	The DateDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDateDefTable getTableDateDef();

	/**
	 *	Get the DateDef Factory interface for the schema.
	 *
	 *	@return	The DateDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDateDefFactory getFactoryDateDef();

	/**
	 *	Get the DateType Table interface for the schema.
	 *
	 *	@return	The DateType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDateTypeTable getTableDateType();

	/**
	 *	Get the DateType Factory interface for the schema.
	 *
	 *	@return	The DateType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDateTypeFactory getFactoryDateType();

	/**
	 *	Get the DbKeyHash128Col Table interface for the schema.
	 *
	 *	@return	The DbKeyHash128Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash128ColTable getTableDbKeyHash128Col();

	/**
	 *	Get the DbKeyHash128Col Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash128Col Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash128ColFactory getFactoryDbKeyHash128Col();

	/**
	 *	Get the DbKeyHash128Def Table interface for the schema.
	 *
	 *	@return	The DbKeyHash128Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash128DefTable getTableDbKeyHash128Def();

	/**
	 *	Get the DbKeyHash128Def Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash128Def Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash128DefFactory getFactoryDbKeyHash128Def();

	/**
	 *	Get the DbKeyHash128Gen Table interface for the schema.
	 *
	 *	@return	The DbKeyHash128Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash128GenTable getTableDbKeyHash128Gen();

	/**
	 *	Get the DbKeyHash128Gen Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash128Gen Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash128GenFactory getFactoryDbKeyHash128Gen();

	/**
	 *	Get the DbKeyHash128Type Table interface for the schema.
	 *
	 *	@return	The DbKeyHash128Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash128TypeTable getTableDbKeyHash128Type();

	/**
	 *	Get the DbKeyHash128Type Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash128Type Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash128TypeFactory getFactoryDbKeyHash128Type();

	/**
	 *	Get the DbKeyHash160Col Table interface for the schema.
	 *
	 *	@return	The DbKeyHash160Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash160ColTable getTableDbKeyHash160Col();

	/**
	 *	Get the DbKeyHash160Col Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash160Col Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash160ColFactory getFactoryDbKeyHash160Col();

	/**
	 *	Get the DbKeyHash160Def Table interface for the schema.
	 *
	 *	@return	The DbKeyHash160Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash160DefTable getTableDbKeyHash160Def();

	/**
	 *	Get the DbKeyHash160Def Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash160Def Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash160DefFactory getFactoryDbKeyHash160Def();

	/**
	 *	Get the DbKeyHash160Gen Table interface for the schema.
	 *
	 *	@return	The DbKeyHash160Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash160GenTable getTableDbKeyHash160Gen();

	/**
	 *	Get the DbKeyHash160Gen Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash160Gen Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash160GenFactory getFactoryDbKeyHash160Gen();

	/**
	 *	Get the DbKeyHash160Type Table interface for the schema.
	 *
	 *	@return	The DbKeyHash160Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash160TypeTable getTableDbKeyHash160Type();

	/**
	 *	Get the DbKeyHash160Type Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash160Type Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash160TypeFactory getFactoryDbKeyHash160Type();

	/**
	 *	Get the DbKeyHash224Col Table interface for the schema.
	 *
	 *	@return	The DbKeyHash224Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash224ColTable getTableDbKeyHash224Col();

	/**
	 *	Get the DbKeyHash224Col Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash224Col Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash224ColFactory getFactoryDbKeyHash224Col();

	/**
	 *	Get the DbKeyHash224Def Table interface for the schema.
	 *
	 *	@return	The DbKeyHash224Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash224DefTable getTableDbKeyHash224Def();

	/**
	 *	Get the DbKeyHash224Def Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash224Def Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash224DefFactory getFactoryDbKeyHash224Def();

	/**
	 *	Get the DbKeyHash224Gen Table interface for the schema.
	 *
	 *	@return	The DbKeyHash224Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash224GenTable getTableDbKeyHash224Gen();

	/**
	 *	Get the DbKeyHash224Gen Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash224Gen Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash224GenFactory getFactoryDbKeyHash224Gen();

	/**
	 *	Get the DbKeyHash224Type Table interface for the schema.
	 *
	 *	@return	The DbKeyHash224Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash224TypeTable getTableDbKeyHash224Type();

	/**
	 *	Get the DbKeyHash224Type Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash224Type Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash224TypeFactory getFactoryDbKeyHash224Type();

	/**
	 *	Get the DbKeyHash256Col Table interface for the schema.
	 *
	 *	@return	The DbKeyHash256Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash256ColTable getTableDbKeyHash256Col();

	/**
	 *	Get the DbKeyHash256Col Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash256Col Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash256ColFactory getFactoryDbKeyHash256Col();

	/**
	 *	Get the DbKeyHash256Def Table interface for the schema.
	 *
	 *	@return	The DbKeyHash256Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash256DefTable getTableDbKeyHash256Def();

	/**
	 *	Get the DbKeyHash256Def Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash256Def Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash256DefFactory getFactoryDbKeyHash256Def();

	/**
	 *	Get the DbKeyHash256Gen Table interface for the schema.
	 *
	 *	@return	The DbKeyHash256Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash256GenTable getTableDbKeyHash256Gen();

	/**
	 *	Get the DbKeyHash256Gen Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash256Gen Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash256GenFactory getFactoryDbKeyHash256Gen();

	/**
	 *	Get the DbKeyHash256Type Table interface for the schema.
	 *
	 *	@return	The DbKeyHash256Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash256TypeTable getTableDbKeyHash256Type();

	/**
	 *	Get the DbKeyHash256Type Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash256Type Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash256TypeFactory getFactoryDbKeyHash256Type();

	/**
	 *	Get the DbKeyHash384Col Table interface for the schema.
	 *
	 *	@return	The DbKeyHash384Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash384ColTable getTableDbKeyHash384Col();

	/**
	 *	Get the DbKeyHash384Col Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash384Col Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash384ColFactory getFactoryDbKeyHash384Col();

	/**
	 *	Get the DbKeyHash384Def Table interface for the schema.
	 *
	 *	@return	The DbKeyHash384Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash384DefTable getTableDbKeyHash384Def();

	/**
	 *	Get the DbKeyHash384Def Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash384Def Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash384DefFactory getFactoryDbKeyHash384Def();

	/**
	 *	Get the DbKeyHash384Gen Table interface for the schema.
	 *
	 *	@return	The DbKeyHash384Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash384GenTable getTableDbKeyHash384Gen();

	/**
	 *	Get the DbKeyHash384Gen Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash384Gen Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash384GenFactory getFactoryDbKeyHash384Gen();

	/**
	 *	Get the DbKeyHash384Type Table interface for the schema.
	 *
	 *	@return	The DbKeyHash384Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash384TypeTable getTableDbKeyHash384Type();

	/**
	 *	Get the DbKeyHash384Type Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash384Type Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash384TypeFactory getFactoryDbKeyHash384Type();

	/**
	 *	Get the DbKeyHash512Col Table interface for the schema.
	 *
	 *	@return	The DbKeyHash512Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash512ColTable getTableDbKeyHash512Col();

	/**
	 *	Get the DbKeyHash512Col Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash512Col Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash512ColFactory getFactoryDbKeyHash512Col();

	/**
	 *	Get the DbKeyHash512Def Table interface for the schema.
	 *
	 *	@return	The DbKeyHash512Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash512DefTable getTableDbKeyHash512Def();

	/**
	 *	Get the DbKeyHash512Def Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash512Def Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash512DefFactory getFactoryDbKeyHash512Def();

	/**
	 *	Get the DbKeyHash512Gen Table interface for the schema.
	 *
	 *	@return	The DbKeyHash512Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash512GenTable getTableDbKeyHash512Gen();

	/**
	 *	Get the DbKeyHash512Gen Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash512Gen Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash512GenFactory getFactoryDbKeyHash512Gen();

	/**
	 *	Get the DbKeyHash512Type Table interface for the schema.
	 *
	 *	@return	The DbKeyHash512Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash512TypeTable getTableDbKeyHash512Type();

	/**
	 *	Get the DbKeyHash512Type Factory interface for the schema.
	 *
	 *	@return	The DbKeyHash512Type Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDbKeyHash512TypeFactory getFactoryDbKeyHash512Type();

	/**
	 *	Get the DelDep Table interface for the schema.
	 *
	 *	@return	The DelDep Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelDepTable getTableDelDep();

	/**
	 *	Get the DelDep Factory interface for the schema.
	 *
	 *	@return	The DelDep Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelDepFactory getFactoryDelDep();

	/**
	 *	Get the DelSubDep1 Table interface for the schema.
	 *
	 *	@return	The DelSubDep1 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelSubDep1Table getTableDelSubDep1();

	/**
	 *	Get the DelSubDep1 Factory interface for the schema.
	 *
	 *	@return	The DelSubDep1 Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelSubDep1Factory getFactoryDelSubDep1();

	/**
	 *	Get the DelSubDep2 Table interface for the schema.
	 *
	 *	@return	The DelSubDep2 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelSubDep2Table getTableDelSubDep2();

	/**
	 *	Get the DelSubDep2 Factory interface for the schema.
	 *
	 *	@return	The DelSubDep2 Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelSubDep2Factory getFactoryDelSubDep2();

	/**
	 *	Get the DelSubDep3 Table interface for the schema.
	 *
	 *	@return	The DelSubDep3 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelSubDep3Table getTableDelSubDep3();

	/**
	 *	Get the DelSubDep3 Factory interface for the schema.
	 *
	 *	@return	The DelSubDep3 Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelSubDep3Factory getFactoryDelSubDep3();

	/**
	 *	Get the DelTopDep Table interface for the schema.
	 *
	 *	@return	The DelTopDep Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelTopDepTable getTableDelTopDep();

	/**
	 *	Get the DelTopDep Factory interface for the schema.
	 *
	 *	@return	The DelTopDep Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDelTopDepFactory getFactoryDelTopDep();

	/**
	 *	Get the DoubleCol Table interface for the schema.
	 *
	 *	@return	The DoubleCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDoubleColTable getTableDoubleCol();

	/**
	 *	Get the DoubleCol Factory interface for the schema.
	 *
	 *	@return	The DoubleCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDoubleColFactory getFactoryDoubleCol();

	/**
	 *	Get the DoubleDef Table interface for the schema.
	 *
	 *	@return	The DoubleDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDoubleDefTable getTableDoubleDef();

	/**
	 *	Get the DoubleDef Factory interface for the schema.
	 *
	 *	@return	The DoubleDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDoubleDefFactory getFactoryDoubleDef();

	/**
	 *	Get the DoubleType Table interface for the schema.
	 *
	 *	@return	The DoubleType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDoubleTypeTable getTableDoubleType();

	/**
	 *	Get the DoubleType Factory interface for the schema.
	 *
	 *	@return	The DoubleType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamDoubleTypeFactory getFactoryDoubleType();

	/**
	 *	Get the EnumDef Table interface for the schema.
	 *
	 *	@return	The EnumDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamEnumDefTable getTableEnumDef();

	/**
	 *	Get the EnumDef Factory interface for the schema.
	 *
	 *	@return	The EnumDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamEnumDefFactory getFactoryEnumDef();

	/**
	 *	Get the EnumTag Table interface for the schema.
	 *
	 *	@return	The EnumTag Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamEnumTagTable getTableEnumTag();

	/**
	 *	Get the EnumTag Factory interface for the schema.
	 *
	 *	@return	The EnumTag Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamEnumTagFactory getFactoryEnumTag();

	/**
	 *	Get the EnumType Table interface for the schema.
	 *
	 *	@return	The EnumType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamEnumTypeTable getTableEnumType();

	/**
	 *	Get the EnumType Factory interface for the schema.
	 *
	 *	@return	The EnumType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamEnumTypeFactory getFactoryEnumType();

	/**
	 *	Get the FloatCol Table interface for the schema.
	 *
	 *	@return	The FloatCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamFloatColTable getTableFloatCol();

	/**
	 *	Get the FloatCol Factory interface for the schema.
	 *
	 *	@return	The FloatCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamFloatColFactory getFactoryFloatCol();

	/**
	 *	Get the FloatDef Table interface for the schema.
	 *
	 *	@return	The FloatDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamFloatDefTable getTableFloatDef();

	/**
	 *	Get the FloatDef Factory interface for the schema.
	 *
	 *	@return	The FloatDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamFloatDefFactory getFactoryFloatDef();

	/**
	 *	Get the FloatType Table interface for the schema.
	 *
	 *	@return	The FloatType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamFloatTypeTable getTableFloatType();

	/**
	 *	Get the FloatType Factory interface for the schema.
	 *
	 *	@return	The FloatType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamFloatTypeFactory getFactoryFloatType();

	/**
	 *	Get the ISOCcy Table interface for the schema.
	 *
	 *	@return	The ISOCcy Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCcyTable getTableISOCcy();

	/**
	 *	Get the ISOCcy Factory interface for the schema.
	 *
	 *	@return	The ISOCcy Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCcyFactory getFactoryISOCcy();

	/**
	 *	Get the ISOCtry Table interface for the schema.
	 *
	 *	@return	The ISOCtry Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCtryTable getTableISOCtry();

	/**
	 *	Get the ISOCtry Factory interface for the schema.
	 *
	 *	@return	The ISOCtry Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCtryFactory getFactoryISOCtry();

	/**
	 *	Get the ISOCtryCcy Table interface for the schema.
	 *
	 *	@return	The ISOCtryCcy Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCtryCcyTable getTableISOCtryCcy();

	/**
	 *	Get the ISOCtryCcy Factory interface for the schema.
	 *
	 *	@return	The ISOCtryCcy Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCtryCcyFactory getFactoryISOCtryCcy();

	/**
	 *	Get the ISOCtryLang Table interface for the schema.
	 *
	 *	@return	The ISOCtryLang Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCtryLangTable getTableISOCtryLang();

	/**
	 *	Get the ISOCtryLang Factory interface for the schema.
	 *
	 *	@return	The ISOCtryLang Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOCtryLangFactory getFactoryISOCtryLang();

	/**
	 *	Get the ISOLang Table interface for the schema.
	 *
	 *	@return	The ISOLang Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOLangTable getTableISOLang();

	/**
	 *	Get the ISOLang Factory interface for the schema.
	 *
	 *	@return	The ISOLang Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOLangFactory getFactoryISOLang();

	/**
	 *	Get the ISOTZone Table interface for the schema.
	 *
	 *	@return	The ISOTZone Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOTZoneTable getTableISOTZone();

	/**
	 *	Get the ISOTZone Factory interface for the schema.
	 *
	 *	@return	The ISOTZone Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecISOTZoneFactory getFactoryISOTZone();

	/**
	 *	Get the Id16Gen Table interface for the schema.
	 *
	 *	@return	The Id16Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamId16GenTable getTableId16Gen();

	/**
	 *	Get the Id16Gen Factory interface for the schema.
	 *
	 *	@return	The Id16Gen Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamId16GenFactory getFactoryId16Gen();

	/**
	 *	Get the Id32Gen Table interface for the schema.
	 *
	 *	@return	The Id32Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamId32GenTable getTableId32Gen();

	/**
	 *	Get the Id32Gen Factory interface for the schema.
	 *
	 *	@return	The Id32Gen Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamId32GenFactory getFactoryId32Gen();

	/**
	 *	Get the Id64Gen Table interface for the schema.
	 *
	 *	@return	The Id64Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamId64GenTable getTableId64Gen();

	/**
	 *	Get the Id64Gen Factory interface for the schema.
	 *
	 *	@return	The Id64Gen Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamId64GenFactory getFactoryId64Gen();

	/**
	 *	Get the Index Table interface for the schema.
	 *
	 *	@return	The Index Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamIndexTable getTableIndex();

	/**
	 *	Get the Index Factory interface for the schema.
	 *
	 *	@return	The Index Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamIndexFactory getFactoryIndex();

	/**
	 *	Get the IndexCol Table interface for the schema.
	 *
	 *	@return	The IndexCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamIndexColTable getTableIndexCol();

	/**
	 *	Get the IndexCol Factory interface for the schema.
	 *
	 *	@return	The IndexCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamIndexColFactory getFactoryIndexCol();

	/**
	 *	Get the IndexTweak Table interface for the schema.
	 *
	 *	@return	The IndexTweak Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamIndexTweakTable getTableIndexTweak();

	/**
	 *	Get the IndexTweak Factory interface for the schema.
	 *
	 *	@return	The IndexTweak Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamIndexTweakFactory getFactoryIndexTweak();

	/**
	 *	Get the Int16Col Table interface for the schema.
	 *
	 *	@return	The Int16Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt16ColTable getTableInt16Col();

	/**
	 *	Get the Int16Col Factory interface for the schema.
	 *
	 *	@return	The Int16Col Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt16ColFactory getFactoryInt16Col();

	/**
	 *	Get the Int16Def Table interface for the schema.
	 *
	 *	@return	The Int16Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt16DefTable getTableInt16Def();

	/**
	 *	Get the Int16Def Factory interface for the schema.
	 *
	 *	@return	The Int16Def Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt16DefFactory getFactoryInt16Def();

	/**
	 *	Get the Int16Type Table interface for the schema.
	 *
	 *	@return	The Int16Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt16TypeTable getTableInt16Type();

	/**
	 *	Get the Int16Type Factory interface for the schema.
	 *
	 *	@return	The Int16Type Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt16TypeFactory getFactoryInt16Type();

	/**
	 *	Get the Int32Col Table interface for the schema.
	 *
	 *	@return	The Int32Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt32ColTable getTableInt32Col();

	/**
	 *	Get the Int32Col Factory interface for the schema.
	 *
	 *	@return	The Int32Col Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt32ColFactory getFactoryInt32Col();

	/**
	 *	Get the Int32Def Table interface for the schema.
	 *
	 *	@return	The Int32Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt32DefTable getTableInt32Def();

	/**
	 *	Get the Int32Def Factory interface for the schema.
	 *
	 *	@return	The Int32Def Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt32DefFactory getFactoryInt32Def();

	/**
	 *	Get the Int32Type Table interface for the schema.
	 *
	 *	@return	The Int32Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt32TypeTable getTableInt32Type();

	/**
	 *	Get the Int32Type Factory interface for the schema.
	 *
	 *	@return	The Int32Type Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt32TypeFactory getFactoryInt32Type();

	/**
	 *	Get the Int64Col Table interface for the schema.
	 *
	 *	@return	The Int64Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt64ColTable getTableInt64Col();

	/**
	 *	Get the Int64Col Factory interface for the schema.
	 *
	 *	@return	The Int64Col Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt64ColFactory getFactoryInt64Col();

	/**
	 *	Get the Int64Def Table interface for the schema.
	 *
	 *	@return	The Int64Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt64DefTable getTableInt64Def();

	/**
	 *	Get the Int64Def Factory interface for the schema.
	 *
	 *	@return	The Int64Def Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt64DefFactory getFactoryInt64Def();

	/**
	 *	Get the Int64Type Table interface for the schema.
	 *
	 *	@return	The Int64Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt64TypeTable getTableInt64Type();

	/**
	 *	Get the Int64Type Factory interface for the schema.
	 *
	 *	@return	The Int64Type Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamInt64TypeFactory getFactoryInt64Type();

	/**
	 *	Get the License Table interface for the schema.
	 *
	 *	@return	The License Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntLicenseTable getTableLicense();

	/**
	 *	Get the License Factory interface for the schema.
	 *
	 *	@return	The License Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntLicenseFactory getFactoryLicense();

	/**
	 *	Get the MajorVersion Table interface for the schema.
	 *
	 *	@return	The MajorVersion Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntMajorVersionTable getTableMajorVersion();

	/**
	 *	Get the MajorVersion Factory interface for the schema.
	 *
	 *	@return	The MajorVersion Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntMajorVersionFactory getFactoryMajorVersion();

	/**
	 *	Get the MimeType Table interface for the schema.
	 *
	 *	@return	The MimeType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntMimeTypeTable getTableMimeType();

	/**
	 *	Get the MimeType Factory interface for the schema.
	 *
	 *	@return	The MimeType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntMimeTypeFactory getFactoryMimeType();

	/**
	 *	Get the MinorVersion Table interface for the schema.
	 *
	 *	@return	The MinorVersion Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntMinorVersionTable getTableMinorVersion();

	/**
	 *	Get the MinorVersion Factory interface for the schema.
	 *
	 *	@return	The MinorVersion Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntMinorVersionFactory getFactoryMinorVersion();

	/**
	 *	Get the NmTokenCol Table interface for the schema.
	 *
	 *	@return	The NmTokenCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokenColTable getTableNmTokenCol();

	/**
	 *	Get the NmTokenCol Factory interface for the schema.
	 *
	 *	@return	The NmTokenCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokenColFactory getFactoryNmTokenCol();

	/**
	 *	Get the NmTokenDef Table interface for the schema.
	 *
	 *	@return	The NmTokenDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokenDefTable getTableNmTokenDef();

	/**
	 *	Get the NmTokenDef Factory interface for the schema.
	 *
	 *	@return	The NmTokenDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokenDefFactory getFactoryNmTokenDef();

	/**
	 *	Get the NmTokenType Table interface for the schema.
	 *
	 *	@return	The NmTokenType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokenTypeTable getTableNmTokenType();

	/**
	 *	Get the NmTokenType Factory interface for the schema.
	 *
	 *	@return	The NmTokenType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokenTypeFactory getFactoryNmTokenType();

	/**
	 *	Get the NmTokensCol Table interface for the schema.
	 *
	 *	@return	The NmTokensCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokensColTable getTableNmTokensCol();

	/**
	 *	Get the NmTokensCol Factory interface for the schema.
	 *
	 *	@return	The NmTokensCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokensColFactory getFactoryNmTokensCol();

	/**
	 *	Get the NmTokensDef Table interface for the schema.
	 *
	 *	@return	The NmTokensDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokensDefTable getTableNmTokensDef();

	/**
	 *	Get the NmTokensDef Factory interface for the schema.
	 *
	 *	@return	The NmTokensDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokensDefFactory getFactoryNmTokensDef();

	/**
	 *	Get the NmTokensType Table interface for the schema.
	 *
	 *	@return	The NmTokensType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokensTypeTable getTableNmTokensType();

	/**
	 *	Get the NmTokensType Factory interface for the schema.
	 *
	 *	@return	The NmTokensType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNmTokensTypeFactory getFactoryNmTokensType();

	/**
	 *	Get the NumberCol Table interface for the schema.
	 *
	 *	@return	The NumberCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNumberColTable getTableNumberCol();

	/**
	 *	Get the NumberCol Factory interface for the schema.
	 *
	 *	@return	The NumberCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNumberColFactory getFactoryNumberCol();

	/**
	 *	Get the NumberDef Table interface for the schema.
	 *
	 *	@return	The NumberDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNumberDefTable getTableNumberDef();

	/**
	 *	Get the NumberDef Factory interface for the schema.
	 *
	 *	@return	The NumberDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNumberDefFactory getFactoryNumberDef();

	/**
	 *	Get the NumberType Table interface for the schema.
	 *
	 *	@return	The NumberType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNumberTypeTable getTableNumberType();

	/**
	 *	Get the NumberType Factory interface for the schema.
	 *
	 *	@return	The NumberType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamNumberTypeFactory getFactoryNumberType();

	/**
	 *	Get the Param Table interface for the schema.
	 *
	 *	@return	The Param Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamParamTable getTableParam();

	/**
	 *	Get the Param Factory interface for the schema.
	 *
	 *	@return	The Param Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamParamFactory getFactoryParam();

	/**
	 *	Get the PopDep Table interface for the schema.
	 *
	 *	@return	The PopDep Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopDepTable getTablePopDep();

	/**
	 *	Get the PopDep Factory interface for the schema.
	 *
	 *	@return	The PopDep Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopDepFactory getFactoryPopDep();

	/**
	 *	Get the PopSubDep1 Table interface for the schema.
	 *
	 *	@return	The PopSubDep1 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopSubDep1Table getTablePopSubDep1();

	/**
	 *	Get the PopSubDep1 Factory interface for the schema.
	 *
	 *	@return	The PopSubDep1 Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopSubDep1Factory getFactoryPopSubDep1();

	/**
	 *	Get the PopSubDep2 Table interface for the schema.
	 *
	 *	@return	The PopSubDep2 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopSubDep2Table getTablePopSubDep2();

	/**
	 *	Get the PopSubDep2 Factory interface for the schema.
	 *
	 *	@return	The PopSubDep2 Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopSubDep2Factory getFactoryPopSubDep2();

	/**
	 *	Get the PopSubDep3 Table interface for the schema.
	 *
	 *	@return	The PopSubDep3 Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopSubDep3Table getTablePopSubDep3();

	/**
	 *	Get the PopSubDep3 Factory interface for the schema.
	 *
	 *	@return	The PopSubDep3 Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopSubDep3Factory getFactoryPopSubDep3();

	/**
	 *	Get the PopTopDep Table interface for the schema.
	 *
	 *	@return	The PopTopDep Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopTopDepTable getTablePopTopDep();

	/**
	 *	Get the PopTopDep Factory interface for the schema.
	 *
	 *	@return	The PopTopDep Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamPopTopDepFactory getFactoryPopTopDep();

	/**
	 *	Get the Relation Table interface for the schema.
	 *
	 *	@return	The Relation Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamRelationTable getTableRelation();

	/**
	 *	Get the Relation Factory interface for the schema.
	 *
	 *	@return	The Relation Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamRelationFactory getFactoryRelation();

	/**
	 *	Get the RelationCol Table interface for the schema.
	 *
	 *	@return	The RelationCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamRelationColTable getTableRelationCol();

	/**
	 *	Get the RelationCol Factory interface for the schema.
	 *
	 *	@return	The RelationCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamRelationColFactory getFactoryRelationCol();

	/**
	 *	Get the RoleDef Table interface for the schema.
	 *
	 *	@return	The RoleDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamRoleDefTable getTableRoleDef();

	/**
	 *	Get the RoleDef Factory interface for the schema.
	 *
	 *	@return	The RoleDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamRoleDefFactory getFactoryRoleDef();

	/**
	 *	Get the SchemaDef Table interface for the schema.
	 *
	 *	@return	The SchemaDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamSchemaDefTable getTableSchemaDef();

	/**
	 *	Get the SchemaDef Factory interface for the schema.
	 *
	 *	@return	The SchemaDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamSchemaDefFactory getFactorySchemaDef();

	/**
	 *	Get the SchemaRef Table interface for the schema.
	 *
	 *	@return	The SchemaRef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamSchemaRefTable getTableSchemaRef();

	/**
	 *	Get the SchemaRef Factory interface for the schema.
	 *
	 *	@return	The SchemaRef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamSchemaRefFactory getFactorySchemaRef();

	/**
	 *	Get the SchemaRole Table interface for the schema.
	 *
	 *	@return	The SchemaRole Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamSchemaRoleTable getTableSchemaRole();

	/**
	 *	Get the SchemaRole Factory interface for the schema.
	 *
	 *	@return	The SchemaRole Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamSchemaRoleFactory getFactorySchemaRole();

	/**
	 *	Get the SchemaTweak Table interface for the schema.
	 *
	 *	@return	The SchemaTweak Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamSchemaTweakTable getTableSchemaTweak();

	/**
	 *	Get the SchemaTweak Factory interface for the schema.
	 *
	 *	@return	The SchemaTweak Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamSchemaTweakFactory getFactorySchemaTweak();

	/**
	 *	Get the Scope Table interface for the schema.
	 *
	 *	@return	The Scope Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamScopeTable getTableScope();

	/**
	 *	Get the Scope Factory interface for the schema.
	 *
	 *	@return	The Scope Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamScopeFactory getFactoryScope();

	/**
	 *	Get the SecClusGrp Table interface for the schema.
	 *
	 *	@return	The SecClusGrp Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusGrpTable getTableSecClusGrp();

	/**
	 *	Get the SecClusGrp Factory interface for the schema.
	 *
	 *	@return	The SecClusGrp Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusGrpFactory getFactorySecClusGrp();

	/**
	 *	Get the SecClusGrpInc Table interface for the schema.
	 *
	 *	@return	The SecClusGrpInc Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusGrpIncTable getTableSecClusGrpInc();

	/**
	 *	Get the SecClusGrpInc Factory interface for the schema.
	 *
	 *	@return	The SecClusGrpInc Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusGrpIncFactory getFactorySecClusGrpInc();

	/**
	 *	Get the SecClusGrpMemb Table interface for the schema.
	 *
	 *	@return	The SecClusGrpMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusGrpMembTable getTableSecClusGrpMemb();

	/**
	 *	Get the SecClusGrpMemb Factory interface for the schema.
	 *
	 *	@return	The SecClusGrpMemb Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusGrpMembFactory getFactorySecClusGrpMemb();

	/**
	 *	Get the SecClusRole Table interface for the schema.
	 *
	 *	@return	The SecClusRole Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusRoleTable getTableSecClusRole();

	/**
	 *	Get the SecClusRole Factory interface for the schema.
	 *
	 *	@return	The SecClusRole Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusRoleFactory getFactorySecClusRole();

	/**
	 *	Get the SecClusRoleMemb Table interface for the schema.
	 *
	 *	@return	The SecClusRoleMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusRoleMembTable getTableSecClusRoleMemb();

	/**
	 *	Get the SecClusRoleMemb Factory interface for the schema.
	 *
	 *	@return	The SecClusRoleMemb Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecClusRoleMembFactory getFactorySecClusRoleMemb();

	/**
	 *	Get the SecRole Table interface for the schema.
	 *
	 *	@return	The SecRole Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecRoleTable getTableSecRole();

	/**
	 *	Get the SecRole Factory interface for the schema.
	 *
	 *	@return	The SecRole Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecRoleFactory getFactorySecRole();

	/**
	 *	Get the SecRoleEnables Table interface for the schema.
	 *
	 *	@return	The SecRoleEnables Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecRoleEnablesTable getTableSecRoleEnables();

	/**
	 *	Get the SecRoleEnables Factory interface for the schema.
	 *
	 *	@return	The SecRoleEnables Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecRoleEnablesFactory getFactorySecRoleEnables();

	/**
	 *	Get the SecRoleMemb Table interface for the schema.
	 *
	 *	@return	The SecRoleMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecRoleMembTable getTableSecRoleMemb();

	/**
	 *	Get the SecRoleMemb Factory interface for the schema.
	 *
	 *	@return	The SecRoleMemb Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecRoleMembFactory getFactorySecRoleMemb();

	/**
	 *	Get the SecSession Table interface for the schema.
	 *
	 *	@return	The SecSession Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSessionTable getTableSecSession();

	/**
	 *	Get the SecSession Factory interface for the schema.
	 *
	 *	@return	The SecSession Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSessionFactory getFactorySecSession();

	/**
	 *	Get the SecSysGrp Table interface for the schema.
	 *
	 *	@return	The SecSysGrp Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysGrpTable getTableSecSysGrp();

	/**
	 *	Get the SecSysGrp Factory interface for the schema.
	 *
	 *	@return	The SecSysGrp Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysGrpFactory getFactorySecSysGrp();

	/**
	 *	Get the SecSysGrpInc Table interface for the schema.
	 *
	 *	@return	The SecSysGrpInc Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysGrpIncTable getTableSecSysGrpInc();

	/**
	 *	Get the SecSysGrpInc Factory interface for the schema.
	 *
	 *	@return	The SecSysGrpInc Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysGrpIncFactory getFactorySecSysGrpInc();

	/**
	 *	Get the SecSysGrpMemb Table interface for the schema.
	 *
	 *	@return	The SecSysGrpMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysGrpMembTable getTableSecSysGrpMemb();

	/**
	 *	Get the SecSysGrpMemb Factory interface for the schema.
	 *
	 *	@return	The SecSysGrpMemb Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecSysGrpMembFactory getFactorySecSysGrpMemb();

	/**
	 *	Get the SecTentGrp Table interface for the schema.
	 *
	 *	@return	The SecTentGrp Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentGrpTable getTableSecTentGrp();

	/**
	 *	Get the SecTentGrp Factory interface for the schema.
	 *
	 *	@return	The SecTentGrp Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentGrpFactory getFactorySecTentGrp();

	/**
	 *	Get the SecTentGrpInc Table interface for the schema.
	 *
	 *	@return	The SecTentGrpInc Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentGrpIncTable getTableSecTentGrpInc();

	/**
	 *	Get the SecTentGrpInc Factory interface for the schema.
	 *
	 *	@return	The SecTentGrpInc Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentGrpIncFactory getFactorySecTentGrpInc();

	/**
	 *	Get the SecTentGrpMemb Table interface for the schema.
	 *
	 *	@return	The SecTentGrpMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentGrpMembTable getTableSecTentGrpMemb();

	/**
	 *	Get the SecTentGrpMemb Factory interface for the schema.
	 *
	 *	@return	The SecTentGrpMemb Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentGrpMembFactory getFactorySecTentGrpMemb();

	/**
	 *	Get the SecTentRole Table interface for the schema.
	 *
	 *	@return	The SecTentRole Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentRoleTable getTableSecTentRole();

	/**
	 *	Get the SecTentRole Factory interface for the schema.
	 *
	 *	@return	The SecTentRole Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentRoleFactory getFactorySecTentRole();

	/**
	 *	Get the SecTentRoleMemb Table interface for the schema.
	 *
	 *	@return	The SecTentRoleMemb Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentRoleMembTable getTableSecTentRoleMemb();

	/**
	 *	Get the SecTentRoleMemb Factory interface for the schema.
	 *
	 *	@return	The SecTentRoleMemb Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecTentRoleMembFactory getFactorySecTentRoleMemb();

	/**
	 *	Get the SecUser Table interface for the schema.
	 *
	 *	@return	The SecUser Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserTable getTableSecUser();

	/**
	 *	Get the SecUser Factory interface for the schema.
	 *
	 *	@return	The SecUser Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserFactory getFactorySecUser();

	/**
	 *	Get the SecUserEMConf Table interface for the schema.
	 *
	 *	@return	The SecUserEMConf Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserEMConfTable getTableSecUserEMConf();

	/**
	 *	Get the SecUserEMConf Factory interface for the schema.
	 *
	 *	@return	The SecUserEMConf Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserEMConfFactory getFactorySecUserEMConf();

	/**
	 *	Get the SecUserPWHistory Table interface for the schema.
	 *
	 *	@return	The SecUserPWHistory Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserPWHistoryTable getTableSecUserPWHistory();

	/**
	 *	Get the SecUserPWHistory Factory interface for the schema.
	 *
	 *	@return	The SecUserPWHistory Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserPWHistoryFactory getFactorySecUserPWHistory();

	/**
	 *	Get the SecUserPWReset Table interface for the schema.
	 *
	 *	@return	The SecUserPWReset Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserPWResetTable getTableSecUserPWReset();

	/**
	 *	Get the SecUserPWReset Factory interface for the schema.
	 *
	 *	@return	The SecUserPWReset Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserPWResetFactory getFactorySecUserPWReset();

	/**
	 *	Get the SecUserPassword Table interface for the schema.
	 *
	 *	@return	The SecUserPassword Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserPasswordTable getTableSecUserPassword();

	/**
	 *	Get the SecUserPassword Factory interface for the schema.
	 *
	 *	@return	The SecUserPassword Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSecUserPasswordFactory getFactorySecUserPassword();

	/**
	 *	Get the ServerListFunc Table interface for the schema.
	 *
	 *	@return	The ServerListFunc Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamServerListFuncTable getTableServerListFunc();

	/**
	 *	Get the ServerListFunc Factory interface for the schema.
	 *
	 *	@return	The ServerListFunc Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamServerListFuncFactory getFactoryServerListFunc();

	/**
	 *	Get the ServerMethod Table interface for the schema.
	 *
	 *	@return	The ServerMethod Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamServerMethodTable getTableServerMethod();

	/**
	 *	Get the ServerMethod Factory interface for the schema.
	 *
	 *	@return	The ServerMethod Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamServerMethodFactory getFactoryServerMethod();

	/**
	 *	Get the ServerObjFunc Table interface for the schema.
	 *
	 *	@return	The ServerObjFunc Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamServerObjFuncTable getTableServerObjFunc();

	/**
	 *	Get the ServerObjFunc Factory interface for the schema.
	 *
	 *	@return	The ServerObjFunc Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamServerObjFuncFactory getFactoryServerObjFunc();

	/**
	 *	Get the ServerProc Table interface for the schema.
	 *
	 *	@return	The ServerProc Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamServerProcTable getTableServerProc();

	/**
	 *	Get the ServerProc Factory interface for the schema.
	 *
	 *	@return	The ServerProc Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamServerProcFactory getFactoryServerProc();

	/**
	 *	Get the StringCol Table interface for the schema.
	 *
	 *	@return	The StringCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamStringColTable getTableStringCol();

	/**
	 *	Get the StringCol Factory interface for the schema.
	 *
	 *	@return	The StringCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamStringColFactory getFactoryStringCol();

	/**
	 *	Get the StringDef Table interface for the schema.
	 *
	 *	@return	The StringDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamStringDefTable getTableStringDef();

	/**
	 *	Get the StringDef Factory interface for the schema.
	 *
	 *	@return	The StringDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamStringDefFactory getFactoryStringDef();

	/**
	 *	Get the StringType Table interface for the schema.
	 *
	 *	@return	The StringType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamStringTypeTable getTableStringType();

	/**
	 *	Get the StringType Factory interface for the schema.
	 *
	 *	@return	The StringType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamStringTypeFactory getFactoryStringType();

	/**
	 *	Get the SubProject Table interface for the schema.
	 *
	 *	@return	The SubProject Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntSubProjectTable getTableSubProject();

	/**
	 *	Get the SubProject Factory interface for the schema.
	 *
	 *	@return	The SubProject Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntSubProjectFactory getFactorySubProject();

	/**
	 *	Get the SysCluster Table interface for the schema.
	 *
	 *	@return	The SysCluster Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSysClusterTable getTableSysCluster();

	/**
	 *	Get the SysCluster Factory interface for the schema.
	 *
	 *	@return	The SysCluster Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecSysClusterFactory getFactorySysCluster();

	/**
	 *	Get the TZDateCol Table interface for the schema.
	 *
	 *	@return	The TZDateCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZDateColTable getTableTZDateCol();

	/**
	 *	Get the TZDateCol Factory interface for the schema.
	 *
	 *	@return	The TZDateCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZDateColFactory getFactoryTZDateCol();

	/**
	 *	Get the TZDateDef Table interface for the schema.
	 *
	 *	@return	The TZDateDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZDateDefTable getTableTZDateDef();

	/**
	 *	Get the TZDateDef Factory interface for the schema.
	 *
	 *	@return	The TZDateDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZDateDefFactory getFactoryTZDateDef();

	/**
	 *	Get the TZDateType Table interface for the schema.
	 *
	 *	@return	The TZDateType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZDateTypeTable getTableTZDateType();

	/**
	 *	Get the TZDateType Factory interface for the schema.
	 *
	 *	@return	The TZDateType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZDateTypeFactory getFactoryTZDateType();

	/**
	 *	Get the TZTimeCol Table interface for the schema.
	 *
	 *	@return	The TZTimeCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimeColTable getTableTZTimeCol();

	/**
	 *	Get the TZTimeCol Factory interface for the schema.
	 *
	 *	@return	The TZTimeCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimeColFactory getFactoryTZTimeCol();

	/**
	 *	Get the TZTimeDef Table interface for the schema.
	 *
	 *	@return	The TZTimeDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimeDefTable getTableTZTimeDef();

	/**
	 *	Get the TZTimeDef Factory interface for the schema.
	 *
	 *	@return	The TZTimeDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimeDefFactory getFactoryTZTimeDef();

	/**
	 *	Get the TZTimeType Table interface for the schema.
	 *
	 *	@return	The TZTimeType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimeTypeTable getTableTZTimeType();

	/**
	 *	Get the TZTimeType Factory interface for the schema.
	 *
	 *	@return	The TZTimeType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimeTypeFactory getFactoryTZTimeType();

	/**
	 *	Get the TZTimestampCol Table interface for the schema.
	 *
	 *	@return	The TZTimestampCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimestampColTable getTableTZTimestampCol();

	/**
	 *	Get the TZTimestampCol Factory interface for the schema.
	 *
	 *	@return	The TZTimestampCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimestampColFactory getFactoryTZTimestampCol();

	/**
	 *	Get the TZTimestampDef Table interface for the schema.
	 *
	 *	@return	The TZTimestampDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimestampDefTable getTableTZTimestampDef();

	/**
	 *	Get the TZTimestampDef Factory interface for the schema.
	 *
	 *	@return	The TZTimestampDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimestampDefFactory getFactoryTZTimestampDef();

	/**
	 *	Get the TZTimestampType Table interface for the schema.
	 *
	 *	@return	The TZTimestampType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimestampTypeTable getTableTZTimestampType();

	/**
	 *	Get the TZTimestampType Factory interface for the schema.
	 *
	 *	@return	The TZTimestampType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTZTimestampTypeFactory getFactoryTZTimestampType();

	/**
	 *	Get the Table Table interface for the schema.
	 *
	 *	@return	The Table Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTableTable getTableTable();

	/**
	 *	Get the Table Factory interface for the schema.
	 *
	 *	@return	The Table Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTableFactory getFactoryTable();

	/**
	 *	Get the TableCol Table interface for the schema.
	 *
	 *	@return	The TableCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTableColTable getTableTableCol();

	/**
	 *	Get the TableCol Factory interface for the schema.
	 *
	 *	@return	The TableCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTableColFactory getFactoryTableCol();

	/**
	 *	Get the TableTweak Table interface for the schema.
	 *
	 *	@return	The TableTweak Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTableTweakTable getTableTableTweak();

	/**
	 *	Get the TableTweak Factory interface for the schema.
	 *
	 *	@return	The TableTweak Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTableTweakFactory getFactoryTableTweak();

	/**
	 *	Get the Tenant Table interface for the schema.
	 *
	 *	@return	The Tenant Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecTenantTable getTableTenant();

	/**
	 *	Get the Tenant Factory interface for the schema.
	 *
	 *	@return	The Tenant Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFSecTenantFactory getFactoryTenant();

	/**
	 *	Get the TextCol Table interface for the schema.
	 *
	 *	@return	The TextCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTextColTable getTableTextCol();

	/**
	 *	Get the TextCol Factory interface for the schema.
	 *
	 *	@return	The TextCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTextColFactory getFactoryTextCol();

	/**
	 *	Get the TextDef Table interface for the schema.
	 *
	 *	@return	The TextDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTextDefTable getTableTextDef();

	/**
	 *	Get the TextDef Factory interface for the schema.
	 *
	 *	@return	The TextDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTextDefFactory getFactoryTextDef();

	/**
	 *	Get the TextType Table interface for the schema.
	 *
	 *	@return	The TextType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTextTypeTable getTableTextType();

	/**
	 *	Get the TextType Factory interface for the schema.
	 *
	 *	@return	The TextType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTextTypeFactory getFactoryTextType();

	/**
	 *	Get the TimeCol Table interface for the schema.
	 *
	 *	@return	The TimeCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimeColTable getTableTimeCol();

	/**
	 *	Get the TimeCol Factory interface for the schema.
	 *
	 *	@return	The TimeCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimeColFactory getFactoryTimeCol();

	/**
	 *	Get the TimeDef Table interface for the schema.
	 *
	 *	@return	The TimeDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimeDefTable getTableTimeDef();

	/**
	 *	Get the TimeDef Factory interface for the schema.
	 *
	 *	@return	The TimeDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimeDefFactory getFactoryTimeDef();

	/**
	 *	Get the TimeType Table interface for the schema.
	 *
	 *	@return	The TimeType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimeTypeTable getTableTimeType();

	/**
	 *	Get the TimeType Factory interface for the schema.
	 *
	 *	@return	The TimeType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimeTypeFactory getFactoryTimeType();

	/**
	 *	Get the TimestampCol Table interface for the schema.
	 *
	 *	@return	The TimestampCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimestampColTable getTableTimestampCol();

	/**
	 *	Get the TimestampCol Factory interface for the schema.
	 *
	 *	@return	The TimestampCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimestampColFactory getFactoryTimestampCol();

	/**
	 *	Get the TimestampDef Table interface for the schema.
	 *
	 *	@return	The TimestampDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimestampDefTable getTableTimestampDef();

	/**
	 *	Get the TimestampDef Factory interface for the schema.
	 *
	 *	@return	The TimestampDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimestampDefFactory getFactoryTimestampDef();

	/**
	 *	Get the TimestampType Table interface for the schema.
	 *
	 *	@return	The TimestampType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimestampTypeTable getTableTimestampType();

	/**
	 *	Get the TimestampType Factory interface for the schema.
	 *
	 *	@return	The TimestampType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTimestampTypeFactory getFactoryTimestampType();

	/**
	 *	Get the Tld Table interface for the schema.
	 *
	 *	@return	The Tld Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntTldTable getTableTld();

	/**
	 *	Get the Tld Factory interface for the schema.
	 *
	 *	@return	The Tld Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntTldFactory getFactoryTld();

	/**
	 *	Get the TokenCol Table interface for the schema.
	 *
	 *	@return	The TokenCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTokenColTable getTableTokenCol();

	/**
	 *	Get the TokenCol Factory interface for the schema.
	 *
	 *	@return	The TokenCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTokenColFactory getFactoryTokenCol();

	/**
	 *	Get the TokenDef Table interface for the schema.
	 *
	 *	@return	The TokenDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTokenDefTable getTableTokenDef();

	/**
	 *	Get the TokenDef Factory interface for the schema.
	 *
	 *	@return	The TokenDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTokenDefFactory getFactoryTokenDef();

	/**
	 *	Get the TokenType Table interface for the schema.
	 *
	 *	@return	The TokenType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTokenTypeTable getTableTokenType();

	/**
	 *	Get the TokenType Factory interface for the schema.
	 *
	 *	@return	The TokenType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTokenTypeFactory getFactoryTokenType();

	/**
	 *	Get the TopDomain Table interface for the schema.
	 *
	 *	@return	The TopDomain Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntTopDomainTable getTableTopDomain();

	/**
	 *	Get the TopDomain Factory interface for the schema.
	 *
	 *	@return	The TopDomain Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntTopDomainFactory getFactoryTopDomain();

	/**
	 *	Get the TopProject Table interface for the schema.
	 *
	 *	@return	The TopProject Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntTopProjectTable getTableTopProject();

	/**
	 *	Get the TopProject Factory interface for the schema.
	 *
	 *	@return	The TopProject Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntTopProjectFactory getFactoryTopProject();

	/**
	 *	Get the Tweak Table interface for the schema.
	 *
	 *	@return	The Tweak Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTweakTable getTableTweak();

	/**
	 *	Get the Tweak Factory interface for the schema.
	 *
	 *	@return	The Tweak Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamTweakFactory getFactoryTweak();

	/**
	 *	Get the UInt16Col Table interface for the schema.
	 *
	 *	@return	The UInt16Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt16ColTable getTableUInt16Col();

	/**
	 *	Get the UInt16Col Factory interface for the schema.
	 *
	 *	@return	The UInt16Col Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt16ColFactory getFactoryUInt16Col();

	/**
	 *	Get the UInt16Def Table interface for the schema.
	 *
	 *	@return	The UInt16Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt16DefTable getTableUInt16Def();

	/**
	 *	Get the UInt16Def Factory interface for the schema.
	 *
	 *	@return	The UInt16Def Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt16DefFactory getFactoryUInt16Def();

	/**
	 *	Get the UInt16Type Table interface for the schema.
	 *
	 *	@return	The UInt16Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt16TypeTable getTableUInt16Type();

	/**
	 *	Get the UInt16Type Factory interface for the schema.
	 *
	 *	@return	The UInt16Type Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt16TypeFactory getFactoryUInt16Type();

	/**
	 *	Get the UInt32Col Table interface for the schema.
	 *
	 *	@return	The UInt32Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt32ColTable getTableUInt32Col();

	/**
	 *	Get the UInt32Col Factory interface for the schema.
	 *
	 *	@return	The UInt32Col Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt32ColFactory getFactoryUInt32Col();

	/**
	 *	Get the UInt32Def Table interface for the schema.
	 *
	 *	@return	The UInt32Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt32DefTable getTableUInt32Def();

	/**
	 *	Get the UInt32Def Factory interface for the schema.
	 *
	 *	@return	The UInt32Def Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt32DefFactory getFactoryUInt32Def();

	/**
	 *	Get the UInt32Type Table interface for the schema.
	 *
	 *	@return	The UInt32Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt32TypeTable getTableUInt32Type();

	/**
	 *	Get the UInt32Type Factory interface for the schema.
	 *
	 *	@return	The UInt32Type Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt32TypeFactory getFactoryUInt32Type();

	/**
	 *	Get the UInt64Col Table interface for the schema.
	 *
	 *	@return	The UInt64Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt64ColTable getTableUInt64Col();

	/**
	 *	Get the UInt64Col Factory interface for the schema.
	 *
	 *	@return	The UInt64Col Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt64ColFactory getFactoryUInt64Col();

	/**
	 *	Get the UInt64Def Table interface for the schema.
	 *
	 *	@return	The UInt64Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt64DefTable getTableUInt64Def();

	/**
	 *	Get the UInt64Def Factory interface for the schema.
	 *
	 *	@return	The UInt64Def Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt64DefFactory getFactoryUInt64Def();

	/**
	 *	Get the UInt64Type Table interface for the schema.
	 *
	 *	@return	The UInt64Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt64TypeTable getTableUInt64Type();

	/**
	 *	Get the UInt64Type Factory interface for the schema.
	 *
	 *	@return	The UInt64Type Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUInt64TypeFactory getFactoryUInt64Type();

	/**
	 *	Get the URLProtocol Table interface for the schema.
	 *
	 *	@return	The URLProtocol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntURLProtocolTable getTableURLProtocol();

	/**
	 *	Get the URLProtocol Factory interface for the schema.
	 *
	 *	@return	The URLProtocol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFIntURLProtocolFactory getFactoryURLProtocol();

	/**
	 *	Get the Uuid6Col Table interface for the schema.
	 *
	 *	@return	The Uuid6Col Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuid6ColTable getTableUuid6Col();

	/**
	 *	Get the Uuid6Col Factory interface for the schema.
	 *
	 *	@return	The Uuid6Col Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuid6ColFactory getFactoryUuid6Col();

	/**
	 *	Get the Uuid6Def Table interface for the schema.
	 *
	 *	@return	The Uuid6Def Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuid6DefTable getTableUuid6Def();

	/**
	 *	Get the Uuid6Def Factory interface for the schema.
	 *
	 *	@return	The Uuid6Def Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuid6DefFactory getFactoryUuid6Def();

	/**
	 *	Get the Uuid6Gen Table interface for the schema.
	 *
	 *	@return	The Uuid6Gen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuid6GenTable getTableUuid6Gen();

	/**
	 *	Get the Uuid6Gen Factory interface for the schema.
	 *
	 *	@return	The Uuid6Gen Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuid6GenFactory getFactoryUuid6Gen();

	/**
	 *	Get the Uuid6Type Table interface for the schema.
	 *
	 *	@return	The Uuid6Type Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuid6TypeTable getTableUuid6Type();

	/**
	 *	Get the Uuid6Type Factory interface for the schema.
	 *
	 *	@return	The Uuid6Type Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuid6TypeFactory getFactoryUuid6Type();

	/**
	 *	Get the UuidCol Table interface for the schema.
	 *
	 *	@return	The UuidCol Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuidColTable getTableUuidCol();

	/**
	 *	Get the UuidCol Factory interface for the schema.
	 *
	 *	@return	The UuidCol Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuidColFactory getFactoryUuidCol();

	/**
	 *	Get the UuidDef Table interface for the schema.
	 *
	 *	@return	The UuidDef Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuidDefTable getTableUuidDef();

	/**
	 *	Get the UuidDef Factory interface for the schema.
	 *
	 *	@return	The UuidDef Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuidDefFactory getFactoryUuidDef();

	/**
	 *	Get the UuidGen Table interface for the schema.
	 *
	 *	@return	The UuidGen Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuidGenTable getTableUuidGen();

	/**
	 *	Get the UuidGen Factory interface for the schema.
	 *
	 *	@return	The UuidGen Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuidGenFactory getFactoryUuidGen();

	/**
	 *	Get the UuidType Table interface for the schema.
	 *
	 *	@return	The UuidType Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuidTypeTable getTableUuidType();

	/**
	 *	Get the UuidType Factory interface for the schema.
	 *
	 *	@return	The UuidType Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamUuidTypeFactory getFactoryUuidType();

	/**
	 *	Get the Value Table interface for the schema.
	 *
	 *	@return	The Value Table interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamValueTable getTableValue();

	/**
	 *	Get the Value Factory interface for the schema.
	 *
	 *	@return	The Value Factory interface for the schema.
	 *
	 *	@throws CFLibNotSupportedException thrown by client-side implementations.
	 */
	public ICFBamValueFactory getFactoryValue();

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

	public void bootstrapSchema(CFSecTableInfo tableInfo[]);
	public void bootstrapAllTablesSecurity(CFLibDbKeyHash256 clusterId, CFLibDbKeyHash256 tenantId, CFSecTableInfo tableInfo[]);
}
