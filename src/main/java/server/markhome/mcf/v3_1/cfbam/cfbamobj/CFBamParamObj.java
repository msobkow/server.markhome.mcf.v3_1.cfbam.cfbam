// Description: Java 25 base object instance implementation for Param

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

public class CFBamParamObj
	implements ICFBamParamObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFBamParamEditObj edit;
	protected ICFBamSchemaObj schema;
	protected CFLibDbKeyHash256 pKey;
	protected ICFBamParam rec;
	protected ICFBamServerMethodObj requiredContainerServerMeth;
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected ICFBamParamObj optionalLookupPrev;
	protected ICFBamParamObj optionalLookupNext;
	protected ICFBamValueObj requiredLookupType;

	public CFBamParamObj() {
		isNew = true;
		requiredContainerServerMeth = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
		requiredLookupType = null;
	}

	public CFBamParamObj( ICFBamSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredContainerServerMeth = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
		requiredLookupType = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getParamTableObj().getClassCode() );
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
		ICFBamParamObj retobj = ((ICFBamSchemaObj)getSchema()).getParamTableObj().realiseParam(
			(ICFBamParamObj)this );
		return( (ICFBamParamObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getParamTableObj().reallyDeepDisposeParam( (ICFBamParamObj)this );
	}

	@Override
	public ICFBamParamObj read() {
		ICFBamParamObj retobj = ((ICFBamSchemaObj)getSchema()).getParamTableObj().readParamByIdIdx( getPKey(), false );
		return( (ICFBamParamObj)retobj );
	}

	@Override
	public ICFBamParamObj read( boolean forceRead ) {
		ICFBamParamObj retobj = ((ICFBamSchemaObj)getSchema()).getParamTableObj().readParamByIdIdx( getPKey(), forceRead );
		return( (ICFBamParamObj)retobj );
	}

	@Override
	public ICFBamParamObj moveUp() {
		ICFBamParamObj retobj = ((ICFBamSchemaObj)getSchema()).getParamTableObj().moveUpParam( this );
		return( (ICFBamParamObj)retobj );
	}

	@Override
	public ICFBamParamObj moveDown() {
		ICFBamParamObj retobj = ((ICFBamSchemaObj)getSchema()).getParamTableObj().moveDownParam( this );
		return( (ICFBamParamObj)retobj );
	}

	@Override
	public ICFBamParamTableObj getParamTable() {
		return( ((ICFBamSchemaObj)getSchema()).getParamTableObj() );
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = value;
	}

	@Override
	public ICFBamParam getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactoryParam().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableParam().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamParam value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamParam ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamParamRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerServerMeth = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
		requiredLookupType = null;
	}

	@Override
	public ICFBamParam getParamRec() {
		return( (ICFBamParam)getRec() );
	}

	@Override
	public CFLibDbKeyHash256 getPKey() {
		return( pKey );
	}

	@Override
	public void setPKey( CFLibDbKeyHash256 value ) {
		if( pKey != value ) {
       		pKey = value;
			copyPKeyToRec();
		}
	}

	@Override
	public boolean getIsNew() {
		return( isNew );
	}

	@Override
	public void setIsNew( boolean value ) {
		isNew = value;
	}

	@Override
	public ICFBamParamEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamParamObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamParamObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getParamTableObj().lockParam( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getParamTableObj().newEditInstance( lockobj );
		return( (ICFBamParamEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFBamParamEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFBamParamEditObj getEditAsParam() {
		return( (ICFBamParamEditObj)edit );
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
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public ICFBamServerMethodObj getRequiredContainerServerMeth() {
		return( getRequiredContainerServerMeth( false ) );
	}

	@Override
	public ICFBamServerMethodObj getRequiredContainerServerMeth( boolean forceRead ) {
		if( ( requiredContainerServerMeth == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerServerMeth = ((ICFBamSchemaObj)getSchema()).getServerMethodTableObj().readServerMethodByIdIdx( getParamRec().getRequiredServerMethodId(), forceRead );
			}
		}
		return( requiredContainerServerMeth );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( ( optionalLookupDefSchema == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getParamRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupDefSchema = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getParamRec().getOptionalDefSchemaId(), forceRead );
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public ICFBamParamObj getOptionalLookupPrev() {
		return( getOptionalLookupPrev( false ) );
	}

	@Override
	public ICFBamParamObj getOptionalLookupPrev( boolean forceRead ) {
		if( ( optionalLookupPrev == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getParamRec().getOptionalPrevId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupPrev = ((ICFBamSchemaObj)getSchema()).getParamTableObj().readParamByIdIdx( getParamRec().getOptionalPrevId(), forceRead );
			}
		}
		return( optionalLookupPrev );
	}

	@Override
	public ICFBamParamObj getOptionalLookupNext() {
		return( getOptionalLookupNext( false ) );
	}

	@Override
	public ICFBamParamObj getOptionalLookupNext( boolean forceRead ) {
		if( ( optionalLookupNext == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getParamRec().getOptionalNextId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupNext = ((ICFBamSchemaObj)getSchema()).getParamTableObj().readParamByIdIdx( getParamRec().getOptionalNextId(), forceRead );
			}
		}
		return( optionalLookupNext );
	}

	@Override
	public ICFBamValueObj getRequiredLookupType() {
		return( getRequiredLookupType( false ) );
	}

	@Override
	public ICFBamValueObj getRequiredLookupType( boolean forceRead ) {
		if( ( requiredLookupType == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getParamRec().getOptionalTypeId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				requiredLookupType = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByIdIdx( getParamRec().getOptionalTypeId(), forceRead );
			}
		}
		return( requiredLookupType );
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
	public String getOptionalShortDescription() {
		return( getParamRec().getOptionalShortDescription() );
	}

	@Override
	public String getOptionalDescription() {
		return( getParamRec().getOptionalDescription() );
	}

	@Override
	public boolean getRequiredIsNullable() {
		return( getParamRec().getRequiredIsNullable() );
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
	public void copyPKeyToRec() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				rec.setPKey(getPKey());
			}
		}
		if( edit != null ) {
			edit.copyPKeyToRec();
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
}
