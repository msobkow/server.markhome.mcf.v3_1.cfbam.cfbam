// Description: Java 25 base object instance implementation for Chain

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

public class CFBamChainObj
	implements ICFBamChainObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFBamChainEditObj edit;
	protected ICFBamSchemaObj schema;
	protected CFLibDbKeyHash256 pKey;
	protected ICFBamChain rec;
	protected ICFBamTableObj requiredContainerTable;
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected ICFBamRelationObj requiredLookupPrevRel;
	protected ICFBamRelationObj requiredLookupNextRel;

	public CFBamChainObj() {
		isNew = true;
		requiredContainerTable = null;
		optionalLookupDefSchema = null;
		requiredLookupPrevRel = null;
		requiredLookupNextRel = null;
	}

	public CFBamChainObj( ICFBamSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredContainerTable = null;
		optionalLookupDefSchema = null;
		requiredLookupPrevRel = null;
		requiredLookupNextRel = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getChainTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "Chain" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamTableObj scope = getRequiredContainerTable();
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
	public ICFBamChainObj realise() {
		ICFBamChainObj retobj = ((ICFBamSchemaObj)getSchema()).getChainTableObj().realiseChain(
			(ICFBamChainObj)this );
		return( (ICFBamChainObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getChainTableObj().reallyDeepDisposeChain( (ICFBamChainObj)this );
	}

	@Override
	public ICFBamChainObj read() {
		ICFBamChainObj retobj = ((ICFBamSchemaObj)getSchema()).getChainTableObj().readChainByIdIdx( getPKey(), false );
		return( (ICFBamChainObj)retobj );
	}

	@Override
	public ICFBamChainObj read( boolean forceRead ) {
		ICFBamChainObj retobj = ((ICFBamSchemaObj)getSchema()).getChainTableObj().readChainByIdIdx( getPKey(), forceRead );
		return( (ICFBamChainObj)retobj );
	}

	@Override
	public ICFBamChainTableObj getChainTable() {
		return( ((ICFBamSchemaObj)getSchema()).getChainTableObj() );
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
	public ICFBamChain getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactoryChain().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableChain().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamChain value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamChain ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamChainRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerTable = null;
		optionalLookupDefSchema = null;
		requiredLookupPrevRel = null;
		requiredLookupNextRel = null;
	}

	@Override
	public ICFBamChain getChainRec() {
		return( (ICFBamChain)getRec() );
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
	public ICFBamChainEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamChainObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamChainObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getChainTableObj().lockChain( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getChainTableObj().newEditInstance( lockobj );
		return( (ICFBamChainEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFBamChainEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFBamChainEditObj getEditAsChain() {
		return( (ICFBamChainEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFBamChain rec = getRec();
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
			ICFBamChain rec = getRec();
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
	public ICFBamTableObj getRequiredContainerTable() {
		return( getRequiredContainerTable( false ) );
	}

	@Override
	public ICFBamTableObj getRequiredContainerTable( boolean forceRead ) {
		if( ( requiredContainerTable == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerTable = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableByIdIdx( getChainRec().getRequiredTableId(), forceRead );
			}
		}
		return( requiredContainerTable );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( ( optionalLookupDefSchema == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getChainRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupDefSchema = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getChainRec().getOptionalDefSchemaId(), forceRead );
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public ICFBamRelationObj getRequiredLookupPrevRel() {
		return( getRequiredLookupPrevRel( false ) );
	}

	@Override
	public ICFBamRelationObj getRequiredLookupPrevRel( boolean forceRead ) {
		if( ( requiredLookupPrevRel == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredLookupPrevRel = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().readRelationByIdIdx( getChainRec().getRequiredPrevRelationId(), forceRead );
			}
		}
		return( requiredLookupPrevRel );
	}

	@Override
	public ICFBamRelationObj getRequiredLookupNextRel() {
		return( getRequiredLookupNextRel( false ) );
	}

	@Override
	public ICFBamRelationObj getRequiredLookupNextRel( boolean forceRead ) {
		if( ( requiredLookupNextRel == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredLookupNextRel = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().readRelationByIdIdx( getChainRec().getRequiredNextRelationId(), forceRead );
			}
		}
		return( requiredLookupNextRel );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( getChainRec().getOptionalDefSchemaId() );
	}

	@Override
	public String getRequiredName() {
		return( getChainRec().getRequiredName() );
	}

	@Override
	public String getOptionalShortName() {
		return( getChainRec().getOptionalShortName() );
	}

	@Override
	public String getOptionalLabel() {
		return( getChainRec().getOptionalLabel() );
	}

	@Override
	public String getOptionalShortDescription() {
		return( getChainRec().getOptionalShortDescription() );
	}

	@Override
	public String getOptionalDescription() {
		return( getChainRec().getOptionalDescription() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTableId() {
		return( getChainRec().getRequiredTableId() );
	}

	@Override
	public String getOptionalSuffix() {
		return( getChainRec().getOptionalSuffix() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredPrevRelationId() {
		return( getChainRec().getRequiredPrevRelationId() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredNextRelationId() {
		return( getChainRec().getRequiredNextRelationId() );
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
				invertedNodeNames.add( getVersionStringForVersion( (ICFBamMinorVersionObj) curDef ) );
			}
			else if( curDef instanceof ICFBamMajorVersionObj ) {
				invertedNodeNames.add( getVersionStringForVersion( (ICFBamMajorVersionObj) curDef ) );
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
				invertedNodeNames.add( getVersionStringForVersion( (ICFBamMinorVersionObj) curDef ) );
			}
			else if( curDef instanceof ICFBamMajorVersionObj ) {
				invertedNodeNames.add( getVersionStringForVersion( (ICFBamMajorVersionObj) curDef ) );
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
