// Description: Java 25 implementation of a Relation buffer

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
import java.io.*;
import java.math.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
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

public class CFBamBuffRelation
	extends CFBamBuffScope
	implements ICFBamRelation
{
	protected CFLibDbKeyHash256 requiredTableId;
	protected CFLibDbKeyHash256 optionalDefSchemaId;
	protected String requiredName;
	protected String optionalShortName;
	protected String optionalLabel;
	protected String optionalShortDescription;
	protected String optionalDescription;
	protected ICFBamPubSchema.RelationTypeEnum requiredRelationType;
	protected String optionalDbName;
	protected String optionalSuffix;
	protected CFLibDbKeyHash256 requiredFromIndexId;
	protected CFLibDbKeyHash256 requiredToTableId;
	protected CFLibDbKeyHash256 requiredToIndexId;
	protected boolean requiredIsRequired;
	protected boolean requiredIsXsdContainer;
	protected boolean requiredIsLateResolver;
	protected boolean requiredAllowAddendum;
	protected CFLibDbKeyHash256 optionalNarrowedId;
	protected ICFBamPubSchema.CodeVisibilityEnum requiredCodeVis;

	public CFBamBuffRelation() {
		super();
		requiredTableId = CFLibDbKeyHash256.fromHex( ICFBamRelation.TABLEID_INIT_VALUE.toString() );
		optionalDefSchemaId = CFLibDbKeyHash256.nullGet();
		requiredName = ICFBamRelation.NAME_INIT_VALUE;
		optionalShortName = null;
		optionalLabel = null;
		optionalShortDescription = null;
		optionalDescription = null;
		requiredRelationType = ICFBamRelation.RELATIONTYPE_INIT_VALUE;
		optionalDbName = null;
		optionalSuffix = null;
		requiredFromIndexId = CFLibDbKeyHash256.fromHex( ICFBamRelation.FROMINDEXID_INIT_VALUE.toString() );
		requiredToTableId = CFLibDbKeyHash256.fromHex( ICFBamRelation.TOTABLEID_INIT_VALUE.toString() );
		requiredToIndexId = CFLibDbKeyHash256.fromHex( ICFBamRelation.TOINDEXID_INIT_VALUE.toString() );
		requiredIsRequired = ICFBamRelation.ISREQUIRED_INIT_VALUE;
		requiredIsXsdContainer = ICFBamRelation.ISXSDCONTAINER_INIT_VALUE;
		requiredIsLateResolver = ICFBamRelation.ISLATERESOLVER_INIT_VALUE;
		requiredAllowAddendum = ICFBamRelation.ALLOWADDENDUM_INIT_VALUE;
		optionalNarrowedId = CFLibDbKeyHash256.nullGet();
		requiredCodeVis = ICFBamRelation.CODEVIS_INIT_VALUE;
	}

	@Override
	public int getClassCode() {
		return( ICFBamRelation.CLASS_CODE );
	}

	@Override
	public ICFBamSchemaDef getOptionalLookupDefSchema() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupDefSchema", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamSchemaDefTable targetTable = targetBackingSchema.getTableSchemaDef();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupDefSchema", 0, "ICFBamSchema.getBackingCFBam().getTableSchemaDef()");
		}
		ICFBamSchemaDef targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getOptionalDefSchemaId());
		return(targetRec);
	}
	@Override
	public void setOptionalLookupDefSchema(ICFBamSchemaDef argObj) {
		if(argObj == null) {
			optionalDefSchemaId = null;
		}
		else {
			optionalDefSchemaId = argObj.getRequiredId();
		}
	}

	@Override
	public void setOptionalLookupDefSchema(CFLibDbKeyHash256 argDefSchemaId) {
		optionalDefSchemaId = argDefSchemaId;
	}

	@Override
	public ICFBamTable getRequiredContainerFromTable() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerFromTable", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamTableTable targetTable = targetBackingSchema.getTableTable();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerFromTable", 0, "ICFBamSchema.getBackingCFBam().getTableTable()");
		}
		ICFBamTable targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredTableId());
		return(targetRec);
	}
	@Override
	public void setRequiredContainerFromTable(ICFBamTable argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerFromTable", 1, "argObj");
		}
		else {
			requiredTableId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredContainerFromTable(CFLibDbKeyHash256 argTableId) {
		requiredTableId = argTableId;
	}

	@Override
	public ICFBamIndex getRequiredLookupFromIndex() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupFromIndex", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamIndexTable targetTable = targetBackingSchema.getTableIndex();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupFromIndex", 0, "ICFBamSchema.getBackingCFBam().getTableIndex()");
		}
		ICFBamIndex targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredFromIndexId());
		return(targetRec);
	}
	@Override
	public void setRequiredLookupFromIndex(ICFBamIndex argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setLookupFromIndex", 1, "argObj");
		}
		else {
			requiredFromIndexId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredLookupFromIndex(CFLibDbKeyHash256 argFromIndexId) {
		requiredFromIndexId = argFromIndexId;
	}

	@Override
	public ICFBamTable getRequiredLookupToTable() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupToTable", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamTableTable targetTable = targetBackingSchema.getTableTable();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupToTable", 0, "ICFBamSchema.getBackingCFBam().getTableTable()");
		}
		ICFBamTable targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredToTableId());
		return(targetRec);
	}
	@Override
	public void setRequiredLookupToTable(ICFBamTable argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setLookupToTable", 1, "argObj");
		}
		else {
			requiredToTableId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredLookupToTable(CFLibDbKeyHash256 argToTableId) {
		requiredToTableId = argToTableId;
	}

	@Override
	public ICFBamIndex getRequiredLookupToIndex() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupToIndex", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamIndexTable targetTable = targetBackingSchema.getTableIndex();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupToIndex", 0, "ICFBamSchema.getBackingCFBam().getTableIndex()");
		}
		ICFBamIndex targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredToIndexId());
		return(targetRec);
	}
	@Override
	public void setRequiredLookupToIndex(ICFBamIndex argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setLookupToIndex", 1, "argObj");
		}
		else {
			requiredToIndexId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredLookupToIndex(CFLibDbKeyHash256 argToIndexId) {
		requiredToIndexId = argToIndexId;
	}

	@Override
	public ICFBamRelation getOptionalLookupNarrowed() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupNarrowed", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamRelationTable targetTable = targetBackingSchema.getTableRelation();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupNarrowed", 0, "ICFBamSchema.getBackingCFBam().getTableRelation()");
		}
		ICFBamRelation targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getOptionalNarrowedId());
		return(targetRec);
	}
	@Override
	public void setOptionalLookupNarrowed(ICFBamRelation argObj) {
		if(argObj == null) {
			optionalNarrowedId = null;
		}
		else {
			optionalNarrowedId = argObj.getRequiredId();
		}
	}

	@Override
	public void setOptionalLookupNarrowed(CFLibDbKeyHash256 argNarrowedId) {
		optionalNarrowedId = argNarrowedId;
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTableId() {
		return( requiredTableId );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( optionalDefSchemaId );
	}

	@Override
	public String getRequiredName() {
		return( requiredName );
	}

	@Override
	public void setRequiredName( String value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredName",
				1,
				"value" );
		}
		else if( value.length() > 192 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setRequiredName",
				1,
				"value.length()",
				value.length(),
				192 );
		}
		requiredName = value;
	}

	@Override
	public String getOptionalShortName() {
		return( optionalShortName );
	}

	@Override
	public void setOptionalShortName( String value ) {
		if( value != null && value.length() > 16 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalShortName",
				1,
				"value.length()",
				value.length(),
				16 );
		}
		optionalShortName = value;
	}

	@Override
	public String getOptionalLabel() {
		return( optionalLabel );
	}

	@Override
	public void setOptionalLabel( String value ) {
		if( value != null && value.length() > 64 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalLabel",
				1,
				"value.length()",
				value.length(),
				64 );
		}
		optionalLabel = value;
	}

	@Override
	public String getOptionalShortDescription() {
		return( optionalShortDescription );
	}

	@Override
	public void setOptionalShortDescription( String value ) {
		if( value != null && value.length() > 128 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalShortDescription",
				1,
				"value.length()",
				value.length(),
				128 );
		}
		optionalShortDescription = value;
	}

	@Override
	public String getOptionalDescription() {
		return( optionalDescription );
	}

	@Override
	public void setOptionalDescription( String value ) {
		if( value != null && value.length() > 1023 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalDescription",
				1,
				"value.length()",
				value.length(),
				1023 );
		}
		optionalDescription = value;
	}

	@Override
	public ICFBamPubSchema.RelationTypeEnum getRequiredRelationType() {
		return( requiredRelationType );
	}

	@Override
	public void setRequiredRelationType( ICFBamPubSchema.RelationTypeEnum value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredRelationType",
				1,
				"value" );
		}
		requiredRelationType = value;
	}

	@Override
	public String getOptionalDbName() {
		return( optionalDbName );
	}

	@Override
	public void setOptionalDbName( String value ) {
		if( value != null && value.length() > 32 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalDbName",
				1,
				"value.length()",
				value.length(),
				32 );
		}
		optionalDbName = value;
	}

	@Override
	public String getOptionalSuffix() {
		return( optionalSuffix );
	}

	@Override
	public void setOptionalSuffix( String value ) {
		if( value != null && value.length() > 16 ) {
			throw new CFLibArgumentOverflowException( getClass(),
				"setOptionalSuffix",
				1,
				"value.length()",
				value.length(),
				16 );
		}
		optionalSuffix = value;
	}

	@Override
	public CFLibDbKeyHash256 getRequiredFromIndexId() {
		return( requiredFromIndexId );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredToTableId() {
		return( requiredToTableId );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredToIndexId() {
		return( requiredToIndexId );
	}

	@Override
	public boolean getRequiredIsRequired() {
		return( requiredIsRequired );
	}

	@Override
	public void setRequiredIsRequired( boolean value ) {
		requiredIsRequired = value;
	}

	@Override
	public boolean getRequiredIsXsdContainer() {
		return( requiredIsXsdContainer );
	}

	@Override
	public void setRequiredIsXsdContainer( boolean value ) {
		requiredIsXsdContainer = value;
	}

	@Override
	public boolean getRequiredIsLateResolver() {
		return( requiredIsLateResolver );
	}

	@Override
	public void setRequiredIsLateResolver( boolean value ) {
		requiredIsLateResolver = value;
	}

	@Override
	public boolean getRequiredAllowAddendum() {
		return( requiredAllowAddendum );
	}

	@Override
	public void setRequiredAllowAddendum( boolean value ) {
		requiredAllowAddendum = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNarrowedId() {
		return( optionalNarrowedId );
	}

	@Override
	public ICFBamPubSchema.CodeVisibilityEnum getRequiredCodeVis() {
		return( requiredCodeVis );
	}

	@Override
	public void setRequiredCodeVis( ICFBamPubSchema.CodeVisibilityEnum value ) {
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredCodeVis",
				1,
				"value" );
		}
		requiredCodeVis = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFBamRelation rhs ) {
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					if( ! getOptionalShortName().equals( rhs.getOptionalShortName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( false );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					if( ! getOptionalLabel().equals( rhs.getOptionalLabel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( false );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					if( ! getOptionalShortDescription().equals( rhs.getOptionalShortDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( false );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					if( ! getOptionalDescription().equals( rhs.getOptionalDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( false );
				}
			}
			if( getRequiredRelationType() != null ) {
				if( rhs.getRequiredRelationType() != null ) {
					if( ! getRequiredRelationType().equals( rhs.getRequiredRelationType() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationType() != null ) {
					return( false );
				}
			}
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					if( ! getOptionalDbName().equals( rhs.getOptionalDbName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( false );
				}
			}
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					if( ! getOptionalSuffix().equals( rhs.getOptionalSuffix() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( false );
				}
			}
			if( getRequiredFromIndexId() != null ) {
				if( rhs.getRequiredFromIndexId() != null ) {
					if( ! getRequiredFromIndexId().equals( rhs.getRequiredFromIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredToTableId() != null ) {
				if( rhs.getRequiredToTableId() != null ) {
					if( ! getRequiredToTableId().equals( rhs.getRequiredToTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredToIndexId() != null ) {
				if( rhs.getRequiredToIndexId() != null ) {
					if( ! getRequiredToIndexId().equals( rhs.getRequiredToIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsRequired() != rhs.getRequiredIsRequired() ) {
				return( false );
			}
			if( getRequiredIsXsdContainer() != rhs.getRequiredIsXsdContainer() ) {
				return( false );
			}
			if( getRequiredIsLateResolver() != rhs.getRequiredIsLateResolver() ) {
				return( false );
			}
			if( getRequiredAllowAddendum() != rhs.getRequiredAllowAddendum() ) {
				return( false );
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					if( ! getOptionalNarrowedId().equals( rhs.getOptionalNarrowedId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( false );
				}
			}
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationH ) {
			ICFBamRelationH rhs = (ICFBamRelationH)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					if( ! getOptionalShortName().equals( rhs.getOptionalShortName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( false );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					if( ! getOptionalLabel().equals( rhs.getOptionalLabel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( false );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					if( ! getOptionalShortDescription().equals( rhs.getOptionalShortDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( false );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					if( ! getOptionalDescription().equals( rhs.getOptionalDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( false );
				}
			}
			if( getRequiredRelationType() != null ) {
				if( rhs.getRequiredRelationType() != null ) {
					if( ! getRequiredRelationType().equals( rhs.getRequiredRelationType() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationType() != null ) {
					return( false );
				}
			}
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					if( ! getOptionalDbName().equals( rhs.getOptionalDbName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( false );
				}
			}
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					if( ! getOptionalSuffix().equals( rhs.getOptionalSuffix() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( false );
				}
			}
			if( getRequiredFromIndexId() != null ) {
				if( rhs.getRequiredFromIndexId() != null ) {
					if( ! getRequiredFromIndexId().equals( rhs.getRequiredFromIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredToTableId() != null ) {
				if( rhs.getRequiredToTableId() != null ) {
					if( ! getRequiredToTableId().equals( rhs.getRequiredToTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredToIndexId() != null ) {
				if( rhs.getRequiredToIndexId() != null ) {
					if( ! getRequiredToIndexId().equals( rhs.getRequiredToIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsRequired() != rhs.getRequiredIsRequired() ) {
				return( false );
			}
			if( getRequiredIsXsdContainer() != rhs.getRequiredIsXsdContainer() ) {
				return( false );
			}
			if( getRequiredIsLateResolver() != rhs.getRequiredIsLateResolver() ) {
				return( false );
			}
			if( getRequiredAllowAddendum() != rhs.getRequiredAllowAddendum() ) {
				return( false );
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					if( ! getOptionalNarrowedId().equals( rhs.getOptionalNarrowedId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( false );
				}
			}
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamScopeHPKey ) {
			ICFBamScopeHPKey rhs = (ICFBamScopeHPKey)obj;
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByUNameIdxKey ) {
			ICFBamRelationByUNameIdxKey rhs = (ICFBamRelationByUNameIdxKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByRelTableIdxKey ) {
			ICFBamRelationByRelTableIdxKey rhs = (ICFBamRelationByRelTableIdxKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByRelCodeVisIdxKey ) {
			ICFBamRelationByRelCodeVisIdxKey rhs = (ICFBamRelationByRelCodeVisIdxKey)obj;
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByRelTableCodeVisXKey ) {
			ICFBamRelationByRelTableCodeVisXKey rhs = (ICFBamRelationByRelTableCodeVisXKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByDefSchemaIdxKey ) {
			ICFBamRelationByDefSchemaIdxKey rhs = (ICFBamRelationByDefSchemaIdxKey)obj;
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByFromKeyIdxKey ) {
			ICFBamRelationByFromKeyIdxKey rhs = (ICFBamRelationByFromKeyIdxKey)obj;
			if( getRequiredFromIndexId() != null ) {
				if( rhs.getRequiredFromIndexId() != null ) {
					if( ! getRequiredFromIndexId().equals( rhs.getRequiredFromIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByToTblIdxKey ) {
			ICFBamRelationByToTblIdxKey rhs = (ICFBamRelationByToTblIdxKey)obj;
			if( getRequiredToTableId() != null ) {
				if( rhs.getRequiredToTableId() != null ) {
					if( ! getRequiredToTableId().equals( rhs.getRequiredToTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByToKeyIdxKey ) {
			ICFBamRelationByToKeyIdxKey rhs = (ICFBamRelationByToKeyIdxKey)obj;
			if( getRequiredToIndexId() != null ) {
				if( rhs.getRequiredToIndexId() != null ) {
					if( ! getRequiredToIndexId().equals( rhs.getRequiredToIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByNarrowedIdxKey ) {
			ICFBamRelationByNarrowedIdxKey rhs = (ICFBamRelationByNarrowedIdxKey)obj;
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					if( ! getOptionalNarrowedId().equals( rhs.getOptionalNarrowedId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtRelation rhs ) {
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					if( ! getOptionalShortName().equals( rhs.getOptionalShortName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( false );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					if( ! getOptionalLabel().equals( rhs.getOptionalLabel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( false );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					if( ! getOptionalShortDescription().equals( rhs.getOptionalShortDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( false );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					if( ! getOptionalDescription().equals( rhs.getOptionalDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( false );
				}
			}
			if( getRequiredRelationType() != null ) {
				if( rhs.getRequiredRelationType() != null ) {
					if( ! getRequiredRelationType().equals( rhs.getRequiredRelationType() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationType() != null ) {
					return( false );
				}
			}
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					if( ! getOptionalDbName().equals( rhs.getOptionalDbName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( false );
				}
			}
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					if( ! getOptionalSuffix().equals( rhs.getOptionalSuffix() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( false );
				}
			}
			if( getRequiredFromIndexId() != null ) {
				if( rhs.getRequiredFromIndexId() != null ) {
					if( ! getRequiredFromIndexId().equals( rhs.getRequiredFromIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredToTableId() != null ) {
				if( rhs.getRequiredToTableId() != null ) {
					if( ! getRequiredToTableId().equals( rhs.getRequiredToTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredToIndexId() != null ) {
				if( rhs.getRequiredToIndexId() != null ) {
					if( ! getRequiredToIndexId().equals( rhs.getRequiredToIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsRequired() != rhs.getRequiredIsRequired() ) {
				return( false );
			}
			if( getRequiredIsXsdContainer() != rhs.getRequiredIsXsdContainer() ) {
				return( false );
			}
			if( getRequiredIsLateResolver() != rhs.getRequiredIsLateResolver() ) {
				return( false );
			}
			if( getRequiredAllowAddendum() != rhs.getRequiredAllowAddendum() ) {
				return( false );
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					if( ! getOptionalNarrowedId().equals( rhs.getOptionalNarrowedId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( false );
				}
			}
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtRelationH rhs ) {
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					if( ! getOptionalShortName().equals( rhs.getOptionalShortName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( false );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					if( ! getOptionalLabel().equals( rhs.getOptionalLabel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( false );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					if( ! getOptionalShortDescription().equals( rhs.getOptionalShortDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( false );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					if( ! getOptionalDescription().equals( rhs.getOptionalDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( false );
				}
			}
			if( getRequiredRelationType() != null ) {
				if( rhs.getRequiredRelationType() != null ) {
					if( ! getRequiredRelationType().equals( rhs.getRequiredRelationType() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationType() != null ) {
					return( false );
				}
			}
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					if( ! getOptionalDbName().equals( rhs.getOptionalDbName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( false );
				}
			}
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					if( ! getOptionalSuffix().equals( rhs.getOptionalSuffix() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( false );
				}
			}
			if( getRequiredFromIndexId() != null ) {
				if( rhs.getRequiredFromIndexId() != null ) {
					if( ! getRequiredFromIndexId().equals( rhs.getRequiredFromIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredToTableId() != null ) {
				if( rhs.getRequiredToTableId() != null ) {
					if( ! getRequiredToTableId().equals( rhs.getRequiredToTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredToIndexId() != null ) {
				if( rhs.getRequiredToIndexId() != null ) {
					if( ! getRequiredToIndexId().equals( rhs.getRequiredToIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsRequired() != rhs.getRequiredIsRequired() ) {
				return( false );
			}
			if( getRequiredIsXsdContainer() != rhs.getRequiredIsXsdContainer() ) {
				return( false );
			}
			if( getRequiredIsLateResolver() != rhs.getRequiredIsLateResolver() ) {
				return( false );
			}
			if( getRequiredAllowAddendum() != rhs.getRequiredAllowAddendum() ) {
				return( false );
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					if( ! getOptionalNarrowedId().equals( rhs.getOptionalNarrowedId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( false );
				}
			}
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtScopeHPKey rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByUNameIdxKey ) {
			ICFBamRelationByUNameIdxKey rhs = (ICFBamRelationByUNameIdxKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByRelTableIdxKey ) {
			ICFBamRelationByRelTableIdxKey rhs = (ICFBamRelationByRelTableIdxKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByRelCodeVisIdxKey ) {
			ICFBamRelationByRelCodeVisIdxKey rhs = (ICFBamRelationByRelCodeVisIdxKey)obj;
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByRelTableCodeVisXKey ) {
			ICFBamRelationByRelTableCodeVisXKey rhs = (ICFBamRelationByRelTableCodeVisXKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByDefSchemaIdxKey ) {
			ICFBamRelationByDefSchemaIdxKey rhs = (ICFBamRelationByDefSchemaIdxKey)obj;
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByFromKeyIdxKey ) {
			ICFBamRelationByFromKeyIdxKey rhs = (ICFBamRelationByFromKeyIdxKey)obj;
			if( getRequiredFromIndexId() != null ) {
				if( rhs.getRequiredFromIndexId() != null ) {
					if( ! getRequiredFromIndexId().equals( rhs.getRequiredFromIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByToTblIdxKey ) {
			ICFBamRelationByToTblIdxKey rhs = (ICFBamRelationByToTblIdxKey)obj;
			if( getRequiredToTableId() != null ) {
				if( rhs.getRequiredToTableId() != null ) {
					if( ! getRequiredToTableId().equals( rhs.getRequiredToTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByToKeyIdxKey ) {
			ICFBamRelationByToKeyIdxKey rhs = (ICFBamRelationByToKeyIdxKey)obj;
			if( getRequiredToIndexId() != null ) {
				if( rhs.getRequiredToIndexId() != null ) {
					if( ! getRequiredToIndexId().equals( rhs.getRequiredToIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByNarrowedIdxKey ) {
			ICFBamRelationByNarrowedIdxKey rhs = (ICFBamRelationByNarrowedIdxKey)obj;
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					if( ! getOptionalNarrowedId().equals( rhs.getOptionalNarrowedId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubRelation rhs ) {
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					if( ! getOptionalShortName().equals( rhs.getOptionalShortName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( false );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					if( ! getOptionalLabel().equals( rhs.getOptionalLabel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( false );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					if( ! getOptionalShortDescription().equals( rhs.getOptionalShortDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( false );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					if( ! getOptionalDescription().equals( rhs.getOptionalDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( false );
				}
			}
			if( getRequiredRelationType() != null ) {
				if( rhs.getRequiredRelationType() != null ) {
					if( ! getRequiredRelationType().equals( rhs.getRequiredRelationType() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationType() != null ) {
					return( false );
				}
			}
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					if( ! getOptionalDbName().equals( rhs.getOptionalDbName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( false );
				}
			}
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					if( ! getOptionalSuffix().equals( rhs.getOptionalSuffix() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( false );
				}
			}
			if( getRequiredFromIndexId() != null ) {
				if( rhs.getRequiredFromIndexId() != null ) {
					if( ! getRequiredFromIndexId().equals( rhs.getRequiredFromIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredToTableId() != null ) {
				if( rhs.getRequiredToTableId() != null ) {
					if( ! getRequiredToTableId().equals( rhs.getRequiredToTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredToIndexId() != null ) {
				if( rhs.getRequiredToIndexId() != null ) {
					if( ! getRequiredToIndexId().equals( rhs.getRequiredToIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsRequired() != rhs.getRequiredIsRequired() ) {
				return( false );
			}
			if( getRequiredIsXsdContainer() != rhs.getRequiredIsXsdContainer() ) {
				return( false );
			}
			if( getRequiredIsLateResolver() != rhs.getRequiredIsLateResolver() ) {
				return( false );
			}
			if( getRequiredAllowAddendum() != rhs.getRequiredAllowAddendum() ) {
				return( false );
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					if( ! getOptionalNarrowedId().equals( rhs.getOptionalNarrowedId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( false );
				}
			}
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubRelationH rhs ) {
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					if( ! getOptionalShortName().equals( rhs.getOptionalShortName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( false );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					if( ! getOptionalLabel().equals( rhs.getOptionalLabel() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( false );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					if( ! getOptionalShortDescription().equals( rhs.getOptionalShortDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( false );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					if( ! getOptionalDescription().equals( rhs.getOptionalDescription() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( false );
				}
			}
			if( getRequiredRelationType() != null ) {
				if( rhs.getRequiredRelationType() != null ) {
					if( ! getRequiredRelationType().equals( rhs.getRequiredRelationType() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredRelationType() != null ) {
					return( false );
				}
			}
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					if( ! getOptionalDbName().equals( rhs.getOptionalDbName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( false );
				}
			}
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					if( ! getOptionalSuffix().equals( rhs.getOptionalSuffix() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( false );
				}
			}
			if( getRequiredFromIndexId() != null ) {
				if( rhs.getRequiredFromIndexId() != null ) {
					if( ! getRequiredFromIndexId().equals( rhs.getRequiredFromIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredToTableId() != null ) {
				if( rhs.getRequiredToTableId() != null ) {
					if( ! getRequiredToTableId().equals( rhs.getRequiredToTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredToIndexId() != null ) {
				if( rhs.getRequiredToIndexId() != null ) {
					if( ! getRequiredToIndexId().equals( rhs.getRequiredToIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToIndexId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsRequired() != rhs.getRequiredIsRequired() ) {
				return( false );
			}
			if( getRequiredIsXsdContainer() != rhs.getRequiredIsXsdContainer() ) {
				return( false );
			}
			if( getRequiredIsLateResolver() != rhs.getRequiredIsLateResolver() ) {
				return( false );
			}
			if( getRequiredAllowAddendum() != rhs.getRequiredAllowAddendum() ) {
				return( false );
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					if( ! getOptionalNarrowedId().equals( rhs.getOptionalNarrowedId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( false );
				}
			}
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubScopeHPKey rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByUNameIdxKey ) {
			ICFBamRelationByUNameIdxKey rhs = (ICFBamRelationByUNameIdxKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredName() != null ) {
				if( rhs.getRequiredName() != null ) {
					if( ! getRequiredName().equals( rhs.getRequiredName() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredName() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByRelTableIdxKey ) {
			ICFBamRelationByRelTableIdxKey rhs = (ICFBamRelationByRelTableIdxKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByRelCodeVisIdxKey ) {
			ICFBamRelationByRelCodeVisIdxKey rhs = (ICFBamRelationByRelCodeVisIdxKey)obj;
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByRelTableCodeVisXKey ) {
			ICFBamRelationByRelTableCodeVisXKey rhs = (ICFBamRelationByRelTableCodeVisXKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			if( getRequiredCodeVis() != null ) {
				if( rhs.getRequiredCodeVis() != null ) {
					if( ! getRequiredCodeVis().equals( rhs.getRequiredCodeVis() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredCodeVis() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByDefSchemaIdxKey ) {
			ICFBamRelationByDefSchemaIdxKey rhs = (ICFBamRelationByDefSchemaIdxKey)obj;
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					if( ! getOptionalDefSchemaId().equals( rhs.getOptionalDefSchemaId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByFromKeyIdxKey ) {
			ICFBamRelationByFromKeyIdxKey rhs = (ICFBamRelationByFromKeyIdxKey)obj;
			if( getRequiredFromIndexId() != null ) {
				if( rhs.getRequiredFromIndexId() != null ) {
					if( ! getRequiredFromIndexId().equals( rhs.getRequiredFromIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredFromIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByToTblIdxKey ) {
			ICFBamRelationByToTblIdxKey rhs = (ICFBamRelationByToTblIdxKey)obj;
			if( getRequiredToTableId() != null ) {
				if( rhs.getRequiredToTableId() != null ) {
					if( ! getRequiredToTableId().equals( rhs.getRequiredToTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByToKeyIdxKey ) {
			ICFBamRelationByToKeyIdxKey rhs = (ICFBamRelationByToKeyIdxKey)obj;
			if( getRequiredToIndexId() != null ) {
				if( rhs.getRequiredToIndexId() != null ) {
					if( ! getRequiredToIndexId().equals( rhs.getRequiredToIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredToIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamRelationByNarrowedIdxKey ) {
			ICFBamRelationByNarrowedIdxKey rhs = (ICFBamRelationByNarrowedIdxKey)obj;
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					if( ! getOptionalNarrowedId().equals( rhs.getOptionalNarrowedId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else {
			boolean retval = super.equals( obj );
			return( retval );
		}
	}

	@Override
	public int hashCode() {
		int hashCode = super.hashCode();
		hashCode = hashCode + getRequiredTableId().hashCode();
		if( getOptionalDefSchemaId() != null ) {
			hashCode = hashCode + getOptionalDefSchemaId().hashCode();
		}
		if( getRequiredName() != null ) {
			hashCode = hashCode + getRequiredName().hashCode();
		}
		if( getOptionalShortName() != null ) {
			hashCode = hashCode + getOptionalShortName().hashCode();
		}
		if( getOptionalLabel() != null ) {
			hashCode = hashCode + getOptionalLabel().hashCode();
		}
		if( getOptionalShortDescription() != null ) {
			hashCode = hashCode + getOptionalShortDescription().hashCode();
		}
		if( getOptionalDescription() != null ) {
			hashCode = hashCode + getOptionalDescription().hashCode();
		}
		hashCode = ( hashCode * 0x10000 ) + getRequiredRelationType().ordinal();
		if( getOptionalDbName() != null ) {
			hashCode = hashCode + getOptionalDbName().hashCode();
		}
		if( getOptionalSuffix() != null ) {
			hashCode = hashCode + getOptionalSuffix().hashCode();
		}
		hashCode = hashCode + getRequiredFromIndexId().hashCode();
		hashCode = hashCode + getRequiredToTableId().hashCode();
		hashCode = hashCode + getRequiredToIndexId().hashCode();
		if( getRequiredIsRequired() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getRequiredIsXsdContainer() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getRequiredIsLateResolver() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getRequiredAllowAddendum() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getOptionalNarrowedId() != null ) {
			hashCode = hashCode + getOptionalNarrowedId().hashCode();
		}
		hashCode = ( hashCode * 0x10000 ) + getRequiredCodeVis().ordinal();
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFBamRelation rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					cmp = getOptionalShortName().compareTo( rhs.getOptionalShortName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					cmp = getOptionalLabel().compareTo( rhs.getOptionalLabel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( -1 );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					cmp = getOptionalShortDescription().compareTo( rhs.getOptionalShortDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					cmp = getOptionalDescription().compareTo( rhs.getOptionalDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( -1 );
				}
			}
			if (getRequiredRelationType() != null) {
				if (rhs.getRequiredRelationType() != null) {
					cmp = getRequiredRelationType().compareTo( rhs.getRequiredRelationType() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationType() != null) {
				return( -1 );
			}
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					cmp = getOptionalDbName().compareTo( rhs.getOptionalDbName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					cmp = getOptionalSuffix().compareTo( rhs.getOptionalSuffix() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( -1 );
				}
			}
			if (getRequiredFromIndexId() != null) {
				if (rhs.getRequiredFromIndexId() != null) {
					cmp = getRequiredFromIndexId().compareTo( rhs.getRequiredFromIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromIndexId() != null) {
				return( -1 );
			}
			if (getRequiredToTableId() != null) {
				if (rhs.getRequiredToTableId() != null) {
					cmp = getRequiredToTableId().compareTo( rhs.getRequiredToTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToTableId() != null) {
				return( -1 );
			}
			if (getRequiredToIndexId() != null) {
				if (rhs.getRequiredToIndexId() != null) {
					cmp = getRequiredToIndexId().compareTo( rhs.getRequiredToIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToIndexId() != null) {
				return( -1 );
			}
			if( getRequiredIsRequired() ) {
				if( ! rhs.getRequiredIsRequired() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsRequired() ) {
					return( -1 );
				}
			}
			if( getRequiredIsXsdContainer() ) {
				if( ! rhs.getRequiredIsXsdContainer() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsXsdContainer() ) {
					return( -1 );
				}
			}
			if( getRequiredIsLateResolver() ) {
				if( ! rhs.getRequiredIsLateResolver() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsLateResolver() ) {
					return( -1 );
				}
			}
			if( getRequiredAllowAddendum() ) {
				if( ! rhs.getRequiredAllowAddendum() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredAllowAddendum() ) {
					return( -1 );
				}
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					cmp = getOptionalNarrowedId().compareTo( rhs.getOptionalNarrowedId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamScopeHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredId() != null) {
				if (rhs.getRequiredId() != null) {
					cmp = getRequiredId().compareTo( rhs.getRequiredId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamRelationH rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					cmp = getOptionalShortName().compareTo( rhs.getOptionalShortName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					cmp = getOptionalLabel().compareTo( rhs.getOptionalLabel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( -1 );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					cmp = getOptionalShortDescription().compareTo( rhs.getOptionalShortDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					cmp = getOptionalDescription().compareTo( rhs.getOptionalDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( -1 );
				}
			}
			if (getRequiredRelationType() != null) {
				if (rhs.getRequiredRelationType() != null) {
					cmp = getRequiredRelationType().compareTo( rhs.getRequiredRelationType() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationType() != null) {
				return( -1 );
			}
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					cmp = getOptionalDbName().compareTo( rhs.getOptionalDbName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					cmp = getOptionalSuffix().compareTo( rhs.getOptionalSuffix() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( -1 );
				}
			}
			if (getRequiredFromIndexId() != null) {
				if (rhs.getRequiredFromIndexId() != null) {
					cmp = getRequiredFromIndexId().compareTo( rhs.getRequiredFromIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromIndexId() != null) {
				return( -1 );
			}
			if (getRequiredToTableId() != null) {
				if (rhs.getRequiredToTableId() != null) {
					cmp = getRequiredToTableId().compareTo( rhs.getRequiredToTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToTableId() != null) {
				return( -1 );
			}
			if (getRequiredToIndexId() != null) {
				if (rhs.getRequiredToIndexId() != null) {
					cmp = getRequiredToIndexId().compareTo( rhs.getRequiredToIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToIndexId() != null) {
				return( -1 );
			}
			if( getRequiredIsRequired() ) {
				if( ! rhs.getRequiredIsRequired() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsRequired() ) {
					return( -1 );
				}
			}
			if( getRequiredIsXsdContainer() ) {
				if( ! rhs.getRequiredIsXsdContainer() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsXsdContainer() ) {
					return( -1 );
				}
			}
			if( getRequiredIsLateResolver() ) {
				if( ! rhs.getRequiredIsLateResolver() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsLateResolver() ) {
					return( -1 );
				}
			}
			if( getRequiredAllowAddendum() ) {
				if( ! rhs.getRequiredAllowAddendum() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredAllowAddendum() ) {
					return( -1 );
				}
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					cmp = getOptionalNarrowedId().compareTo( rhs.getOptionalNarrowedId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamRelationByUNameIdxKey rhs ) {
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamRelationByRelTableIdxKey rhs ) {
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamRelationByRelCodeVisIdxKey rhs ) {
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamRelationByRelTableCodeVisXKey rhs ) {
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamRelationByDefSchemaIdxKey rhs ) {
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamRelationByFromKeyIdxKey rhs ) {
			if (getRequiredFromIndexId() != null) {
				if (rhs.getRequiredFromIndexId() != null) {
					cmp = getRequiredFromIndexId().compareTo( rhs.getRequiredFromIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromIndexId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamRelationByToTblIdxKey rhs ) {
			if (getRequiredToTableId() != null) {
				if (rhs.getRequiredToTableId() != null) {
					cmp = getRequiredToTableId().compareTo( rhs.getRequiredToTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToTableId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamRelationByToKeyIdxKey rhs ) {
			if (getRequiredToIndexId() != null) {
				if (rhs.getRequiredToIndexId() != null) {
					cmp = getRequiredToIndexId().compareTo( rhs.getRequiredToIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToIndexId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamRelationByNarrowedIdxKey rhs ) {
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					cmp = getOptionalNarrowedId().compareTo( rhs.getOptionalNarrowedId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtRelation rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					cmp = getOptionalShortName().compareTo( rhs.getOptionalShortName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					cmp = getOptionalLabel().compareTo( rhs.getOptionalLabel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( -1 );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					cmp = getOptionalShortDescription().compareTo( rhs.getOptionalShortDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					cmp = getOptionalDescription().compareTo( rhs.getOptionalDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( -1 );
				}
			}
			if (getRequiredRelationType() != null) {
				if (rhs.getRequiredRelationType() != null) {
					cmp = getRequiredRelationType().compareTo( rhs.getRequiredRelationType() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationType() != null) {
				return( -1 );
			}
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					cmp = getOptionalDbName().compareTo( rhs.getOptionalDbName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					cmp = getOptionalSuffix().compareTo( rhs.getOptionalSuffix() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( -1 );
				}
			}
			if (getRequiredFromIndexId() != null) {
				if (rhs.getRequiredFromIndexId() != null) {
					cmp = getRequiredFromIndexId().compareTo( rhs.getRequiredFromIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromIndexId() != null) {
				return( -1 );
			}
			if (getRequiredToTableId() != null) {
				if (rhs.getRequiredToTableId() != null) {
					cmp = getRequiredToTableId().compareTo( rhs.getRequiredToTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToTableId() != null) {
				return( -1 );
			}
			if (getRequiredToIndexId() != null) {
				if (rhs.getRequiredToIndexId() != null) {
					cmp = getRequiredToIndexId().compareTo( rhs.getRequiredToIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToIndexId() != null) {
				return( -1 );
			}
			if( getRequiredIsRequired() ) {
				if( ! rhs.getRequiredIsRequired() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsRequired() ) {
					return( -1 );
				}
			}
			if( getRequiredIsXsdContainer() ) {
				if( ! rhs.getRequiredIsXsdContainer() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsXsdContainer() ) {
					return( -1 );
				}
			}
			if( getRequiredIsLateResolver() ) {
				if( ! rhs.getRequiredIsLateResolver() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsLateResolver() ) {
					return( -1 );
				}
			}
			if( getRequiredAllowAddendum() ) {
				if( ! rhs.getRequiredAllowAddendum() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredAllowAddendum() ) {
					return( -1 );
				}
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					cmp = getOptionalNarrowedId().compareTo( rhs.getOptionalNarrowedId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}
			return( 0 );
 		}
		else if( obj instanceof ICFBamProtScopeHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredId() != null) {
				if (rhs.getRequiredId() != null) {
					cmp = getRequiredId().compareTo( rhs.getRequiredId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamProtRelationH rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					cmp = getOptionalShortName().compareTo( rhs.getOptionalShortName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					cmp = getOptionalLabel().compareTo( rhs.getOptionalLabel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( -1 );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					cmp = getOptionalShortDescription().compareTo( rhs.getOptionalShortDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					cmp = getOptionalDescription().compareTo( rhs.getOptionalDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( -1 );
				}
			}
			if (getRequiredRelationType() != null) {
				if (rhs.getRequiredRelationType() != null) {
					cmp = getRequiredRelationType().compareTo( rhs.getRequiredRelationType() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationType() != null) {
				return( -1 );
			}
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					cmp = getOptionalDbName().compareTo( rhs.getOptionalDbName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					cmp = getOptionalSuffix().compareTo( rhs.getOptionalSuffix() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( -1 );
				}
			}
			if (getRequiredFromIndexId() != null) {
				if (rhs.getRequiredFromIndexId() != null) {
					cmp = getRequiredFromIndexId().compareTo( rhs.getRequiredFromIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromIndexId() != null) {
				return( -1 );
			}
			if (getRequiredToTableId() != null) {
				if (rhs.getRequiredToTableId() != null) {
					cmp = getRequiredToTableId().compareTo( rhs.getRequiredToTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToTableId() != null) {
				return( -1 );
			}
			if (getRequiredToIndexId() != null) {
				if (rhs.getRequiredToIndexId() != null) {
					cmp = getRequiredToIndexId().compareTo( rhs.getRequiredToIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToIndexId() != null) {
				return( -1 );
			}
			if( getRequiredIsRequired() ) {
				if( ! rhs.getRequiredIsRequired() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsRequired() ) {
					return( -1 );
				}
			}
			if( getRequiredIsXsdContainer() ) {
				if( ! rhs.getRequiredIsXsdContainer() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsXsdContainer() ) {
					return( -1 );
				}
			}
			if( getRequiredIsLateResolver() ) {
				if( ! rhs.getRequiredIsLateResolver() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsLateResolver() ) {
					return( -1 );
				}
			}
			if( getRequiredAllowAddendum() ) {
				if( ! rhs.getRequiredAllowAddendum() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredAllowAddendum() ) {
					return( -1 );
				}
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					cmp = getOptionalNarrowedId().compareTo( rhs.getOptionalNarrowedId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamProtRelationByUNameIdxKey rhs ) {
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtRelationByRelTableIdxKey rhs ) {
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtRelationByRelCodeVisIdxKey rhs ) {
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtRelationByRelTableCodeVisXKey rhs ) {
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtRelationByDefSchemaIdxKey rhs ) {
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtRelationByFromKeyIdxKey rhs ) {
			if (getRequiredFromIndexId() != null) {
				if (rhs.getRequiredFromIndexId() != null) {
					cmp = getRequiredFromIndexId().compareTo( rhs.getRequiredFromIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromIndexId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtRelationByToTblIdxKey rhs ) {
			if (getRequiredToTableId() != null) {
				if (rhs.getRequiredToTableId() != null) {
					cmp = getRequiredToTableId().compareTo( rhs.getRequiredToTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToTableId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtRelationByToKeyIdxKey rhs ) {
			if (getRequiredToIndexId() != null) {
				if (rhs.getRequiredToIndexId() != null) {
					cmp = getRequiredToIndexId().compareTo( rhs.getRequiredToIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToIndexId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtRelationByNarrowedIdxKey rhs ) {
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					cmp = getOptionalNarrowedId().compareTo( rhs.getOptionalNarrowedId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubRelation rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					cmp = getOptionalShortName().compareTo( rhs.getOptionalShortName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					cmp = getOptionalLabel().compareTo( rhs.getOptionalLabel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( -1 );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					cmp = getOptionalShortDescription().compareTo( rhs.getOptionalShortDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					cmp = getOptionalDescription().compareTo( rhs.getOptionalDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( -1 );
				}
			}
			if (getRequiredRelationType() != null) {
				if (rhs.getRequiredRelationType() != null) {
					cmp = getRequiredRelationType().compareTo( rhs.getRequiredRelationType() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationType() != null) {
				return( -1 );
			}
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					cmp = getOptionalDbName().compareTo( rhs.getOptionalDbName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					cmp = getOptionalSuffix().compareTo( rhs.getOptionalSuffix() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( -1 );
				}
			}
			if (getRequiredFromIndexId() != null) {
				if (rhs.getRequiredFromIndexId() != null) {
					cmp = getRequiredFromIndexId().compareTo( rhs.getRequiredFromIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromIndexId() != null) {
				return( -1 );
			}
			if (getRequiredToTableId() != null) {
				if (rhs.getRequiredToTableId() != null) {
					cmp = getRequiredToTableId().compareTo( rhs.getRequiredToTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToTableId() != null) {
				return( -1 );
			}
			if (getRequiredToIndexId() != null) {
				if (rhs.getRequiredToIndexId() != null) {
					cmp = getRequiredToIndexId().compareTo( rhs.getRequiredToIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToIndexId() != null) {
				return( -1 );
			}
			if( getRequiredIsRequired() ) {
				if( ! rhs.getRequiredIsRequired() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsRequired() ) {
					return( -1 );
				}
			}
			if( getRequiredIsXsdContainer() ) {
				if( ! rhs.getRequiredIsXsdContainer() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsXsdContainer() ) {
					return( -1 );
				}
			}
			if( getRequiredIsLateResolver() ) {
				if( ! rhs.getRequiredIsLateResolver() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsLateResolver() ) {
					return( -1 );
				}
			}
			if( getRequiredAllowAddendum() ) {
				if( ! rhs.getRequiredAllowAddendum() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredAllowAddendum() ) {
					return( -1 );
				}
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					cmp = getOptionalNarrowedId().compareTo( rhs.getOptionalNarrowedId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubScopeHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredId() != null) {
				if (rhs.getRequiredId() != null) {
					cmp = getRequiredId().compareTo( rhs.getRequiredId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubRelationH rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}
			if( getOptionalShortName() != null ) {
				if( rhs.getOptionalShortName() != null ) {
					cmp = getOptionalShortName().compareTo( rhs.getOptionalShortName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalLabel() != null ) {
				if( rhs.getOptionalLabel() != null ) {
					cmp = getOptionalLabel().compareTo( rhs.getOptionalLabel() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalLabel() != null ) {
					return( -1 );
				}
			}
			if( getOptionalShortDescription() != null ) {
				if( rhs.getOptionalShortDescription() != null ) {
					cmp = getOptionalShortDescription().compareTo( rhs.getOptionalShortDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalShortDescription() != null ) {
					return( -1 );
				}
			}
			if( getOptionalDescription() != null ) {
				if( rhs.getOptionalDescription() != null ) {
					cmp = getOptionalDescription().compareTo( rhs.getOptionalDescription() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDescription() != null ) {
					return( -1 );
				}
			}
			if (getRequiredRelationType() != null) {
				if (rhs.getRequiredRelationType() != null) {
					cmp = getRequiredRelationType().compareTo( rhs.getRequiredRelationType() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredRelationType() != null) {
				return( -1 );
			}
			if( getOptionalDbName() != null ) {
				if( rhs.getOptionalDbName() != null ) {
					cmp = getOptionalDbName().compareTo( rhs.getOptionalDbName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDbName() != null ) {
					return( -1 );
				}
			}
			if( getOptionalSuffix() != null ) {
				if( rhs.getOptionalSuffix() != null ) {
					cmp = getOptionalSuffix().compareTo( rhs.getOptionalSuffix() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalSuffix() != null ) {
					return( -1 );
				}
			}
			if (getRequiredFromIndexId() != null) {
				if (rhs.getRequiredFromIndexId() != null) {
					cmp = getRequiredFromIndexId().compareTo( rhs.getRequiredFromIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromIndexId() != null) {
				return( -1 );
			}
			if (getRequiredToTableId() != null) {
				if (rhs.getRequiredToTableId() != null) {
					cmp = getRequiredToTableId().compareTo( rhs.getRequiredToTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToTableId() != null) {
				return( -1 );
			}
			if (getRequiredToIndexId() != null) {
				if (rhs.getRequiredToIndexId() != null) {
					cmp = getRequiredToIndexId().compareTo( rhs.getRequiredToIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToIndexId() != null) {
				return( -1 );
			}
			if( getRequiredIsRequired() ) {
				if( ! rhs.getRequiredIsRequired() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsRequired() ) {
					return( -1 );
				}
			}
			if( getRequiredIsXsdContainer() ) {
				if( ! rhs.getRequiredIsXsdContainer() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsXsdContainer() ) {
					return( -1 );
				}
			}
			if( getRequiredIsLateResolver() ) {
				if( ! rhs.getRequiredIsLateResolver() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsLateResolver() ) {
					return( -1 );
				}
			}
			if( getRequiredAllowAddendum() ) {
				if( ! rhs.getRequiredAllowAddendum() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredAllowAddendum() ) {
					return( -1 );
				}
			}
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					cmp = getOptionalNarrowedId().compareTo( rhs.getOptionalNarrowedId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( -1 );
				}
			}
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubRelationByUNameIdxKey rhs ) {
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if (getRequiredName() != null) {
				if (rhs.getRequiredName() != null) {
					cmp = getRequiredName().compareTo( rhs.getRequiredName() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredName() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubRelationByRelTableIdxKey rhs ) {
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubRelationByRelCodeVisIdxKey rhs ) {
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubRelationByRelTableCodeVisXKey rhs ) {
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			if (getRequiredCodeVis() != null) {
				if (rhs.getRequiredCodeVis() != null) {
					cmp = getRequiredCodeVis().compareTo( rhs.getRequiredCodeVis() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredCodeVis() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubRelationByDefSchemaIdxKey rhs ) {
			if( getOptionalDefSchemaId() != null ) {
				if( rhs.getOptionalDefSchemaId() != null ) {
					cmp = getOptionalDefSchemaId().compareTo( rhs.getOptionalDefSchemaId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalDefSchemaId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubRelationByFromKeyIdxKey rhs ) {
			if (getRequiredFromIndexId() != null) {
				if (rhs.getRequiredFromIndexId() != null) {
					cmp = getRequiredFromIndexId().compareTo( rhs.getRequiredFromIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredFromIndexId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubRelationByToTblIdxKey rhs ) {
			if (getRequiredToTableId() != null) {
				if (rhs.getRequiredToTableId() != null) {
					cmp = getRequiredToTableId().compareTo( rhs.getRequiredToTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToTableId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubRelationByToKeyIdxKey rhs ) {
			if (getRequiredToIndexId() != null) {
				if (rhs.getRequiredToIndexId() != null) {
					cmp = getRequiredToIndexId().compareTo( rhs.getRequiredToIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredToIndexId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubRelationByNarrowedIdxKey rhs ) {
			if( getOptionalNarrowedId() != null ) {
				if( rhs.getOptionalNarrowedId() != null ) {
					cmp = getOptionalNarrowedId().compareTo( rhs.getOptionalNarrowedId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNarrowedId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else {
			cmp = super.compareTo( obj );
			return( cmp );
		}
	}

	@Override
	public void set( ICFBamScope src ) {
		if( src instanceof CFBamBuffRelation ) {
			setRelation( (CFBamBuffRelation)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamBuffRelation" );
		}
	}

	@Override
	public void setRelation( ICFBamRelation src ) {
		super.setScope( src );
		setOptionalLookupDefSchema(src.getOptionalLookupDefSchema());
		setRequiredContainerFromTable(src.getRequiredContainerFromTable());
		setRequiredLookupFromIndex(src.getRequiredLookupFromIndex());
		setRequiredLookupToTable(src.getRequiredLookupToTable());
		setRequiredLookupToIndex(src.getRequiredLookupToIndex());
		setOptionalLookupNarrowed(src.getOptionalLookupNarrowed());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredRelationType(src.getRequiredRelationType());
		setOptionalDbName(src.getOptionalDbName());
		setOptionalSuffix(src.getOptionalSuffix());
		setRequiredIsRequired(src.getRequiredIsRequired());
		setRequiredIsXsdContainer(src.getRequiredIsXsdContainer());
		setRequiredIsLateResolver(src.getRequiredIsLateResolver());
		setRequiredAllowAddendum(src.getRequiredAllowAddendum());
		setRequiredCodeVis(src.getRequiredCodeVis());
	}

	@Override
	public void set( ICFBamScopeH src ) {
		if( src instanceof ICFBamRelationH ) {
			setRelation( (ICFBamRelationH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamRelationH" );
		}
	}

	@Override
	public void setRelation( ICFBamRelationH src ) {
		super.setScope( src );
		setOptionalLookupDefSchema(src.getOptionalDefSchemaId());
		setRequiredContainerFromTable(src.getRequiredTableId());
		setRequiredLookupFromIndex(src.getRequiredFromIndexId());
		setRequiredLookupToTable(src.getRequiredToTableId());
		setRequiredLookupToIndex(src.getRequiredToIndexId());
		setOptionalLookupNarrowed(src.getOptionalNarrowedId());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredRelationType(src.getRequiredRelationType());
		setOptionalDbName(src.getOptionalDbName());
		setOptionalSuffix(src.getOptionalSuffix());
		setRequiredIsRequired(src.getRequiredIsRequired());
		setRequiredIsXsdContainer(src.getRequiredIsXsdContainer());
		setRequiredIsLateResolver(src.getRequiredIsLateResolver());
		setRequiredAllowAddendum(src.getRequiredAllowAddendum());
		setRequiredCodeVis(src.getRequiredCodeVis());
	}

	@Override
	public void set( ICFBamProtScope src ) {
		if( src instanceof CFBamProtBuffRelation ) {
			setRelation( (CFBamProtBuffRelation)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamProtBuffRelation" );
		}
	}

	@Override
	public void setRelation( ICFBamProtRelation src ) {
		super.setScope( src );
		setOptionalLookupDefSchema(src.getOptionalLookupDefSchema());
		setRequiredContainerFromTable(src.getRequiredContainerFromTable());
		setRequiredLookupFromIndex(src.getRequiredLookupFromIndex());
		setRequiredLookupToTable(src.getRequiredLookupToTable());
		setRequiredLookupToIndex(src.getRequiredLookupToIndex());
		setOptionalLookupNarrowed(src.getOptionalLookupNarrowed());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredRelationType(src.getRequiredRelationType());
		setOptionalDbName(src.getOptionalDbName());
		setOptionalSuffix(src.getOptionalSuffix());
		setRequiredIsRequired(src.getRequiredIsRequired());
		setRequiredIsXsdContainer(src.getRequiredIsXsdContainer());
		setRequiredIsLateResolver(src.getRequiredIsLateResolver());
		setRequiredAllowAddendum(src.getRequiredAllowAddendum());
		setRequiredCodeVis(src.getRequiredCodeVis());
	}

	@Override
	public void set( ICFBamProtScopeH src ) {
		if( src instanceof ICFBamProtRelationH ) {
			setRelation( (ICFBamProtRelationH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamProtRelationH" );
		}
	}

	@Override
	public void setRelation( ICFBamProtRelationH src ) {
		super.setScope( src );
		setOptionalLookupDefSchema(src.getOptionalDefSchemaId());
		setRequiredContainerFromTable(src.getRequiredTableId());
		setRequiredLookupFromIndex(src.getRequiredFromIndexId());
		setRequiredLookupToTable(src.getRequiredToTableId());
		setRequiredLookupToIndex(src.getRequiredToIndexId());
		setOptionalLookupNarrowed(src.getOptionalNarrowedId());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredRelationType(src.getRequiredRelationType());
		setOptionalDbName(src.getOptionalDbName());
		setOptionalSuffix(src.getOptionalSuffix());
		setRequiredIsRequired(src.getRequiredIsRequired());
		setRequiredIsXsdContainer(src.getRequiredIsXsdContainer());
		setRequiredIsLateResolver(src.getRequiredIsLateResolver());
		setRequiredAllowAddendum(src.getRequiredAllowAddendum());
		setRequiredCodeVis(src.getRequiredCodeVis());
	}

	@Override
	public void set( ICFBamPubScope src ) {
		if( src instanceof CFBamPubBuffRelation ) {
			setRelation( (CFBamPubBuffRelation)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamPubBuffRelation" );
		}
	}

	@Override
	public void setRelation( ICFBamPubRelation src ) {
		super.setScope( src );
		setOptionalLookupDefSchema(src.getOptionalLookupDefSchema());
		setRequiredContainerFromTable(src.getRequiredContainerFromTable());
		setRequiredLookupFromIndex(src.getRequiredLookupFromIndex());
		setRequiredLookupToTable(src.getRequiredLookupToTable());
		setRequiredLookupToIndex(src.getRequiredLookupToIndex());
		setOptionalLookupNarrowed(src.getOptionalLookupNarrowed());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredRelationType(src.getRequiredRelationType());
		setOptionalDbName(src.getOptionalDbName());
		setOptionalSuffix(src.getOptionalSuffix());
		setRequiredIsRequired(src.getRequiredIsRequired());
		setRequiredIsXsdContainer(src.getRequiredIsXsdContainer());
		setRequiredIsLateResolver(src.getRequiredIsLateResolver());
		setRequiredAllowAddendum(src.getRequiredAllowAddendum());
		setRequiredCodeVis(src.getRequiredCodeVis());
	}

	@Override
	public void set( ICFBamPubScopeH src ) {
		if( src instanceof ICFBamPubRelationH ) {
			setRelation( (ICFBamPubRelationH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamPubRelationH" );
		}
	}

	@Override
	public void setRelation( ICFBamPubRelationH src ) {
		super.setScope( src );
		setOptionalLookupDefSchema(src.getOptionalDefSchemaId());
		setRequiredContainerFromTable(src.getRequiredTableId());
		setRequiredLookupFromIndex(src.getRequiredFromIndexId());
		setRequiredLookupToTable(src.getRequiredToTableId());
		setRequiredLookupToIndex(src.getRequiredToIndexId());
		setOptionalLookupNarrowed(src.getOptionalNarrowedId());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredRelationType(src.getRequiredRelationType());
		setOptionalDbName(src.getOptionalDbName());
		setOptionalSuffix(src.getOptionalSuffix());
		setRequiredIsRequired(src.getRequiredIsRequired());
		setRequiredIsXsdContainer(src.getRequiredIsXsdContainer());
		setRequiredIsLateResolver(src.getRequiredIsLateResolver());
		setRequiredAllowAddendum(src.getRequiredAllowAddendum());
		setRequiredCodeVis(src.getRequiredCodeVis());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredTableId=" + "\"" + getRequiredTableId().toString() + "\""
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " OptionalDefSchemaId=" + ( ( getOptionalDefSchemaId() == null ) ? "null" : "\"" + getOptionalDefSchemaId().toString() + "\"" )
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\""
			+ " OptionalShortName=" + ( ( getOptionalShortName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalShortName() ) + "\"" )
			+ " OptionalLabel=" + ( ( getOptionalLabel() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalLabel() ) + "\"" )
			+ " OptionalShortDescription=" + ( ( getOptionalShortDescription() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalShortDescription() ) + "\"" )
			+ " OptionalDescription=" + ( ( getOptionalDescription() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDescription() ) + "\"" )
			+ " RequiredRelationType=" + "\"" + getRequiredRelationType().toString() + "\""
			+ " OptionalDbName=" + ( ( getOptionalDbName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDbName() ) + "\"" )
			+ " OptionalSuffix=" + ( ( getOptionalSuffix() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalSuffix() ) + "\"" )
			+ " RequiredFromIndexId=" + "\"" + getRequiredFromIndexId().toString() + "\""
			+ " RequiredToTableId=" + "\"" + getRequiredToTableId().toString() + "\""
			+ " RequiredToIndexId=" + "\"" + getRequiredToIndexId().toString() + "\""
			+ " RequiredIsRequired=" + (( getRequiredIsRequired() ) ? "\"true\"" : "\"false\"" )
			+ " RequiredIsXsdContainer=" + (( getRequiredIsXsdContainer() ) ? "\"true\"" : "\"false\"" )
			+ " RequiredIsLateResolver=" + (( getRequiredIsLateResolver() ) ? "\"true\"" : "\"false\"" )
			+ " RequiredAllowAddendum=" + (( getRequiredAllowAddendum() ) ? "\"true\"" : "\"false\"" )
			+ " OptionalNarrowedId=" + ( ( getOptionalNarrowedId() == null ) ? "null" : "\"" + getOptionalNarrowedId().toString() + "\"" )
			+ " RequiredCodeVis=" + "\"" + getRequiredCodeVis().toString() + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamBuffRelation" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
