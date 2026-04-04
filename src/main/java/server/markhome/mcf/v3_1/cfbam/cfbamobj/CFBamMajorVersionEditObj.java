// Description: Java 25 edit object instance implementation for CFBam MajorVersion.

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
import server.markhome.mcf.v3_1.cfint.cfint.*;

public class CFBamMajorVersionEditObj
	implements ICFBamMajorVersionEditObj
{
	protected ICFIntMajorVersionObj orig;
	protected ICFIntMajorVersion rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFSecTenantObj requiredOwnerTenant;
	protected ICFIntSubProjectObj requiredContainerParentSPrj;
	protected List<ICFIntMinorVersionObj> optionalComponentsMinorVer;

	public CFBamMajorVersionEditObj( ICFIntMajorVersionObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFIntMajorVersion origRec = orig.getRec();
		rec.set( origRec );
		requiredOwnerTenant = null;
		requiredContainerParentSPrj = null;
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFIntMajorVersion rec = getRec();
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
			ICFIntMajorVersion rec = getRec();
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
		return( ((ICFIntSchemaObj)orig.getSchema()).getMajorVersionTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "MajorVersion" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFIntSubProjectObj scope = getRequiredContainerParentSPrj();
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
			try {
				if (nextName == null) {
					throw new CFLibNullArgumentException(getClass(), "getNamedObject", 0, "RequiredName");
				}
				String natNextName = nextName;
				subObj = ((ICFBamSchemaObj)getSchema()).getMinorVersionTableObj().readMinorVersionByNameIdx( getRequiredId(),
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
	public ICFIntMajorVersionObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFIntMajorVersionObj retobj = getSchema().getMajorVersionTableObj().realiseMajorVersion( (ICFBamMajorVersionObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsMajorVersion().forget();
	}

	@Override
	public ICFIntMajorVersionObj read() {
		ICFIntMajorVersionObj retval = getOrigAsMajorVersion().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFIntMajorVersionObj read( boolean forceRead ) {
		ICFIntMajorVersionObj retval = getOrigAsMajorVersion().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFIntMajorVersionObj create() {
		copyRecToOrig();
		ICFIntMajorVersionObj retobj = ((ICFBamSchemaObj)getOrigAsMajorVersion().getSchema()).getMajorVersionTableObj().createMajorVersion( getOrigAsMajorVersion() );
		if( retobj == getOrigAsMajorVersion() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFIntMajorVersionEditObj update() {
		getSchema().getMajorVersionTableObj().updateMajorVersion( (ICFIntMajorVersionObj)this );
		return( null );
	}

	@Override
	public CFIntMajorVersionEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getMajorVersionTableObj().deleteMajorVersion( getOrigAsMajorVersion() );
		return( null );
	}

	@Override
	public ICFIntMajorVersionTableObj getMajorVersionTable() {
		return( orig.getSchema().getMajorVersionTableObj() );
	}

	@Override
	public ICFIntMajorVersionEditObj getEdit() {
		return( (ICFIntMajorVersionEditObj)this );
	}

	@Override
	public ICFIntMajorVersionEditObj getEditAsMajorVersion() {
		return( (ICFIntMajorVersionEditObj)this );
	}

	@Override
	public ICFIntMajorVersionEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFIntMajorVersionObj getOrig() {
		return( orig );
	}

	@Override
	public ICFIntMajorVersionObj getOrigAsMajorVersion() {
		return( (ICFIntMajorVersionObj)orig );
	}

	@Override
	public ICFIntSchemaObj getSchema() {
		return( orig.getSchema() );
	}

	@Override
	public void setSchema( ICFIntSchemaObj value ) {
		orig.setSchema(value);
	}

	@Override
	public ICFIntMajorVersion getRec() {
		if( rec == null ) {
			rec = getOrigAsMajorVersion().getSchema().getCFIntBackingStore().getFactoryMajorVersion().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFIntMajorVersion value ) {
		if( rec != value ) {
			rec = value;
			requiredOwnerTenant = null;
			requiredContainerParentSPrj = null;
		}
	}

	@Override
	public ICFIntMajorVersion getMajorVersionRec() {
		return( (ICFIntMajorVersion)getRec() );
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
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredId(CFLibDbKeyHash256 value) {
		if (getPKey() != value) {
			setPKey(value);
			requiredOwnerTenant = null;
			requiredContainerParentSPrj = null;
			optionalComponentsMinorVer = null;
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTenantId() {
		return( getMajorVersionRec().getRequiredTenantId() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredSubProjectId() {
		return( getMajorVersionRec().getRequiredSubProjectId() );
	}

	@Override
	public String getRequiredName() {
		return( getMajorVersionRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getMajorVersionRec().getRequiredName() != value ) {
			getMajorVersionRec().setRequiredName( value );
		}
	}

	@Override
	public String getOptionalDescription() {
		return( getMajorVersionRec().getOptionalDescription() );
	}

	@Override
	public void setOptionalDescription( String value ) {
		if( getMajorVersionRec().getOptionalDescription() != value ) {
			getMajorVersionRec().setOptionalDescription( value );
		}
	}

	@Override
	public ICFSecTenantObj getRequiredOwnerTenant() {
		return( getRequiredOwnerTenant( false ) );
	}

	@Override
	public ICFSecTenantObj getRequiredOwnerTenant( boolean forceRead ) {
		if( forceRead || ( requiredOwnerTenant == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecTenantObj obj = ((ICFBamSchemaObj)getOrigAsMajorVersion().getSchema()).getTenantTableObj().readTenantByIdIdx( getMajorVersionRec().getRequiredTenantId() );
				requiredOwnerTenant = obj;
			}
		}
		return( requiredOwnerTenant );
	}

	@Override
	public void setRequiredOwnerTenant( ICFSecTenantObj value ) {
		if( rec == null ) {
			getMajorVersionRec();
		}
		if( value != null ) {
			requiredOwnerTenant = value;
			getMajorVersionRec().setRequiredOwnerTenant(value.getTenantRec());
		}
		requiredOwnerTenant = value;
	}

	@Override
	public ICFIntSubProjectObj getRequiredContainerParentSPrj() {
		return( getRequiredContainerParentSPrj( false ) );
	}

	@Override
	public ICFIntSubProjectObj getRequiredContainerParentSPrj( boolean forceRead ) {
		if( forceRead || ( requiredContainerParentSPrj == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFIntSubProjectObj obj = ((ICFBamSchemaObj)getOrigAsMajorVersion().getSchema()).getSubProjectTableObj().readSubProjectByIdIdx( getMajorVersionRec().getRequiredSubProjectId() );
				requiredContainerParentSPrj = obj;
				if( obj != null ) {
					requiredContainerParentSPrj = obj;
				}
			}
		}
		return( requiredContainerParentSPrj );
	}

	@Override
	public void setRequiredContainerParentSPrj( ICFIntSubProjectObj value ) {
		if( rec == null ) {
			getMajorVersionRec();
		}
		if( value != null ) {
			requiredContainerParentSPrj = value;
			getMajorVersionRec().setRequiredContainerParentSPrj(value.getSubProjectRec());
		}
		requiredContainerParentSPrj = value;
	}

	@Override
	public List<ICFIntMinorVersionObj> getOptionalComponentsMinorVer() {
		List<ICFIntMinorVersionObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getMinorVersionTableObj().readMinorVersionByMajorVerIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFIntMinorVersionObj> getOptionalComponentsMinorVer( boolean forceRead ) {
		List<ICFIntMinorVersionObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getMinorVersionTableObj().readMinorVersionByMajorVerIdx( getPKey(),
			forceRead );
		return( retval );
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
		ICFIntMajorVersion origRec = getOrigAsMajorVersion().getMajorVersionRec();
		ICFIntMajorVersion myRec = getMajorVersionRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFIntMajorVersion origRec = getOrigAsMajorVersion().getMajorVersionRec();
		ICFIntMajorVersion myRec = getMajorVersionRec();
		myRec.set( origRec );
	}
}
