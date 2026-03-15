// Description: Java 25 edit object instance implementation for CFBam EnumTag.

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

public class CFBamEnumTagEditObj
	implements ICFBamEnumTagEditObj
{
	protected ICFBamEnumTagObj orig;
	protected ICFBamEnumTag rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFBamEnumDefObj requiredContainerEnumDef;
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected ICFBamEnumTagObj optionalLookupPrev;
	protected ICFBamEnumTagObj optionalLookupNext;

	public CFBamEnumTagEditObj( ICFBamEnumTagObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFBamEnumTag origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerEnumDef = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFBamEnumTag rec = getRec();
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
			ICFBamEnumTag rec = getRec();
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
		return( ((ICFBamSchemaObj)orig.getSchema()).getEnumTagTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "EnumTag" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamEnumDefObj scope = getRequiredContainerEnumDef();
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
	public ICFBamEnumTagObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFBamEnumTagObj retobj = getSchema().getEnumTagTableObj().realiseEnumTag( (ICFBamEnumTagObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsEnumTag().forget();
	}

	@Override
	public ICFBamEnumTagObj read() {
		ICFBamEnumTagObj retval = getOrigAsEnumTag().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFBamEnumTagObj read( boolean forceRead ) {
		ICFBamEnumTagObj retval = getOrigAsEnumTag().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFBamEnumTagObj moveUp() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUp" );
	}

	@Override
	public ICFBamEnumTagObj moveDown() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDown" );
	}

	@Override
	public ICFBamEnumTagObj create() {
		copyRecToOrig();
		ICFBamEnumTagObj retobj = ((ICFBamSchemaObj)getOrigAsEnumTag().getSchema()).getEnumTagTableObj().createEnumTag( getOrigAsEnumTag() );
		if( retobj == getOrigAsEnumTag() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamEnumTagEditObj update() {
		getSchema().getEnumTagTableObj().updateEnumTag( (ICFBamEnumTagObj)this );
		return( null );
	}

	@Override
	public CFBamEnumTagEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getEnumTagTableObj().deleteEnumTag( getOrigAsEnumTag() );
		return( null );
	}

	@Override
	public ICFBamEnumTagTableObj getEnumTagTable() {
		return( orig.getSchema().getEnumTagTableObj() );
	}

	@Override
	public ICFBamEnumTagEditObj getEdit() {
		return( (ICFBamEnumTagEditObj)this );
	}

	@Override
	public ICFBamEnumTagEditObj getEditAsEnumTag() {
		return( (ICFBamEnumTagEditObj)this );
	}

	@Override
	public ICFBamEnumTagEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFBamEnumTagObj getOrig() {
		return( orig );
	}

	@Override
	public ICFBamEnumTagObj getOrigAsEnumTag() {
		return( (ICFBamEnumTagObj)orig );
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
	public ICFBamEnumTag getRec() {
		if( rec == null ) {
			rec = getOrigAsEnumTag().getSchema().getCFBamBackingStore().getFactoryEnumTag().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamEnumTag value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerEnumDef = null;
			optionalLookupDefSchema = null;
			optionalLookupPrev = null;
			optionalLookupNext = null;
		}
	}

	@Override
	public ICFBamEnumTag getEnumTagRec() {
		return( (ICFBamEnumTag)getRec() );
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
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( getEnumTagRec().getOptionalDefSchemaId() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredEnumId() {
		return( getEnumTagRec().getRequiredEnumId() );
	}

	@Override
	public Short getOptionalEnumCode() {
		return( getEnumTagRec().getOptionalEnumCode() );
	}

	@Override
	public void setOptionalEnumCode( Short value ) {
		if( getEnumTagRec().getOptionalEnumCode() != value ) {
			getEnumTagRec().setOptionalEnumCode( value );
		}
	}

	@Override
	public String getRequiredName() {
		return( getEnumTagRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getEnumTagRec().getRequiredName() != value ) {
			getEnumTagRec().setRequiredName( value );
		}
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( getEnumTagRec().getOptionalPrevId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( getEnumTagRec().getOptionalNextId() );
	}

	@Override
	public ICFBamEnumDefObj getRequiredContainerEnumDef() {
		return( getRequiredContainerEnumDef( false ) );
	}

	@Override
	public ICFBamEnumDefObj getRequiredContainerEnumDef( boolean forceRead ) {
		if( forceRead || ( requiredContainerEnumDef == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamEnumDefObj obj = ((ICFBamSchemaObj)getOrigAsEnumTag().getSchema()).getEnumDefTableObj().readEnumDefByIdIdx( getEnumTagRec().getRequiredEnumId() );
				requiredContainerEnumDef = obj;
				if( obj != null ) {
					requiredContainerEnumDef = obj;
				}
			}
		}
		return( requiredContainerEnumDef );
	}

	@Override
	public void setRequiredContainerEnumDef( ICFBamEnumDefObj value ) {
		if( rec == null ) {
			getEnumTagRec();
		}
		if( value != null ) {
			requiredContainerEnumDef = value;
			getEnumTagRec().setRequiredContainerEnumDef(value.getEnumDefRec());
		}
		requiredContainerEnumDef = value;
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( forceRead || ( optionalLookupDefSchema == null ) ) {
			boolean anyMissing = false;
			if( getEnumTagRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamSchemaDefObj obj = ((ICFBamSchemaObj)getOrigAsEnumTag().getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getEnumTagRec().getOptionalDefSchemaId() );
				optionalLookupDefSchema = obj;
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public void setOptionalLookupDefSchema( ICFBamSchemaDefObj value ) {
		if( rec == null ) {
			getEnumTagRec();
		}
		if( value != null ) {
			optionalLookupDefSchema = value;
			getEnumTagRec().setOptionalLookupDefSchema(value.getSchemaDefRec());
		}
		else {
			optionalLookupDefSchema = null;
			getEnumTagRec().setOptionalLookupDefSchema((ICFBamSchemaDef)null);
		}
		optionalLookupDefSchema = value;
	}

	@Override
	public ICFBamEnumTagObj getOptionalLookupPrev() {
		return( getOptionalLookupPrev( false ) );
	}

	@Override
	public ICFBamEnumTagObj getOptionalLookupPrev( boolean forceRead ) {
		if( forceRead || ( optionalLookupPrev == null ) ) {
			boolean anyMissing = false;
			if( getEnumTagRec().getOptionalPrevId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamEnumTagObj obj = ((ICFBamSchemaObj)getOrigAsEnumTag().getSchema()).getEnumTagTableObj().readEnumTagByIdIdx( getEnumTagRec().getOptionalPrevId() );
				optionalLookupPrev = obj;
			}
		}
		return( optionalLookupPrev );
	}

	@Override
	public void setOptionalLookupPrev( ICFBamEnumTagObj value ) {
		if( rec == null ) {
			getEnumTagRec();
		}
		if( value != null ) {
			optionalLookupPrev = value;
			getEnumTagRec().setOptionalLookupPrev(value.getEnumTagRec());
		}
		else {
			optionalLookupPrev = null;
			getEnumTagRec().setOptionalLookupPrev((ICFBamEnumTag)null);
		}
		optionalLookupPrev = value;
	}

	@Override
	public ICFBamEnumTagObj getOptionalLookupNext() {
		return( getOptionalLookupNext( false ) );
	}

	@Override
	public ICFBamEnumTagObj getOptionalLookupNext( boolean forceRead ) {
		if( forceRead || ( optionalLookupNext == null ) ) {
			boolean anyMissing = false;
			if( getEnumTagRec().getOptionalNextId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamEnumTagObj obj = ((ICFBamSchemaObj)getOrigAsEnumTag().getSchema()).getEnumTagTableObj().readEnumTagByIdIdx( getEnumTagRec().getOptionalNextId() );
				optionalLookupNext = obj;
			}
		}
		return( optionalLookupNext );
	}

	@Override
	public void setOptionalLookupNext( ICFBamEnumTagObj value ) {
		if( rec == null ) {
			getEnumTagRec();
		}
		if( value != null ) {
			optionalLookupNext = value;
			getEnumTagRec().setOptionalLookupNext(value.getEnumTagRec());
		}
		else {
			optionalLookupNext = null;
			getEnumTagRec().setOptionalLookupNext((ICFBamEnumTag)null);
		}
		optionalLookupNext = value;
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
		ICFBamEnumTag origRec = getOrigAsEnumTag().getEnumTagRec();
		ICFBamEnumTag myRec = getEnumTagRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamEnumTag origRec = getOrigAsEnumTag().getEnumTagRec();
		ICFBamEnumTag myRec = getEnumTagRec();
		myRec.set( origRec );
	}
}
