// Description: Java 25 edit object instance implementation for CFBam Cluster.

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

public class CFBamClusterEditObj
	implements ICFBamClusterEditObj
{
	protected ICFSecClusterObj orig;
	protected ICFSecCluster rec;
	protected ICFSecSecUserObj createdBy = null;
	protected ICFSecSecUserObj updatedBy = null;
	protected List<ICFSecHostNodeObj> optionalComponentsHostNode;
	protected List<ICFSecTenantObj> optionalComponentsTenant;
	protected List<ICFSecSecGroupObj> optionalComponentsSecGroup;
	protected List<ICFSecSysClusterObj> optionalComponentsSysCluster;

	public CFBamClusterEditObj( ICFSecClusterObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecCluster origRec = orig.getRec();
		rec.set( origRec );
	}

	@Override
	public ICFSecSecUserObj getCreatedBy() {
		if( createdBy == null ) {
			ICFSecCluster rec = getRec();
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
			ICFSecCluster rec = getRec();
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
		return( ((ICFSecSchemaObj)orig.getSchema()).getClusterTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "Cluster" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		return( null );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredFullDomName();
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
			subObj = ((ICFBamSchemaObj)getSchema()).getHostNodeTableObj().readHostNodeByHostNameIdx( getRequiredId(),
				nextName, false );
		}
		if( subObj == null ) {
			subObj = ((ICFBamSchemaObj)getSchema()).getTenantTableObj().readTenantByUNameIdx( getRequiredId(),
				nextName, false );
		}
		if( subObj == null ) {
			subObj = ((ICFBamSchemaObj)getSchema()).getSecGroupTableObj().readSecGroupByUNameIdx( getRequiredId(),
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
	public ICFSecClusterObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecClusterObj retobj = getSchema().getClusterTableObj().realiseCluster( (ICFBamClusterObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsCluster().forget();
	}

	@Override
	public ICFSecClusterObj read() {
		ICFSecClusterObj retval = getOrigAsCluster().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecClusterObj read( boolean forceRead ) {
		ICFSecClusterObj retval = getOrigAsCluster().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecClusterObj create() {
		copyRecToOrig();
		ICFSecClusterObj retobj = ((ICFBamSchemaObj)getOrigAsCluster().getSchema()).getClusterTableObj().createCluster( getOrigAsCluster() );
		if( retobj == getOrigAsCluster() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecClusterEditObj update() {
		getSchema().getClusterTableObj().updateCluster( (ICFSecClusterObj)this );
		return( null );
	}

	@Override
	public CFSecClusterEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getClusterTableObj().deleteCluster( getOrigAsCluster() );
		return( null );
	}

	@Override
	public ICFSecClusterTableObj getClusterTable() {
		return( orig.getSchema().getClusterTableObj() );
	}

	@Override
	public ICFSecClusterEditObj getEdit() {
		return( (ICFSecClusterEditObj)this );
	}

	@Override
	public ICFSecClusterEditObj getEditAsCluster() {
		return( (ICFSecClusterEditObj)this );
	}

	@Override
	public ICFSecClusterEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecClusterObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecClusterObj getOrigAsCluster() {
		return( (ICFSecClusterObj)orig );
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
	public ICFSecCluster getRec() {
		if( rec == null ) {
			rec = getOrigAsCluster().getSchema().getCFSecBackingStore().getFactoryCluster().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecCluster value ) {
		if( rec != value ) {
			rec = value;
		}
	}

	@Override
	public ICFSecCluster getClusterRec() {
		return( (ICFSecCluster)getRec() );
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
	public void setRequiredId(CFLibDbKeyHash256 id) {
		if (getPKey() != id) {
			setPKey(id);
			optionalComponentsHostNode = null;
			optionalComponentsTenant = null;
			optionalComponentsSecGroup = null;
			optionalComponentsSysCluster = null;
		}
	}

	@Override
	public String getRequiredFullDomName() {
		return( getClusterRec().getRequiredFullDomName() );
	}

	@Override
	public void setRequiredFullDomName( String value ) {
		if( getClusterRec().getRequiredFullDomName() != value ) {
			getClusterRec().setRequiredFullDomName( value );
		}
	}

	@Override
	public String getRequiredDescription() {
		return( getClusterRec().getRequiredDescription() );
	}

	@Override
	public void setRequiredDescription( String value ) {
		if( getClusterRec().getRequiredDescription() != value ) {
			getClusterRec().setRequiredDescription( value );
		}
	}

	@Override
	public List<ICFSecHostNodeObj> getOptionalComponentsHostNode() {
		List<ICFSecHostNodeObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getHostNodeTableObj().readHostNodeByClusterIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecHostNodeObj> getOptionalComponentsHostNode( boolean forceRead ) {
		List<ICFSecHostNodeObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getHostNodeTableObj().readHostNodeByClusterIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecTenantObj> getOptionalComponentsTenant() {
		List<ICFSecTenantObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTenantTableObj().readTenantByClusterIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecTenantObj> getOptionalComponentsTenant( boolean forceRead ) {
		List<ICFSecTenantObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTenantTableObj().readTenantByClusterIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSecGroupObj> getOptionalComponentsSecGroup() {
		List<ICFSecSecGroupObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSecGroupTableObj().readSecGroupByClusterIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSecGroupObj> getOptionalComponentsSecGroup( boolean forceRead ) {
		List<ICFSecSecGroupObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSecGroupTableObj().readSecGroupByClusterIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public List<ICFSecSysClusterObj> getOptionalComponentsSysCluster() {
		List<ICFSecSysClusterObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSysClusterTableObj().readSysClusterByClusterIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecSysClusterObj> getOptionalComponentsSysCluster( boolean forceRead ) {
		List<ICFSecSysClusterObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getSysClusterTableObj().readSysClusterByClusterIdx( getPKey(),
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
		ICFSecCluster origRec = getOrigAsCluster().getClusterRec();
		ICFSecCluster myRec = getClusterRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecCluster origRec = getOrigAsCluster().getClusterRec();
		ICFSecCluster myRec = getClusterRec();
		myRec.set( origRec );
	}
}
