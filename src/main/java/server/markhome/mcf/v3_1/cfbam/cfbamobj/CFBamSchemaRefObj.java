// Description: Java 25 base object instance implementation for SchemaRef

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

public class CFBamSchemaRefObj
	extends CFBamScopeObj
	implements ICFBamSchemaRefObj
{
	protected ICFBamSchemaDefObj requiredContainerSchema;
	protected ICFBamSchemaDefObj optionalLookupRefSchema;
	protected ICFBamSchemaRefObj optionalLookupPrev;
	protected ICFBamSchemaRefObj optionalLookupNext;

	public CFBamSchemaRefObj() {
		super();
		requiredContainerSchema = null;
		optionalLookupRefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	public CFBamSchemaRefObj( ICFBamSchemaObj argSchema ) {
		super( argSchema );
		requiredContainerSchema = null;
		optionalLookupRefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)schema).getSchemaRefTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SchemaRef" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamSchemaDefObj scope = getRequiredContainerSchema();
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
		ICFBamSchemaRefObj retobj = ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().realiseSchemaRef(
			(ICFBamSchemaRefObj)this );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().reallyDeepDisposeSchemaRef( (ICFBamSchemaRefObj)this );
	}

	@Override
	public ICFBamScopeObj read() {
		ICFBamSchemaRefObj retobj = ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().readSchemaRefByIdIdx( getPKey(), false );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamScopeObj read( boolean forceRead ) {
		ICFBamSchemaRefObj retobj = ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().readSchemaRefByIdIdx( getPKey(), forceRead );
		return( (ICFBamScopeObj)retobj );
	}

	@Override
	public ICFBamSchemaRefObj moveUp() {
		ICFBamSchemaRefObj retobj = ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().moveUpSchemaRef( this );
		return( (ICFBamSchemaRefObj)retobj );
	}

	@Override
	public ICFBamSchemaRefObj moveDown() {
		ICFBamSchemaRefObj retobj = ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().moveDownSchemaRef( this );
		return( (ICFBamSchemaRefObj)retobj );
	}

	@Override
	public ICFBamSchemaRefTableObj getSchemaRefTable() {
		return( ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj() );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFBamBackingStore().getFactorySchemaRef().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFBamBackingStore().getTableSchemaRef().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
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
		if( ! ( ( value == null ) || ! ( value instanceof ICFBamSchemaRef ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFBamSchemaRefRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredOwnerTenant = null;
		requiredContainerSchema = null;
		optionalLookupRefSchema = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	@Override
	public ICFBamSchemaRef getSchemaRefRec() {
		return( (ICFBamSchemaRef)getRec() );
	}

	@Override
	public ICFBamScopeEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFBamSchemaRefObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFBamSchemaRefObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().lockSchemaRef( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().newEditInstance( lockobj );
		return( (ICFBamScopeEditObj)edit );
	}

	@Override
	public ICFBamSchemaRefEditObj getEditAsSchemaRef() {
		return( (ICFBamSchemaRefEditObj)edit );
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
	public ICFBamSchemaDefObj getRequiredContainerSchema() {
		return( getRequiredContainerSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getRequiredContainerSchema( boolean forceRead ) {
		if( ( requiredContainerSchema == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerSchema = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getSchemaRefRec().getRequiredSchemaId(), forceRead );
			}
		}
		return( requiredContainerSchema );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupRefSchema() {
		return( getOptionalLookupRefSchema( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getOptionalLookupRefSchema( boolean forceRead ) {
		if( ( optionalLookupRefSchema == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getSchemaRefRec().getOptionalRefSchemaId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupRefSchema = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getSchemaRefRec().getOptionalRefSchemaId(), forceRead );
			}
		}
		return( optionalLookupRefSchema );
	}

	@Override
	public ICFBamSchemaRefObj getOptionalLookupPrev() {
		return( getOptionalLookupPrev( false ) );
	}

	@Override
	public ICFBamSchemaRefObj getOptionalLookupPrev( boolean forceRead ) {
		if( ( optionalLookupPrev == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getSchemaRefRec().getOptionalPrevId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupPrev = ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().readSchemaRefByIdIdx( getSchemaRefRec().getOptionalPrevId(), forceRead );
			}
		}
		return( optionalLookupPrev );
	}

	@Override
	public ICFBamSchemaRefObj getOptionalLookupNext() {
		return( getOptionalLookupNext( false ) );
	}

	@Override
	public ICFBamSchemaRefObj getOptionalLookupNext( boolean forceRead ) {
		if( ( optionalLookupNext == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getSchemaRefRec().getOptionalNextId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalLookupNext = ((ICFBamSchemaObj)getSchema()).getSchemaRefTableObj().readSchemaRefByIdIdx( getSchemaRefRec().getOptionalNextId(), forceRead );
			}
		}
		return( optionalLookupNext );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredSchemaId() {
		return( getSchemaRefRec().getRequiredSchemaId() );
	}

	@Override
	public String getRequiredName() {
		return( getSchemaRefRec().getRequiredName() );
	}

	@Override
	public String getRequiredRefModelName() {
		return( getSchemaRefRec().getRequiredRefModelName() );
	}

	@Override
	public String getRequiredIncludeRoot() {
		return( getSchemaRefRec().getRequiredIncludeRoot() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalRefSchemaId() {
		return( getSchemaRefRec().getOptionalRefSchemaId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( getSchemaRefRec().getOptionalPrevId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( getSchemaRefRec().getOptionalNextId() );
	}
}
