// Description: Java 25 base object instance implementation for Value

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

public class CFBamValueObj
	implements ICFBamValueObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFBamValueEditObj edit;
	protected ICFBamSchemaObj schema;
	protected CFLibDbKeyHash256 pKey;
	protected ICFBamValue rec;
	protected ICFBamScopeObj requiredContainerScope;
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected List<ICFBamTableColObj> optionalChildrenRefTableCol;
	protected List<ICFBamIndexColObj> optionalChildrenRefIndexCol;
	protected ICFBamValueObj optionalLookupPrev;
	protected ICFBamValueObj optionalLookupNext;

	public CFBamValueObj() {
		isNew = true;
		requiredContainerScope = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	public CFBamValueObj( ICFBamSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredContainerScope = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getValueTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "Value" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamScopeObj scope = getRequiredContainerScope();
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
	public ICFBamValueObj realise() {
		ICFBamValueObj retobj = ((ICFBamSchemaObj)getSchema()).getValueTableObj().realiseValue(
			(ICFBamValueObj)this );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getValueTableObj().reallyDeepDisposeValue( (ICFBamValueObj)this );
	}

	@Override
	public ICFBamValueObj read() {
		ICFBamValueObj retobj = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByIdIdx( getPKey(), false );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public ICFBamValueObj read( boolean forceRead ) {
		ICFBamValueObj retobj = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByIdIdx( getPKey(), forceRead );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public ICFBamValueObj moveUp() {
		ICFBamValueObj retobj = ((ICFBamSchemaObj)getSchema()).getValueTableObj().moveUpValue( this );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public ICFBamValueObj moveDown() {
		ICFBamValueObj retobj = ((ICFBamSchemaObj)getSchema()).getValueTableObj().moveDownValue( this );
		return( (ICFBamValueObj)retobj );
	}

	@Override
	public ICFBamValueTableObj getValueTable() {
		return( ((ICFBamSchemaObj)getSchema()).getValueTableObj() );
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
	public ICFBamValue getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactoryValue().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableValue().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamValue value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamValue ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamValueRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerScope = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	@Override
	public ICFBamValue getValueRec() {
		return( (ICFBamValue)getRec() );
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
	public ICFBamValueEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamValueObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamValueObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getValueTableObj().lockValue( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getValueTableObj().newEditInstance( lockobj );
		return( (ICFBamValueEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFBamValueEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFBamValueEditObj getEditAsValue() {
		return( (ICFBamValueEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFBamValue rec = getRec();
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
			ICFBamValue rec = getRec();
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
	public ICFBamScopeObj getRequiredContainerScope() {
		return( getRequiredContainerScope( false ) );
	}

	@Override
	public ICFBamScopeObj getRequiredContainerScope( boolean forceRead ) {
		if( ( requiredContainerScope == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerScope = ((ICFBamSchemaObj)getSchema()).getScopeTableObj().readScopeByIdIdx( getValueRec().getRequiredScopeId(), forceRead );
			}
		}
		return( requiredContainerScope );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( ( optionalLookupDefSchema == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getValueRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupDefSchema = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getValueRec().getOptionalDefSchemaId(), forceRead );
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public List<ICFBamTableColObj> getOptionalChildrenRefTableCol() {
		List<ICFBamTableColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTableColTableObj().readTableColByDataIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamTableColObj> getOptionalChildrenRefTableCol( boolean forceRead ) {
		List<ICFBamTableColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTableColTableObj().readTableColByDataIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamIndexColObj> getOptionalChildrenRefIndexCol() {
		List<ICFBamIndexColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().readIndexColByColIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamIndexColObj> getOptionalChildrenRefIndexCol( boolean forceRead ) {
		List<ICFBamIndexColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().readIndexColByColIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFBamValueObj getOptionalLookupPrev() {
		return( getOptionalLookupPrev( false ) );
	}

	@Override
	public ICFBamValueObj getOptionalLookupPrev( boolean forceRead ) {
		if( ( optionalLookupPrev == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getValueRec().getOptionalPrevId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupPrev = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByIdIdx( getValueRec().getOptionalPrevId(), forceRead );
			}
		}
		return( optionalLookupPrev );
	}

	@Override
	public ICFBamValueObj getOptionalLookupNext() {
		return( getOptionalLookupNext( false ) );
	}

	@Override
	public ICFBamValueObj getOptionalLookupNext( boolean forceRead ) {
		if( ( optionalLookupNext == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getValueRec().getOptionalNextId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupNext = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByIdIdx( getValueRec().getOptionalNextId(), forceRead );
			}
		}
		return( optionalLookupNext );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredScopeId() {
		return( getValueRec().getRequiredScopeId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( getValueRec().getOptionalDefSchemaId() );
	}

	@Override
	public String getRequiredName() {
		return( getValueRec().getRequiredName() );
	}

	@Override
	public String getOptionalShortName() {
		return( getValueRec().getOptionalShortName() );
	}

	@Override
	public String getOptionalLabel() {
		return( getValueRec().getOptionalLabel() );
	}

	@Override
	public String getOptionalShortDescription() {
		return( getValueRec().getOptionalShortDescription() );
	}

	@Override
	public String getOptionalDescription() {
		return( getValueRec().getOptionalDescription() );
	}

	@Override
	public String getOptionalDefaultXmlValue() {
		return( getValueRec().getOptionalDefaultXmlValue() );
	}

	@Override
	public boolean getRequiredIsNullable() {
		return( getValueRec().getRequiredIsNullable() );
	}

	@Override
	public Boolean getOptionalGenerateId() {
		return( getValueRec().getOptionalGenerateId() );
	}

	@Override
	public boolean getRequiredImplementsPolymorph() {
		return( getValueRec().getRequiredImplementsPolymorph() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( getValueRec().getOptionalPrevId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( getValueRec().getOptionalNextId() );
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

	public List<ICFBamIndexObj> getAffectedIndexes() {
		List<ICFBamIndexObj> list = new LinkedList<ICFBamIndexObj>();
		ICFBamScopeObj container = getRequiredContainerScope();
		ICFBamTableObj tableDef = null;
		if( container == null ) {
			throw new RuntimeException("ValueDefObj.getAffectedIndexes() RequiredContainerScope has no value");
		}
		else if( container instanceof ICFBamTableObj ) {
			tableDef = (ICFBamTableObj)container;
		}
		else {
			throw new RuntimeException( "ValueDefObj.getAffectedIndexes() RequiredContainerScope is not an ICFBamTableObj");
		}
		Iterator<ICFBamIndexObj> cursorIndexDef = tableDef.getOptionalComponentsIndex().iterator();
		while (cursorIndexDef.hasNext())
		{
			ICFBamIndexObj indexDef = cursorIndexDef.next();

			Iterator<ICFBamIndexColObj> cursorIndexColDef = indexDef.getOptionalComponentsColumns().iterator();
			while (cursorIndexColDef.hasNext())
			{
				ICFBamIndexColObj indexColDef = cursorIndexColDef.next();
				if( indexColDef.getRequiredLookupColumn().equals(this))
				{
					list.add(indexDef);
				}
			}
		}
		return( list );
	}

	public List<ICFBamRelationObj> getAffectedRelations() {
		List<ICFBamRelationObj> list = new LinkedList<ICFBamRelationObj>();
		ICFBamScopeObj container = getRequiredContainerScope();
		ICFBamTableObj tableDef = null;
		if( container == null ) {
			throw new RuntimeException("ValueDefObj.getAffectedRelations() RequiredContainerScope has no value");
		}
		else if( container instanceof ICFBamTableObj ) {
			tableDef = (ICFBamTableObj)container;
		}
		else {
			throw new RuntimeException( "ValueDefObj.getAffectedRelations() RequiredContainerScope is not an ICFBamTableObj");
		}
		Iterator<ICFBamRelationObj> cursorRelationDef = tableDef.getOptionalComponentsRelation().iterator();
		while (cursorRelationDef.hasNext())
		{
			ICFBamRelationObj relationDef = cursorRelationDef.next();
			Iterator<ICFBamRelationColObj> cursorRelationColDef = relationDef.getOptionalComponentsColumns().iterator();
			while (cursorRelationColDef.hasNext())
			{
				ICFBamRelationColObj relationColDef = cursorRelationColDef.next();
				if( relationColDef.getRequiredLookupFromCol().equals(this))
				{
					list.add(relationDef);
				}
			}
		}
		return( list );
	}

	public List<ICFBamIndexObj> getAffectedUniqueIndexes() {
		List<ICFBamIndexObj> affectedIndexes = getAffectedIndexes();
		List<ICFBamIndexObj> list = new LinkedList<ICFBamIndexObj>();
		ICFBamScopeObj container = getRequiredContainerScope();
		ICFBamTableObj tableDef = null;
		if( container == null ) {
			throw new RuntimeException("ValueDefObj.getAffectedUniqueIndexes() RequiredContainerScope has no value");
		}
		else if( container instanceof ICFBamTableObj ) {
			tableDef = (ICFBamTableObj)container;
		}
		else {
			throw new RuntimeException( "ValueDefObj.getAffectedUniqueIndexes() RequiredContainerScope is not an ICFBamTableObj");
		}
		Iterator<ICFBamIndexObj> cursorIndexDef = affectedIndexes.iterator();
		while (cursorIndexDef.hasNext())
		{
			ICFBamIndexObj indexDef = cursorIndexDef.next();
			if( indexDef.getRequiredIsUnique())
			{
				list.add(indexDef);
			}
		}
		return( list );
	}

	public List<ICFBamRelationObj> getColumnInMemberRelations() {
		final String S_ProcName = "ValueDefObj.getColumnInMemberRelations() ";

		ICFBamScopeObj scopeDef = (ICFBamScopeObj)getObjScope();
		if( scopeDef == null)
		{
			throw new RuntimeException(S_ProcName + "OptionalParentScope is required");
		}
		else if( scopeDef instanceof ICFBamTableObj)
		{
			List<ICFBamRelationObj> list = new LinkedList<ICFBamRelationObj>();
			Iterator<ICFBamRelationObj> relations = ((ICFBamTableObj)scopeDef).getOptionalComponentsRelation().iterator();
			ICFBamRelationColObj relationCol;
			Iterator<ICFBamRelationColObj> relationColumns;
			ICFBamRelationObj relation;
			ICFBamSchema.RelationTypeEnum relType;
			while (relations.hasNext())
			{
				relation = relations.next();
				relType = relation.getRequiredRelationType();
				if( ( relType == ICFBamSchema.RelationTypeEnum.Lookup )
				 || ( relType == ICFBamSchema.RelationTypeEnum.Container )
				 || ( relType == ICFBamSchema.RelationTypeEnum.Components )
				 || ( relType == ICFBamSchema.RelationTypeEnum.Parent )
				 || ( relType == ICFBamSchema.RelationTypeEnum.Children )
				 || ( relType == ICFBamSchema.RelationTypeEnum.Owner ) )
				{
					relationColumns = relation.getOptionalComponentsColumns().iterator();
					while (relationColumns.hasNext())
					{
						relationCol = relationColumns.next();
						if( this == relationCol.getRequiredLookupFromCol().getRequiredLookupColumn() )
						{
							list.add(relation);
							break;
						}
					}
				}
			}
			return( list );
		}
		else
		{
			throw new RuntimeException(S_ProcName + "Expected scope definition to be an ICFBamTableObj");
		}
	}

	public List<ICFBamRelationObj> getColumnInComponentsRelations() {
		final String S_ProcName = "ValueDefObj.getColumnInComponentsRelations() ";

		ICFBamScopeObj container = getRequiredContainerScope();
		ICFBamTableObj tableDef = null;
		if( container == null ) {
			throw new RuntimeException("ValueDefObj.getColumnInComponentsRelations() RequiredContainerScope has no value");
		}
		else if( container instanceof ICFBamTableObj ) {
			tableDef = (ICFBamTableObj)container;
		}
		else {
			throw new RuntimeException( "ValueDefObj.getColumnInComponentsRelations() RequiredContainerScope is not an ICFBamTableObj");
		}

		ICFBamRelationColObj relationCol;
		ICFBamRelationObj relation;
		Iterator<ICFBamRelationColObj> relationColumns;
		List<ICFBamRelationObj> list = new LinkedList<ICFBamRelationObj>();
		Iterator<ICFBamRelationObj> relations = tableDef.getOptionalComponentsRelation().iterator();

		while( relations.hasNext() ) {
			relation = relations.next();
			if( relation.getRequiredRelationType() == ICFBamSchema.RelationTypeEnum.Components ) {
				relationColumns = relation.getOptionalComponentsColumns().iterator();
				while (relationColumns.hasNext()) {
					relationCol = relationColumns.next();
					if( this == relationCol.getRequiredLookupFromCol().getRequiredLookupColumn() ) {
						list.add(relation);
						break;
					}
				}
			}
		}

		return( list );
	}

	public boolean getGenerateId() {
		boolean ret;
		if( getOptionalGenerateId() != null )		 {
			ret = getOptionalGenerateId().booleanValue();
		}
		else {
			ret = false;
		}
		return( ret );
	}
}
