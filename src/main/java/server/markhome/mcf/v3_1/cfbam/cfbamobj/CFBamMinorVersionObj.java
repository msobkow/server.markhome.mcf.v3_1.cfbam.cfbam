// Description: Java 25 base object instance implementation for MinorVersion

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
import server.markhome.mcf.v3_1.cfint.cfint.*;

public class CFBamMinorVersionObj
	implements ICFBamMinorVersionObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFIntMinorVersionEditObj edit;
	protected ICFIntSchemaObj schema;
	protected CFLibDbKeyHash256 pKey;
	protected ICFIntMinorVersion rec;
	protected ICFSecTenantObj requiredOwnerTenant;
	protected ICFIntMajorVersionObj requiredContainerParentMajVer;
	protected List<ICFBamSchemaDefObj> optionalComponentsSchemaDef;

	public CFBamMinorVersionObj() {
		isNew = true;
		requiredOwnerTenant = null;
		requiredContainerParentMajVer = null;
	}

	public CFBamMinorVersionObj( ICFIntSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredOwnerTenant = null;
		requiredContainerParentMajVer = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFIntSchemaObj)schema).getMinorVersionTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "MinorVersion" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFIntMajorVersionObj scope = getRequiredContainerParentMajVer();
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
		if( subObj == null ) {
			subObj = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByUNameIdx( getRequiredId(),
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
			else if( container instanceof ICFIntTenantObj ) {
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
	public ICFIntMinorVersionObj realise() {
		ICFIntMinorVersionObj retobj = ((ICFBamSchemaObj)getSchema()).getMinorVersionTableObj().realiseMinorVersion(
			(ICFIntMinorVersionObj)this );
		return( (ICFIntMinorVersionObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getMinorVersionTableObj().reallyDeepDisposeMinorVersion( (ICFIntMinorVersionObj)this );
	}

	@Override
	public ICFIntMinorVersionObj read() {
		ICFIntMinorVersionObj retobj = ((ICFBamSchemaObj)getSchema()).getMinorVersionTableObj().readMinorVersionByIdIdx( getPKey(), false );
		return( (ICFIntMinorVersionObj)retobj );
	}

	@Override
	public ICFIntMinorVersionObj read( boolean forceRead ) {
		ICFIntMinorVersionObj retobj = ((ICFBamSchemaObj)getSchema()).getMinorVersionTableObj().readMinorVersionByIdIdx( getPKey(), forceRead );
		return( (ICFIntMinorVersionObj)retobj );
	}

	@Override
	public ICFIntMinorVersionTableObj getMinorVersionTable() {
		return( ((ICFBamSchemaObj)getSchema()).getMinorVersionTableObj() );
	}

	@Override
	public ICFIntSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFIntSchemaObj value ) {
		schema = value;
	}

	@Override
	public ICFIntMinorVersion getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFIntBackingStore().getFactoryMinorVersion().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFIntBackingStore().getTableMinorVersion().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFIntMinorVersion value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFIntMinorVersion ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFIntMinorVersionRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredOwnerTenant = null;
		requiredContainerParentMajVer = null;
	}

	@Override
	public ICFIntMinorVersion getMinorVersionRec() {
		return( (ICFIntMinorVersion)getRec() );
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
	public ICFIntMinorVersionEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFIntMinorVersionObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFIntMinorVersionObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getMinorVersionTableObj().lockMinorVersion( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getMinorVersionTableObj().newEditInstance( lockobj );
		return( (ICFIntMinorVersionEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFIntMinorVersionEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFIntMinorVersionEditObj getEditAsMinorVersion() {
		return( (ICFIntMinorVersionEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFIntMinorVersion rec = getRec();
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
			ICFIntMinorVersion rec = getRec();
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
	public ICFSecTenantObj getRequiredOwnerTenant() {
		return( getRequiredOwnerTenant( false ) );
	}

	@Override
	public ICFSecTenantObj getRequiredOwnerTenant( boolean forceRead ) {
		if( ( requiredOwnerTenant == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredOwnerTenant = ((ICFBamSchemaObj)getSchema()).getTenantTableObj().readTenantByIdIdx( getMinorVersionRec().getRequiredTenantId(), forceRead );
			}
		}
		return( requiredOwnerTenant );
	}

	@Override
	public ICFIntMajorVersionObj getRequiredContainerParentMajVer() {
		return( getRequiredContainerParentMajVer( false ) );
	}

	@Override
	public ICFIntMajorVersionObj getRequiredContainerParentMajVer( boolean forceRead ) {
		if( ( requiredContainerParentMajVer == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerParentMajVer = ((ICFBamSchemaObj)getSchema()).getMajorVersionTableObj().readMajorVersionByIdIdx( getMinorVersionRec().getRequiredMajorVersionId(), forceRead );
			}
		}
		return( requiredContainerParentMajVer );
	}

	@Override
	public List<ICFBamSchemaDefObj> getOptionalComponentsSchemaDef() {
		List<ICFBamSchemaDefObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByMinorVersionIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamSchemaDefObj> getOptionalComponentsSchemaDef( boolean forceRead ) {
		List<ICFBamSchemaDefObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().readSchemaDefByMinorVersionIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTenantId() {
		return( getMinorVersionRec().getRequiredTenantId() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredMajorVersionId() {
		return( getMinorVersionRec().getRequiredMajorVersionId() );
	}

	@Override
	public String getRequiredName() {
		return( getMinorVersionRec().getRequiredName() );
	}

	@Override
	public String getOptionalDescription() {
		return( getMinorVersionRec().getOptionalDescription() );
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
