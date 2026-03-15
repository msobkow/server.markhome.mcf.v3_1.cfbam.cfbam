// Description: Java 25 edit object instance implementation for CFBam ClearTopDep.

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
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamClearTopDepEditObj
	extends CFBamClearDepEditObj

	implements ICFBamClearTopDepEditObj
{
	protected ICFBamTableObj requiredContainerTable;
	protected List<ICFBamClearSubDep1Obj> optionalComponentsClearDep;
	protected ICFBamClearTopDepObj optionalLookupPrev;
	protected ICFBamClearTopDepObj optionalLookupNext;

	public CFBamClearTopDepEditObj( ICFBamClearTopDepObj argOrig ) {
		super( argOrig );
		requiredContainerTable = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getClearTopDepTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "ClearTopDep" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamTableObj scope = getRequiredContainerTable();
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
			else if( container instanceof ICFBamSchemaDefObj ) {
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
	public ICFBamScopeObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFBamClearTopDepObj retobj = getSchema().getClearTopDepTableObj().realiseClearTopDep( (ICFBamClearTopDepObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsClearTopDep().forget();
	}

	@Override
	public ICFBamClearTopDepObj moveUp() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUp" );
	}

	@Override
	public ICFBamClearTopDepObj moveDown() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDown" );
	}

	@Override
	public ICFBamScopeObj create() {
		copyRecToOrig();
		ICFBamClearTopDepObj retobj = ((ICFBamSchemaObj)getOrigAsClearTopDep().getSchema()).getClearTopDepTableObj().createClearTopDep( getOrigAsClearTopDep() );
		if( retobj == getOrigAsClearTopDep() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamScopeEditObj update() {
		getSchema().getClearTopDepTableObj().updateClearTopDep( (ICFBamClearTopDepObj)this );
		return( null );
	}

	@Override
	public CFBamScopeEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getClearTopDepTableObj().deleteClearTopDep( getOrigAsClearTopDep() );
		return( null );
	}

	@Override
	public ICFBamClearTopDepTableObj getClearTopDepTable() {
		return( orig.getSchema().getClearTopDepTableObj() );
	}

	@Override
	public ICFBamClearTopDepEditObj getEditAsClearTopDep() {
		return( (ICFBamClearTopDepEditObj)this );
	}

	@Override
	public ICFBamClearTopDepObj getOrigAsClearTopDep() {
		return( (ICFBamClearTopDepObj)orig );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			rec = getOrigAsClearTopDep().getSchema().getCFBamBackingStore().getFactoryClearTopDep().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( rec != value ) {
			super.setRec( value );
			requiredContainerTable = null;
			optionalLookupPrev = null;
			optionalLookupNext = null;
		}
	}

	@Override
	public ICFBamClearTopDep getClearTopDepRec() {
		return( (ICFBamClearTopDep)getRec() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredId(CFLibDbKeyHash256 id) {
		if (getPKey() != id) {
			setPKey(id);
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTableId() {
		return( getClearTopDepRec().getRequiredTableId() );
	}

	@Override
	public String getRequiredName() {
		return( getClearTopDepRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getClearTopDepRec().getRequiredName() != value ) {
			getClearTopDepRec().setRequiredName( value );
		}
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( getClearTopDepRec().getOptionalPrevId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( getClearTopDepRec().getOptionalNextId() );
	}

	@Override
	public ICFBamTableObj getRequiredContainerTable() {
		return( getRequiredContainerTable( false ) );
	}

	@Override
	public ICFBamTableObj getRequiredContainerTable( boolean forceRead ) {
		if( forceRead || ( requiredContainerTable == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamTableObj obj = ((ICFBamSchemaObj)getOrigAsClearTopDep().getSchema()).getTableTableObj().readTableByIdIdx( getClearTopDepRec().getRequiredTableId() );
				requiredContainerTable = obj;
				if( obj != null ) {
					requiredContainerTable = obj;
				}
			}
		}
		return( requiredContainerTable );
	}

	@Override
	public void setRequiredContainerTable( ICFBamTableObj value ) {
		if( rec == null ) {
			getClearTopDepRec();
		}
		if( value != null ) {
			requiredContainerTable = value;
			getClearTopDepRec().setRequiredContainerTable(value.getTableRec());
		}
		requiredContainerTable = value;
	}

	@Override
	public List<ICFBamClearSubDep1Obj> getOptionalComponentsClearDep() {
		List<ICFBamClearSubDep1Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getClearSubDep1TableObj().readClearSubDep1ByClearTopDepIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> getOptionalComponentsClearDep( boolean forceRead ) {
		List<ICFBamClearSubDep1Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getClearSubDep1TableObj().readClearSubDep1ByClearTopDepIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFBamClearTopDepObj getOptionalLookupPrev() {
		return( getOptionalLookupPrev( false ) );
	}

	@Override
	public ICFBamClearTopDepObj getOptionalLookupPrev( boolean forceRead ) {
		if( forceRead || ( optionalLookupPrev == null ) ) {
			boolean anyMissing = false;
			if( getClearTopDepRec().getOptionalPrevId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamClearTopDepObj obj = ((ICFBamSchemaObj)getOrigAsClearTopDep().getSchema()).getClearTopDepTableObj().readClearTopDepByIdIdx( getClearTopDepRec().getOptionalPrevId() );
				optionalLookupPrev = obj;
			}
		}
		return( optionalLookupPrev );
	}

	@Override
	public void setOptionalLookupPrev( ICFBamClearTopDepObj value ) {
		if( rec == null ) {
			getClearTopDepRec();
		}
		if( value != null ) {
			optionalLookupPrev = value;
			getClearTopDepRec().setOptionalLookupPrev(value.getClearTopDepRec());
		}
		else {
			optionalLookupPrev = null;
			getClearTopDepRec().setOptionalLookupPrev((ICFBamClearTopDep)null);
		}
		optionalLookupPrev = value;
	}

	@Override
	public ICFBamClearTopDepObj getOptionalLookupNext() {
		return( getOptionalLookupNext( false ) );
	}

	@Override
	public ICFBamClearTopDepObj getOptionalLookupNext( boolean forceRead ) {
		if( forceRead || ( optionalLookupNext == null ) ) {
			boolean anyMissing = false;
			if( getClearTopDepRec().getOptionalNextId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamClearTopDepObj obj = ((ICFBamSchemaObj)getOrigAsClearTopDep().getSchema()).getClearTopDepTableObj().readClearTopDepByIdIdx( getClearTopDepRec().getOptionalNextId() );
				optionalLookupNext = obj;
			}
		}
		return( optionalLookupNext );
	}

	@Override
	public void setOptionalLookupNext( ICFBamClearTopDepObj value ) {
		if( rec == null ) {
			getClearTopDepRec();
		}
		if( value != null ) {
			optionalLookupNext = value;
			getClearTopDepRec().setOptionalLookupNext(value.getClearTopDepRec());
		}
		else {
			optionalLookupNext = null;
			getClearTopDepRec().setOptionalLookupNext((ICFBamClearTopDep)null);
		}
		optionalLookupNext = value;
	}

	@Override
	public void copyRecToOrig() {
		ICFBamClearTopDep origRec = getOrigAsClearTopDep().getClearTopDepRec();
		ICFBamClearTopDep myRec = getClearTopDepRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamClearTopDep origRec = getOrigAsClearTopDep().getClearTopDepRec();
		ICFBamClearTopDep myRec = getClearTopDepRec();
		myRec.set( origRec );
	}
}
