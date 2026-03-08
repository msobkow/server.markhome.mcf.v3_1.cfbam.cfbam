// Description: Java 25 base object instance implementation for Index

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

public class CFBamIndexObj
	extends CFBamScopeObj
	implements ICFBamIndexObj
{
	protected ICFBamTableObj requiredContainerTable;
	protected ICFBamSchemaDefObj optionalLookupDefSchema;
	protected List<ICFBamIndexColObj> optionalComponentsColumns;

	public CFBamIndexObj() {
		super();
		requiredContainerTable = null;
		optionalLookupDefSchema = null;
	}

	public CFBamIndexObj( ICFBamSchemaObj argSchema ) {
		super( argSchema );
		requiredContainerTable = null;
		optionalLookupDefSchema = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getIndexTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "Index" );
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
			subObj = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().readIndexColByUNameIdx( getRequiredId(),
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
		ICFBamIndexObj retobj = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().realiseIndex(
			(ICFBamIndexObj)this );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getIndexTableObj().reallyDeepDisposeIndex( (ICFBamIndexObj)this );
	}

	@Override
	public ICFBamScopeObj read() {
		ICFBamIndexObj retobj = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().readIndexByIdIdx( getPKey(), false );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamScopeObj read( boolean forceRead ) {
		ICFBamIndexObj retobj = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().readIndexByIdIdx( getPKey(), forceRead );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamIndexTableObj getIndexTable() {
		return( ((ICFBamSchemaObj)getSchema()).getIndexTableObj() );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactoryIndex().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableIndex().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
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
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamIndex ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamIndexRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredOwnerTenant = null;
		requiredContainerTable = null;
		optionalLookupDefSchema = null;
	}

	@Override
	public ICFBamIndex getIndexRec() {
		return( (ICFBamIndex)getRec() );
	}

	@Override
	public ICFBamScopeEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamIndexObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamIndexObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().lockIndex( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getIndexTableObj().newEditInstance( lockobj );
		return( (ICFBamScopeEditObj)edit );
	}

	@Override
	public ICFBamIndexEditObj getEditAsIndex() {
		return( (ICFBamIndexEditObj)edit );
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
	public ICFBamTableObj getRequiredContainerTable() {
		return( getRequiredContainerTable( false ) );
	}

	@Override
	public ICFBamTableObj getRequiredContainerTable( boolean forceRead ) {
		if( ( requiredContainerTable == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerTable = ((ICFBamSchemaObj)getSchema()).getTableTableObj().readTableByIdIdx( getIndexRec().getRequiredTableId(), forceRead );
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
			if( getIndexRec().getOptionalDefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupDefSchema = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getIndexRec().getOptionalDefSchemaId(), forceRead );
			}
		}
		return( optionalLookupDefSchema );
	}

	@Override
	public List<ICFBamIndexColObj> getOptionalComponentsColumns() {
		List<ICFBamIndexColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().readIndexColByIndexIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamIndexColObj> getOptionalComponentsColumns( boolean forceRead ) {
		List<ICFBamIndexColObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getIndexColTableObj().readIndexColByIndexIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTableId() {
		return( getIndexRec().getRequiredTableId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalDefSchemaId() {
		return( getIndexRec().getOptionalDefSchemaId() );
	}

	@Override
	public String getRequiredName() {
		return( getIndexRec().getRequiredName() );
	}

	@Override
	public String getOptionalShortName() {
		return( getIndexRec().getOptionalShortName() );
	}

	@Override
	public String getOptionalLabel() {
		return( getIndexRec().getOptionalLabel() );
	}

	@Override
	public String getOptionalShortDescription() {
		return( getIndexRec().getOptionalShortDescription() );
	}

	@Override
	public String getOptionalDescription() {
		return( getIndexRec().getOptionalDescription() );
	}

	@Override
	public String getOptionalDbName() {
		return( getIndexRec().getOptionalDbName() );
	}

	@Override
	public String getOptionalSuffix() {
		return( getIndexRec().getOptionalSuffix() );
	}

	@Override
	public boolean getRequiredIsUnique() {
		return( getIndexRec().getRequiredIsUnique() );
	}

	@Override
	public boolean getRequiredIsDbMapped() {
		return( getIndexRec().getRequiredIsDbMapped() );
	}
}
