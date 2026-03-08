// Description: Java 25 edit object instance implementation for CFBam Value.

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

public class CFBamValueEditObj
	implements ICFBamValueEditObj
{
	protected ICFBamValueObj orig;
	protected ICFBamValue rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFBamScopeObj requiredContainerScope;
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected List<ICFBamTableColObj> optionalChildrenRefTableCol;
	protected List<ICFBamIndexColObj> optionalChildrenRefIndexCol;
	protected ICFBamValueObj optionalLookupPrev;
	protected ICFBamValueObj optionalLookupNext;

	public CFBamValueEditObj( ICFBamValueObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFBamValue origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerScope = null;
		optionalLookupDefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
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
		return( ((ICFBamSchemaObj)orig.getSchema()).getValueTableObj().getClassCode() );
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
		// We realise this so that it's record will get copied to orig during realization
		ICFBamValueObj retobj = getSchema().getValueTableObj().realiseValue( (ICFBamValueObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsValue().forget();
	}

	@Override
	public ICFBamValueObj read() {
		ICFBamValueObj retval = getOrigAsValue().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFBamValueObj read( boolean forceRead ) {
		ICFBamValueObj retval = getOrigAsValue().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFBamValueObj moveUp() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUp" );
	}

	@Override
	public ICFBamValueObj moveDown() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDown" );
	}

	@Override
	public ICFBamValueObj create() {
		copyRecToOrig();
		ICFBamValueObj retobj = ((ICFBamSchemaObj)getOrigAsValue().getSchema()).getValueTableObj().createValue( getOrigAsValue() );
		if( retobj == getOrigAsValue() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamValueEditObj update() {
		getSchema().getValueTableObj().updateValue( (ICFBamValueObj)this );
		return( null );
	}

	@Override
	public CFBamValueEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getValueTableObj().deleteValue( getOrigAsValue() );
		return( null );
	}

	@Override
	public ICFBamValueTableObj getValueTable() {
		return( orig.getSchema().getValueTableObj() );
	}

	@Override
	public ICFBamValueEditObj getEdit() {
		return( (ICFBamValueEditObj)this );
	}

	@Override
	public ICFBamValueEditObj getEditAsValue() {
		return( (ICFBamValueEditObj)this );
	}

	@Override
	public ICFBamValueEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFBamValueObj getOrig() {
		return( orig );
	}

	@Override
	public ICFBamValueObj getOrigAsValue() {
		return( (ICFBamValueObj)orig );
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
	public ICFBamValue getRec() {
		if( rec == null ) {
			rec = getOrigAsValue().getSchema().getCFBamBackingStore().getFactoryValue().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamValue value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerScope = null;
			optionalLookupDefSchema = null;
			optionalLookupPrev = null;
			optionalLookupNext = null;
		}
	}

	@Override
	public ICFBamValue getValueRec() {
		return( (ICFBamValue)getRec() );
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
			requiredContainerScope = null;
			optionalLookupDefSchema = null;
			optionalChildrenRefTableCol = null;
			optionalChildrenRefIndexCol = null;
			optionalLookupPrev = null;
			optionalLookupNext = null;
		}
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
	public void setRequiredName( String value ) {
		if( getValueRec().getRequiredName() != value ) {
			getValueRec().setRequiredName( value );
		}
	}

	@Override
	public String getOptionalShortName() {
		return( getValueRec().getOptionalShortName() );
	}

	@Override
	public void setOptionalShortName( String value ) {
		if( getValueRec().getOptionalShortName() != value ) {
			getValueRec().setOptionalShortName( value );
		}
	}

	@Override
	public String getOptionalLabel() {
		return( getValueRec().getOptionalLabel() );
	}

	@Override
	public void setOptionalLabel( String value ) {
		if( getValueRec().getOptionalLabel() != value ) {
			getValueRec().setOptionalLabel( value );
		}
	}

	@Override
	public String getOptionalShortDescription() {
		return( getValueRec().getOptionalShortDescription() );
	}

	@Override
	public void setOptionalShortDescription( String value ) {
		if( getValueRec().getOptionalShortDescription() != value ) {
			getValueRec().setOptionalShortDescription( value );
		}
	}

	@Override
	public String getOptionalDescription() {
		return( getValueRec().getOptionalDescription() );
	}

	@Override
	public void setOptionalDescription( String value ) {
		if( getValueRec().getOptionalDescription() != value ) {
			getValueRec().setOptionalDescription( value );
		}
	}

	@Override
	public String getOptionalDefaultXmlValue() {
		return( getValueRec().getOptionalDefaultXmlValue() );
	}

	@Override
	public void setOptionalDefaultXmlValue( String value ) {
		if( getValueRec().getOptionalDefaultXmlValue() != value ) {
			getValueRec().setOptionalDefaultXmlValue( value );
		}
	}

	@Override
	public boolean getRequiredIsNullable() {
		return( getValueRec().getRequiredIsNullable() );
	}

	@Override
	public void setRequiredIsNullable( boolean value ) {
		if( getValueRec().getRequiredIsNullable() != value ) {
			getValueRec().setRequiredIsNullable( value );
		}
	}

	@Override
	public Boolean getOptionalGenerateId() {
		return( getValueRec().getOptionalGenerateId() );
	}

	@Override
	public void setOptionalGenerateId( Boolean value ) {
		if( getValueRec().getOptionalGenerateId() != value ) {
			getValueRec().setOptionalGenerateId( value );
		}
	}

	@Override
	public boolean getRequiredImplementsPolymorph() {
		return( getValueRec().getRequiredImplementsPolymorph() );
	}

	@Override
	public void setRequiredImplementsPolymorph( boolean value ) {
		if( getValueRec().getRequiredImplementsPolymorph() != value ) {
			getValueRec().setRequiredImplementsPolymorph( value );
		}
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
	public ICFBamScopeObj getRequiredContainerScope() {
		return( getRequiredContainerScope( false ) );
	}

	@Override
	public ICFBamScopeObj getRequiredContainerScope( boolean forceRead ) {
		if( forceRead || ( requiredContainerScope == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamScopeObj obj = ((ICFBamSchemaObj)getOrigAsValue().getSchema()).getScopeTableObj().readScopeByIdIdx( getValueRec().getRequiredScopeId() );
				requiredContainerScope = obj;
				if( obj != null ) {
					requiredContainerScope = obj;
				}
			}
		}
		return( requiredContainerScope );
	}

	@Override
	public void setRequiredContainerScope( ICFBamScopeObj value ) {
		if( rec == null ) {
			getValueRec();
		}
		if( value != null ) {
			requiredContainerScope = value;
			getValueRec().setRequiredContainerScope(value.getScopeRec());
		}
		requiredContainerScope = value;
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( forceRead || ( optionalLookupDefSchema == null ) ) {
			boolean anyMissing = false;
			if( getValueRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamSchemaDefObj obj = ((ICFBamSchemaObj)getOrigAsValue().getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getValueRec().getOptionalDefSchemaId() );
				optionalLookupDefSchema = obj;
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public void setOptionalLookupDefSchema( ICFBamSchemaDefObj value ) {
		if( rec == null ) {
			getValueRec();
		}
		if( value != null ) {
			optionalLookupDefSchema = value;
			getValueRec().setOptionalLookupDefSchema(value.getSchemaDefRec());
		}
		else {
			optionalLookupDefSchema = null;
			getValueRec().setOptionalLookupDefSchema((ICFBamSchemaDef)null);
		}
		optionalLookupDefSchema = value;
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
		if( forceRead || ( optionalLookupPrev == null ) ) {
			boolean anyMissing = false;
			if( getValueRec().getOptionalPrevId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamValueObj obj = ((ICFBamSchemaObj)getOrigAsValue().getSchema()).getValueTableObj().readValueByIdIdx( getValueRec().getOptionalPrevId() );
				optionalLookupPrev = obj;
			}
		}
		return( optionalLookupPrev );
	}

	@Override
	public void setOptionalLookupPrev( ICFBamValueObj value ) {
		if( rec == null ) {
			getValueRec();
		}
		if( value != null ) {
			optionalLookupPrev = value;
			getValueRec().setOptionalLookupPrev(value.getValueRec());
		}
		else {
			optionalLookupPrev = null;
			getValueRec().setOptionalLookupPrev((ICFBamValue)null);
		}
		optionalLookupPrev = value;
	}

	@Override
	public ICFBamValueObj getOptionalLookupNext() {
		return( getOptionalLookupNext( false ) );
	}

	@Override
	public ICFBamValueObj getOptionalLookupNext( boolean forceRead ) {
		if( forceRead || ( optionalLookupNext == null ) ) {
			boolean anyMissing = false;
			if( getValueRec().getOptionalNextId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamValueObj obj = ((ICFBamSchemaObj)getOrigAsValue().getSchema()).getValueTableObj().readValueByIdIdx( getValueRec().getOptionalNextId() );
				optionalLookupNext = obj;
			}
		}
		return( optionalLookupNext );
	}

	@Override
	public void setOptionalLookupNext( ICFBamValueObj value ) {
		if( rec == null ) {
			getValueRec();
		}
		if( value != null ) {
			optionalLookupNext = value;
			getValueRec().setOptionalLookupNext(value.getValueRec());
		}
		else {
			optionalLookupNext = null;
			getValueRec().setOptionalLookupNext((ICFBamValue)null);
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
		ICFBamValue origRec = getOrigAsValue().getValueRec();
		ICFBamValue myRec = getValueRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamValue origRec = getOrigAsValue().getValueRec();
		ICFBamValue myRec = getValueRec();
		myRec.set( origRec );
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
