// Description: Java 25 base object instance implementation for DelSubDep2

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

public class CFBamDelSubDep2Obj
	extends CFBamDelDepObj
	implements ICFBamDelSubDep2Obj
{
	protected ICFBamDelSubDep1Obj requiredContainerDelSubDep1;
	protected List<ICFBamDelSubDep3Obj> optionalComponentsDelDep;

	public CFBamDelSubDep2Obj() {
		super();
		requiredContainerDelSubDep1 = null;
	}

	public CFBamDelSubDep2Obj( ICFBamSchemaObj argSchema ) {
		super( argSchema );
		requiredContainerDelSubDep1 = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getDelSubDep2TableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "DelSubDep2" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamDelSubDep1Obj scope = getRequiredContainerDelSubDep1();
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
		ICFBamDelSubDep2Obj retobj = ((ICFBamSchemaObj)getSchema()).getDelSubDep2TableObj().realiseDelSubDep2(
			(ICFBamDelSubDep2Obj)this );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getDelSubDep2TableObj().reallyDeepDisposeDelSubDep2( (ICFBamDelSubDep2Obj)this );
	}

	@Override
	public ICFBamScopeObj read() {
		ICFBamDelSubDep2Obj retobj = ((ICFBamSchemaObj)getSchema()).getDelSubDep2TableObj().readDelSubDep2ByIdIdx( getPKey(), false );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamScopeObj read( boolean forceRead ) {
		ICFBamDelSubDep2Obj retobj = ((ICFBamSchemaObj)getSchema()).getDelSubDep2TableObj().readDelSubDep2ByIdIdx( getPKey(), forceRead );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamDelSubDep2TableObj getDelSubDep2Table() {
		return( ((ICFBamSchemaObj)getSchema()).getDelSubDep2TableObj() );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactoryDelSubDep2().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableDelSubDep2().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
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
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamDelSubDep2 ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamDelSubDep2Rec" );
		}
		rec = value;
		copyRecToPKey();
		requiredOwnerTenant = null;
		requiredLookupRelation = null;
		optionalLookupDefSchema = null;
		requiredContainerDelSubDep1 = null;
	}

	@Override
	public ICFBamDelSubDep2 getDelSubDep2Rec() {
		return( (ICFBamDelSubDep2)getRec() );
	}

	@Override
	public ICFBamScopeEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamDelSubDep2Obj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamDelSubDep2Obj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getDelSubDep2TableObj().lockDelSubDep2( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getDelSubDep2TableObj().newEditInstance( lockobj );
		return( (ICFBamScopeEditObj)edit );
	}

	@Override
	public ICFBamDelSubDep2EditObj getEditAsDelSubDep2() {
		return( (ICFBamDelSubDep2EditObj)edit );
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
	public ICFBamDelSubDep1Obj getRequiredContainerDelSubDep1() {
		return( getRequiredContainerDelSubDep1( false ) );
	}

	@Override
	public ICFBamDelSubDep1Obj getRequiredContainerDelSubDep1( boolean forceRead ) {
		if( ( requiredContainerDelSubDep1 == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerDelSubDep1 = ((ICFBamSchemaObj)getSchema()).getDelSubDep1TableObj().readDelSubDep1ByIdIdx( getDelSubDep2Rec().getRequiredDelSubDep1Id(), forceRead );
			}
		}
		return( requiredContainerDelSubDep1 );
	}

	@Override
	public List<ICFBamDelSubDep3Obj> getOptionalComponentsDelDep() {
		List<ICFBamDelSubDep3Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getDelSubDep3TableObj().readDelSubDep3ByDelSubDep2Idx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamDelSubDep3Obj> getOptionalComponentsDelDep( boolean forceRead ) {
		List<ICFBamDelSubDep3Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getDelSubDep3TableObj().readDelSubDep3ByDelSubDep2Idx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredDelSubDep1Id() {
		return( getDelSubDep2Rec().getRequiredDelSubDep1Id() );
	}

	@Override
	public String getRequiredName() {
		return( getDelSubDep2Rec().getRequiredName() );
	}
}
