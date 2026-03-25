// Description: Java 25 edit object instance implementation for CFBam SecSysGrpInc.

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

public class CFBamSecSysGrpIncEditObj
	implements ICFBamSecSysGrpIncEditObj
{
	protected ICFSecSecSysGrpIncObj orig;
	protected ICFSecSecSysGrpInc rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected ICFSecSecSysGrpObj requiredContainerGroup;
	protected ICFSecSecSysGrpObj requiredParentSubGroup;

	public CFBamSecSysGrpIncEditObj( ICFSecSecSysGrpIncObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecSysGrpInc origRec = orig.getRec();
		rec.set( origRec );
		requiredContainerGroup = null;
		requiredParentSubGroup = null;
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecSysGrpInc rec = getRec();
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
			ICFSecSecSysGrpInc rec = getRec();
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecSysGrpIncTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecSysGrpInc" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFSecSecSysGrpObj scope = getRequiredContainerGroup();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredInclName();
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
	public ICFSecSecSysGrpIncObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecSysGrpIncObj retobj = getSchema().getSecSysGrpIncTableObj().realiseSecSysGrpInc( (ICFBamSecSysGrpIncObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecSysGrpInc().forget();
	}

	@Override
	public ICFSecSecSysGrpIncObj read() {
		ICFSecSecSysGrpIncObj retval = getOrigAsSecSysGrpInc().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecSysGrpIncObj read( boolean forceRead ) {
		ICFSecSecSysGrpIncObj retval = getOrigAsSecSysGrpInc().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecSysGrpIncObj create() {
		copyRecToOrig();
		ICFSecSecSysGrpIncObj retobj = ((ICFBamSchemaObj)getOrigAsSecSysGrpInc().getSchema()).getSecSysGrpIncTableObj().createSecSysGrpInc( getOrigAsSecSysGrpInc() );
		if( retobj == getOrigAsSecSysGrpInc() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecSysGrpIncEditObj update() {
		getSchema().getSecSysGrpIncTableObj().updateSecSysGrpInc( (ICFSecSecSysGrpIncObj)this );
		return( null );
	}

	@Override
	public CFSecSecSysGrpIncEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecSysGrpIncTableObj().deleteSecSysGrpInc( getOrigAsSecSysGrpInc() );
		return( null );
	}

	@Override
	public ICFSecSecSysGrpIncTableObj getSecSysGrpIncTable() {
		return( orig.getSchema().getSecSysGrpIncTableObj() );
	}

	@Override
	public ICFSecSecSysGrpIncEditObj getEdit() {
		return( (ICFSecSecSysGrpIncEditObj)this );
	}

	@Override
	public ICFSecSecSysGrpIncEditObj getEditAsSecSysGrpInc() {
		return( (ICFSecSecSysGrpIncEditObj)this );
	}

	@Override
	public ICFSecSecSysGrpIncEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecSysGrpIncObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecSysGrpIncObj getOrigAsSecSysGrpInc() {
		return( (ICFSecSecSysGrpIncObj)orig );
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
	public ICFSecSecSysGrpInc getRec() {
		if( rec == null ) {
			rec = getOrigAsSecSysGrpInc().getSchema().getCFSecBackingStore().getFactorySecSysGrpInc().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecSysGrpInc value ) {
		if( rec != value ) {
			rec = value;
			requiredContainerGroup = null;
			requiredParentSubGroup = null;
		}
	}

	@Override
	public ICFSecSecSysGrpInc getSecSysGrpIncRec() {
		return( (ICFSecSecSysGrpInc)getRec() );
	}

	@Override
	public ICFSecSecSysGrpIncPKey getPKey() {
		return( orig.getPKey() );
	}

	@Override
	public void setPKey( ICFSecSecSysGrpIncPKey value ) {
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
	public CFLibDbKeyHash256 getRequiredSecSysGrpId() {
		return( getPKey().getRequiredSecSysGrpId() );
	}

	@Override
	public String getRequiredInclName() {
		return( getPKey().getRequiredInclName() );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerGroup() {
		return( getRequiredContainerGroup( false ) );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredContainerGroup( boolean forceRead ) {
		if( forceRead || ( requiredContainerGroup == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecSysGrpObj obj = ((ICFBamSchemaObj)getOrigAsSecSysGrpInc().getSchema()).getSecSysGrpTableObj().readSecSysGrpByIdIdx( getPKey().getRequiredSecSysGrpId() );
				requiredContainerGroup = obj;
				if( obj != null ) {
					requiredContainerGroup = obj;
				}
			}
		}
		return( requiredContainerGroup );
	}

	@Override
	public void setRequiredContainerGroup( ICFSecSecSysGrpObj value ) {
		if( rec == null ) {
			getSecSysGrpIncRec();
		}
		if( value != null ) {
			requiredContainerGroup = value;
			getSecSysGrpIncRec().setRequiredContainerGroup(value.getSecSysGrpRec());
		}
		requiredContainerGroup = value;
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredParentSubGroup() {
		return( getRequiredParentSubGroup( false ) );
	}

	@Override
	public ICFSecSecSysGrpObj getRequiredParentSubGroup( boolean forceRead ) {
		if( forceRead || ( requiredParentSubGroup == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFSecSecSysGrpObj obj = ((ICFBamSchemaObj)getOrigAsSecSysGrpInc().getSchema()).getSecSysGrpTableObj().readSecSysGrpByUNameIdx( getPKey().getRequiredInclName() );
				requiredParentSubGroup = obj;
			}
		}
		return( requiredParentSubGroup );
	}

	@Override
	public void setRequiredParentSubGroup( ICFSecSecSysGrpObj value ) {
		if( rec == null ) {
			getSecSysGrpIncRec();
		}
		if( value != null ) {
			requiredParentSubGroup = value;
			getSecSysGrpIncRec().setRequiredParentSubGroup(value.getSecSysGrpRec());
		}
		else {
			requiredParentSubGroup = null;
			getSecSysGrpIncRec().setRequiredParentSubGroup((ICFSecSecSysGrp)null);
		}
		requiredParentSubGroup = value;
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredContainerGroup(getPKey().getRequiredContainerGroup());
			rec.getPKey().setRequiredParentSubGroup(getPKey().getRequiredParentSubGroup());
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredContainerGroup(rec.getPKey().getRequiredContainerGroup());
			getPKey().setRequiredParentSubGroup(rec.getPKey().getRequiredParentSubGroup());
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFSecSecSysGrpInc origRec = getOrigAsSecSysGrpInc().getSecSysGrpIncRec();
		ICFSecSecSysGrpInc myRec = getSecSysGrpIncRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecSysGrpInc origRec = getOrigAsSecSysGrpInc().getSecSysGrpIncRec();
		ICFSecSecSysGrpInc myRec = getSecSysGrpIncRec();
		myRec.set( origRec );
	}
}
