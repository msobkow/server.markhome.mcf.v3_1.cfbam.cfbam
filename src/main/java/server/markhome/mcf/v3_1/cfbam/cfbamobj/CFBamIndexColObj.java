// Description: Java 25 base object instance implementation for IndexCol

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

public class CFBamIndexColObj
	implements ICFBamIndexColObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFBamIndexColEditObj edit;
	protected ICFBamSchemaObj schema;
	protected CFLibDbKeyHash256 pKey;
	protected ICFBamIndexCol rec;
	protected ICFBamIndexObj requiredContainerIndex;
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected List<ICFBamRelationColObj> optionalChildrenRefRelFromCol;
	protected List<ICFBamRelationColObj> optionalChildrenRefRelToCol;
	protected ICFBamIndexColObj optionalLookupPrev;
	protected ICFBamIndexColObj optionalLookupNext;
	protected ICFBamValueObj requiredLookupColumn;

	public CFBamIndexColObj() {
		isNew = true;
		requiredContainerIndex = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
		requiredLookupColumn = null;
	}

	public CFBamIndexColObj( ICFBamSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredContainerIndex = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
		requiredLookupColumn = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getIndexColTableObj().getClassCode() );
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
		ICFBamIndexColObj retobj = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().realiseIndexCol(
			(ICFBamIndexColObj)this );
		return( (ICFBamIndexColObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getIndexColTableObj().reallyDeepDisposeIndexCol( (ICFBamIndexColObj)this );
	}

	@Override
	public ICFBamIndexColObj read() {
		ICFBamIndexColObj retobj = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().readIndexColByIdIdx( getPKey(), false );
		return( (ICFBamIndexColObj)retobj );
	}

	@Override
	public ICFBamIndexColObj read( boolean forceRead ) {
		ICFBamIndexColObj retobj = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().readIndexColByIdIdx( getPKey(), forceRead );
		return( (ICFBamIndexColObj)retobj );
	}

	@Override
	public ICFBamIndexColObj moveUp() {
		ICFBamIndexColObj retobj = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().moveUpIndexCol( this );
		return( (ICFBamIndexColObj)retobj );
	}

	@Override
	public ICFBamIndexColObj moveDown() {
		ICFBamIndexColObj retobj = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().moveDownIndexCol( this );
		return( (ICFBamIndexColObj)retobj );
	}

	@Override
	public ICFBamIndexColTableObj getIndexColTable() {
		return( ((ICFBamSchemaObj)getSchema()).getIndexColTableObj() );
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
	public ICFBamIndexCol getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactoryIndexCol().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableIndexCol().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamIndexCol value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamIndexCol ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamIndexColRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerIndex = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
		requiredLookupColumn = null;
	}

	@Override
	public ICFBamIndexCol getIndexColRec() {
		return( (ICFBamIndexCol)getRec() );
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
	public ICFBamIndexColEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamIndexColObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamIndexColObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().lockIndexCol( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().newEditInstance( lockobj );
		return( (ICFBamIndexColEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFBamIndexColEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFBamIndexColEditObj getEditAsIndexCol() {
		return( (ICFBamIndexColEditObj)edit );
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
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public ICFBamIndexObj getRequiredContainerIndex() {
		return( getRequiredContainerIndex( false ) );
	}

	@Override
	public ICFBamIndexObj getRequiredContainerIndex( boolean forceRead ) {
		if( ( requiredContainerIndex == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerIndex = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().readIndexByIdIdx( getIndexColRec().getRequiredIndexId(), forceRead );
			}
		}
		return( requiredContainerIndex );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( ( optionalLookupDefSchema == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getIndexColRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupDefSchema = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getIndexColRec().getOptionalDefSchemaId(), forceRead );
			}
		}
		return( optionalLookupDefSchema );
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
		if( ( optionalLookupPrev == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getIndexColRec().getOptionalPrevId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupPrev = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().readIndexColByIdIdx( getIndexColRec().getOptionalPrevId(), forceRead );
			}
		}
		return( optionalLookupPrev );
	}

	@Override
	public ICFBamIndexColObj getOptionalLookupNext() {
		return( getOptionalLookupNext( false ) );
	}

	@Override
	public ICFBamIndexColObj getOptionalLookupNext( boolean forceRead ) {
		if( ( optionalLookupNext == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getIndexColRec().getOptionalNextId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupNext = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().readIndexColByIdIdx( getIndexColRec().getOptionalNextId(), forceRead );
			}
		}
		return( optionalLookupNext );
	}

	@Override
	public ICFBamValueObj getRequiredLookupColumn() {
		return( getRequiredLookupColumn( false ) );
	}

	@Override
	public ICFBamValueObj getRequiredLookupColumn( boolean forceRead ) {
		if( ( requiredLookupColumn == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredLookupColumn = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByIdIdx( getIndexColRec().getRequiredColumnId(), forceRead );
			}
		}
		return( requiredLookupColumn );
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
	public String getOptionalShortName() {
		return( getIndexColRec().getOptionalShortName() );
	}

	@Override
	public String getOptionalLabel() {
		return( getIndexColRec().getOptionalLabel() );
	}

	@Override
	public String getOptionalShortDescription() {
		return( getIndexColRec().getOptionalShortDescription() );
	}

	@Override
	public String getOptionalDescription() {
		return( getIndexColRec().getOptionalDescription() );
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
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( getIndexColRec().getOptionalPrevId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( getIndexColRec().getOptionalNextId() );
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
