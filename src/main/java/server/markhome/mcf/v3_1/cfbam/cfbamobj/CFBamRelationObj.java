// Description: Java 25 base object instance implementation for Relation

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

public class CFBamRelationObj
	extends CFBamScopeObj
	implements ICFBamRelationObj
{
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected ICFBamTableObj requiredContainerFromTable;
	protected List<ICFBamRelationColObj> optionalComponentsColumns;
	protected List<ICFBamPopTopDepObj> optionalComponentsPopDep;
	protected ICFBamIndexObj requiredLookupFromIndex;
	protected ICFBamTableObj requiredLookupToTable;
	protected ICFBamIndexObj requiredLookupToIndex;
	protected ICFBamRelationObj optionalLookupNarrowed;

	public CFBamRelationObj() {
		super();
		optionalLookupDefSchema = null;
		requiredContainerFromTable = null;
		requiredLookupFromIndex = null;
		requiredLookupToTable = null;
		requiredLookupToIndex = null;
		optionalLookupNarrowed = null;
	}

	public CFBamRelationObj( ICFBamSchemaObj argSchema ) {
		super( argSchema );
		optionalLookupDefSchema = null;
		requiredContainerFromTable = null;
		requiredLookupFromIndex = null;
		requiredLookupToTable = null;
		requiredLookupToIndex = null;
		optionalLookupNarrowed = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getRelationTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "Relation" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamTableObj scope = getRequiredContainerFromTable();
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
		if( subObj == null ) {
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFBamSchemaObj)getSchema()).getRelationColTableObj().readRelationColByUNameIdx( getRequiredId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
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
	public ICFBamScopeObj realise() {
		ICFBamRelationObj retobj = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().realiseRelation(
			(ICFBamRelationObj)this );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getRelationTableObj().reallyDeepDisposeRelation( (ICFBamRelationObj)this );
	}

	@Override
	public ICFBamScopeObj read() {
		ICFBamRelationObj retobj = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().readRelationByIdIdx( getPKey(), false );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamScopeObj read( boolean forceRead ) {
		ICFBamRelationObj retobj = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().readRelationByIdIdx( getPKey(), forceRead );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamRelationTableObj getRelationTable() {
		return( ((ICFBamSchemaObj)getSchema()).getRelationTableObj() );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactoryRelation().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableRelation().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamRelation ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamRelationRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredOwnerTenant = null;
		optionalLookupDefSchema = null;
		requiredContainerFromTable = null;
		requiredLookupFromIndex = null;
		requiredLookupToTable = null;
		requiredLookupToIndex = null;
		optionalLookupNarrowed = null;
	}

	@Override
	public ICFBamRelation getRelationRec() {
		return( (ICFBamRelation)getRec() );
	}

	@Override
	public ICFBamScopeEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamRelationObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamRelationObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().lockRelation( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().newEditInstance( lockobj );
		return( (ICFBamScopeEditObj)edit );
	}

	@Override
	public ICFBamRelationEditObj getEditAsRelation() {
		return( (ICFBamRelationEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFBamScope rec = getRec();
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
			ICFBamScope rec = getRec();
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
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( ( optionalLookupDefSchema == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getRelationRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupDefSchema = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getRelationRec().getOptionalDefSchemaId(), forceRead );
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public ICFBamTableObj getRequiredContainerFromTable() {
		return( getRequiredContainerFromTable( false ) );
	}

	@Override
	public ICFBamTableObj getRequiredContainerFromTable( boolean forceRead ) {
		if( ( requiredContainerFromTable == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerFromTable = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableByIdIdx( getRelationRec().getRequiredTableId(), forceRead );
			}
		}
		return( requiredContainerFromTable );
	}

	@Override
	public List<ICFBamRelationColObj> getOptionalComponentsColumns() {
		List<ICFBamRelationColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getRelationColTableObj().readRelationColByRelationIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamRelationColObj> getOptionalComponentsColumns( boolean forceRead ) {
		List<ICFBamRelationColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getRelationColTableObj().readRelationColByRelationIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamPopTopDepObj> getOptionalComponentsPopDep() {
		List<ICFBamPopTopDepObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getPopTopDepTableObj().readPopTopDepByContRelIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamPopTopDepObj> getOptionalComponentsPopDep( boolean forceRead ) {
		List<ICFBamPopTopDepObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getPopTopDepTableObj().readPopTopDepByContRelIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFBamIndexObj getRequiredLookupFromIndex() {
		return( getRequiredLookupFromIndex( false ) );
	}

	@Override
	public ICFBamIndexObj getRequiredLookupFromIndex( boolean forceRead ) {
		if( ( requiredLookupFromIndex == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredLookupFromIndex = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().readIndexByIdIdx( getRelationRec().getRequiredFromIndexId(), forceRead );
			}
		}
		return( requiredLookupFromIndex );
	}

	@Override
	public ICFBamTableObj getRequiredLookupToTable() {
		return( getRequiredLookupToTable( false ) );
	}

	@Override
	public ICFBamTableObj getRequiredLookupToTable( boolean forceRead ) {
		if( ( requiredLookupToTable == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredLookupToTable = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableByIdIdx( getRelationRec().getRequiredToTableId(), forceRead );
			}
		}
		return( requiredLookupToTable );
	}

	@Override
	public ICFBamIndexObj getRequiredLookupToIndex() {
		return( getRequiredLookupToIndex( false ) );
	}

	@Override
	public ICFBamIndexObj getRequiredLookupToIndex( boolean forceRead ) {
		if( ( requiredLookupToIndex == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredLookupToIndex = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().readIndexByIdIdx( getRelationRec().getRequiredToIndexId(), forceRead );
			}
		}
		return( requiredLookupToIndex );
	}

	@Override
	public ICFBamRelationObj getOptionalLookupNarrowed() {
		return( getOptionalLookupNarrowed( false ) );
	}

	@Override
	public ICFBamRelationObj getOptionalLookupNarrowed( boolean forceRead ) {
		if( ( optionalLookupNarrowed == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getRelationRec().getOptionalNarrowedId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupNarrowed = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().readRelationByIdIdx( getRelationRec().getOptionalNarrowedId(), forceRead );
			}
		}
		return( optionalLookupNarrowed );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTableId() {
		return( getRelationRec().getRequiredTableId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( getRelationRec().getOptionalDefSchemaId() );
	}

	@Override
	public String getRequiredName() {
		return( getRelationRec().getRequiredName() );
	}

	@Override
	public String getOptionalShortName() {
		return( getRelationRec().getOptionalShortName() );
	}

	@Override
	public String getOptionalLabel() {
		return( getRelationRec().getOptionalLabel() );
	}

	@Override
	public String getOptionalShortDescription() {
		return( getRelationRec().getOptionalShortDescription() );
	}

	@Override
	public String getOptionalDescription() {
		return( getRelationRec().getOptionalDescription() );
	}

	@Override
	public ICFBamSchema.RelationTypeEnum getRequiredRelationType() {
		return( getRelationRec().getRequiredRelationType() );
	}

	@Override
	public String getOptionalDbName() {
		return( getRelationRec().getOptionalDbName() );
	}

	@Override
	public String getOptionalSuffix() {
		return( getRelationRec().getOptionalSuffix() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredFromIndexId() {
		return( getRelationRec().getRequiredFromIndexId() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredToTableId() {
		return( getRelationRec().getRequiredToTableId() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredToIndexId() {
		return( getRelationRec().getRequiredToIndexId() );
	}

	@Override
	public boolean getRequiredIsRequired() {
		return( getRelationRec().getRequiredIsRequired() );
	}

	@Override
	public boolean getRequiredIsXsdContainer() {
		return( getRelationRec().getRequiredIsXsdContainer() );
	}

	@Override
	public boolean getRequiredIsLateResolver() {
		return( getRelationRec().getRequiredIsLateResolver() );
	}

	@Override
	public boolean getRequiredAllowAddendum() {
		return( getRelationRec().getRequiredAllowAddendum() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNarrowedId() {
		return( getRelationRec().getOptionalNarrowedId() );
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
