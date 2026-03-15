// Description: Java 25 edit object instance implementation for CFBam Param.

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

public class CFBamParamEditObj
	implements ICFBamParamEditObj
{
	protected ICFBamParamObj orig;
	protected ICFBamParam rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFBamServerMethodObj requiredContainerServerMeth;
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected ICFBamParamObj optionalLookupPrev;
	protected ICFBamParamObj optionalLookupNext;
	protected ICFBamValueObj requiredLookupType;

	public CFBamParamEditObj( ICFBamParamObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFBamParam origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerServerMeth = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
		requiredLookupType = null;
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFBamParam rec = getRec();
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
			ICFBamParam rec = getRec();
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
		return( ((ICFBamSchemaObj)orig.getSchema()).getParamTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "Param" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamServerMethodObj scope = getRequiredContainerServerMeth();
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
	public ICFBamParamObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFBamParamObj retobj = getSchema().getParamTableObj().realiseParam( (ICFBamParamObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsParam().forget();
	}

	@Override
	public ICFBamParamObj read() {
		ICFBamParamObj retval = getOrigAsParam().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFBamParamObj read( boolean forceRead ) {
		ICFBamParamObj retval = getOrigAsParam().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFBamParamObj moveUp() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUp" );
	}

	@Override
	public ICFBamParamObj moveDown() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDown" );
	}

	@Override
	public ICFBamParamObj create() {
		copyRecToOrig();
		ICFBamParamObj retobj = ((ICFBamSchemaObj)getOrigAsParam().getSchema()).getParamTableObj().createParam( getOrigAsParam() );
		if( retobj == getOrigAsParam() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamParamEditObj update() {
		getSchema().getParamTableObj().updateParam( (ICFBamParamObj)this );
		return( null );
	}

	@Override
	public CFBamParamEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getParamTableObj().deleteParam( getOrigAsParam() );
		return( null );
	}

	@Override
	public ICFBamParamTableObj getParamTable() {
		return( orig.getSchema().getParamTableObj() );
	}

	@Override
	public ICFBamParamEditObj getEdit() {
		return( (ICFBamParamEditObj)this );
	}

	@Override
	public ICFBamParamEditObj getEditAsParam() {
		return( (ICFBamParamEditObj)this );
	}

	@Override
	public ICFBamParamEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFBamParamObj getOrig() {
		return( orig );
	}

	@Override
	public ICFBamParamObj getOrigAsParam() {
		return( (ICFBamParamObj)orig );
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
	public ICFBamParam getRec() {
		if( rec == null ) {
			rec = getOrigAsParam().getSchema().getCFBamBackingStore().getFactoryParam().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamParam value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerServerMeth = null;
			optionalLookupDefSchema = null;
			optionalLookupPrev = null;
			optionalLookupNext = null;
			requiredLookupType = null;
		}
	}

	@Override
	public ICFBamParam getParamRec() {
		return( (ICFBamParam)getRec() );
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
	public CFLibDbKeyHash256 getRequiredServerMethodId() {
		return( getParamRec().getRequiredServerMethodId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( getParamRec().getOptionalDefSchemaId() );
	}

	@Override
	public String getRequiredName() {
		return( getParamRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getParamRec().getRequiredName() != value ) {
			getParamRec().setRequiredName( value );
		}
	}

	@Override
	public String getOptionalShortDescription() {
		return( getParamRec().getOptionalShortDescription() );
	}

	@Override
	public void setOptionalShortDescription( String value ) {
		if( getParamRec().getOptionalShortDescription() != value ) {
			getParamRec().setOptionalShortDescription( value );
		}
	}

	@Override
	public String getOptionalDescription() {
		return( getParamRec().getOptionalDescription() );
	}

	@Override
	public void setOptionalDescription( String value ) {
		if( getParamRec().getOptionalDescription() != value ) {
			getParamRec().setOptionalDescription( value );
		}
	}

	@Override
	public boolean getRequiredIsNullable() {
		return( getParamRec().getRequiredIsNullable() );
	}

	@Override
	public void setRequiredIsNullable( boolean value ) {
		if( getParamRec().getRequiredIsNullable() != value ) {
			getParamRec().setRequiredIsNullable( value );
		}
	}

	@Override
	public CFLibDbKeyHash256 getOptionalTypeId() {
		return( getParamRec().getOptionalTypeId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( getParamRec().getOptionalPrevId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( getParamRec().getOptionalNextId() );
	}

	@Override
	public ICFBamServerMethodObj getRequiredContainerServerMeth() {
		return( getRequiredContainerServerMeth( false ) );
	}

	@Override
	public ICFBamServerMethodObj getRequiredContainerServerMeth( boolean forceRead ) {
		if( forceRead || ( requiredContainerServerMeth == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamServerMethodObj obj = ((ICFBamSchemaObj)getOrigAsParam().getSchema()).getServerMethodTableObj().readServerMethodByIdIdx( getParamRec().getRequiredServerMethodId() );
				requiredContainerServerMeth = obj;
				if( obj != null ) {
					requiredContainerServerMeth = obj;
				}
			}
		}
		return( requiredContainerServerMeth );
	}

	@Override
	public void setRequiredContainerServerMeth( ICFBamServerMethodObj value ) {
		if( rec == null ) {
			getParamRec();
		}
		if( value != null ) {
			requiredContainerServerMeth = value;
			getParamRec().setRequiredContainerServerMeth(value.getServerMethodRec());
		}
		requiredContainerServerMeth = value;
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( forceRead || ( optionalLookupDefSchema == null ) ) {
			boolean anyMissing = false;
			if( getParamRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamSchemaDefObj obj = ((ICFBamSchemaObj)getOrigAsParam().getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getParamRec().getOptionalDefSchemaId() );
				optionalLookupDefSchema = obj;
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public void setOptionalLookupDefSchema( ICFBamSchemaDefObj value ) {
		if( rec == null ) {
			getParamRec();
		}
		if( value != null ) {
			optionalLookupDefSchema = value;
			getParamRec().setOptionalLookupDefSchema(value.getSchemaDefRec());
		}
		else {
			optionalLookupDefSchema = null;
			getParamRec().setOptionalLookupDefSchema((ICFBamSchemaDef)null);
		}
		optionalLookupDefSchema = value;
	}

	@Override
	public ICFBamParamObj getOptionalLookupPrev() {
		return( getOptionalLookupPrev( false ) );
	}

	@Override
	public ICFBamParamObj getOptionalLookupPrev( boolean forceRead ) {
		if( forceRead || ( optionalLookupPrev == null ) ) {
			boolean anyMissing = false;
			if( getParamRec().getOptionalPrevId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamParamObj obj = ((ICFBamSchemaObj)getOrigAsParam().getSchema()).getParamTableObj().readParamByIdIdx( getParamRec().getOptionalPrevId() );
				optionalLookupPrev = obj;
			}
		}
		return( optionalLookupPrev );
	}

	@Override
	public void setOptionalLookupPrev( ICFBamParamObj value ) {
		if( rec == null ) {
			getParamRec();
		}
		if( value != null ) {
			optionalLookupPrev = value;
			getParamRec().setOptionalLookupPrev(value.getParamRec());
		}
		else {
			optionalLookupPrev = null;
			getParamRec().setOptionalLookupPrev((ICFBamParam)null);
		}
		optionalLookupPrev = value;
	}

	@Override
	public ICFBamParamObj getOptionalLookupNext() {
		return( getOptionalLookupNext( false ) );
	}

	@Override
	public ICFBamParamObj getOptionalLookupNext( boolean forceRead ) {
		if( forceRead || ( optionalLookupNext == null ) ) {
			boolean anyMissing = false;
			if( getParamRec().getOptionalNextId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamParamObj obj = ((ICFBamSchemaObj)getOrigAsParam().getSchema()).getParamTableObj().readParamByIdIdx( getParamRec().getOptionalNextId() );
				optionalLookupNext = obj;
			}
		}
		return( optionalLookupNext );
	}

	@Override
	public void setOptionalLookupNext( ICFBamParamObj value ) {
		if( rec == null ) {
			getParamRec();
		}
		if( value != null ) {
			optionalLookupNext = value;
			getParamRec().setOptionalLookupNext(value.getParamRec());
		}
		else {
			optionalLookupNext = null;
			getParamRec().setOptionalLookupNext((ICFBamParam)null);
		}
		optionalLookupNext = value;
	}

	@Override
	public ICFBamValueObj getRequiredLookupType() {
		return( getRequiredLookupType( false ) );
	}

	@Override
	public ICFBamValueObj getRequiredLookupType( boolean forceRead ) {
		if( forceRead || ( requiredLookupType == null ) ) {
			boolean anyMissing = false;
			if( getParamRec().getOptionalTypeId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamValueObj obj = ((ICFBamSchemaObj)getOrigAsParam().getSchema()).getValueTableObj().readValueByIdIdx( getParamRec().getOptionalTypeId() );
				requiredLookupType = obj;
			}
		}
		return( requiredLookupType );
	}

	@Override
	public void setRequiredLookupType( ICFBamValueObj value ) {
		if( rec == null ) {
			getParamRec();
		}
		if( value != null ) {
			requiredLookupType = value;
			getParamRec().setRequiredLookupType(value.getValueRec());
		}
		else {
			requiredLookupType = null;
			getParamRec().setRequiredLookupType((ICFBamValue)null);
		}
		requiredLookupType = value;
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
		ICFBamParam origRec = getOrigAsParam().getParamRec();
		ICFBamParam myRec = getParamRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamParam origRec = getOrigAsParam().getParamRec();
		ICFBamParam myRec = getParamRec();
		myRec.set( origRec );
	}
}
