// Description: Java 25 edit object instance implementation for CFBam IndexCol.

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
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamIndexColEditObj
	implements ICFBamIndexColEditObj
{
	protected ICFBamIndexColObj orig;
	protected ICFBamIndexCol rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFBamIndexObj requiredContainerIndex;
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected List<ICFBamRelationColObj> optionalChildrenRefRelFromCol;
	protected List<ICFBamRelationColObj> optionalChildrenRefRelToCol;
	protected ICFBamIndexColObj optionalLookupPrev;
	protected ICFBamIndexColObj optionalLookupNext;
	protected ICFBamValueObj requiredLookupColumn;

	public CFBamIndexColEditObj( ICFBamIndexColObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFBamIndexCol origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerIndex = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
		requiredLookupColumn = null;
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFBamIndexCol rec = getRec();
			createdBy = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getCreatedByUserId() );
		}
		return( createdBy );
	}

	@Override
	public LocalDateTime getCreatedAt() {
		return( getRec().getCreatedAt() );
	}

	@Override
	public ICFSecSecUserObj getUpdatedBy() {
		if( updatedBy == null ) {
			ICFBamIndexCol rec = getRec();
			updatedBy = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public void setCreatedBy( ICFSecSecUserObj value ) {
		createdBy = value;
		if( value != null ) {
			getRec().setCreatedByUserId( value.getRequiredSecUserId() );
		}
	}

	@Override
	public void setCreatedAt( LocalDateTime value ) {
		getRec().setCreatedAt( value );
	}

	@Override
	public void setUpdatedBy( ICFSecSecUserObj value ) {
		updatedBy = value;
		if( value != null ) {
			getRec().setUpdatedByUserId( value.getRequiredSecUserId() );
		}
	}

	@Override
	public void setUpdatedAt( LocalDateTime value ) {
		getRec().setUpdatedAt( value );
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getIndexColTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "IndexCol" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamIndexObj scope = getRequiredContainerIndex();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredName();
		return( objName );
	}

	@Override
	public ICFLibAnyObj getObjQualifier( Class qualifyingClass ) {
		ICFLibAnyObj container = this;
		if( qualifyingClass != null ) {
			while( container != null ) {
				if( container instanceof ICFBamClusterObj ) {
					break;
				}
				else if( container instanceof ICFBamTenantObj ) {
					break;
				}
				else if( qualifyingClass.isInstance( container ) ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		else {
			while( container != null ) {
				if( container instanceof ICFBamClusterObj ) {
					break;
				}
				else if( container instanceof ICFBamTenantObj ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		return( container );
	}

	@Override
	public ICFLibAnyObj getNamedObject( Class qualifyingClass, String objName ) {
		ICFLibAnyObj topContainer = getObjQualifier( qualifyingClass );
		if( topContainer == null ) {
			return( null );
		}
		ICFLibAnyObj namedObject = topContainer.getNamedObject( objName );
		return( namedObject );
	}

	@Override
	public ICFLibAnyObj getNamedObject( String objName ) {
		String nextName;
		String remainingName;
		ICFLibAnyObj subObj = null;
		ICFLibAnyObj retObj;
		int nextDot = objName.indexOf( '.' );
		if( nextDot >= 0 ) {
			nextName = objName.substring( 0, nextDot );
			remainingName = objName.substring( nextDot + 1 );
		}
		else {
			nextName = objName;
			remainingName = null;
		}
		if( remainingName == null ) {
			retObj = subObj;
		}
		else if( subObj == null ) {
			retObj = null;
		}
		else {
			retObj = subObj.getNamedObject( remainingName );
		}
		return( retObj );
	}

	@Override
	public String getObjQualifiedName() {
		String qualName = getObjName();
		ICFLibAnyObj container = getObjScope();
		String containerName;
		while( container != null ) {
			if( container instanceof ICFSecClusterObj ) {
				container = null;
			}
			else if( container instanceof ICFSecTenantObj ) {
				container = null;
			}
			else if( container instanceof ICFBamSchemaDefObj ) {
				container = null;
			}
			else {
				containerName = container.getObjName();
				qualName = containerName + "." + qualName;
				container = container.getObjScope();
			}
		}
		return( qualName );
	}

	@Override
	public String getObjFullName() {
		String fullName = getObjName();
		ICFLibAnyObj container = getObjScope();
		String containerName;
		while( container != null ) {
			if( container instanceof ICFSecClusterObj ) {
				container = null;
			}
			else if( container instanceof ICFSecTenantObj ) {
				container = null;
			}
			else {
				containerName = container.getObjName();
				fullName = containerName + "." + fullName;
				container = container.getObjScope();
			}
		}
		return( fullName );
	}

	@Override
	public ICFBamIndexColObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFBamIndexColObj retobj = getSchema().getIndexColTableObj().realiseIndexCol( (ICFBamIndexColObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsIndexCol().forget();
	}

	@Override
	public ICFBamIndexColObj read() {
		ICFBamIndexColObj retval = getOrigAsIndexCol().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFBamIndexColObj read( boolean forceRead ) {
		ICFBamIndexColObj retval = getOrigAsIndexCol().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFBamIndexColObj moveUp() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUp" );
	}

	@Override
	public ICFBamIndexColObj moveDown() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDown" );
	}

	@Override
	public ICFBamIndexColObj create() {
		copyRecToOrig();
		ICFBamIndexColObj retobj = ((ICFBamSchemaObj)getOrigAsIndexCol().getSchema()).getIndexColTableObj().createIndexCol( getOrigAsIndexCol() );
		if( retobj == getOrigAsIndexCol() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamIndexColEditObj update() {
		getSchema().getIndexColTableObj().updateIndexCol( (ICFBamIndexColObj)this );
		return( null );
	}

	@Override
	public CFBamIndexColEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getIndexColTableObj().deleteIndexCol( getOrigAsIndexCol() );
		return( null );
	}

	@Override
	public ICFBamIndexColTableObj getIndexColTable() {
		return( orig.getSchema().getIndexColTableObj() );
	}

	@Override
	public ICFBamIndexColEditObj getEdit() {
		return( (ICFBamIndexColEditObj)this );
	}

	@Override
	public ICFBamIndexColEditObj getEditAsIndexCol() {
		return( (ICFBamIndexColEditObj)this );
	}

	@Override
	public ICFBamIndexColEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFBamIndexColObj getOrig() {
		return( orig );
	}

	@Override
	public ICFBamIndexColObj getOrigAsIndexCol() {
		return( (ICFBamIndexColObj)orig );
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( orig.getSchema() );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		orig.setSchema(value);
	}

	@Override
	public ICFBamIndexCol getRec() {
		if( rec == null ) {
			rec = getOrigAsIndexCol().getSchema().getCFBamBackingStore().getFactoryIndexCol().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamIndexCol value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerIndex = null;
			optionalLookupDefSchema = null;
			optionalLookupPrev = null;
			optionalLookupNext = null;
			requiredLookupColumn = null;
		}
	}

	@Override
	public ICFBamIndexCol getIndexColRec() {
		return( (ICFBamIndexCol)getRec() );
	}

	@Override
	public CFLibDbKeyHash256 getPKey() {
		return( orig.getPKey() );
	}

	@Override
	public void setPKey( CFLibDbKeyHash256 value ) {
		orig.setPKey( value );
		copyPKeyToRec();
	}

	@Override
	public boolean getIsNew() {
		return( orig.getIsNew() );
	}

	@Override
	public void setIsNew( boolean value ) {
		orig.setIsNew( value );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredId(CFLibDbKeyHash256 value) {
		if (getPKey() != value) {
			setPKey(value);
			requiredContainerIndex = null;
			optionalLookupDefSchema = null;
			optionalChildrenRefRelFromCol = null;
			optionalChildrenRefRelToCol = null;
			optionalLookupPrev = null;
			optionalLookupNext = null;
			requiredLookupColumn = null;
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredIndexId() {
		return( getIndexColRec().getRequiredIndexId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( getIndexColRec().getOptionalDefSchemaId() );
	}

	@Override
	public String getRequiredName() {
		return( getIndexColRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getIndexColRec().getRequiredName() != value ) {
			getIndexColRec().setRequiredName( value );
		}
	}

	@Override
	public String getOptionalShortName() {
		return( getIndexColRec().getOptionalShortName() );
	}

	@Override
	public void setOptionalShortName( String value ) {
		if( getIndexColRec().getOptionalShortName() != value ) {
			getIndexColRec().setOptionalShortName( value );
		}
	}

	@Override
	public String getOptionalLabel() {
		return( getIndexColRec().getOptionalLabel() );
	}

	@Override
	public void setOptionalLabel( String value ) {
		if( getIndexColRec().getOptionalLabel() != value ) {
			getIndexColRec().setOptionalLabel( value );
		}
	}

	@Override
	public String getOptionalShortDescription() {
		return( getIndexColRec().getOptionalShortDescription() );
	}

	@Override
	public void setOptionalShortDescription( String value ) {
		if( getIndexColRec().getOptionalShortDescription() != value ) {
			getIndexColRec().setOptionalShortDescription( value );
		}
	}

	@Override
	public String getOptionalDescription() {
		return( getIndexColRec().getOptionalDescription() );
	}

	@Override
	public void setOptionalDescription( String value ) {
		if( getIndexColRec().getOptionalDescription() != value ) {
			getIndexColRec().setOptionalDescription( value );
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredColumnId() {
		return( getIndexColRec().getRequiredColumnId() );
	}

	@Override
	public boolean getRequiredIsAscending() {
		return( getIndexColRec().getRequiredIsAscending() );
	}

	@Override
	public void setRequiredIsAscending( boolean value ) {
		if( getIndexColRec().getRequiredIsAscending() != value ) {
			getIndexColRec().setRequiredIsAscending( value );
		}
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( getIndexColRec().getOptionalPrevId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( getIndexColRec().getOptionalNextId() );
	}

	@Override
	public ICFBamIndexObj getRequiredContainerIndex() {
		return( getRequiredContainerIndex( false ) );
	}

	@Override
	public ICFBamIndexObj getRequiredContainerIndex( boolean forceRead ) {
		if( forceRead || ( requiredContainerIndex == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamIndexObj obj = ((ICFBamSchemaObj)getOrigAsIndexCol().getSchema()).getIndexTableObj().readIndexByIdIdx( getIndexColRec().getRequiredIndexId() );
				requiredContainerIndex = obj;
				if( obj != null ) {
					requiredContainerIndex = obj;
				}
			}
		}
		return( requiredContainerIndex );
	}

	@Override
	public void setRequiredContainerIndex( ICFBamIndexObj value ) {
		if( rec == null ) {
			getIndexColRec();
		}
		if( value != null ) {
			requiredContainerIndex = value;
			getIndexColRec().setRequiredContainerIndex(value.getIndexRec());
		}
		requiredContainerIndex = value;
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( forceRead || ( optionalLookupDefSchema == null ) ) {
			boolean anyMissing = false;
			if( getIndexColRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamSchemaDefObj obj = ((ICFBamSchemaObj)getOrigAsIndexCol().getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getIndexColRec().getOptionalDefSchemaId() );
				optionalLookupDefSchema = obj;
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public void setOptionalLookupDefSchema( ICFBamSchemaDefObj value ) {
		if( rec == null ) {
			getIndexColRec();
		}
		if( value != null ) {
			optionalLookupDefSchema = value;
			getIndexColRec().setOptionalLookupDefSchema(value.getSchemaDefRec());
		}
		else {
			optionalLookupDefSchema = null;
			getIndexColRec().setOptionalLookupDefSchema((ICFBamSchemaDef)null);
		}
		optionalLookupDefSchema = value;
	}

	@Override
	public List<ICFBamRelationColObj> getOptionalChildrenRefRelFromCol() {
		List<ICFBamRelationColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getRelationColTableObj().readRelationColByFromColIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamRelationColObj> getOptionalChildrenRefRelFromCol( boolean forceRead ) {
		List<ICFBamRelationColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getRelationColTableObj().readRelationColByFromColIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamRelationColObj> getOptionalChildrenRefRelToCol() {
		List<ICFBamRelationColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getRelationColTableObj().readRelationColByToColIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamRelationColObj> getOptionalChildrenRefRelToCol( boolean forceRead ) {
		List<ICFBamRelationColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getRelationColTableObj().readRelationColByToColIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFBamIndexColObj getOptionalLookupPrev() {
		return( getOptionalLookupPrev( false ) );
	}

	@Override
	public ICFBamIndexColObj getOptionalLookupPrev( boolean forceRead ) {
		if( forceRead || ( optionalLookupPrev == null ) ) {
			boolean anyMissing = false;
			if( getIndexColRec().getOptionalPrevId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamIndexColObj obj = ((ICFBamSchemaObj)getOrigAsIndexCol().getSchema()).getIndexColTableObj().readIndexColByIdIdx( getIndexColRec().getOptionalPrevId() );
				optionalLookupPrev = obj;
			}
		}
		return( optionalLookupPrev );
	}

	@Override
	public void setOptionalLookupPrev( ICFBamIndexColObj value ) {
		if( rec == null ) {
			getIndexColRec();
		}
		if( value != null ) {
			optionalLookupPrev = value;
			getIndexColRec().setOptionalLookupPrev(value.getIndexColRec());
		}
		else {
			optionalLookupPrev = null;
			getIndexColRec().setOptionalLookupPrev((ICFBamIndexCol)null);
		}
		optionalLookupPrev = value;
	}

	@Override
	public ICFBamIndexColObj getOptionalLookupNext() {
		return( getOptionalLookupNext( false ) );
	}

	@Override
	public ICFBamIndexColObj getOptionalLookupNext( boolean forceRead ) {
		if( forceRead || ( optionalLookupNext == null ) ) {
			boolean anyMissing = false;
			if( getIndexColRec().getOptionalNextId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamIndexColObj obj = ((ICFBamSchemaObj)getOrigAsIndexCol().getSchema()).getIndexColTableObj().readIndexColByIdIdx( getIndexColRec().getOptionalNextId() );
				optionalLookupNext = obj;
			}
		}
		return( optionalLookupNext );
	}

	@Override
	public void setOptionalLookupNext( ICFBamIndexColObj value ) {
		if( rec == null ) {
			getIndexColRec();
		}
		if( value != null ) {
			optionalLookupNext = value;
			getIndexColRec().setOptionalLookupNext(value.getIndexColRec());
		}
		else {
			optionalLookupNext = null;
			getIndexColRec().setOptionalLookupNext((ICFBamIndexCol)null);
		}
		optionalLookupNext = value;
	}

	@Override
	public ICFBamValueObj getRequiredLookupColumn() {
		return( getRequiredLookupColumn( false ) );
	}

	@Override
	public ICFBamValueObj getRequiredLookupColumn( boolean forceRead ) {
		if( forceRead || ( requiredLookupColumn == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamValueObj obj = ((ICFBamSchemaObj)getOrigAsIndexCol().getSchema()).getValueTableObj().readValueByIdIdx( getIndexColRec().getRequiredColumnId() );
				requiredLookupColumn = obj;
			}
		}
		return( requiredLookupColumn );
	}

	@Override
	public void setRequiredLookupColumn( ICFBamValueObj value ) {
		if( rec == null ) {
			getIndexColRec();
		}
		if( value != null ) {
			requiredLookupColumn = value;
			getIndexColRec().setRequiredLookupColumn(value.getValueRec());
		}
		else {
			requiredLookupColumn = null;
			getIndexColRec().setRequiredLookupColumn((ICFBamValue)null);
		}
		requiredLookupColumn = value;
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				rec.setPKey(getPKey());
			}
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				setPKey(rec.getPKey());
			}
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFBamIndexCol origRec = getOrigAsIndexCol().getIndexColRec();
		ICFBamIndexCol myRec = getIndexColRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamIndexCol origRec = getOrigAsIndexCol().getIndexColRec();
		ICFBamIndexCol myRec = getIndexColRec();
		myRec.set( origRec );
	}
}
