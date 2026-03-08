// Description: Java 25 edit object instance implementation for CFBam RelationCol.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
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
 *	
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

public class CFBamRelationColEditObj
	implements ICFBamRelationColEditObj
{
	protected ICFBamRelationColObj orig;
	protected ICFBamRelationCol rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFBamRelationObj requiredContainerRelation;
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected ICFBamRelationColObj optionalLookupPrev;
	protected ICFBamRelationColObj optionalLookupNext;
	protected ICFBamIndexColObj requiredLookupFromCol;
	protected ICFBamIndexColObj requiredLookupToCol;

	public CFBamRelationColEditObj( ICFBamRelationColObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFBamRelationCol origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerRelation = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
		requiredLookupFromCol = null;
		requiredLookupToCol = null;
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFBamRelationCol rec = getRec();
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
			ICFBamRelationCol rec = getRec();
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
		return( ((ICFBamSchemaObj)orig.getSchema()).getRelationColTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "RelationCol" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamRelationObj scope = getRequiredContainerRelation();
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
	public ICFBamRelationColObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFBamRelationColObj retobj = getSchema().getRelationColTableObj().realiseRelationCol( (ICFBamRelationColObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsRelationCol().forget();
	}

	@Override
	public ICFBamRelationColObj read() {
		ICFBamRelationColObj retval = getOrigAsRelationCol().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFBamRelationColObj read( boolean forceRead ) {
		ICFBamRelationColObj retval = getOrigAsRelationCol().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFBamRelationColObj moveUp() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUp" );
	}

	@Override
	public ICFBamRelationColObj moveDown() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDown" );
	}

	@Override
	public ICFBamRelationColObj create() {
		copyRecToOrig();
		ICFBamRelationColObj retobj = ((ICFBamSchemaObj)getOrigAsRelationCol().getSchema()).getRelationColTableObj().createRelationCol( getOrigAsRelationCol() );
		if( retobj == getOrigAsRelationCol() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamRelationColEditObj update() {
		getSchema().getRelationColTableObj().updateRelationCol( (ICFBamRelationColObj)this );
		return( null );
	}

	@Override
	public CFBamRelationColEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getRelationColTableObj().deleteRelationCol( getOrigAsRelationCol() );
		return( null );
	}

	@Override
	public ICFBamRelationColTableObj getRelationColTable() {
		return( orig.getSchema().getRelationColTableObj() );
	}

	@Override
	public ICFBamRelationColEditObj getEdit() {
		return( (ICFBamRelationColEditObj)this );
	}

	@Override
	public ICFBamRelationColEditObj getEditAsRelationCol() {
		return( (ICFBamRelationColEditObj)this );
	}

	@Override
	public ICFBamRelationColEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFBamRelationColObj getOrig() {
		return( orig );
	}

	@Override
	public ICFBamRelationColObj getOrigAsRelationCol() {
		return( (ICFBamRelationColObj)orig );
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
	public ICFBamRelationCol getRec() {
		if( rec == null ) {
			rec = getOrigAsRelationCol().getSchema().getCFBamBackingStore().getFactoryRelationCol().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamRelationCol value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerRelation = null;
			optionalLookupDefSchema = null;
			optionalLookupPrev = null;
			optionalLookupNext = null;
			requiredLookupFromCol = null;
			requiredLookupToCol = null;
		}
	}

	@Override
	public ICFBamRelationCol getRelationColRec() {
		return( (ICFBamRelationCol)getRec() );
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
	public void setRequiredId(CFLibDbKeyHash256 id) {
		if (getPKey() != id) {
			setPKey(id);
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredRelationId() {
		return( getRelationColRec().getRequiredRelationId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( getRelationColRec().getOptionalDefSchemaId() );
	}

	@Override
	public String getRequiredName() {
		return( getRelationColRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getRelationColRec().getRequiredName() != value ) {
			getRelationColRec().setRequiredName( value );
		}
	}

	@Override
	public String getOptionalShortName() {
		return( getRelationColRec().getOptionalShortName() );
	}

	@Override
	public void setOptionalShortName( String value ) {
		if( getRelationColRec().getOptionalShortName() != value ) {
			getRelationColRec().setOptionalShortName( value );
		}
	}

	@Override
	public String getOptionalLabel() {
		return( getRelationColRec().getOptionalLabel() );
	}

	@Override
	public void setOptionalLabel( String value ) {
		if( getRelationColRec().getOptionalLabel() != value ) {
			getRelationColRec().setOptionalLabel( value );
		}
	}

	@Override
	public String getOptionalShortDescription() {
		return( getRelationColRec().getOptionalShortDescription() );
	}

	@Override
	public void setOptionalShortDescription( String value ) {
		if( getRelationColRec().getOptionalShortDescription() != value ) {
			getRelationColRec().setOptionalShortDescription( value );
		}
	}

	@Override
	public String getOptionalDescription() {
		return( getRelationColRec().getOptionalDescription() );
	}

	@Override
	public void setOptionalDescription( String value ) {
		if( getRelationColRec().getOptionalDescription() != value ) {
			getRelationColRec().setOptionalDescription( value );
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredFromColId() {
		return( getRelationColRec().getRequiredFromColId() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredToColId() {
		return( getRelationColRec().getRequiredToColId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( getRelationColRec().getOptionalPrevId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( getRelationColRec().getOptionalNextId() );
	}

	@Override
	public ICFBamRelationObj getRequiredContainerRelation() {
		return( getRequiredContainerRelation( false ) );
	}

	@Override
	public ICFBamRelationObj getRequiredContainerRelation( boolean forceRead ) {
		if( forceRead || ( requiredContainerRelation == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamRelationObj obj = ((ICFBamSchemaObj)getOrigAsRelationCol().getSchema()).getRelationTableObj().readRelationByIdIdx( getRelationColRec().getRequiredRelationId() );
				requiredContainerRelation = obj;
				if( obj != null ) {
					requiredContainerRelation = obj;
				}
			}
		}
		return( requiredContainerRelation );
	}

	@Override
	public void setRequiredContainerRelation( ICFBamRelationObj value ) {
		if( rec == null ) {
			getRelationColRec();
		}
		if( value != null ) {
			requiredContainerRelation = value;
			getRelationColRec().setRequiredContainerRelation(value.getRelationRec());
		}
		requiredContainerRelation = value;
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( forceRead || ( optionalLookupDefSchema == null ) ) {
			boolean anyMissing = false;
			if( getRelationColRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamSchemaDefObj obj = ((ICFBamSchemaObj)getOrigAsRelationCol().getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getRelationColRec().getOptionalDefSchemaId() );
				optionalLookupDefSchema = obj;
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public void setOptionalLookupDefSchema( ICFBamSchemaDefObj value ) {
		if( rec == null ) {
			getRelationColRec();
		}
		if( value != null ) {
			optionalLookupDefSchema = value;
			getRelationColRec().setOptionalLookupDefSchema(value.getSchemaDefRec());
		}
		else {
			optionalLookupDefSchema = null;
			getRelationColRec().setOptionalLookupDefSchema((ICFBamSchemaDef)null);
		}
		optionalLookupDefSchema = value;
	}

	@Override
	public ICFBamRelationColObj getOptionalLookupPrev() {
		return( getOptionalLookupPrev( false ) );
	}

	@Override
	public ICFBamRelationColObj getOptionalLookupPrev( boolean forceRead ) {
		if( forceRead || ( optionalLookupPrev == null ) ) {
			boolean anyMissing = false;
			if( getRelationColRec().getOptionalPrevId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamRelationColObj obj = ((ICFBamSchemaObj)getOrigAsRelationCol().getSchema()).getRelationColTableObj().readRelationColByIdIdx( getRelationColRec().getOptionalPrevId() );
				optionalLookupPrev = obj;
			}
		}
		return( optionalLookupPrev );
	}

	@Override
	public void setOptionalLookupPrev( ICFBamRelationColObj value ) {
		if( rec == null ) {
			getRelationColRec();
		}
		if( value != null ) {
			optionalLookupPrev = value;
			getRelationColRec().setOptionalLookupPrev(value.getRelationColRec());
		}
		else {
			optionalLookupPrev = null;
			getRelationColRec().setOptionalLookupPrev((ICFBamRelationCol)null);
		}
		optionalLookupPrev = value;
	}

	@Override
	public ICFBamRelationColObj getOptionalLookupNext() {
		return( getOptionalLookupNext( false ) );
	}

	@Override
	public ICFBamRelationColObj getOptionalLookupNext( boolean forceRead ) {
		if( forceRead || ( optionalLookupNext == null ) ) {
			boolean anyMissing = false;
			if( getRelationColRec().getOptionalNextId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamRelationColObj obj = ((ICFBamSchemaObj)getOrigAsRelationCol().getSchema()).getRelationColTableObj().readRelationColByIdIdx( getRelationColRec().getOptionalNextId() );
				optionalLookupNext = obj;
			}
		}
		return( optionalLookupNext );
	}

	@Override
	public void setOptionalLookupNext( ICFBamRelationColObj value ) {
		if( rec == null ) {
			getRelationColRec();
		}
		if( value != null ) {
			optionalLookupNext = value;
			getRelationColRec().setOptionalLookupNext(value.getRelationColRec());
		}
		else {
			optionalLookupNext = null;
			getRelationColRec().setOptionalLookupNext((ICFBamRelationCol)null);
		}
		optionalLookupNext = value;
	}

	@Override
	public ICFBamIndexColObj getRequiredLookupFromCol() {
		return( getRequiredLookupFromCol( false ) );
	}

	@Override
	public ICFBamIndexColObj getRequiredLookupFromCol( boolean forceRead ) {
		if( forceRead || ( requiredLookupFromCol == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamIndexColObj obj = ((ICFBamSchemaObj)getOrigAsRelationCol().getSchema()).getIndexColTableObj().readIndexColByIdIdx( getRelationColRec().getRequiredFromColId() );
				requiredLookupFromCol = obj;
			}
		}
		return( requiredLookupFromCol );
	}

	@Override
	public void setRequiredLookupFromCol( ICFBamIndexColObj value ) {
		if( rec == null ) {
			getRelationColRec();
		}
		if( value != null ) {
			requiredLookupFromCol = value;
			getRelationColRec().setRequiredLookupFromCol(value.getIndexColRec());
		}
		else {
			requiredLookupFromCol = null;
			getRelationColRec().setRequiredLookupFromCol((ICFBamIndexCol)null);
		}
		requiredLookupFromCol = value;
	}

	@Override
	public ICFBamIndexColObj getRequiredLookupToCol() {
		return( getRequiredLookupToCol( false ) );
	}

	@Override
	public ICFBamIndexColObj getRequiredLookupToCol( boolean forceRead ) {
		if( forceRead || ( requiredLookupToCol == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamIndexColObj obj = ((ICFBamSchemaObj)getOrigAsRelationCol().getSchema()).getIndexColTableObj().readIndexColByIdIdx( getRelationColRec().getRequiredToColId() );
				requiredLookupToCol = obj;
			}
		}
		return( requiredLookupToCol );
	}

	@Override
	public void setRequiredLookupToCol( ICFBamIndexColObj value ) {
		if( rec == null ) {
			getRelationColRec();
		}
		if( value != null ) {
			requiredLookupToCol = value;
			getRelationColRec().setRequiredLookupToCol(value.getIndexColRec());
		}
		else {
			requiredLookupToCol = null;
			getRelationColRec().setRequiredLookupToCol((ICFBamIndexCol)null);
		}
		requiredLookupToCol = value;
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
		ICFBamRelationCol origRec = getOrigAsRelationCol().getRelationColRec();
		ICFBamRelationCol myRec = getRelationColRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamRelationCol origRec = getOrigAsRelationCol().getRelationColRec();
		ICFBamRelationCol myRec = getRelationColRec();
		myRec.set( origRec );
	}

	public ICFBamSubProjectObj getProject() {
		for( ICFLibAnyObj curDef = this; curDef != null; curDef = curDef.getObjScope() ) {
			if( curDef instanceof ICFBamSubProjectObj ) {
				return ((ICFBamSubProjectObj)curDef);
			}
			else if( curDef instanceof ICFBamTldObj ) {
				return( null );
			}
		}
		return( null );
	}

	public ICFBamTopDomainObj getCompany() {
		for( ICFLibAnyObj curDef = this; curDef != null; curDef = curDef.getObjScope() ) {
			if( curDef instanceof ICFBamTopDomainObj ) {
				return ((ICFBamTopDomainObj)curDef);
			}
			else if( curDef instanceof ICFBamTldObj ) {
				return( null );
			}
		}
		return( null );
	}

	public ICFBamSchemaDefObj getSchemaDef() {
		for( ICFLibAnyObj curDef = this; curDef != null; curDef = curDef.getObjScope() ) {
			if( curDef instanceof ICFBamSchemaDefObj ) {
				return ((ICFBamSchemaDefObj)curDef);
			}
			else if( curDef instanceof ICFBamTldObj ) {
				return( null );
			}
		}
		return( null );
	}

	public ICFBamMinorVersionObj getVersion() {
		for( ICFLibAnyObj curDef = this; curDef != null; curDef = curDef.getObjScope() ) {
			if( curDef instanceof ICFBamMinorVersionObj ) {
				return ((ICFBamMinorVersionObj)curDef);
			}
			else if( curDef instanceof ICFBamMajorVersionObj ) {
				return( null );
			}
			else if( curDef instanceof ICFBamSubProjectObj ) {
				return( null );
			}
			else if( curDef instanceof ICFBamTopProjectObj ) {
				return( null );
			}
			else if( curDef instanceof ICFBamTopDomainObj ) {
				return( null );
			}
			else if( curDef instanceof ICFBamTldObj ) {
				return( null );
			}
		}
		return( null );
	}

	protected String getVersionStringForVersion( ICFLibAnyObj value ) {
		String ret;
		if( value instanceof ICFBamMinorVersionObj ) {
			ret = ((ICFBamMinorVersionObj)value).getRequiredName();
		}
		else if( value instanceof ICFBamMajorVersionObj ) {
			ret = ((ICFBamMajorVersionObj)value).getRequiredName();
		}
		else {
			ret = null;
		}
		return( ret );
	}

	public String getVersionString() {
		ICFLibAnyObj scopeDef;
		ICFLibAnyObj curDef = getVersion();
		List<String> invertedNodeNames = new ArrayList<String>();
		while (curDef != null) {
			if( curDef instanceof ICFBamMinorVersionObj ) {
				invertedNodeNames.add( getVersionStringForVersion( (ICFBamMinorVersionObj)( curDef ) ) );
			}
			else if( curDef instanceof ICFBamMajorVersionObj ) {
				invertedNodeNames.add( getVersionStringForVersion( (ICFBamMajorVersionObj)( curDef ) ) );
			}
			scopeDef = curDef.getObjScope();
			if( scopeDef == null) {
				curDef = null;
			}
			else if( scopeDef instanceof ICFBamMinorVersionObj ) {
				curDef = scopeDef;
			}
			else if( scopeDef instanceof ICFBamMajorVersionObj ) {
				curDef = scopeDef;
			}
			else {
				curDef = null;
			}
		}
		String ret = "";
		for( int idx = invertedNodeNames.size() - 1; idx >= 0; idx-- )
		{
			if( ret.length() == 0) {
				ret = invertedNodeNames.get(idx);
			}
			else {
				ret = ret + "-" + invertedNodeNames.get(idx);
			}
		}
		return( ret );
	}

	public String getPackedVersionString() {
		ICFLibAnyObj scopeDef;
		ICFLibAnyObj curDef = getVersion();
		List<String> invertedNodeNames = new ArrayList<String>();
		while (curDef != null) {
			if( curDef instanceof ICFBamMinorVersionObj ) {
				invertedNodeNames.add( getVersionStringForVersion( (ICFBamMinorVersionObj)( curDef ) ) );
			}
			else if( curDef instanceof ICFBamMajorVersionObj ) {
				invertedNodeNames.add( getVersionStringForVersion( (ICFBamMajorVersionObj)( curDef ) ) );
			}
			scopeDef = curDef.getObjScope();
			if( scopeDef == null) {
				curDef = null;
			}
			else if( scopeDef instanceof ICFBamMinorVersionObj ) {
				curDef = scopeDef;
			}
			else if( scopeDef instanceof ICFBamMajorVersionObj ) {
				curDef = scopeDef;
			}
			else {
				curDef = null;
			}
		}
		String ret = "";
		for( int idx = invertedNodeNames.size() - 1; idx >= 0; idx-- )
		{
			if( ret.length() == 0) {
				ret = invertedNodeNames.get(idx);
			}
			else {
				ret = ret + invertedNodeNames.get(idx);
			}
		}
		return( ret );
	}

	public Boolean isColumnInOwnerRelation() {

		ICFLibAnyObj		focusDef;
		ICFBamTableObj		tableDef;
		final String S_ProcName = "isColumnInOwnerRelation() ";

		if( this instanceof ICFBamAtomObj ) {
			ICFBamAtomObj atomDef = (ICFBamAtomObj)this;
			ICFLibAnyObj atomScopeDef = atomDef.getObjScope();
			tableDef = (ICFBamTableObj)atomScopeDef;
			focusDef = this;
		}
		else if( this instanceof ICFBamTableColObj ) {
			ICFBamTableColObj tableColDef = (ICFBamTableColObj)this;
			ICFLibAnyObj tableColScopeDef = tableColDef.getObjScope();
			tableDef = (ICFBamTableObj)tableColScopeDef;
			focusDef = this;
		}
		else if( this instanceof ICFBamIndexColObj ) {
			ICFBamIndexColObj indexColDef = (ICFBamIndexColObj)this;
			focusDef = indexColDef.getRequiredLookupColumn();
			if( focusDef instanceof ICFBamAtomObj ) {
				tableDef = (ICFBamTableObj)((ICFBamAtomObj)focusDef).getObjScope();
			}
			else if( focusDef instanceof ICFBamTableColObj ) {
				tableDef = (ICFBamTableObj)((ICFBamTableColObj)focusDef).getObjScope();
			}
			else {
				throw new RuntimeException(
					S_ProcName + "genContext.getGenDef().getColumnDef() for a ICFBamIndexColObj did not return a ICFBamAtomObj"
						+	" nor a ICFBamTableColObj" );
			}
		}
		else if( this instanceof ICFBamRelationColObj ) {
			ICFBamRelationColObj relColDef = (ICFBamRelationColObj)this;
			ICFLibAnyObj columnDef = relColDef.getRequiredLookupFromCol();
			if( columnDef instanceof ICFBamAtomObj ) {
				focusDef = columnDef;
				tableDef = (ICFBamTableObj)columnDef.getObjScope();
			}
			else if( columnDef instanceof ICFBamTableColObj ) {
				focusDef = columnDef;
				tableDef = (ICFBamTableObj)columnDef.getObjScope();
			}
			else {
				throw new RuntimeException(
					S_ProcName + "genContext.getGenDef().getFromColumnDef() for a ICFBamIndexColObj did not return a ICFBamAtomObj"
						+	" nor a ICFBamTableColObj" );
			}
		}
		else {
			throw new RuntimeException(
				S_ProcName + "genContext.getGenDef() did not return a ICFBamAtomObj, ICFBamTableColObj, nor ICFBamIndexColObj instance" );
		}

		List<ICFBamRelationObj> ownerRelations = tableDef.getContainerOwnerRelations();
		if( ( ownerRelations == null )
		 || ( ( ownerRelations != null ) && ( ownerRelations.size() == 0 ) ) )
		{
			return( false );
		}

		ListIterator<ICFBamRelationObj> ownerEnumerator = ownerRelations.listIterator();

		ICFBamRelationObj ownerRelation;
		ICFBamRelationColObj ownerRelationCol;
		Iterator<ICFBamRelationColObj> ownerRelationCols;

		while( ownerEnumerator.hasNext() ) {

			ownerRelation = ownerEnumerator.next();
			ownerRelationCols = ownerRelation.getOptionalComponentsColumns().iterator();

			while( ownerRelationCols.hasNext() ) {
				ownerRelationCol = ownerRelationCols.next();
				if( ownerRelationCol.getRequiredLookupFromCol() == focusDef ) {
					return( true );
				}
			}
		}

		return( false );
	}
}
