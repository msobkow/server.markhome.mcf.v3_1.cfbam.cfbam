// Description: Java 25 edit object instance implementation for CFBam Table.

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

public class CFBamTableEditObj
	extends CFBamScopeEditObj

	implements ICFBamTableEditObj
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

	public CFBamTableEditObj( ICFBamTableObj argOrig ) {
		super( argOrig );
		requiredContainerSchemaDef = null;
		optionalLookupDefSchema = null;
		optionalLookupLookupIndex = null;
		optionalLookupAltIndex = null;
		optionalLookupQualTable = null;
		optionalLookupPrimaryIndex = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getTableTableObj().getClassCode() );
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
			subObj = ((ICFBamSchemaObj)getSchema()).getRelationTableObj().readRelationByUNameIdx( getRequiredId(),
				nextName, false );
		}
		if( subObj == null ) {
			subObj = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().readIndexByUNameIdx( getRequiredId(),
				nextName, false );
		}
		if( subObj == null ) {
			subObj = ((ICFBamSchemaObj)getSchema()).getValueTableObj().readValueByUNameIdx( getRequiredId(),
				nextName, false );
		}
		if( subObj == null ) {
			subObj = ((ICFBamSchemaObj)getSchema()).getChainTableObj().readChainByUNameIdx( getRequiredId(),
				nextName, false );
		}
		if( subObj == null ) {
			subObj = ((ICFBamSchemaObj)getSchema()).getServerMethodTableObj().readServerMethodByUNameIdx( getRequiredId(),
				nextName, false );
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
		// We realise this so that it's record will get copied to orig during realization
		ICFBamTableObj retobj = getSchema().getTableTableObj().realiseTable( (ICFBamTableObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsTable().forget();
	}

	@Override
	public ICFBamScopeObj create() {
		copyRecToOrig();
		ICFBamTableObj retobj = ((ICFBamSchemaObj)getOrigAsTable().getSchema()).getTableTableObj().createTable( getOrigAsTable() );
		if( retobj == getOrigAsTable() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamScopeEditObj update() {
		getSchema().getTableTableObj().updateTable( (ICFBamTableObj)this );
		return( null );
	}

	@Override
	public CFBamScopeEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getTableTableObj().deleteTable( getOrigAsTable() );
		return( null );
	}

	@Override
	public ICFBamTableTableObj getTableTable() {
		return( orig.getSchema().getTableTableObj() );
	}

	@Override
	public ICFBamTableEditObj getEditAsTable() {
		return( (ICFBamTableEditObj)this );
	}

	@Override
	public ICFBamTableObj getOrigAsTable() {
		return( (ICFBamTableObj)orig );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			rec = getOrigAsTable().getSchema().getCFBamBackingStore().getFactoryTable().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( rec != value ) {
			super.setRec( value );
			requiredContainerSchemaDef = null;
			optionalLookupDefSchema = null;
			optionalLookupLookupIndex = null;
			optionalLookupAltIndex = null;
			optionalLookupQualTable = null;
			optionalLookupPrimaryIndex = null;
		}
	}

	@Override
	public ICFBamTable getTableRec() {
		return( (ICFBamTable)getRec() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredId(CFLibDbKeyHash256 value) {
		if (getPKey() != value) {
			setPKey(value);
		}
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
	public void setRequiredName( String value ) {
		if( getTableRec().getRequiredName() != value ) {
			getTableRec().setRequiredName( value );
		}
	}

	@Override
	public String getOptionalDbName() {
		return( getTableRec().getOptionalDbName() );
	}

	@Override
	public void setOptionalDbName( String value ) {
		if( getTableRec().getOptionalDbName() != value ) {
			getTableRec().setOptionalDbName( value );
		}
	}

	@Override
	public String getOptionalShortName() {
		return( getTableRec().getOptionalShortName() );
	}

	@Override
	public void setOptionalShortName( String value ) {
		if( getTableRec().getOptionalShortName() != value ) {
			getTableRec().setOptionalShortName( value );
		}
	}

	@Override
	public String getOptionalLabel() {
		return( getTableRec().getOptionalLabel() );
	}

	@Override
	public void setOptionalLabel( String value ) {
		if( getTableRec().getOptionalLabel() != value ) {
			getTableRec().setOptionalLabel( value );
		}
	}

	@Override
	public String getOptionalShortDescription() {
		return( getTableRec().getOptionalShortDescription() );
	}

	@Override
	public void setOptionalShortDescription( String value ) {
		if( getTableRec().getOptionalShortDescription() != value ) {
			getTableRec().setOptionalShortDescription( value );
		}
	}

	@Override
	public String getOptionalDescription() {
		return( getTableRec().getOptionalDescription() );
	}

	@Override
	public void setOptionalDescription( String value ) {
		if( getTableRec().getOptionalDescription() != value ) {
			getTableRec().setOptionalDescription( value );
		}
	}

	@Override
	public boolean getRequiredPageData() {
		return( getTableRec().getRequiredPageData() );
	}

	@Override
	public void setRequiredPageData( boolean value ) {
		if( getTableRec().getRequiredPageData() != value ) {
			getTableRec().setRequiredPageData( value );
		}
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
	public void setRequiredTableClassCode( String value ) {
		if( getTableRec().getRequiredTableClassCode() != value ) {
			getTableRec().setRequiredTableClassCode( value );
		}
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
	public void setRequiredIsInstantiable( boolean value ) {
		if( getTableRec().getRequiredIsInstantiable() != value ) {
			getTableRec().setRequiredIsInstantiable( value );
		}
	}

	@Override
	public boolean getRequiredHasHistory() {
		return( getTableRec().getRequiredHasHistory() );
	}

	@Override
	public void setRequiredHasHistory( boolean value ) {
		if( getTableRec().getRequiredHasHistory() != value ) {
			getTableRec().setRequiredHasHistory( value );
		}
	}

	@Override
	public boolean getRequiredHasAuditColumns() {
		return( getTableRec().getRequiredHasAuditColumns() );
	}

	@Override
	public void setRequiredHasAuditColumns( boolean value ) {
		if( getTableRec().getRequiredHasAuditColumns() != value ) {
			getTableRec().setRequiredHasAuditColumns( value );
		}
	}

	@Override
	public boolean getRequiredIsMutable() {
		return( getTableRec().getRequiredIsMutable() );
	}

	@Override
	public void setRequiredIsMutable( boolean value ) {
		if( getTableRec().getRequiredIsMutable() != value ) {
			getTableRec().setRequiredIsMutable( value );
		}
	}

	@Override
	public boolean getRequiredIsServerOnly() {
		return( getTableRec().getRequiredIsServerOnly() );
	}

	@Override
	public void setRequiredIsServerOnly( boolean value ) {
		if( getTableRec().getRequiredIsServerOnly() != value ) {
			getTableRec().setRequiredIsServerOnly( value );
		}
	}

	@Override
	public ICFBamSchema.LoaderBehaviourEnum getRequiredLoaderBehaviour() {
		return( getTableRec().getRequiredLoaderBehaviour() );
	}

	@Override
	public void setRequiredLoaderBehaviour( ICFBamSchema.LoaderBehaviourEnum value ) {
		if( getTableRec().getRequiredLoaderBehaviour() != value ) {
			getTableRec().setRequiredLoaderBehaviour( value );
		}
	}

	@Override
	public ICFBamSchema.SecScopeEnum getRequiredSecScope() {
		return( getTableRec().getRequiredSecScope() );
	}

	@Override
	public void setRequiredSecScope( ICFBamSchema.SecScopeEnum value ) {
		if( getTableRec().getRequiredSecScope() != value ) {
			getTableRec().setRequiredSecScope( value );
		}
	}

	@Override
	public ICFBamSchemaDefObj getRequiredContainerSchemaDef() {
		return( getRequiredContainerSchemaDef( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getRequiredContainerSchemaDef( boolean forceRead ) {
		if( forceRead || ( requiredContainerSchemaDef == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamSchemaDefObj obj = ((ICFBamSchemaObj)getOrigAsTable().getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getTableRec().getRequiredSchemaDefId() );
				requiredContainerSchemaDef = obj;
				if( obj != null ) {
					requiredContainerSchemaDef = obj;
				}
			}
		}
		return( requiredContainerSchemaDef );
	}

	@Override
	public void setRequiredContainerSchemaDef( ICFBamSchemaDefObj value ) {
		if( rec == null ) {
			getTableRec();
		}
		if( value != null ) {
			requiredContainerSchemaDef = value;
			getTableRec().setRequiredContainerSchemaDef(value.getSchemaDefRec());
		}
		requiredContainerSchemaDef = value;
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema() {
		return( getOptionalLookupDefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead ) {
		if( forceRead || ( optionalLookupDefSchema == null ) ) {
			boolean anyMissing = false;
			if( getTableRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamSchemaDefObj obj = ((ICFBamSchemaObj)getOrigAsTable().getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getTableRec().getOptionalDefSchemaId() );
				optionalLookupDefSchema = obj;
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public void setOptionalLookupDefSchema( ICFBamSchemaDefObj value ) {
		if( rec == null ) {
			getTableRec();
		}
		if( value != null ) {
			optionalLookupDefSchema = value;
			getTableRec().setOptionalLookupDefSchema(value.getSchemaDefRec());
		}
		else {
			optionalLookupDefSchema = null;
			getTableRec().setOptionalLookupDefSchema((ICFBamSchemaDef)null);
		}
		optionalLookupDefSchema = value;
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
		if( forceRead || ( optionalLookupLookupIndex == null ) ) {
			boolean anyMissing = false;
			if( getTableRec().getOptionalLookupIndexId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamIndexObj obj = ((ICFBamSchemaObj)getOrigAsTable().getSchema()).getIndexTableObj().readIndexByIdIdx( getTableRec().getOptionalLookupIndexId() );
				optionalLookupLookupIndex = obj;
			}
		}
		return( optionalLookupLookupIndex );
	}

	@Override
	public void setOptionalLookupLookupIndex( ICFBamIndexObj value ) {
		if( rec == null ) {
			getTableRec();
		}
		if( value != null ) {
			optionalLookupLookupIndex = value;
			getTableRec().setOptionalLookupLookupIndex(value.getIndexRec());
		}
		else {
			optionalLookupLookupIndex = null;
			getTableRec().setOptionalLookupLookupIndex((ICFBamIndex)null);
		}
		optionalLookupLookupIndex = value;
	}

	@Override
	public ICFBamIndexObj getOptionalLookupAltIndex() {
		return( getOptionalLookupAltIndex( false ) );
	}

	@Override
	public ICFBamIndexObj getOptionalLookupAltIndex( boolean forceRead ) {
		if( forceRead || ( optionalLookupAltIndex == null ) ) {
			boolean anyMissing = false;
			if( getTableRec().getOptionalAltIndexId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamIndexObj obj = ((ICFBamSchemaObj)getOrigAsTable().getSchema()).getIndexTableObj().readIndexByIdIdx( getTableRec().getOptionalAltIndexId() );
				optionalLookupAltIndex = obj;
			}
		}
		return( optionalLookupAltIndex );
	}

	@Override
	public void setOptionalLookupAltIndex( ICFBamIndexObj value ) {
		if( rec == null ) {
			getTableRec();
		}
		if( value != null ) {
			optionalLookupAltIndex = value;
			getTableRec().setOptionalLookupAltIndex(value.getIndexRec());
		}
		else {
			optionalLookupAltIndex = null;
			getTableRec().setOptionalLookupAltIndex((ICFBamIndex)null);
		}
		optionalLookupAltIndex = value;
	}

	@Override
	public ICFBamTableObj getOptionalLookupQualTable() {
		return( getOptionalLookupQualTable( false ) );
	}

	@Override
	public ICFBamTableObj getOptionalLookupQualTable( boolean forceRead ) {
		if( forceRead || ( optionalLookupQualTable == null ) ) {
			boolean anyMissing = false;
			if( getTableRec().getOptionalQualifyingTableId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamTableObj obj = ((ICFBamSchemaObj)getOrigAsTable().getSchema()).getTableTableObj().readTableByIdIdx( getTableRec().getOptionalQualifyingTableId() );
				optionalLookupQualTable = obj;
			}
		}
		return( optionalLookupQualTable );
	}

	@Override
	public void setOptionalLookupQualTable( ICFBamTableObj value ) {
		if( rec == null ) {
			getTableRec();
		}
		if( value != null ) {
			optionalLookupQualTable = value;
			getTableRec().setOptionalLookupQualTable(value.getTableRec());
		}
		else {
			optionalLookupQualTable = null;
			getTableRec().setOptionalLookupQualTable((ICFBamTable)null);
		}
		optionalLookupQualTable = value;
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
		if( forceRead || ( optionalLookupPrimaryIndex == null ) ) {
			boolean anyMissing = false;
			if( getTableRec().getOptionalPrimaryIndexId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamIndexObj obj = ((ICFBamSchemaObj)getOrigAsTable().getSchema()).getIndexTableObj().readIndexByIdIdx( getTableRec().getOptionalPrimaryIndexId() );
				optionalLookupPrimaryIndex = obj;
			}
		}
		return( optionalLookupPrimaryIndex );
	}

	@Override
	public void setOptionalLookupPrimaryIndex( ICFBamIndexObj value ) {
		if( rec == null ) {
			getTableRec();
		}
		if( value != null ) {
			optionalLookupPrimaryIndex = value;
			getTableRec().setOptionalLookupPrimaryIndex(value.getIndexRec());
		}
		else {
			optionalLookupPrimaryIndex = null;
			getTableRec().setOptionalLookupPrimaryIndex((ICFBamIndex)null);
		}
		optionalLookupPrimaryIndex = value;
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
	public void copyRecToOrig() {
		ICFBamTable origRec = getOrigAsTable().getTableRec();
		ICFBamTable myRec = getTableRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamTable origRec = getOrigAsTable().getTableRec();
		ICFBamTable myRec = getTableRec();
		myRec.set( origRec );
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
