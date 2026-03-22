// Description: Java 25 edit object instance implementation for CFBam SecSysGrp.

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
import server.markhome.mcf.v3_1.cfsec.cfsec.*;

public class CFBamSecSysGrpEditObj
	implements ICFBamSecSysGrpEditObj
{
	protected ICFSecSecSysGrpObj orig;
	protected ICFSecSecSysGrp rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected List<ICFSecSecSysGrpIncObj> optionalChildrenIncByGrp;
	protected List<ICFSecSecSysGrpMembObj> optionalChildrenMembByGrp;

	public CFBamSecSysGrpEditObj( ICFSecSecSysGrpObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecSysGrp origRec = orig.getRec();
		rec.set( origRec );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecSecSysGrp rec = getRec();
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
			ICFSecSecSysGrp rec = getRec();
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecSysGrpTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecSysGrp" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		return( null );
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
	public ICFSecSecSysGrpObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecSysGrpObj retobj = getSchema().getSecSysGrpTableObj().realiseSecSysGrp( (ICFBamSecSysGrpObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecSysGrp().forget();
	}

	@Override
	public ICFSecSecSysGrpObj read() {
		ICFSecSecSysGrpObj retval = getOrigAsSecSysGrp().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecSysGrpObj read( boolean forceRead ) {
		ICFSecSecSysGrpObj retval = getOrigAsSecSysGrp().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecSysGrpObj create() {
		copyRecToOrig();
		ICFSecSecSysGrpObj retobj = ((ICFBamSchemaObj)getOrigAsSecSysGrp().getSchema()).getSecSysGrpTableObj().createSecSysGrp( getOrigAsSecSysGrp() );
		if( retobj == getOrigAsSecSysGrp() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecSysGrpEditObj update() {
		getSchema().getSecSysGrpTableObj().updateSecSysGrp( (ICFSecSecSysGrpObj)this );
		return( null );
	}

	@Override
	public CFSecSecSysGrpEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecSysGrpTableObj().deleteSecSysGrp( getOrigAsSecSysGrp() );
		return( null );
	}

	@Override
	public ICFSecSecSysGrpTableObj getSecSysGrpTable() {
		return( orig.getSchema().getSecSysGrpTableObj() );
	}

	@Override
	public ICFSecSecSysGrpEditObj getEdit() {
		return( (ICFSecSecSysGrpEditObj)this );
	}

	@Override
	public ICFSecSecSysGrpEditObj getEditAsSecSysGrp() {
		return( (ICFSecSecSysGrpEditObj)this );
	}

	@Override
	public ICFSecSecSysGrpEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecSysGrpObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecSysGrpObj getOrigAsSecSysGrp() {
		return( (ICFSecSecSysGrpObj)orig );
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
	public ICFSecSecSysGrp getRec() {
		if( rec == null ) {
			rec = getOrigAsSecSysGrp().getSchema().getCFSecBackingStore().getFactorySecSysGrp().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecSysGrp value ) {
		if( rec != value ) {
			rec = value;
		}
	}

	@Override
	public ICFSecSecSysGrp getSecSysGrpRec() {
		return( (ICFSecSecSysGrp)getRec() );
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
	public CFLibDbKeyHash256 getRequiredSecSysGrpId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredSecSysGrpId(CFLibDbKeyHash256 secSysGrpId) {
		if (getPKey() != secSysGrpId) {
			setPKey(secSysGrpId);
			optionalChildrenIncByGrp = null;
			optionalChildrenMembByGrp = null;
		}
	}

	@Override
	public String getRequiredName() {
		return( getSecSysGrpRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getSecSysGrpRec().getRequiredName() != value ) {
			getSecSysGrpRec().setRequiredName( value );
		}
	}

	@Override
	public ICFSecSchema.SecLevelEnum getRequiredSecLevel() {
		return( getSecSysGrpRec().getRequiredSecLevel() );
	}

	@Override
	public void setRequiredSecLevel( ICFSecSchema.SecLevelEnum value ) {
		if( getSecSysGrpRec().getRequiredSecLevel() != value ) {
			getSecSysGrpRec().setRequiredSecLevel( value );
		}
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> getOptionalChildrenIncByGrp() {
		List<ICFSecSecSysGrpIncObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSecSysGrpIncTableObj().readSecSysGrpIncBySysGrpIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysGrpIncObj> getOptionalChildrenIncByGrp( boolean forceRead ) {
		List<ICFSecSecSysGrpIncObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSecSysGrpIncTableObj().readSecSysGrpIncBySysGrpIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> getOptionalChildrenMembByGrp() {
		List<ICFSecSecSysGrpMembObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSecSysGrpMembTableObj().readSecSysGrpMembBySysGrpIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecSysGrpMembObj> getOptionalChildrenMembByGrp( boolean forceRead ) {
		List<ICFSecSecSysGrpMembObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSecSysGrpMembTableObj().readSecSysGrpMembBySysGrpIdx( getPKey(),
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
		ICFSecSecSysGrp origRec = getOrigAsSecSysGrp().getSecSysGrpRec();
		ICFSecSecSysGrp myRec = getSecSysGrpRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecSysGrp origRec = getOrigAsSecSysGrp().getSecSysGrpRec();
		ICFSecSecSysGrp myRec = getSecSysGrpRec();
		myRec.set( origRec );
	}
}
