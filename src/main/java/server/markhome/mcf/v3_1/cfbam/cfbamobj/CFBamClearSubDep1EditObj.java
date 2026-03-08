// Description: Java 25 edit object instance implementation for CFBam ClearSubDep1.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
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
 *	
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

public class CFBamClearSubDep1EditObj
	extends CFBamClearDepEditObj

	implements ICFBamClearSubDep1EditObj
{
	protected ICFBamClearTopDepObj requiredContainerClearTopDep;
	protected List<ICFBamClearSubDep2Obj> optionalComponentsClearDep;

	public CFBamClearSubDep1EditObj( ICFBamClearSubDep1Obj argOrig ) {
		super( argOrig );
		requiredContainerClearTopDep = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getClearSubDep1TableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "ClearSubDep1" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamClearTopDepObj scope = getRequiredContainerClearTopDep();
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
		ICFBamClearSubDep1Obj retobj = getSchema().getClearSubDep1TableObj().realiseClearSubDep1( (ICFBamClearSubDep1Obj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsClearSubDep1().forget();
	}

	@Override
	public ICFBamScopeObj create() {
		copyRecToOrig();
		ICFBamClearSubDep1Obj retobj = ((ICFBamSchemaObj)getOrigAsClearSubDep1().getSchema()).getClearSubDep1TableObj().createClearSubDep1( getOrigAsClearSubDep1() );
		if( retobj == getOrigAsClearSubDep1() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamScopeEditObj update() {
		getSchema().getClearSubDep1TableObj().updateClearSubDep1( (ICFBamClearSubDep1Obj)this );
		return( null );
	}

	@Override
	public CFBamScopeEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getClearSubDep1TableObj().deleteClearSubDep1( getOrigAsClearSubDep1() );
		return( null );
	}

	@Override
	public ICFBamClearSubDep1TableObj getClearSubDep1Table() {
		return( orig.getSchema().getClearSubDep1TableObj() );
	}

	@Override
	public ICFBamClearSubDep1EditObj getEditAsClearSubDep1() {
		return( (ICFBamClearSubDep1EditObj)this );
	}

	@Override
	public ICFBamClearSubDep1Obj getOrigAsClearSubDep1() {
		return( (ICFBamClearSubDep1Obj)orig );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			rec = getOrigAsClearSubDep1().getSchema().getCFBamBackingStore().getFactoryClearSubDep1().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( rec != value ) {
			super.setRec( value );
			requiredContainerClearTopDep = null;
		}
	}

	@Override
	public ICFBamClearSubDep1 getClearSubDep1Rec() {
		return( (ICFBamClearSubDep1)getRec() );
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
	public CFLibDbKeyHash256 getRequiredClearTopDepId() {
		return( getClearSubDep1Rec().getRequiredClearTopDepId() );
	}

	@Override
	public String getRequiredName() {
		return( getClearSubDep1Rec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getClearSubDep1Rec().getRequiredName() != value ) {
			getClearSubDep1Rec().setRequiredName( value );
		}
	}

	@Override
	public ICFBamClearTopDepObj getRequiredContainerClearTopDep() {
		return( getRequiredContainerClearTopDep( false ) );
	}

	@Override
	public ICFBamClearTopDepObj getRequiredContainerClearTopDep( boolean forceRead ) {
		if( forceRead || ( requiredContainerClearTopDep == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamClearTopDepObj obj = ((ICFBamSchemaObj)getOrigAsClearSubDep1().getSchema()).getClearTopDepTableObj().readClearTopDepByIdIdx( getClearSubDep1Rec().getRequiredClearTopDepId() );
				requiredContainerClearTopDep = obj;
				if( obj != null ) {
					requiredContainerClearTopDep = obj;
				}
			}
		}
		return( requiredContainerClearTopDep );
	}

	@Override
	public void setRequiredContainerClearTopDep( ICFBamClearTopDepObj value ) {
		if( rec == null ) {
			getClearSubDep1Rec();
		}
		if( value != null ) {
			requiredContainerClearTopDep = value;
			getClearSubDep1Rec().setRequiredContainerClearTopDep(value.getClearTopDepRec());
		}
		requiredContainerClearTopDep = value;
	}

	@Override
	public List<ICFBamClearSubDep2Obj> getOptionalComponentsClearDep() {
		List<ICFBamClearSubDep2Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getClearSubDep2TableObj().readClearSubDep2ByClearSubDep1Idx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamClearSubDep2Obj> getOptionalComponentsClearDep( boolean forceRead ) {
		List<ICFBamClearSubDep2Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getClearSubDep2TableObj().readClearSubDep2ByClearSubDep1Idx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public void copyRecToOrig() {
		ICFBamClearSubDep1 origRec = getOrigAsClearSubDep1().getClearSubDep1Rec();
		ICFBamClearSubDep1 myRec = getClearSubDep1Rec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamClearSubDep1 origRec = getOrigAsClearSubDep1().getClearSubDep1Rec();
		ICFBamClearSubDep1 myRec = getClearSubDep1Rec();
		myRec.set( origRec );
	}
}
