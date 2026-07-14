// Description: Java 25 implementation of a IndexCol buffer

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

public class CFBamBuffIndexCol
	implements ICFBamIndexCol, Comparable<Object>, Serializable
{
	protected CFLibDbKeyHash256 requiredId;
	protected int requiredRevision;
	protected CFLibDbKeyHash256 createdByUserId = CFLibDbKeyHash256.fromHex(ICFBamIndexCol.S_INIT_CREATED_BY);
	protected LocalDateTime createdAt = LocalDateTime.now();
	protected CFLibDbKeyHash256 updatedByUserId = CFLibDbKeyHash256.fromHex(ICFBamIndexCol.S_INIT_UPDATED_BY);
	protected LocalDateTime updatedAt = LocalDateTime.now();
	protected CFLibDbKeyHash256 requiredIndexId;
	protected CFLibDbKeyHash256 optionalDefSchemaId;
	protected String requiredName;
	protected String optionalShortName;
	protected String optionalLabel;
	protected String optionalShortDescription;
	protected String optionalDescription;
	protected CFLibDbKeyHash256 requiredColumnId;
	protected boolean requiredIsAscending;
	protected CFLibDbKeyHash256 optionalPrevId;
	protected CFLibDbKeyHash256 optionalNextId;

	public CFBamBuffIndexCol() {
		requiredId = CFLibDbKeyHash256.fromHex( ICFBamIndexCol.ID_INIT_VALUE.toString() );
		requiredIndexId = CFLibDbKeyHash256.fromHex( ICFBamIndexCol.INDEXID_INIT_VALUE.toString() );
		optionalDefSchemaId = CFLibDbKeyHash256.nullGet();
		requiredName = ICFBamIndexCol.NAME_INIT_VALUE;
		optionalShortName = null;
		optionalLabel = null;
		optionalShortDescription = null;
		optionalDescription = null;
		requiredColumnId = CFLibDbKeyHash256.fromHex( ICFBamIndexCol.COLUMNID_INIT_VALUE.toString() );
		requiredIsAscending = ICFBamIndexCol.ISASCENDING_INIT_VALUE;
		optionalPrevId = CFLibDbKeyHash256.nullGet();
		optionalNextId = CFLibDbKeyHash256.nullGet();
	}

	@Override
	public CFLibDbKeyHash256 getPKey() {
		return getRequiredId();
	}

	@Override
	public void setPKey(CFLibDbKeyHash256 requiredId) {
		this.requiredId = requiredId;
	}

	@Override
	public List<ICFBamRelationCol> getOptionalChildrenRefRelFromCol() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalChildrenRefRelFromCol", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamRelationColTable targetTable = targetBackingSchema.getTableRelationCol();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalChildrenRefRelFromCol", 0, "ICFBamSchema.getBackingCFBam().getTableRelationCol()");
		}
		ICFBamRelationCol[] targetArr = targetTable.readDerivedByFromColIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredId());
		if( targetArr != null ) {
			List<ICFBamRelationCol> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFBamRelationCol> results = new ArrayList<>();
			return( results );
		}
	}
	@Override
	public List<ICFBamRelationCol> getOptionalChildrenRefRelToCol() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalChildrenRefRelToCol", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamRelationColTable targetTable = targetBackingSchema.getTableRelationCol();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalChildrenRefRelToCol", 0, "ICFBamSchema.getBackingCFBam().getTableRelationCol()");
		}
		ICFBamRelationCol[] targetArr = targetTable.readDerivedByToColIdx(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredId());
		if( targetArr != null ) {
			List<ICFBamRelationCol> results = new ArrayList<>(targetArr.length);
			for (int idx = 0; idx < targetArr.length; idx++) {
				results.add(targetArr[idx]);
			}
			return( results );
		}
		else {
			List<ICFBamRelationCol> results = new ArrayList<>();
			return( results );
		}
	}
	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( requiredId );
	}

	@Override
	public void setRequiredId( CFLibDbKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredId",
				1,
				"value" );
		}
		requiredId = value;
	}

	@Override
	public CFLibDbKeyHash256 getCreatedByUserId() {
		return( createdByUserId );
	}

	@Override
	public void setCreatedByUserId( CFLibDbKeyHash256 value ) {
		createdByUserId = value;
	}

	@Override
	public LocalDateTime getCreatedAt() {
		return( createdAt );
	}

	@Override
	public void setCreatedAt( LocalDateTime value ) {
		createdAt = value;
	}

	@Override
	public CFLibDbKeyHash256 getUpdatedByUserId() {
		return( updatedByUserId );
	}

	@Override
	public void setUpdatedByUserId( CFLibDbKeyHash256 value ) {
		updatedByUserId = value;
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( updatedAt );
	}

	@Override
	public void setUpdatedAt( LocalDateTime value ) {
		updatedAt = value;
	}
	@Override
	public int getRequiredRevision() {
		return( requiredRevision );
	}

	@Override
	public void setRequiredRevision( int value ) {
		requiredRevision = value;
	}

	@Override
	public int getClassCode() {
		return( ICFBamIndexCol.CLASS_CODE );
	}

	@Override
	public ICFBamIndex getRequiredContainerIndex() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerIndex", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamIndexTable targetTable = targetBackingSchema.getTableIndex();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerIndex", 0, "ICFBamSchema.getBackingCFBam().getTableIndex()");
		}
		ICFBamIndex targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredIndexId());
		return(targetRec);
	}
	@Override
	public void setRequiredContainerIndex(ICFBamIndex argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerIndex", 1, "argObj");
		}
		else {
			requiredIndexId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredContainerIndex(CFLibDbKeyHash256 argIndexId) {
		requiredIndexId = argIndexId;
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
	public ICFBamIndexCol getOptionalLookupPrev() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupPrev", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamIndexColTable targetTable = targetBackingSchema.getTableIndexCol();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupPrev", 0, "ICFBamSchema.getBackingCFBam().getTableIndexCol()");
		}
		ICFBamIndexCol targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getOptionalPrevId());
		return(targetRec);
	}
	@Override
	public void setOptionalLookupPrev(ICFBamIndexCol argObj) {
		if(argObj == null) {
			optionalPrevId = null;
		}
		else {
			optionalPrevId = argObj.getRequiredId();
		}
	}

	@Override
	public void setOptionalLookupPrev(CFLibDbKeyHash256 argPrevId) {
		optionalPrevId = argPrevId;
	}

	@Override
	public ICFBamIndexCol getOptionalLookupNext() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupNext", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamIndexColTable targetTable = targetBackingSchema.getTableIndexCol();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setOptionalLookupNext", 0, "ICFBamSchema.getBackingCFBam().getTableIndexCol()");
		}
		ICFBamIndexCol targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getOptionalNextId());
		return(targetRec);
	}
	@Override
	public void setOptionalLookupNext(ICFBamIndexCol argObj) {
		if(argObj == null) {
			optionalNextId = null;
		}
		else {
			optionalNextId = argObj.getRequiredId();
		}
	}

	@Override
	public void setOptionalLookupNext(CFLibDbKeyHash256 argNextId) {
		optionalNextId = argNextId;
	}

	@Override
	public ICFBamValue getRequiredLookupColumn() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupColumn", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamValueTable targetTable = targetBackingSchema.getTableValue();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredLookupColumn", 0, "ICFBamSchema.getBackingCFBam().getTableValue()");
		}
		ICFBamValue targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredColumnId());
		return(targetRec);
	}
	@Override
	public void setRequiredLookupColumn(ICFBamValue argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setLookupColumn", 1, "argObj");
		}
		else {
			requiredColumnId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredLookupColumn(CFLibDbKeyHash256 argColumnId) {
		requiredColumnId = argColumnId;
	}

	@Override
	public CFLibDbKeyHash256 getRequiredIndexId() {
		return( requiredIndexId );
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
	public CFLibDbKeyHash256 getRequiredColumnId() {
		return( requiredColumnId );
	}

	@Override
	public boolean getRequiredIsAscending() {
		return( requiredIsAscending );
	}

	@Override
	public void setRequiredIsAscending( boolean value ) {
		requiredIsAscending = value;
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( optionalPrevId );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( optionalNextId );
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFBamIndexCol rhs ) {
			if( ! getCreatedByUserId().equals( rhs.getCreatedByUserId() ) ) {
				return( false );
			}
			if( ! getCreatedAt().equals( rhs.getCreatedAt() ) ) {
				return( false );
			}
			if( ! getUpdatedByUserId().equals( rhs.getUpdatedByUserId() ) ) {
				return( false );
			}
			if( ! getUpdatedAt().equals( rhs.getUpdatedAt() ) ) {
				return( false );
			}
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
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
			if( getRequiredColumnId() != null ) {
				if( rhs.getRequiredColumnId() != null ) {
					if( ! getRequiredColumnId().equals( rhs.getRequiredColumnId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredColumnId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsAscending() != rhs.getRequiredIsAscending() ) {
				return( false );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( false );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColH ) {
			ICFBamIndexColH rhs = (ICFBamIndexColH)obj;
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
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
			if( getRequiredColumnId() != null ) {
				if( rhs.getRequiredColumnId() != null ) {
					if( ! getRequiredColumnId().equals( rhs.getRequiredColumnId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredColumnId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsAscending() != rhs.getRequiredIsAscending() ) {
				return( false );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( false );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColHPKey ) {
			ICFBamIndexColHPKey rhs = (ICFBamIndexColHPKey)obj;
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
		else if( obj instanceof ICFBamIndexColByUNameIdxKey ) {
			ICFBamIndexColByUNameIdxKey rhs = (ICFBamIndexColByUNameIdxKey)obj;
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
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
		else if( obj instanceof ICFBamIndexColByIndexIdxKey ) {
			ICFBamIndexColByIndexIdxKey rhs = (ICFBamIndexColByIndexIdxKey)obj;
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByDefSchemaIdxKey ) {
			ICFBamIndexColByDefSchemaIdxKey rhs = (ICFBamIndexColByDefSchemaIdxKey)obj;
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
		else if( obj instanceof ICFBamIndexColByColIdxKey ) {
			ICFBamIndexColByColIdxKey rhs = (ICFBamIndexColByColIdxKey)obj;
			if( getRequiredColumnId() != null ) {
				if( rhs.getRequiredColumnId() != null ) {
					if( ! getRequiredColumnId().equals( rhs.getRequiredColumnId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredColumnId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByPrevIdxKey ) {
			ICFBamIndexColByPrevIdxKey rhs = (ICFBamIndexColByPrevIdxKey)obj;
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByNextIdxKey ) {
			ICFBamIndexColByNextIdxKey rhs = (ICFBamIndexColByNextIdxKey)obj;
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByIdxPrevIdxKey ) {
			ICFBamIndexColByIdxPrevIdxKey rhs = (ICFBamIndexColByIdxPrevIdxKey)obj;
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByIdxNextIdxKey ) {
			ICFBamIndexColByIdxNextIdxKey rhs = (ICFBamIndexColByIdxNextIdxKey)obj;
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtIndexCol rhs ) {
			if( ! getCreatedByUserId().equals( rhs.getCreatedByUserId() ) ) {
				return( false );
			}
			if( ! getCreatedAt().equals( rhs.getCreatedAt() ) ) {
				return( false );
			}
			if( ! getUpdatedByUserId().equals( rhs.getUpdatedByUserId() ) ) {
				return( false );
			}
			if( ! getUpdatedAt().equals( rhs.getUpdatedAt() ) ) {
				return( false );
			}
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
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
			if( getRequiredColumnId() != null ) {
				if( rhs.getRequiredColumnId() != null ) {
					if( ! getRequiredColumnId().equals( rhs.getRequiredColumnId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredColumnId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsAscending() != rhs.getRequiredIsAscending() ) {
				return( false );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( false );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtIndexColH rhs ) {
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
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
			if( getRequiredColumnId() != null ) {
				if( rhs.getRequiredColumnId() != null ) {
					if( ! getRequiredColumnId().equals( rhs.getRequiredColumnId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredColumnId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsAscending() != rhs.getRequiredIsAscending() ) {
				return( false );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( false );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtIndexColHPKey rhs ) {
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
		else if( obj instanceof ICFBamIndexColByUNameIdxKey ) {
			ICFBamIndexColByUNameIdxKey rhs = (ICFBamIndexColByUNameIdxKey)obj;
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
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
		else if( obj instanceof ICFBamIndexColByIndexIdxKey ) {
			ICFBamIndexColByIndexIdxKey rhs = (ICFBamIndexColByIndexIdxKey)obj;
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByDefSchemaIdxKey ) {
			ICFBamIndexColByDefSchemaIdxKey rhs = (ICFBamIndexColByDefSchemaIdxKey)obj;
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
		else if( obj instanceof ICFBamIndexColByColIdxKey ) {
			ICFBamIndexColByColIdxKey rhs = (ICFBamIndexColByColIdxKey)obj;
			if( getRequiredColumnId() != null ) {
				if( rhs.getRequiredColumnId() != null ) {
					if( ! getRequiredColumnId().equals( rhs.getRequiredColumnId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredColumnId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByPrevIdxKey ) {
			ICFBamIndexColByPrevIdxKey rhs = (ICFBamIndexColByPrevIdxKey)obj;
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByNextIdxKey ) {
			ICFBamIndexColByNextIdxKey rhs = (ICFBamIndexColByNextIdxKey)obj;
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByIdxPrevIdxKey ) {
			ICFBamIndexColByIdxPrevIdxKey rhs = (ICFBamIndexColByIdxPrevIdxKey)obj;
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByIdxNextIdxKey ) {
			ICFBamIndexColByIdxNextIdxKey rhs = (ICFBamIndexColByIdxNextIdxKey)obj;
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubIndexCol rhs ) {
			if( ! getCreatedByUserId().equals( rhs.getCreatedByUserId() ) ) {
				return( false );
			}
			if( ! getCreatedAt().equals( rhs.getCreatedAt() ) ) {
				return( false );
			}
			if( ! getUpdatedByUserId().equals( rhs.getUpdatedByUserId() ) ) {
				return( false );
			}
			if( ! getUpdatedAt().equals( rhs.getUpdatedAt() ) ) {
				return( false );
			}
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
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
			if( getRequiredColumnId() != null ) {
				if( rhs.getRequiredColumnId() != null ) {
					if( ! getRequiredColumnId().equals( rhs.getRequiredColumnId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredColumnId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsAscending() != rhs.getRequiredIsAscending() ) {
				return( false );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( false );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubIndexColH rhs ) {
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
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
			if( getRequiredColumnId() != null ) {
				if( rhs.getRequiredColumnId() != null ) {
					if( ! getRequiredColumnId().equals( rhs.getRequiredColumnId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredColumnId() != null ) {
					return( false );
				}
			}
			if( getRequiredIsAscending() != rhs.getRequiredIsAscending() ) {
				return( false );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( false );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubIndexColHPKey rhs ) {
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
		else if( obj instanceof ICFBamIndexColByUNameIdxKey ) {
			ICFBamIndexColByUNameIdxKey rhs = (ICFBamIndexColByUNameIdxKey)obj;
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
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
		else if( obj instanceof ICFBamIndexColByIndexIdxKey ) {
			ICFBamIndexColByIndexIdxKey rhs = (ICFBamIndexColByIndexIdxKey)obj;
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByDefSchemaIdxKey ) {
			ICFBamIndexColByDefSchemaIdxKey rhs = (ICFBamIndexColByDefSchemaIdxKey)obj;
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
		else if( obj instanceof ICFBamIndexColByColIdxKey ) {
			ICFBamIndexColByColIdxKey rhs = (ICFBamIndexColByColIdxKey)obj;
			if( getRequiredColumnId() != null ) {
				if( rhs.getRequiredColumnId() != null ) {
					if( ! getRequiredColumnId().equals( rhs.getRequiredColumnId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredColumnId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByPrevIdxKey ) {
			ICFBamIndexColByPrevIdxKey rhs = (ICFBamIndexColByPrevIdxKey)obj;
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByNextIdxKey ) {
			ICFBamIndexColByNextIdxKey rhs = (ICFBamIndexColByNextIdxKey)obj;
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByIdxPrevIdxKey ) {
			ICFBamIndexColByIdxPrevIdxKey rhs = (ICFBamIndexColByIdxPrevIdxKey)obj;
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					if( ! getOptionalPrevId().equals( rhs.getOptionalPrevId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamIndexColByIdxNextIdxKey ) {
			ICFBamIndexColByIdxNextIdxKey rhs = (ICFBamIndexColByIdxNextIdxKey)obj;
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					if( ! getOptionalNextId().equals( rhs.getOptionalNextId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
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
		int hashCode = 0;
		hashCode = hashCode + getCreatedByUserId().hashCode();
		hashCode = hashCode + getCreatedAt().hashCode();
		hashCode = hashCode + getUpdatedByUserId().hashCode();
		hashCode = hashCode + getUpdatedAt().hashCode();
		hashCode = hashCode + getRequiredIndexId().hashCode();
		hashCode = hashCode + getRequiredId().hashCode();
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
		hashCode = hashCode + getRequiredColumnId().hashCode();
		if( getRequiredIsAscending() ) {
			hashCode = ( hashCode * 2 ) + 1;
		}
		else {
			hashCode = hashCode * 2;
		}
		if( getOptionalPrevId() != null ) {
			hashCode = hashCode + getOptionalPrevId().hashCode();
		}
		if( getOptionalNextId() != null ) {
			hashCode = hashCode + getOptionalNextId().hashCode();
		}
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFBamIndexCol rhs ) {
			cmp = 0;
			{
				cmp = getCreatedByUserId().compareTo( rhs.getCreatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getCreatedAt().compareTo( rhs.getCreatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedByUserId().compareTo( rhs.getUpdatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedAt().compareTo( rhs.getUpdatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}
			}
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
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
			if (getRequiredColumnId() != null) {
				if (rhs.getRequiredColumnId() != null) {
					cmp = getRequiredColumnId().compareTo( rhs.getRequiredColumnId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredColumnId() != null) {
				return( -1 );
			}
			if( getRequiredIsAscending() ) {
				if( ! rhs.getRequiredIsAscending() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsAscending() ) {
					return( -1 );
				}
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamIndexColHPKey rhs ) {
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
		else if( obj instanceof ICFBamIndexColH rhs ) {
			cmp = 0;
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
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
			if (getRequiredColumnId() != null) {
				if (rhs.getRequiredColumnId() != null) {
					cmp = getRequiredColumnId().compareTo( rhs.getRequiredColumnId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredColumnId() != null) {
				return( -1 );
			}
			if( getRequiredIsAscending() ) {
				if( ! rhs.getRequiredIsAscending() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsAscending() ) {
					return( -1 );
				}
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamIndexColByUNameIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
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
		else if( obj instanceof ICFBamIndexColByIndexIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamIndexColByDefSchemaIdxKey rhs ) {
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
		else if( obj instanceof ICFBamIndexColByColIdxKey rhs ) {
			if (getRequiredColumnId() != null) {
				if (rhs.getRequiredColumnId() != null) {
					cmp = getRequiredColumnId().compareTo( rhs.getRequiredColumnId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredColumnId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamIndexColByPrevIdxKey rhs ) {
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamIndexColByNextIdxKey rhs ) {
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamIndexColByIdxPrevIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamIndexColByIdxNextIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtIndexCol rhs ) {
			cmp = 0;
			{
				cmp = getCreatedByUserId().compareTo( rhs.getCreatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getCreatedAt().compareTo( rhs.getCreatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedByUserId().compareTo( rhs.getUpdatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedAt().compareTo( rhs.getUpdatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}
			}
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
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
			if (getRequiredColumnId() != null) {
				if (rhs.getRequiredColumnId() != null) {
					cmp = getRequiredColumnId().compareTo( rhs.getRequiredColumnId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredColumnId() != null) {
				return( -1 );
			}
			if( getRequiredIsAscending() ) {
				if( ! rhs.getRequiredIsAscending() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsAscending() ) {
					return( -1 );
				}
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
 		}
		else if( obj instanceof ICFBamProtIndexColHPKey rhs ) {
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
		else if( obj instanceof ICFBamProtIndexColH rhs ) {
			cmp = 0;
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
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
			if (getRequiredColumnId() != null) {
				if (rhs.getRequiredColumnId() != null) {
					cmp = getRequiredColumnId().compareTo( rhs.getRequiredColumnId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredColumnId() != null) {
				return( -1 );
			}
			if( getRequiredIsAscending() ) {
				if( ! rhs.getRequiredIsAscending() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsAscending() ) {
					return( -1 );
				}
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamProtIndexColByUNameIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
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
		else if( obj instanceof ICFBamProtIndexColByIndexIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtIndexColByDefSchemaIdxKey rhs ) {
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
		else if( obj instanceof ICFBamProtIndexColByColIdxKey rhs ) {
			if (getRequiredColumnId() != null) {
				if (rhs.getRequiredColumnId() != null) {
					cmp = getRequiredColumnId().compareTo( rhs.getRequiredColumnId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredColumnId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtIndexColByPrevIdxKey rhs ) {
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtIndexColByNextIdxKey rhs ) {
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtIndexColByIdxPrevIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtIndexColByIdxNextIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubIndexCol rhs ) {
			cmp = 0;
			{
				cmp = getCreatedByUserId().compareTo( rhs.getCreatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getCreatedAt().compareTo( rhs.getCreatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedByUserId().compareTo( rhs.getUpdatedByUserId() );
				if( cmp != 0 ) {
					return( cmp );
				}

				cmp = getUpdatedAt().compareTo( rhs.getUpdatedAt() );
				if( cmp != 0 ) {
					return( cmp );
				}
			}
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
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
			if (getRequiredColumnId() != null) {
				if (rhs.getRequiredColumnId() != null) {
					cmp = getRequiredColumnId().compareTo( rhs.getRequiredColumnId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredColumnId() != null) {
				return( -1 );
			}
			if( getRequiredIsAscending() ) {
				if( ! rhs.getRequiredIsAscending() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsAscending() ) {
					return( -1 );
				}
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubIndexColHPKey rhs ) {
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
		else if( obj instanceof ICFBamPubIndexColH rhs ) {
			cmp = 0;
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
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
			if (getRequiredColumnId() != null) {
				if (rhs.getRequiredColumnId() != null) {
					cmp = getRequiredColumnId().compareTo( rhs.getRequiredColumnId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredColumnId() != null) {
				return( -1 );
			}
			if( getRequiredIsAscending() ) {
				if( ! rhs.getRequiredIsAscending() ) {
					return( 1 );
				}
			}
			else {
				if( rhs.getRequiredIsAscending() ) {
					return( -1 );
				}
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubIndexColByUNameIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
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
		else if( obj instanceof ICFBamPubIndexColByIndexIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubIndexColByDefSchemaIdxKey rhs ) {
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
		else if( obj instanceof ICFBamPubIndexColByColIdxKey rhs ) {
			if (getRequiredColumnId() != null) {
				if (rhs.getRequiredColumnId() != null) {
					cmp = getRequiredColumnId().compareTo( rhs.getRequiredColumnId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredColumnId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubIndexColByPrevIdxKey rhs ) {
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubIndexColByNextIdxKey rhs ) {
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubIndexColByIdxPrevIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			if( getOptionalPrevId() != null ) {
				if( rhs.getOptionalPrevId() != null ) {
					cmp = getOptionalPrevId().compareTo( rhs.getOptionalPrevId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalPrevId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubIndexColByIdxNextIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			if( getOptionalNextId() != null ) {
				if( rhs.getOptionalNextId() != null ) {
					cmp = getOptionalNextId().compareTo( rhs.getOptionalNextId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else {
				if( rhs.getOptionalNextId() != null ) {
					return( -1 );
				}
			}			return( 0 );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"obj",
				obj,
				null );
		}
	}

	@Override
	public void set( ICFBamIndexCol src ) {
		setIndexCol( src );
	}

	@Override
	public void setIndexCol( ICFBamIndexCol src ) {
		setRequiredId(src.getRequiredId());
		setRequiredRevision( src.getRequiredRevision() );
		setCreatedByUserId( src.getCreatedByUserId() );
		setCreatedAt( src.getCreatedAt() );
		setUpdatedByUserId( src.getUpdatedByUserId() );
		setUpdatedAt( src.getUpdatedAt() );
		setRequiredContainerIndex(src.getRequiredContainerIndex());
		setOptionalLookupDefSchema(src.getOptionalLookupDefSchema());
		setOptionalLookupPrev(src.getOptionalLookupPrev());
		setOptionalLookupNext(src.getOptionalLookupNext());
		setRequiredLookupColumn(src.getRequiredLookupColumn());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredIsAscending(src.getRequiredIsAscending());
	}

	@Override
	public void set( ICFBamIndexColH src ) {
		setIndexCol( src );
	}

	@Override
	public void setIndexCol( ICFBamIndexColH src ) {
		setRequiredId(src.getRequiredId());
		setRequiredContainerIndex(src.getRequiredIndexId());
		setOptionalLookupDefSchema(src.getOptionalDefSchemaId());
		setOptionalLookupPrev(src.getOptionalPrevId());
		setOptionalLookupNext(src.getOptionalNextId());
		setRequiredLookupColumn(src.getRequiredColumnId());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredIsAscending(src.getRequiredIsAscending());
	}

	@Override
	public void set( ICFBamProtIndexCol src ) {
		setIndexCol( src );
	}

	@Override
	public void setIndexCol( ICFBamProtIndexCol src ) {
		setRequiredId(src.getRequiredId());
		setRequiredRevision( src.getRequiredRevision() );
		setCreatedByUserId( src.getCreatedByUserId() );
		setCreatedAt( src.getCreatedAt() );
		setUpdatedByUserId( src.getUpdatedByUserId() );
		setUpdatedAt( src.getUpdatedAt() );
		setRequiredContainerIndex(src.getRequiredContainerIndex());
		setOptionalLookupDefSchema(src.getOptionalLookupDefSchema());
		setOptionalLookupPrev(src.getOptionalLookupPrev());
		setOptionalLookupNext(src.getOptionalLookupNext());
		setRequiredLookupColumn(src.getRequiredLookupColumn());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredIsAscending(src.getRequiredIsAscending());
	}

	@Override
	public void set( ICFBamProtIndexColH src ) {
		setIndexCol( src );
	}

	@Override
	public void setIndexCol( ICFBamProtIndexColH src ) {
		setRequiredId(src.getRequiredId());
		setRequiredContainerIndex(src.getRequiredIndexId());
		setOptionalLookupDefSchema(src.getOptionalDefSchemaId());
		setOptionalLookupPrev(src.getOptionalPrevId());
		setOptionalLookupNext(src.getOptionalNextId());
		setRequiredLookupColumn(src.getRequiredColumnId());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredIsAscending(src.getRequiredIsAscending());
	}

	@Override
	public void set( ICFBamPubIndexCol src ) {
		setIndexCol( src );
	}

	@Override
	public void setIndexCol( ICFBamPubIndexCol src ) {
		setRequiredId(src.getRequiredId());
		setRequiredRevision( src.getRequiredRevision() );
		setCreatedByUserId( src.getCreatedByUserId() );
		setCreatedAt( src.getCreatedAt() );
		setUpdatedByUserId( src.getUpdatedByUserId() );
		setUpdatedAt( src.getUpdatedAt() );
		setRequiredContainerIndex(src.getRequiredContainerIndex());
		setOptionalLookupDefSchema(src.getOptionalLookupDefSchema());
		setOptionalLookupPrev(src.getOptionalLookupPrev());
		setOptionalLookupNext(src.getOptionalLookupNext());
		setRequiredLookupColumn(src.getRequiredLookupColumn());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredIsAscending(src.getRequiredIsAscending());
	}

	@Override
	public void set( ICFBamPubIndexColH src ) {
		setIndexCol( src );
	}

	@Override
	public void setIndexCol( ICFBamPubIndexColH src ) {
		setRequiredId(src.getRequiredId());
		setRequiredContainerIndex(src.getRequiredIndexId());
		setOptionalLookupDefSchema(src.getOptionalDefSchemaId());
		setOptionalLookupPrev(src.getOptionalPrevId());
		setOptionalLookupNext(src.getOptionalNextId());
		setRequiredLookupColumn(src.getRequiredColumnId());
		setRequiredName(src.getRequiredName());
		setOptionalShortName(src.getOptionalShortName());
		setOptionalLabel(src.getOptionalLabel());
		setOptionalShortDescription(src.getOptionalShortDescription());
		setOptionalDescription(src.getOptionalDescription());
		setRequiredIsAscending(src.getRequiredIsAscending());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = ""
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredRevision=\"" + Integer.toString( getRequiredRevision() ) + "\""
			+ " RequiredIndexId=" + "\"" + getRequiredIndexId().toString() + "\""
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " OptionalDefSchemaId=" + ( ( getOptionalDefSchemaId() == null ) ? "null" : "\"" + getOptionalDefSchemaId().toString() + "\"" )
			+ " RequiredName=" + "\"" + StringEscapeUtils.escapeXml11( getRequiredName() ) + "\""
			+ " OptionalShortName=" + ( ( getOptionalShortName() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalShortName() ) + "\"" )
			+ " OptionalLabel=" + ( ( getOptionalLabel() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalLabel() ) + "\"" )
			+ " OptionalShortDescription=" + ( ( getOptionalShortDescription() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalShortDescription() ) + "\"" )
			+ " OptionalDescription=" + ( ( getOptionalDescription() == null ) ? "null" : "\"" + StringEscapeUtils.escapeXml11( getOptionalDescription() ) + "\"" )
			+ " RequiredColumnId=" + "\"" + getRequiredColumnId().toString() + "\""
			+ " RequiredIsAscending=" + (( getRequiredIsAscending() ) ? "\"true\"" : "\"false\"" )
			+ " OptionalPrevId=" + ( ( getOptionalPrevId() == null ) ? "null" : "\"" + getOptionalPrevId().toString() + "\"" )
			+ " OptionalNextId=" + ( ( getOptionalNextId() == null ) ? "null" : "\"" + getOptionalNextId().toString() + "\"" );
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamBuffIndexCol" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
