// Description: Java 25 edit object instance implementation for CFBam PopSubDep3.

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

public class CFBamPopSubDep3EditObj
	extends CFBamPopDepEditObj

	implements ICFBamPopSubDep3EditObj
{
	protected ICFBamPopSubDep2Obj requiredContainerPopSubDep2;

	public CFBamPopSubDep3EditObj( ICFBamPopSubDep3Obj argOrig ) {
		super( argOrig );
		requiredContainerPopSubDep2 = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getPopSubDep3TableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "PopSubDep3" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamPopSubDep2Obj scope = getRequiredContainerPopSubDep2();
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
		ICFBamPopSubDep3Obj retobj = getSchema().getPopSubDep3TableObj().realisePopSubDep3( (ICFBamPopSubDep3Obj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsPopSubDep3().forget();
	}

	@Override
	public ICFBamScopeObj create() {
		copyRecToOrig();
		ICFBamPopSubDep3Obj retobj = ((ICFBamSchemaObj)getOrigAsPopSubDep3().getSchema()).getPopSubDep3TableObj().createPopSubDep3( getOrigAsPopSubDep3() );
		if( retobj == getOrigAsPopSubDep3() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamScopeEditObj update() {
		getSchema().getPopSubDep3TableObj().updatePopSubDep3( (ICFBamPopSubDep3Obj)this );
		return( null );
	}

	@Override
	public CFBamScopeEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getPopSubDep3TableObj().deletePopSubDep3( getOrigAsPopSubDep3() );
		return( null );
	}

	@Override
	public ICFBamPopSubDep3TableObj getPopSubDep3Table() {
		return( orig.getSchema().getPopSubDep3TableObj() );
	}

	@Override
	public ICFBamPopSubDep3EditObj getEditAsPopSubDep3() {
		return( (ICFBamPopSubDep3EditObj)this );
	}

	@Override
	public ICFBamPopSubDep3Obj getOrigAsPopSubDep3() {
		return( (ICFBamPopSubDep3Obj)orig );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			rec = getOrigAsPopSubDep3().getSchema().getCFBamBackingStore().getFactoryPopSubDep3().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( rec != value ) {
			super.setRec( value );
			requiredContainerPopSubDep2 = null;
		}
	}

	@Override
	public ICFBamPopSubDep3 getPopSubDep3Rec() {
		return( (ICFBamPopSubDep3)getRec() );
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
	public CFLibDbKeyHash256 getRequiredPopSubDep2Id() {
		return( getPopSubDep3Rec().getRequiredPopSubDep2Id() );
	}

	@Override
	public String getRequiredName() {
		return( getPopSubDep3Rec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getPopSubDep3Rec().getRequiredName() != value ) {
			getPopSubDep3Rec().setRequiredName( value );
		}
	}

	@Override
	public ICFBamPopSubDep2Obj getRequiredContainerPopSubDep2() {
		return( getRequiredContainerPopSubDep2( false ) );
	}

	@Override
	public ICFBamPopSubDep2Obj getRequiredContainerPopSubDep2( boolean forceRead ) {
		if( forceRead || ( requiredContainerPopSubDep2 == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamPopSubDep2Obj obj = ((ICFBamSchemaObj)getOrigAsPopSubDep3().getSchema()).getPopSubDep2TableObj().readPopSubDep2ByIdIdx( getPopSubDep3Rec().getRequiredPopSubDep2Id() );
				requiredContainerPopSubDep2 = obj;
				if( obj != null ) {
					requiredContainerPopSubDep2 = obj;
				}
			}
		}
		return( requiredContainerPopSubDep2 );
	}

	@Override
	public void setRequiredContainerPopSubDep2( ICFBamPopSubDep2Obj value ) {
		if( rec == null ) {
			getPopSubDep3Rec();
		}
		if( value != null ) {
			requiredContainerPopSubDep2 = value;
			getPopSubDep3Rec().setRequiredContainerPopSubDep2(value.getPopSubDep2Rec());
		}
		requiredContainerPopSubDep2 = value;
	}

	@Override
	public void copyRecToOrig() {
		ICFBamPopSubDep3 origRec = getOrigAsPopSubDep3().getPopSubDep3Rec();
		ICFBamPopSubDep3 myRec = getPopSubDep3Rec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamPopSubDep3 origRec = getOrigAsPopSubDep3().getPopSubDep3Rec();
		ICFBamPopSubDep3 myRec = getPopSubDep3Rec();
		myRec.set( origRec );
	}
}
