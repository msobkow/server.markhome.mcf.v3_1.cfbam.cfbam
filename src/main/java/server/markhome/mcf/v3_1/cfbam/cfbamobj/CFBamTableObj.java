// Description: Java 25 base object instance implementation for Table

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

public class CFBamTableObj
	extends CFBamScopeObj
	implements ICFBamTableObj
{
	protected ICFBamSchemaDefObj requiredContainerSchemaDef;
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected List<ICFBamRelationObj> optionalComponentsRelation;
	protected ICFBamIndexObj optionalLookupLookupIndex;
	protected ICFBamIndexObj optionalLookupAltIndex;
	protected ICFBamTableObj optionalLookupQualTable;
	protected List<ICFBamIndexObj> optionalComponentsIndex;
	protected ICFBamIndexObj optionalLookupPrimaryIndex;
	protected List<ICFBamValueObj> optionalComponentsColumns;
	protected List<ICFBamRelationObj> optionalChildrenReverseRelations;
	protected List<ICFBamChainObj> optionalComponentsChains;
	protected List<ICFBamDelTopDepObj> optionalComponentsDelDep;
	protected List<ICFBamClearTopDepObj> optionalComponentsClearDep;
	protected List<ICFBamServerMethodObj> optionalComponentsServerMethods;
	protected List<ICFBamTweakObj> optionalComponentsTweaks;

	public CFBamTableObj() {
		super();
		requiredContainerSchemaDef = null;
		optionalLookupDefSchema = null;
		optionalLookupLookupIndex = null;
		optionalLookupAltIndex = null;
		optionalLookupQualTable = null;
		optionalLookupPrimaryIndex = null;
	}

	public CFBamTableObj( ICFBamSchemaObj argSchema ) {
		super( argSchema );
		requiredContainerSchemaDef = null;
		optionalLookupDefSchema = null;
		optionalLookupLookupIndex = null;
		optionalLookupAltIndex = null;
		optionalLookupQualTable = null;
		optionalLookupPrimaryIndex = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getTableTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "Table" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamSchemaDefObj scope = getRequiredContainerSchemaDef();
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
				subObj = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().readRelationByUNameIdx( getRequiredId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
				throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
			}
			String natNextName = nextName;
				subObj = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().readIndexByUNameIdx( getRequiredId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
				throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
			}
			String natNextName = nextName;
				subObj = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByUNameIdx( getRequiredId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
				throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
			}
			String natNextName = nextName;
				subObj = ((ICFBamSchemaObj)getSchema()).getChainTableObj().readChainByUNameIdx( getRequiredId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
				throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
			}
			String natNextName = nextName;
				subObj = ((ICFBamSchemaObj)getSchema()).getServerMethodTableObj().readServerMethodByUNameIdx( getRequiredId(),
				natNextName, false );
			}
			catch (Throwable th) {
				subObj = null;
			}
		}
		if( subObj == null ) {
			try {
				if (nextName == null) {
				throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
			}
			String natNextName = nextName;
				subObj = ((ICFBamSchemaObj)getSchema()).getTweakTableObj().readTweakByUNameIdx( getRequiredId(),
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
		ICFBamTableObj retobj = ((ICFBamSchemaObj)getSchema()).getTableTableObj().realiseTable(
			(ICFBamTableObj)this );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getTableTableObj().reallyDeepDisposeTable( (ICFBamTableObj)this );
	}

	@Override
	public ICFBamScopeObj read() {
		ICFBamTableObj retobj = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableByIdIdx( getPKey(), false );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamScopeObj read( boolean forceRead ) {
		ICFBamTableObj retobj = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableByIdIdx( getPKey(), forceRead );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamTableTableObj getTableTable() {
		return( ((ICFBamSchemaObj)getSchema()).getTableTableObj() );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactoryTable().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableTable().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
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
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamTable ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamTableRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredOwnerTenant = null;
		requiredContainerSchemaDef = null;
		optionalLookupDefSchema = null;
		optionalLookupLookupIndex = null;
		optionalLookupAltIndex = null;
		optionalLookupQualTable = null;
		optionalLookupPrimaryIndex = null;
	}

	@Override
	public ICFBamTable getTableRec() {
		return( (ICFBamTable)getRec() );
	}

	@Override
	public ICFBamScopeEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamTableObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamTableObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getTableTableObj().lockTable( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getTableTableObj().newEditInstance( lockobj );
		return( (ICFBamScopeEditObj)edit );
	}

	@Override
	public ICFBamTableEditObj getEditAsTable() {
		return( (ICFBamTableEditObj)edit );
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
	public ICFBamSchemaDefObj getRequiredContainerSchemaDef() {
		return( getRequiredContainerSchemaDef( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getRequiredContainerSchemaDef( boolean forceRead ) {
		if( ( requiredContainerSchemaDef == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerSchemaDef = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getTableRec().getRequiredSchemaDefId(), forceRead );
			}
		}
		return( requiredContainerSchemaDef );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( ( optionalLookupDefSchema == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getTableRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupDefSchema = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getTableRec().getOptionalDefSchemaId(), forceRead );
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public List<ICFBamRelationObj> getOptionalComponentsRelation() {
		List<ICFBamRelationObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().readRelationByRelTableIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamRelationObj> getOptionalComponentsRelation( boolean forceRead ) {
		List<ICFBamRelationObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().readRelationByRelTableIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFBamIndexObj getOptionalLookupLookupIndex() {
		return( getOptionalLookupLookupIndex( false ) );
	}

	@Override
	public ICFBamIndexObj getOptionalLookupLookupIndex( boolean forceRead ) {
		if( ( optionalLookupLookupIndex == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getTableRec().getOptionalLookupIndexId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupLookupIndex = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().readIndexByIdIdx( getTableRec().getOptionalLookupIndexId(), forceRead );
			}
		}
		return( optionalLookupLookupIndex );
	}

	@Override
	public ICFBamIndexObj getOptionalLookupAltIndex() {
		return( getOptionalLookupAltIndex( false ) );
	}

	@Override
	public ICFBamIndexObj getOptionalLookupAltIndex( boolean forceRead ) {
		if( ( optionalLookupAltIndex == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getTableRec().getOptionalAltIndexId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupAltIndex = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().readIndexByIdIdx( getTableRec().getOptionalAltIndexId(), forceRead );
			}
		}
		return( optionalLookupAltIndex );
	}

	@Override
	public ICFBamTableObj getOptionalLookupQualTable() {
		return( getOptionalLookupQualTable( false ) );
	}

	@Override
	public ICFBamTableObj getOptionalLookupQualTable( boolean forceRead ) {
		if( ( optionalLookupQualTable == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getTableRec().getOptionalQualifyingTableId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupQualTable = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableByIdIdx( getTableRec().getOptionalQualifyingTableId(), forceRead );
			}
		}
		return( optionalLookupQualTable );
	}

	@Override
	public List<ICFBamIndexObj> getOptionalComponentsIndex() {
		List<ICFBamIndexObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().readIndexByIdxTableIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamIndexObj> getOptionalComponentsIndex( boolean forceRead ) {
		List<ICFBamIndexObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().readIndexByIdxTableIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFBamIndexObj getOptionalLookupPrimaryIndex() {
		return( getOptionalLookupPrimaryIndex( false ) );
	}

	@Override
	public ICFBamIndexObj getOptionalLookupPrimaryIndex( boolean forceRead ) {
		if( ( optionalLookupPrimaryIndex == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getTableRec().getOptionalPrimaryIndexId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupPrimaryIndex = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().readIndexByIdIdx( getTableRec().getOptionalPrimaryIndexId(), forceRead );
			}
		}
		return( optionalLookupPrimaryIndex );
	}

	@Override
	public List<ICFBamValueObj> getOptionalComponentsColumns() {
		List<ICFBamValueObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByScopeIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamValueObj> getOptionalComponentsColumns( boolean forceRead ) {
		List<ICFBamValueObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByScopeIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamRelationObj> getOptionalChildrenReverseRelations() {
		List<ICFBamRelationObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().readRelationByToTblIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamRelationObj> getOptionalChildrenReverseRelations( boolean forceRead ) {
		List<ICFBamRelationObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().readRelationByToTblIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamChainObj> getOptionalComponentsChains() {
		List<ICFBamChainObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getChainTableObj().readChainByChainTableIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamChainObj> getOptionalComponentsChains( boolean forceRead ) {
		List<ICFBamChainObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getChainTableObj().readChainByChainTableIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamDelTopDepObj> getOptionalComponentsDelDep() {
		List<ICFBamDelTopDepObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getDelTopDepTableObj().readDelTopDepByDelTopDepTblIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamDelTopDepObj> getOptionalComponentsDelDep( boolean forceRead ) {
		List<ICFBamDelTopDepObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getDelTopDepTableObj().readDelTopDepByDelTopDepTblIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamClearTopDepObj> getOptionalComponentsClearDep() {
		List<ICFBamClearTopDepObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj().readClearTopDepByClrTopDepTblIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamClearTopDepObj> getOptionalComponentsClearDep( boolean forceRead ) {
		List<ICFBamClearTopDepObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj().readClearTopDepByClrTopDepTblIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamServerMethodObj> getOptionalComponentsServerMethods() {
		List<ICFBamServerMethodObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getServerMethodTableObj().readServerMethodByMethTableIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamServerMethodObj> getOptionalComponentsServerMethods( boolean forceRead ) {
		List<ICFBamServerMethodObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getServerMethodTableObj().readServerMethodByMethTableIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFBamTweakObj> getOptionalComponentsTweaks() {
		List<ICFBamTweakObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTweakTableObj().readTweakByScopeIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamTweakObj> getOptionalComponentsTweaks( boolean forceRead ) {
		List<ICFBamTweakObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTweakTableObj().readTweakByScopeIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredSchemaDefId() {
		return( getTableRec().getRequiredSchemaDefId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( getTableRec().getOptionalDefSchemaId() );
	}

	@Override
	public String getRequiredName() {
		return( getTableRec().getRequiredName() );
	}

	@Override
	public String getOptionalDbName() {
		return( getTableRec().getOptionalDbName() );
	}

	@Override
	public String getOptionalShortName() {
		return( getTableRec().getOptionalShortName() );
	}

	@Override
	public String getOptionalLabel() {
		return( getTableRec().getOptionalLabel() );
	}

	@Override
	public String getOptionalShortDescription() {
		return( getTableRec().getOptionalShortDescription() );
	}

	@Override
	public String getOptionalDescription() {
		return( getTableRec().getOptionalDescription() );
	}

	@Override
	public boolean getRequiredPageData() {
		return( getTableRec().getRequiredPageData() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrimaryIndexId() {
		return( getTableRec().getOptionalPrimaryIndexId() );
	}

	@Override
	public String getRequiredTableClassCode() {
		return( getTableRec().getRequiredTableClassCode() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalLookupIndexId() {
		return( getTableRec().getOptionalLookupIndexId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalAltIndexId() {
		return( getTableRec().getOptionalAltIndexId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalQualifyingTableId() {
		return( getTableRec().getOptionalQualifyingTableId() );
	}

	@Override
	public boolean getRequiredIsInstantiable() {
		return( getTableRec().getRequiredIsInstantiable() );
	}

	@Override
	public boolean getRequiredHasHistory() {
		return( getTableRec().getRequiredHasHistory() );
	}

	@Override
	public boolean getRequiredHasAuditColumns() {
		return( getTableRec().getRequiredHasAuditColumns() );
	}

	@Override
	public boolean getRequiredIsMutable() {
		return( getTableRec().getRequiredIsMutable() );
	}

	@Override
	public boolean getRequiredIsServerOnly() {
		return( getTableRec().getRequiredIsServerOnly() );
	}

	@Override
	public ICFBamSchema.LoaderBehaviourEnum getRequiredLoaderBehaviour() {
		return( getTableRec().getRequiredLoaderBehaviour() );
	}

	@Override
	public ICFBamSchema.SecScopeEnum getRequiredSecScope() {
		return( getTableRec().getRequiredSecScope() );
	}

	public List<ICFBamRelationObj> getOnlyOwnerRelations() {
		ICFBamTableObj tableDef;
		ICFBamRelationObj relationDef = null;
		List<ICFBamRelationObj> list = new LinkedList<ICFBamRelationObj>();
		Iterator<ICFBamRelationObj> childrenRelations = getOptionalComponentsRelation().iterator();
		while (childrenRelations.hasNext()) {
			relationDef = childrenRelations.next();
			if( relationDef.getRequiredRelationType() == ICFBamSchema.RelationTypeEnum.Owner ) {
				list.add(relationDef);
			}
		}

		ICFBamRelationObj superClass = getSuperClassRelation();
		while( superClass != null ) {
			tableDef = superClass.getRequiredLookupToTable();
			childrenRelations = tableDef.getOptionalComponentsRelation().iterator();
			while (childrenRelations.hasNext()) {
				relationDef = childrenRelations.next();
				if( relationDef.getRequiredRelationType() == ICFBamSchema.RelationTypeEnum.Owner ) {
					list.add(relationDef);
				}
			}
			superClass = tableDef.getSuperClassRelation();
		}

		childrenRelations = getOptionalComponentsRelation().iterator();
		while (childrenRelations.hasNext()) {
			relationDef = childrenRelations.next();
			if( relationDef.getRequiredRelationType() == ICFBamSchema.RelationTypeEnum.Container ) {
				list.add(relationDef);
			}
		}

		superClass = getSuperClassRelation();
		while( superClass != null ) {
			tableDef = superClass.getRequiredLookupToTable();
			childrenRelations = tableDef.getOptionalComponentsRelation().iterator();
			while (childrenRelations.hasNext()) {
				relationDef = childrenRelations.next();
				if( relationDef.getRequiredRelationType() == ICFBamSchema.RelationTypeEnum.Container ) {
					list.add(relationDef);
				}
			}
			superClass = tableDef.getSuperClassRelation();
		}

		return( list );
	}

	public List<ICFBamRelationObj> getContainerOwnerRelations() {
		ICFBamTableObj tableDef;
		ICFBamRelationObj relationDef = null;
		ICFBamSchema.RelationTypeEnum relType;
		List<ICFBamRelationObj> list = new LinkedList<ICFBamRelationObj>();
		Iterator<ICFBamRelationObj> childrenRelations = getOptionalComponentsRelation().iterator();
		while (childrenRelations.hasNext()) {
			relationDef = childrenRelations.next();
			relType = relationDef.getRequiredRelationType();
			if( ( relType == ICFBamSchema.RelationTypeEnum.Owner )
			 || ( relType == ICFBamSchema.RelationTypeEnum.Parent )
			 || ( relType == ICFBamSchema.RelationTypeEnum.Container ) )
			{
				list.add(relationDef);
			}
		}

		ICFBamRelationObj superClass = getSuperClassRelation();
		while( superClass != null ) {
			tableDef = superClass.getRequiredLookupToTable();
			childrenRelations = tableDef.getOptionalComponentsRelation().iterator();
			while (childrenRelations.hasNext()) {
				relationDef = childrenRelations.next();
				relType = relationDef.getRequiredRelationType();
				if( ( relType == ICFBamSchema.RelationTypeEnum.Owner )
				 || ( relType == ICFBamSchema.RelationTypeEnum.Parent )
				 || ( relType == ICFBamSchema.RelationTypeEnum.Container ) )
				{
					list.add(relationDef);
				}
			}
			superClass = tableDef.getSuperClassRelation();
		}

		return( list );
	}

	public ICFBamRelationObj getContainerRelation() {
		ICFBamRelationObj relationDef;
		Iterator<ICFBamRelationObj> childrenRelations = getOptionalComponentsRelation().iterator();
		while (childrenRelations.hasNext()) {
			relationDef = childrenRelations.next();
			if( relationDef.getRequiredRelationType() == ICFBamSchema.RelationTypeEnum.Container ) {
				return( relationDef );
			}
		}
		return( null );
	}

	public ICFBamRelationObj getInheritedContainerRelation() {
		ICFBamRelationObj inheritedContainerRelation = null;
		ICFBamRelationObj relationDef;
		Iterator<ICFBamRelationObj> childrenRelations;
		ICFBamTableObj tableDef = this;
		while( ( inheritedContainerRelation == null ) && ( tableDef != null ) ) {
			childrenRelations = tableDef.getOptionalComponentsRelation().iterator();
			while (childrenRelations.hasNext()) {
				relationDef = childrenRelations.next();
				if( relationDef.getRequiredRelationType() == ICFBamSchema.RelationTypeEnum.Container ) {
					return( relationDef );
				}
			}
			relationDef = tableDef.getSuperClassRelation();
			if( relationDef == null ) {
				tableDef = null;
			}
			else {
				tableDef = relationDef.getRequiredLookupToTable();
			}
		}
		return( null );
	}

	public ICFBamRelationObj getOwnerRelation() {
		ICFBamRelationObj relationDef;
		Iterator<ICFBamRelationObj> childrenRelations = getOptionalComponentsRelation().iterator();
		while (childrenRelations.hasNext()) {
			relationDef = childrenRelations.next();
			if( relationDef.getRequiredRelationType() == ICFBamSchema.RelationTypeEnum.Owner ) {
				return( relationDef );
			}
		}
		return( null );
	}

	public ICFBamRelationObj getInheritedOwnerRelation() {
		ICFBamTableObj tableDef = this;
		ICFBamRelationObj inheritedOwnerRelation = null;
		ICFBamRelationObj relationDef;
		Iterator<ICFBamRelationObj> childrenRelations;
		while( ( inheritedOwnerRelation == null ) && ( tableDef != null ) ) {
			childrenRelations = tableDef.getOptionalComponentsRelation().iterator();
			while (childrenRelations.hasNext()) {
				relationDef = childrenRelations.next();
				if( relationDef.getRequiredRelationType() == ICFBamSchema.RelationTypeEnum.Owner ) {
					return( relationDef );
				}
			}
			relationDef = tableDef.getSuperClassRelation();
			if( relationDef == null ) {
				tableDef = null;
			}
			else {
				tableDef = relationDef.getRequiredLookupToTable();
			}
		}
		return( null );
	}

	public List<ICFBamRelationObj> getOwnerLookupRelations() {
		ICFBamSchema.RelationTypeEnum relType;
		ICFBamRelationObj relationDef;
		List<ICFBamRelationObj> list = new LinkedList<ICFBamRelationObj>();
		Iterator<ICFBamRelationObj> childrenRelations = getOptionalComponentsRelation().iterator();
		while (childrenRelations.hasNext())
		{
			relationDef = childrenRelations.next();
			relType = relationDef.getRequiredRelationType();
			if( ( relType == ICFBamSchema.RelationTypeEnum.Parent )
			 || ( relType == ICFBamSchema.RelationTypeEnum.Container )
			 || ( relType == ICFBamSchema.RelationTypeEnum.Lookup )
			 || ( relType == ICFBamSchema.RelationTypeEnum.Owner ) )
			{
				list.add(relationDef);
			}
		}
		return( list );
	}

	public List<ICFBamRelationObj> getOwnerContainerNamedLookupChainRelations() {
		ICFBamSchema.RelationTypeEnum relType;
		ICFBamRelationObj relationDef;
		List<ICFBamRelationObj> list = new LinkedList<ICFBamRelationObj>();
		List<ICFBamChainObj> componentChains = getOptionalComponentsChains();
		Iterator<ICFBamRelationObj> childrenRelations = getOptionalComponentsRelation().iterator();
		while (childrenRelations.hasNext()) {
			relationDef = childrenRelations.next();
			relType = relationDef.getRequiredRelationType();
			if( relType == ICFBamSchema.RelationTypeEnum.Superclass ) {
				;
			}
			else if( ( relType == ICFBamSchema.RelationTypeEnum.Container )
				|| ( relType == ICFBamSchema.RelationTypeEnum.Owner ) )
			{
				list.add(relationDef);
			}
			else {
				if( ! relationDef.getRequiredIsXsdContainer() ) {
					ICFBamIndexObj toIndex = relationDef.getRequiredLookupToIndex();
					if( toIndex.getRequiredIsUnique() ) {
						boolean referencedByChain = false;
						Iterator<ICFBamChainObj> iterChain = componentChains.iterator();
						while( ( ! referencedByChain ) && iterChain.hasNext() ) {
							ICFBamChainObj chain = iterChain.next();
							if( chain.getRequiredLookupPrevRel() == relationDef ) {
								referencedByChain = true;
							}
							else if( chain.getRequiredLookupNextRel() == relationDef ) {
								referencedByChain = true;
							}
						}
						if( referencedByChain ) {
							list.add( relationDef );
						}
						else {
							ICFBamTableObj toTable = relationDef.getRequiredLookupToTable();
							ICFBamIndexObj lookupIndex = toTable.getOptionalLookupLookupIndex();
							while( ( lookupIndex == null ) && ( toTable != null ) ) {
								ICFBamRelationObj superRelation = toTable.getSuperClassRelation();
								if( superRelation != null ) {
									toTable = superRelation.getRequiredLookupToTable();
									if( toTable != null ) {
										lookupIndex = toTable.getOptionalLookupLookupIndex();
									}
								}
								else {
									toTable = null;
								}
							}
							if( lookupIndex != null ) {
								list.add( relationDef );
							}
						}
					}
				}
			}
		}
		return( list );
	}

	public List<ICFBamAtomObj> getChildrenAtoms() {
		List<ICFBamAtomObj> list = new LinkedList<ICFBamAtomObj>();
		Iterator<ICFBamValueObj> childrenColumns = getOptionalComponentsColumns().iterator();
		while (childrenColumns.hasNext())
		{
			ICFBamValueObj colDef = childrenColumns.next();
			if( colDef instanceof ICFBamAtomObj)
			{
				list.add( (ICFBamAtomObj)colDef);
			}
		}
		return( list );
	}

	public ICFBamRelationObj getSuperClassRelation() {
		Iterator<ICFBamRelationObj> childrenRelations = getOptionalComponentsRelation().iterator();
		while (childrenRelations.hasNext())
		{
			ICFBamRelationObj relationDef = childrenRelations.next();
			if( relationDef.getRequiredRelationType() == ICFBamSchema.RelationTypeEnum.Superclass ) {
				return( relationDef );
			}
		}
		return( null );
	}

	public List<ICFBamRelationObj> getSubClassRelations() {
		List<ICFBamRelationObj> list = new LinkedList<ICFBamRelationObj>();
		Iterator<ICFBamRelationObj> reverseRelations = getOptionalChildrenReverseRelations().iterator();
		while (reverseRelations.hasNext())
		{
			ICFBamRelationObj reverseRelation = reverseRelations.next();
			if( reverseRelation.getRequiredRelationType() == ICFBamSchema.RelationTypeEnum.Superclass ) {
				list.add(reverseRelation);
			}
		}
		return( list );
	}

	public ICFBamIndexObj getPrimaryKeyIndex() {
		ICFBamIndexObj primaryIndex = getOptionalLookupPrimaryIndex();
		return( primaryIndex );
	}

	public List<ICFBamRelationObj> getFactoryOwnerRelations() {
		List<ICFBamRelationObj> list = new LinkedList<ICFBamRelationObj>();
		Iterator<ICFBamRelationObj> ownerRelations = getContainerOwnerRelations().iterator();
		while (ownerRelations.hasNext())
		{
			// throw new NotImplementedException();
			ICFBamRelationObj ownerRelation = ownerRelations.next();
			if( ownerRelation.getRequiredRelationType() == ICFBamSchema.RelationTypeEnum.Superclass ) {
				list.add(ownerRelation);
			}
		}
		return( list );
	}

	public List<ICFBamIndexObj> getInheritedIndexes() {
		List<ICFBamIndexObj> list = new LinkedList<ICFBamIndexObj>();
		ICFBamRelationObj superClassRelation = getSuperClassRelation();
		if( superClassRelation != null)
		{
			Iterator<ICFBamIndexObj> superClassInheritedIndexes
				= superClassRelation.getRequiredLookupToTable().getInheritedIndexes().iterator();
			while (superClassInheritedIndexes.hasNext())
			{
				list.add(superClassInheritedIndexes.next());
			}
			Iterator<ICFBamIndexObj> myIndexes = getChildrenIndexes().iterator();
			while( myIndexes.hasNext() )
			{
				list.add( myIndexes.next() );
			}
		}
		return( list );
	}

	public List<ICFBamIndexObj> getChildrenIndexes() {
		Iterator<ICFBamIndexObj> iter = getOptionalComponentsIndex().iterator();
		List<ICFBamIndexObj> list = new LinkedList<ICFBamIndexObj>();
		while( iter.hasNext() ) {
			list.add( iter.next() );
		}
		return( list );
	}

	public List<ICFBamRelationObj> getInheritedRelations() {
		List<ICFBamRelationObj> list = new LinkedList<ICFBamRelationObj>();
		ICFBamRelationObj superClassRelation = getSuperClassRelation();
		if( superClassRelation != null)
		{
			Iterator<ICFBamRelationObj> superClassInheritedRelations
				= superClassRelation.getRequiredLookupToTable().getInheritedRelations().iterator();
			while (superClassInheritedRelations.hasNext())
			{
				list.add(superClassInheritedRelations.next());
			}
			Iterator<ICFBamRelationObj> myRelations = getChildrenRelations().iterator();
			while (myRelations.hasNext())
			{
				list.add(myRelations.next());
			}
		}
		return( list );
	}

	public List<ICFBamRelationObj> getChildrenRelations() {
		List<ICFBamRelationObj> childrenRelations = new LinkedList<ICFBamRelationObj>();
		Collection<ICFBamRelationObj> cltn = getOptionalComponentsRelation();
		Iterator<ICFBamRelationObj> iter = cltn.iterator();
		while( iter.hasNext() ) {
			childrenRelations.add( iter.next() );
		}
		return( childrenRelations );
	}
}
