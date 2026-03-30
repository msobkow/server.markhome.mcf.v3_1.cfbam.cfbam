// Description: Java 25 edit object instance implementation for CFBam SecUserPWHistory.

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

public class CFBamSecUserPWHistoryEditObj
	implements ICFBamSecUserPWHistoryEditObj
{
	protected ICFSecSecUserPWHistoryObj orig;
	protected ICFSecSecUserPWHistory rec;

	public CFBamSecUserPWHistoryEditObj( ICFSecSecUserPWHistoryObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecSecUserPWHistory origRec = orig.getRec();
		rec.set( origRec );
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)orig.getSchema()).getSecUserPWHistoryTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SecUserPWHistory" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		return( null );
	}

	@Override
	public String getObjName() {
		String objName;
		LocalDateTime val = rec.getRequiredPWSetStamp();
		if (val != null) {
			objName = CFLibXmlUtil.formatTimestamp(val);
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
	public ICFSecSecUserPWHistoryObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFSecSecUserPWHistoryObj retobj = getSchema().getSecUserPWHistoryTableObj().realiseSecUserPWHistory( (ICFBamSecUserPWHistoryObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSecUserPWHistory().forget();
	}

	@Override
	public ICFSecSecUserPWHistoryObj read() {
		ICFSecSecUserPWHistoryObj retval = getOrigAsSecUserPWHistory().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecUserPWHistoryObj read( boolean forceRead ) {
		ICFSecSecUserPWHistoryObj retval = getOrigAsSecUserPWHistory().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecSecUserPWHistoryObj create() {
		copyRecToOrig();
		ICFSecSecUserPWHistoryObj retobj = ((ICFBamSchemaObj)getOrigAsSecUserPWHistory().getSchema()).getSecUserPWHistoryTableObj().createSecUserPWHistory( getOrigAsSecUserPWHistory() );
		if( retobj == getOrigAsSecUserPWHistory() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecSecUserPWHistoryEditObj update() {
		getSchema().getSecUserPWHistoryTableObj().updateSecUserPWHistory( (ICFSecSecUserPWHistoryObj)this );
		return( null );
	}

	@Override
	public CFSecSecUserPWHistoryEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSecUserPWHistoryTableObj().deleteSecUserPWHistory( getOrigAsSecUserPWHistory() );
		return( null );
	}

	@Override
	public ICFSecSecUserPWHistoryTableObj getSecUserPWHistoryTable() {
		return( orig.getSchema().getSecUserPWHistoryTableObj() );
	}

	@Override
	public ICFSecSecUserPWHistoryEditObj getEdit() {
		return( (ICFSecSecUserPWHistoryEditObj)this );
	}

	@Override
	public ICFSecSecUserPWHistoryEditObj getEditAsSecUserPWHistory() {
		return( (ICFSecSecUserPWHistoryEditObj)this );
	}

	@Override
	public ICFSecSecUserPWHistoryEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecSecUserPWHistoryObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecSecUserPWHistoryObj getOrigAsSecUserPWHistory() {
		return( (ICFSecSecUserPWHistoryObj)orig );
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
	public ICFSecSecUserPWHistory getRec() {
		if( rec == null ) {
			rec = getOrigAsSecUserPWHistory().getSchema().getCFSecBackingStore().getFactorySecUserPWHistory().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecSecUserPWHistory value ) {
		if( rec != value ) {
			rec = value;
		}
	}

	@Override
	public ICFSecSecUserPWHistory getSecUserPWHistoryRec() {
		return( (ICFSecSecUserPWHistory)getRec() );
	}

	@Override
	public ICFSecSecUserPWHistoryPKey getPKey() {
		return( orig.getPKey() );
	}

	@Override
	public void setPKey( ICFSecSecUserPWHistoryPKey value ) {
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
		return( getPKey().getRequiredSecUserId() );
	}

	@Override
	public void setRequiredSecUserId(CFLibDbKeyHash256 value) {
		if ((getPKey().getRequiredSecUserId() != value ) || ( getSecUserPWHistoryRec().getRequiredSecUserId() != value )) {
			getPKey().setRequiredSecUserId(value);
			getSecUserPWHistoryRec().setRequiredSecUserId( value );
		}
	}

	@Override
	public LocalDateTime getRequiredPWSetStamp() {
		return( getPKey().getRequiredPWSetStamp() );
	}

	@Override
	public void setRequiredPWSetStamp(LocalDateTime value) {
		if ((getPKey().getRequiredPWSetStamp() != value ) || ( getSecUserPWHistoryRec().getRequiredPWSetStamp() != value )) {
			getPKey().setRequiredPWSetStamp(value);
			getSecUserPWHistoryRec().setRequiredPWSetStamp( value );
		}
	}

	@Override
	public LocalDateTime getRequiredPWReplacedStamp() {
		return( getSecUserPWHistoryRec().getRequiredPWReplacedStamp() );
	}

	@Override
	public void setRequiredPWReplacedStamp( LocalDateTime value ) {
		if( getSecUserPWHistoryRec().getRequiredPWReplacedStamp() != value ) {
			getSecUserPWHistoryRec().setRequiredPWReplacedStamp( value );
		}
	}

	@Override
	public String getRequiredPasswordHash() {
		return( getSecUserPWHistoryRec().getRequiredPasswordHash() );
	}

	@Override
	public void setRequiredPasswordHash( String value ) {
		if( getSecUserPWHistoryRec().getRequiredPasswordHash() != value ) {
			getSecUserPWHistoryRec().setRequiredPasswordHash( value );
		}
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			rec.getPKey().setRequiredSecUserId(getPKey().getRequiredSecUserId());
			rec.getPKey().setRequiredPWSetStamp(getPKey().getRequiredPWSetStamp());
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			getPKey().setRequiredSecUserId(rec.getPKey().getRequiredSecUserId());
			getPKey().setRequiredPWSetStamp(rec.getPKey().getRequiredPWSetStamp());
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFSecSecUserPWHistory origRec = getOrigAsSecUserPWHistory().getSecUserPWHistoryRec();
		ICFSecSecUserPWHistory myRec = getSecUserPWHistoryRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecSecUserPWHistory origRec = getOrigAsSecUserPWHistory().getSecUserPWHistoryRec();
		ICFSecSecUserPWHistory myRec = getSecUserPWHistoryRec();
		myRec.set( origRec );
	}
}
