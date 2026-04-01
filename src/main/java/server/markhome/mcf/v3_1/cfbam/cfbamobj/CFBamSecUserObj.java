// Description: Java 25 base object instance implementation for SecUser

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

public class CFBamSecUserObj
	implements ICFBamSecUserObj
{
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected boolean isNew;
	protected ICFSecSecUserEditObj edit;
	protected ICFSecSchemaObj schema;
	protected CFLibDbKeyHash256 pKey;
	protected ICFSecSecUser rec;
	protected ICFSecSecUserPasswordObj optionalComponentsPassword;
	protected ICFSecSecUserEMConfObj optionalComponentsEMConf;
	protected ICFSecSecUserPWResetObj optionalComponentsPWReset;
	protected List<ICFSecSecSysGrpMembObj> optionalChildrenSysSecGrpMemb;
	protected List<ICFSecSecClusGrpMembObj> optionalChildrenClusSecGrpMemb;
	protected List<ICFSecSecTentGrpMembObj> optionalChildrenTentSecGrpMemb;

	public CFBamSecUserObj() {
		isNew = true;
	}

	public CFBamSecUserObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getSecUserTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecUser" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		return( null );
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
		if( subObj == null ) {
			subObj = ((ICFBamSchemaObj)getSchema()).getSecUserEMConfTableObj().readSecUserEMConfByUUuid6Idx( nextName, false );
		}
		if( subObj == null ) {
			subObj = ((ICFBamSchemaObj)getSchema()).getSecUserPWResetTableObj().readSecUserPWResetByUUuid6Idx( nextName, false );
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
	public ICFSecSecUserObj realise() {
		ICFSecSecUserObj retobj = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().realiseSecUser(
			(ICFSecSecUserObj)this );
		return( (ICFSecSecUserObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getSecUserTableObj().reallyDeepDisposeSecUser( (ICFSecSecUserObj)this );
	}

	@Override
	public ICFSecSecUserObj read() {
		ICFSecSecUserObj retobj = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( getPKey(), false );
		return( (ICFSecSecUserObj)retobj );
	}

	@Override
	public ICFSecSecUserObj read( boolean forceRead ) {
		ICFSecSecUserObj retobj = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( getPKey(), forceRead );
		return( (ICFSecSecUserObj)retobj );
	}

	@Override
	public ICFSecSecUserTableObj getSecUserTable() {
		return( ((ICFBamSchemaObj)getSchema()).getSecUserTableObj() );
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
	public ICFSecSecUser getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getFactorySecUser().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableSecUser().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecUser value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecSecUser ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecSecUserRec" );
		}
		rec = value;
		copyRecToPKey();
	}

	@Override
	public ICFSecSecUser getSecUserRec() {
		return( (ICFSecSecUser)getRec() );
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
	public ICFSecSecUserEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecSecUserObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecSecUserObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().lockSecUser( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().newEditInstance( lockobj );
		return( (ICFSecSecUserEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecSecUserEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecSecUserEditObj getEditAsSecUser() {
		return( (ICFSecSecUserEditObj)edit );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecUser rec = getRec();
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
			ICFSecSecUser rec = getRec();
			updatedBy = ((ICFBamSchemaObj)getSchema()).getSecUserTableObj().readSecUserByIdIdx( rec.getUpdatedByUserId() );
		}
		return( updatedBy );
	}

	@Override
	public LocalDateTime getUpdatedAt() {
		return( getRec().getUpdatedAt() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredSecUserId() {
		return( getPKey() );
	}

	@Override
	public ICFSecSecUserPasswordObj getOptionalComponentsPassword() {
		return( getOptionalComponentsPassword( false ) );
	}

	@Override
	public ICFSecSecUserPasswordObj getOptionalComponentsPassword( boolean forceRead ) {
		if( ( optionalComponentsPassword == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				optionalComponentsPassword = ((ICFBamSchemaObj)getSchema()).getSecUserPasswordTableObj().readSecUserPasswordByIdIdx( getPKey(), forceRead );
			}
		}
		return( optionalComponentsPassword );
	}

	@Override
	public ICFSecSecUserEMConfObj getOptionalComponentsEMConf() {
		return( getOptionalComponentsEMConf( false ) );
	}

	@Override
	public ICFSecSecUserEMConfObj getOptionalComponentsEMConf( boolean forceRead ) {
		if( ( optionalComponentsEMConf == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				optionalComponentsEMConf = ((ICFBamSchemaObj)getSchema()).getSecUserEMConfTableObj().readSecUserEMConfByIdIdx( getPKey(), forceRead );
			}
		}
		return( optionalComponentsEMConf );
	}

	@Override
	public ICFSecSecUserPWResetObj getOptionalComponentsPWReset() {
		return( getOptionalComponentsPWReset( false ) );
	}

	@Override
	public ICFSecSecUserPWResetObj getOptionalComponentsPWReset( boolean forceRead ) {
		if( ( optionalComponentsPWReset == null ) || forceRead ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				optionalComponentsPWReset = ((ICFBamSchemaObj)getSchema()).getSecUserPWResetTableObj().readSecUserPWResetByIdIdx( getPKey(), forceRead );
			}
		}
		return( optionalComponentsPWReset );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> getOptionalChildrenSysSecGrpMemb() {
		List<ICFSecSecSysGrpMembObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSecSysGrpMembTableObj().readSecSysGrpMembByLoginIdx( getSecUserRec().getRequiredLoginId(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> getOptionalChildrenSysSecGrpMemb( boolean forceRead ) {
		List<ICFSecSecSysGrpMembObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSecSysGrpMembTableObj().readSecSysGrpMembByLoginIdx( getSecUserRec().getRequiredLoginId(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> getOptionalChildrenClusSecGrpMemb() {
		List<ICFSecSecClusGrpMembObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSecClusGrpMembTableObj().readSecClusGrpMembByLoginIdx( getSecUserRec().getRequiredLoginId(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecClusGrpMembObj> getOptionalChildrenClusSecGrpMemb( boolean forceRead ) {
		List<ICFSecSecClusGrpMembObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSecClusGrpMembTableObj().readSecClusGrpMembByLoginIdx( getSecUserRec().getRequiredLoginId(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> getOptionalChildrenTentSecGrpMemb() {
		List<ICFSecSecTentGrpMembObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSecTentGrpMembTableObj().readSecTentGrpMembByUserIdx( getSecUserRec().getRequiredLoginId(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> getOptionalChildrenTentSecGrpMemb( boolean forceRead ) {
		List<ICFSecSecTentGrpMembObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSecTentGrpMembTableObj().readSecTentGrpMembByUserIdx( getSecUserRec().getRequiredLoginId(),
			forceRead );
		return( retval );
	}

	@Override
	public String getRequiredLoginId() {
		return( getSecUserRec().getRequiredLoginId() );
	}

	@Override
	public String getOptionalDfltSysGrpName() {
		return( getSecUserRec().getOptionalDfltSysGrpName() );
	}

	@Override
	public String getOptionalDfltClusGrpName() {
		return( getSecUserRec().getOptionalDfltClusGrpName() );
	}

	@Override
	public String getOptionalDfltTentGrpName() {
		return( getSecUserRec().getOptionalDfltTentGrpName() );
	}

	@Override
	public String getRequiredEMailAddress() {
		return( getSecUserRec().getRequiredEMailAddress() );
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
