// Description: Java 25 edit object instance implementation for CFBam SecClusRoleMemb.

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

public class CFBamSecClusRoleMembEditObj
	implements ICFBamSecClusRoleMembEditObj
{
	protected ICFSecSecClusRoleMembObj orig;
	protected ICFSecSecClusRoleMemb rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;

	public CFBamSecClusRoleMembEditObj( ICFSecSecClusRoleMembObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecClusRoleMemb origRec = orig.getRec();
		rec.set( origRec );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecClusRoleMemb rec = getRec();
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
			ICFSecSecClusRoleMemb rec = getRec();
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecClusRoleMembTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecClusRoleMemb" );
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
	public ICFSecSecClusRoleMembObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecClusRoleMembObj retobj = getSchema().getSecClusRoleMembTableObj().realiseSecClusRoleMemb( (ICFBamSecClusRoleMembObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecClusRoleMemb().forget();
	}

	@Override
	public ICFSecSecClusRoleMembObj read() {
		ICFSecSecClusRoleMembObj retval = getOrigAsSecClusRoleMemb().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecClusRoleMembObj read( boolean forceRead ) {
		ICFSecSecClusRoleMembObj retval = getOrigAsSecClusRoleMemb().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecClusRoleMembObj create() {
		copyRecToOrig();
		ICFSecSecClusRoleMembObj retobj = ((ICFBamSchemaObj)getOrigAsSecClusRoleMemb().getSchema()).getSecClusRoleMembTableObj().createSecClusRoleMemb( getOrigAsSecClusRoleMemb() );
		if( retobj == getOrigAsSecClusRoleMemb() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecClusRoleMembEditObj update() {
		getSchema().getSecClusRoleMembTableObj().updateSecClusRoleMemb( (ICFSecSecClusRoleMembObj)this );
		return( null );
	}

	@Override
	public CFSecSecClusRoleMembEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecClusRoleMembTableObj().deleteSecClusRoleMemb( getOrigAsSecClusRoleMemb() );
		return( null );
	}

	@Override
	public ICFSecSecClusRoleMembTableObj getSecClusRoleMembTable() {
		return( orig.getSchema().getSecClusRoleMembTableObj() );
	}

	@Override
	public ICFSecSecClusRoleMembEditObj getEdit() {
		return( (ICFSecSecClusRoleMembEditObj)this );
	}

	@Override
	public ICFSecSecClusRoleMembEditObj getEditAsSecClusRoleMemb() {
		return( (ICFSecSecClusRoleMembEditObj)this );
	}

	@Override
	public ICFSecSecClusRoleMembEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecClusRoleMembObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecClusRoleMembObj getOrigAsSecClusRoleMemb() {
		return( (ICFSecSecClusRoleMembObj)orig );
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
	public ICFSecSecClusRoleMemb getRec() {
		if( rec == null ) {
			rec = getOrigAsSecClusRoleMemb().getSchema().getCFSecBackingStore().getFactorySecClusRoleMemb().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecClusRoleMemb value ) {
		if( rec != value ) {
			rec = value;
		}
	}

	@Override
	public ICFSecSecClusRoleMemb getSecClusRoleMembRec() {
		return( (ICFSecSecClusRoleMemb)getRec() );
	}

	@Override
	public ICFSecSecClusRoleMembPKey getPKey() {
		return( orig.getPKey() );
	}

	@Override
	public void setPKey( ICFSecSecClusRoleMembPKey value ) {
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
	public CFLibDbKeyHash256 getRequiredSecClusRoleId() {
		return( getPKey().getRequiredSecClusRoleId() );
	}

	@Override
	public void setRequiredSecClusRoleId(CFLibDbKeyHash256 value) {
		if ((getPKey().getRequiredSecClusRoleId() != value ) || ( getSecClusRoleMembRec().getRequiredSecClusRoleId() != value )) {
			getPKey().setRequiredSecClusRoleId(value);
			getSecClusRoleMembRec().setRequiredSecClusRoleId( value );
		}
	}

	@Override
	public String getRequiredLoginId() {
		return( getPKey().getRequiredLoginId() );
	}

	@Override
	public void setRequiredLoginId(String value) {
		if ((getPKey().getRequiredLoginId() != value ) || ( getSecClusRoleMembRec().getRequiredLoginId() != value )) {
			getPKey().setRequiredLoginId(value);
			getSecClusRoleMembRec().setRequiredLoginId( value );
		}
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredSecClusRoleId(getPKey().getRequiredSecClusRoleId());
			rec.getPKey().setRequiredLoginId(getPKey().getRequiredLoginId());
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredSecClusRoleId(rec.getPKey().getRequiredSecClusRoleId());
			getPKey().setRequiredLoginId(rec.getPKey().getRequiredLoginId());
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFSecSecClusRoleMemb origRec = getOrigAsSecClusRoleMemb().getSecClusRoleMembRec();
		ICFSecSecClusRoleMemb myRec = getSecClusRoleMembRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecClusRoleMemb origRec = getOrigAsSecClusRoleMemb().getSecClusRoleMembRec();
		ICFSecSecClusRoleMemb myRec = getSecClusRoleMembRec();
		myRec.set( origRec );
	}
}
