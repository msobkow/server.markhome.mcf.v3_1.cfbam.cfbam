// Description: Java 25 base object instance implementation for SecSysGrpMemb

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
import server.markhome.mcf.v3_1.cfsec.cfsec.*;

public class CFBamSecSysGrpMembObj
	implements ICFBamSecSysGrpMembObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecSecSysGrpMembEditObj edit;
	protected ICFSecSchemaObj schema;
	protected ICFSecSecSysGrpMembPKey pKey;
	protected ICFSecSecSysGrpMemb rec;
	protected ICFSecSecSysGrpObj requiredContainerGroup;
	protected ICFSecSecUserObj requiredParentUser;

	public CFBamSecSysGrpMembObj() {
		isNew = true;
		requiredContainerGroup = null;
		requiredParentUser = null;
	}

	public CFBamSecSysGrpMembObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		requiredContainerGroup = null;
		requiredParentUser = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getSecSysGrpMembTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecSysGrpMemb" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecSecSysGrpObj scope = getRequiredContainerGroup();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredLoginId();
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
	public ICFSecSecSysGrpMembObj realise() {
		ICFSecSecSysGrpMembObj retobj = ((ICFBamSchemaObj)getSchema()).getSecSysGrpMembTableObj().realiseSecSysGrpMemb(
			(ICFSecSecSysGrpMembObj)this );
		return( (ICFSecSecSysGrpMembObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getSecSysGrpMembTableObj().reallyDeepDisposeSecSysGrpMemb( (ICFSecSecSysGrpMembObj)this );
	}

	@Override
	public ICFSecSecSysGrpMembObj read() {
		ICFSecSecSysGrpMembObj retobj = ((ICFBamSchemaObj)getSchema()).getSecSysGrpMembTableObj().readSecSysGrpMembByIdIdx( getPKey().getRequiredSecSysGrpId(),
			getPKey().getRequiredLoginId(), false );
		return( (ICFSecSecSysGrpMembObj)retobj );
	}

	@Override
	public ICFSecSecSysGrpMembObj read( boolean forceRead ) {
		ICFSecSecSysGrpMembObj retobj = ((ICFBamSchemaObj)getSchema()).getSecSysGrpMembTableObj().readSecSysGrpMembByIdIdx( getPKey().getRequiredSecSysGrpId(),
			getPKey().getRequiredLoginId(), forceRead );
		return( (ICFSecSecSysGrpMembObj)retobj );
	}

	@Override
	public ICFSecSecSysGrpMembTableObj getSecSysGrpMembTable() {
		return( ((ICFBamSchemaObj)getSchema()).getSecSysGrpMembTableObj() );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		schema = value;
	}

	@Override
	public ICFSecSecSysGrpMemb getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getFactorySecSysGrpMemb().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableSecSysGrpMemb().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
						getPKey().getRequiredSecSysGrpId(),
						getPKey().getRequiredLoginId() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecSysGrpMemb value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecSecSysGrpMemb ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecSecSysGrpMembRec" );
		}
		rec = value;
		copyRecToPKey();
		requiredContainerGroup = null;
		requiredParentUser = null;
	}

	@Override
	public ICFSecSecSysGrpMemb getSecSysGrpMembRec() {
		return( (ICFSecSecSysGrpMemb)getRec() );
	}

	@Override
	public ICFSecSecSysGrpMembPKey getPKey() {
		if( pKey == null ) {
			pKey = getSchema().getCFSecBackingStore().getFactorySecSysGrpMemb().newPKey();
		}
		return( pKey );
	}

	@Override
	public void setPKey( ICFSecSecSysGrpMembPKey value ) {
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
	public ICFSecSecSysGrpMembEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecSecSysGrpMembObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecSecSysGrpMembObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getSecSysGrpMembTableObj().lockSecSysGrpMemb( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getSecSysGrpMembTableObj().newEditInstance( lockobj );
		return( (ICFSecSecSysGrpMembEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecSecSysGrpMembEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecSecSysGrpMembEditObj getEditAsSecSysGrpMemb() {
		return( (ICFSecSecSysGrpMembEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecSysGrpMemb rec = getRec();
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
			ICFSecSecSysGrpMemb rec = getRec();
			updatedBy = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredSecSysGrpId() {
		return( getPKey().getRequiredSecSysGrpId() );
	}

	@Override
	public String getRequiredLoginId() {
		return( getPKey().getRequiredLoginId() );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerGroup() {
		return( getRequiredContainerGroup( false ) );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerGroup( boolean forceRead ) {
		if( ( requiredContainerGroup == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredContainerGroup = ((ICFBamSchemaObj)getSchema()).getSecSysGrpTableObj().readSecSysGrpByIdIdx( getPKey().getRequiredSecSysGrpId(), forceRead );
			}
		}
		return( requiredContainerGroup );
	}

	@Override
	public ICFSecSecUserObj getRequiredParentUser() {
		return( getRequiredParentUser( false ) );
	}

	@Override
	public ICFSecSecUserObj getRequiredParentUser( boolean forceRead ) {
		if( ( requiredParentUser == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				requiredParentUser = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().readSecUserByULoginIdx( getPKey().getRequiredLoginId(), forceRead );
			}
		}
		return( requiredParentUser );
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredContainerGroup(getPKey().getRequiredContainerGroup());
			rec.getPKey().setRequiredParentUser(getPKey().getRequiredParentUser());
		}
		if( edit != null ) {
			edit.copyPKeyToRec();
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredContainerGroup(rec.getPKey().getRequiredContainerGroup());
			getPKey().setRequiredParentUser(rec.getPKey().getRequiredParentUser());
		}
	}
}
