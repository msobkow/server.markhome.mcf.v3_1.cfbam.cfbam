// Description: Java 25 edit object instance implementation for CFBam SecUserEMConf.

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

public class CFBamSecUserEMConfEditObj
	implements ICFBamSecUserEMConfEditObj
{
	protected ICFSecSecUserEMConfObj orig;
	protected ICFSecSecUserEMConf rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFSecSecUserObj requiredContainerUser;

	public CFBamSecUserEMConfEditObj( ICFSecSecUserEMConfObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecUserEMConf origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerUser = null;
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecUserEMConf rec = getRec();
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
			ICFSecSecUserEMConf rec = getRec();
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecUserEMConfTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecUserEMConf" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecSecUserObj scope = getRequiredContainerUser();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		CFLibUuid6 val = rec.getRequiredEMConfirmationUuid6();
		if (val != null) {
			objName = val.toString();
		}
		else {
			objName = "";
		}
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
	public ICFSecSecUserEMConfObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecUserEMConfObj retobj = getSchema().getSecUserEMConfTableObj().realiseSecUserEMConf( (ICFBamSecUserEMConfObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecUserEMConf().forget();
	}

	@Override
	public ICFSecSecUserEMConfObj read() {
		ICFSecSecUserEMConfObj retval = getOrigAsSecUserEMConf().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecUserEMConfObj read( boolean forceRead ) {
		ICFSecSecUserEMConfObj retval = getOrigAsSecUserEMConf().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecUserEMConfObj create() {
		copyRecToOrig();
		ICFSecSecUserEMConfObj retobj = ((ICFBamSchemaObj)getOrigAsSecUserEMConf().getSchema()).getSecUserEMConfTableObj().createSecUserEMConf( getOrigAsSecUserEMConf() );
		if( retobj == getOrigAsSecUserEMConf() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecUserEMConfEditObj update() {
		getSchema().getSecUserEMConfTableObj().updateSecUserEMConf( (ICFSecSecUserEMConfObj)this );
		return( null );
	}

	@Override
	public CFSecSecUserEMConfEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecUserEMConfTableObj().deleteSecUserEMConf( getOrigAsSecUserEMConf() );
		return( null );
	}

	@Override
	public ICFSecSecUserEMConfTableObj getSecUserEMConfTable() {
		return( orig.getSchema().getSecUserEMConfTableObj() );
	}

	@Override
	public ICFSecSecUserEMConfEditObj getEdit() {
		return( (ICFSecSecUserEMConfEditObj)this );
	}

	@Override
	public ICFSecSecUserEMConfEditObj getEditAsSecUserEMConf() {
		return( (ICFSecSecUserEMConfEditObj)this );
	}

	@Override
	public ICFSecSecUserEMConfEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecUserEMConfObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecUserEMConfObj getOrigAsSecUserEMConf() {
		return( (ICFSecSecUserEMConfObj)orig );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( orig.getSchema() );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		orig.setSchema(value);
	}

	@Override
	public ICFSecSecUserEMConf getRec() {
		if( rec == null ) {
			rec = getOrigAsSecUserEMConf().getSchema().getCFSecBackingStore().getFactorySecUserEMConf().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecUserEMConf value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerUser = null;
		}
	}

	@Override
	public ICFSecSecUserEMConf getSecUserEMConfRec() {
		return( (ICFSecSecUserEMConf)getRec() );
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
	public CFLibDbKeyHash256 getRequiredSecUserId() {
		return( getPKey() );
	}

	@Override
	public String getRequiredConfirmEMailAddr() {
		return( getSecUserEMConfRec().getRequiredConfirmEMailAddr() );
	}

	@Override
	public void setRequiredConfirmEMailAddr( String value ) {
		if( getSecUserEMConfRec().getRequiredConfirmEMailAddr() != value ) {
			getSecUserEMConfRec().setRequiredConfirmEMailAddr( value );
		}
	}

	@Override
	public LocalDateTime getRequiredEMailSentStamp() {
		return( getSecUserEMConfRec().getRequiredEMailSentStamp() );
	}

	@Override
	public void setRequiredEMailSentStamp( LocalDateTime value ) {
		if( getSecUserEMConfRec().getRequiredEMailSentStamp() != value ) {
			getSecUserEMConfRec().setRequiredEMailSentStamp( value );
		}
	}

	@Override
	public CFLibUuid6 getRequiredEMConfirmationUuid6() {
		return( getSecUserEMConfRec().getRequiredEMConfirmationUuid6() );
	}

	@Override
	public void setRequiredEMConfirmationUuid6( CFLibUuid6 value ) {
		if( getSecUserEMConfRec().getRequiredEMConfirmationUuid6() != value ) {
			getSecUserEMConfRec().setRequiredEMConfirmationUuid6( value );
		}
	}

	@Override
	public boolean getRequiredNewAccount() {
		return( getSecUserEMConfRec().getRequiredNewAccount() );
	}

	@Override
	public void setRequiredNewAccount( boolean value ) {
		if( getSecUserEMConfRec().getRequiredNewAccount() != value ) {
			getSecUserEMConfRec().setRequiredNewAccount( value );
		}
	}

	@Override
	public ICFSecSecUserObj getRequiredContainerUser() {
		return( getRequiredContainerUser( false ) );
	}

	@Override
	public ICFSecSecUserObj getRequiredContainerUser( boolean forceRead ) {
		if( forceRead || ( requiredContainerUser == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecUserObj obj = ((ICFBamSchemaObj)getOrigAsSecUserEMConf().getSchema()).getSecUserTableObj().readSecUserByIdIdx( getPKey() );
				requiredContainerUser = obj;
				if( obj != null ) {
					requiredContainerUser = obj;
				}
			}
		}
		return( requiredContainerUser );
	}

	@Override
	public void setRequiredContainerUser( ICFSecSecUserObj value ) {
		if( rec == null ) {
			getSecUserEMConfRec();
		}
		if( value != null ) {
			requiredContainerUser = value;
			getSecUserEMConfRec().setRequiredContainerUser(value.getSecUserRec());
		}
		requiredContainerUser = value;
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
		ICFSecSecUserEMConf origRec = getOrigAsSecUserEMConf().getSecUserEMConfRec();
		ICFSecSecUserEMConf myRec = getSecUserEMConfRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecUserEMConf origRec = getOrigAsSecUserEMConf().getSecUserEMConfRec();
		ICFSecSecUserEMConf myRec = getSecUserEMConfRec();
		myRec.set( origRec );
	}
}
