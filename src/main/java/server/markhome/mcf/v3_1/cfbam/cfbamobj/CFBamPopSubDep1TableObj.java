// Description: Java 25 Table Object implementation for PopSubDep1.

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

public class CFBamPopSubDep1TableObj
	implements ICFBamPopSubDep1TableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamPopSubDep1.CLASS_CODE;
	protected static final int backingClassCode = ICFBamPopSubDep1.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> members;
	private Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> allPopSubDep1;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > > indexByTenantIdx;
	private Map< ICFBamPopDepByRelationIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > > indexByRelationIdx;
	private Map< ICFBamPopDepByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > > indexByDefSchemaIdx;
	private Map< ICFBamPopSubDep1ByPopTopDepIdxKey,
		Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > > indexByPopTopDepIdx;
	private Map< ICFBamPopSubDep1ByUNameIdxKey,
		ICFBamPopSubDep1Obj > indexByUNameIdx;
	public static String TABLE_NAME = "PopSubDep1";
	public static String TABLE_DBNAME = "popsubdep1";

	public CFBamPopSubDep1TableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamPopSubDep1Obj>();
		allPopSubDep1 = null;
		indexByTenantIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
		indexByPopTopDepIdx = null;
		indexByUNameIdx = null;
	}

	public CFBamPopSubDep1TableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamPopSubDep1Obj>();
		allPopSubDep1 = null;
		indexByTenantIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
		indexByPopTopDepIdx = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamPopSubDep1TableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamPopSubDep1TableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allPopSubDep1 = null;
		indexByTenantIdx = null;
		indexByRelationIdx = null;
		indexByDefSchemaIdx = null;
		indexByPopTopDepIdx = null;
		indexByUNameIdx = null;
		List<ICFBamPopSubDep1Obj> toForget = new LinkedList<ICFBamPopSubDep1Obj>();
		ICFBamPopSubDep1Obj cur = null;
		Iterator<ICFBamPopSubDep1Obj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamPopSubDep1Obj.
	 */
	@Override
	public ICFBamPopSubDep1Obj newInstance() {
		ICFBamPopSubDep1Obj inst = new CFBamPopSubDep1Obj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamPopSubDep1Obj.
	 */
	@Override
	public ICFBamPopSubDep1EditObj newEditInstance( ICFBamPopSubDep1Obj orig ) {
		ICFBamPopSubDep1EditObj edit = new CFBamPopSubDep1EditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamPopSubDep1Obj realisePopSubDep1( ICFBamPopSubDep1Obj Obj ) {
		ICFBamPopSubDep1Obj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamPopSubDep1Obj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamPopSubDep1Obj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByRelationIdx != null ) {
				ICFBamPopDepByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					indexByRelationIdx.remove( keyRelationIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamPopDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByPopTopDepIdx != null ) {
				ICFBamPopSubDep1ByPopTopDepIdxKey keyPopTopDepIdx =
					schema.getCFBamBackingStore().getFactoryPopSubDep1().newByPopTopDepIdxKey();
				keyPopTopDepIdx.setRequiredPopTopDepId( keepObj.getRequiredPopTopDepId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > mapPopTopDepIdx = indexByPopTopDepIdx.get( keyPopTopDepIdx );
				if( mapPopTopDepIdx != null ) {
					mapPopTopDepIdx.remove( keepObj.getPKey() );
					if( mapPopTopDepIdx.size() <= 0 ) {
						indexByPopTopDepIdx.remove( keyPopTopDepIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamPopSubDep1ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryPopSubDep1().newByUNameIdxKey();
				keyUNameIdx.setRequiredPopTopDepId( keepObj.getRequiredPopTopDepId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamPopSubDep1Obj)schema.getPopDepTableObj().realisePopDep( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRelationIdx != null ) {
				ICFBamPopDepByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					mapRelationIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamPopDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPopTopDepIdx != null ) {
				ICFBamPopSubDep1ByPopTopDepIdxKey keyPopTopDepIdx =
					schema.getCFBamBackingStore().getFactoryPopSubDep1().newByPopTopDepIdxKey();
				keyPopTopDepIdx.setRequiredPopTopDepId( keepObj.getRequiredPopTopDepId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > mapPopTopDepIdx = indexByPopTopDepIdx.get( keyPopTopDepIdx );
				if( mapPopTopDepIdx != null ) {
					mapPopTopDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamPopSubDep1ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryPopSubDep1().newByUNameIdxKey();
				keyUNameIdx.setRequiredPopTopDepId( keepObj.getRequiredPopTopDepId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allPopSubDep1 != null ) {
				allPopSubDep1.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamPopSubDep1Obj)schema.getPopDepTableObj().realisePopDep( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allPopSubDep1 != null ) {
				allPopSubDep1.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRelationIdx != null ) {
				ICFBamPopDepByRelationIdxKey keyRelationIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
				keyRelationIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > mapRelationIdx = indexByRelationIdx.get( keyRelationIdx );
				if( mapRelationIdx != null ) {
					mapRelationIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamPopDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPopTopDepIdx != null ) {
				ICFBamPopSubDep1ByPopTopDepIdxKey keyPopTopDepIdx =
					schema.getCFBamBackingStore().getFactoryPopSubDep1().newByPopTopDepIdxKey();
				keyPopTopDepIdx.setRequiredPopTopDepId( keepObj.getRequiredPopTopDepId() );
				Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj > mapPopTopDepIdx = indexByPopTopDepIdx.get( keyPopTopDepIdx );
				if( mapPopTopDepIdx != null ) {
					mapPopTopDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamPopSubDep1ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryPopSubDep1().newByUNameIdxKey();
				keyUNameIdx.setRequiredPopTopDepId( keepObj.getRequiredPopTopDepId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamPopSubDep1Obj createPopSubDep1( ICFBamPopSubDep1Obj Obj ) {
		ICFBamPopSubDep1Obj obj = Obj;
		ICFBamPopSubDep1 rec = obj.getPopSubDep1Rec();
		schema.getCFBamBackingStore().getTablePopSubDep1().createPopSubDep1(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamPopSubDep1Obj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamPopSubDep1Obj readPopSubDep1( CFLibDbKeyHash256 pkey ) {
		return( readPopSubDep1( pkey, false ) );
	}

	@Override
	public ICFBamPopSubDep1Obj readPopSubDep1( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamPopSubDep1Obj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamPopSubDep1 readRec = schema.getCFBamBackingStore().getTablePopSubDep1().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamPopSubDep1Obj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamPopSubDep1Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamPopSubDep1Obj readCachedPopSubDep1( CFLibDbKeyHash256 pkey ) {
		ICFBamPopSubDep1Obj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposePopSubDep1( ICFBamPopSubDep1Obj obj )
	{
		final String S_ProcName = "CFBamPopSubDep1TableObj.reallyDeepDisposePopSubDep1() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamPopSubDep1Obj existing = readCachedPopSubDep1( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamPopSubDep1ByPopTopDepIdxKey keyPopTopDepIdx = schema.getCFBamBackingStore().getFactoryPopSubDep1().newByPopTopDepIdxKey();
		keyPopTopDepIdx.setRequiredPopTopDepId( existing.getRequiredPopTopDepId() );

		ICFBamPopSubDep1ByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryPopSubDep1().newByUNameIdxKey();
		keyUNameIdx.setRequiredPopTopDepId( existing.getRequiredPopTopDepId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );


		schema.getPopSubDep2TableObj().deepDisposePopSubDep2ByPopSubDep1Idx( existing.getRequiredId() );

		if( indexByPopTopDepIdx != null ) {
			if( indexByPopTopDepIdx.containsKey( keyPopTopDepIdx ) ) {
				indexByPopTopDepIdx.get( keyPopTopDepIdx ).remove( pkey );
				if( indexByPopTopDepIdx.get( keyPopTopDepIdx ).size() <= 0 ) {
					indexByPopTopDepIdx.remove( keyPopTopDepIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


		schema.getPopDepTableObj().reallyDeepDisposePopDep( obj );
	}
	@Override
	public void deepDisposePopSubDep1( CFLibDbKeyHash256 pkey ) {
		ICFBamPopSubDep1Obj obj = readCachedPopSubDep1( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamPopSubDep1Obj lockPopSubDep1( CFLibDbKeyHash256 pkey ) {
		ICFBamPopSubDep1Obj locked = null;
		ICFBamPopSubDep1 lockRec = schema.getCFBamBackingStore().getTablePopSubDep1().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamPopSubDep1Obj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamPopSubDep1Obj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockPopSubDep1", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readAllPopSubDep1() {
		return( readAllPopSubDep1( false ) );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readAllPopSubDep1( boolean forceRead ) {
		final String S_ProcName = "readAllPopSubDep1";
		if( ( allPopSubDep1 == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> map = new HashMap<CFLibDbKeyHash256,ICFBamPopSubDep1Obj>();
			allPopSubDep1 = map;
			ICFBamPopSubDep1[] recList = schema.getCFBamBackingStore().getTablePopSubDep1().readAllDerived( null );
			ICFBamPopSubDep1 rec;
			ICFBamPopSubDep1Obj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopSubDep1Obj realised = (ICFBamPopSubDep1Obj)obj.realise();
			}
		}
		int len = allPopSubDep1.size();
		ICFBamPopSubDep1Obj arr[] = new ICFBamPopSubDep1Obj[len];
		Iterator<ICFBamPopSubDep1Obj> valIter = allPopSubDep1.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamPopSubDep1Obj> arrayList = new ArrayList<ICFBamPopSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopSubDep1Obj> cmp = new Comparator<ICFBamPopSubDep1Obj>() {
			@Override
			public int compare( ICFBamPopSubDep1Obj lhs, ICFBamPopSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamPopSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readCachedAllPopSubDep1() {
		final String S_ProcName = "readCachedAllPopSubDep1";
		ArrayList<ICFBamPopSubDep1Obj> arrayList = new ArrayList<ICFBamPopSubDep1Obj>();
		if( allPopSubDep1 != null ) {
			int len = allPopSubDep1.size();
			ICFBamPopSubDep1Obj arr[] = new ICFBamPopSubDep1Obj[len];
			Iterator<ICFBamPopSubDep1Obj> valIter = allPopSubDep1.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamPopSubDep1Obj> cmp = new Comparator<ICFBamPopSubDep1Obj>() {
			public int compare( ICFBamPopSubDep1Obj lhs, ICFBamPopSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamPopSubDep1Obj readPopSubDep1ByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readPopSubDep1ByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamPopSubDep1Obj readPopSubDep1ByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamPopSubDep1Obj obj = readPopSubDep1( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readPopSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readPopSubDep1ByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readPopSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopSubDep1ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep1Obj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopSubDep1Obj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopSubDep1Obj realised = (ICFBamPopSubDep1Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopSubDep1Obj arr[] = new ICFBamPopSubDep1Obj[len];
		Iterator<ICFBamPopSubDep1Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamPopSubDep1Obj> arrayList = new ArrayList<ICFBamPopSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopSubDep1Obj> cmp = new Comparator<ICFBamPopSubDep1Obj>() {
			@Override
			public int compare( ICFBamPopSubDep1Obj lhs, ICFBamPopSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamPopSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readPopSubDep1ByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readPopSubDep1ByRelationIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readPopSubDep1ByRelationIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopSubDep1ByRelationIdx";
		ICFBamPopDepByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> dict;
		if( indexByRelationIdx == null ) {
			indexByRelationIdx = new HashMap< ICFBamPopDepByRelationIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep1Obj > >();
		}
		if( ( ! forceRead ) && indexByRelationIdx.containsKey( key ) ) {
			dict = indexByRelationIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopSubDep1Obj>();
			ICFBamPopDepObj obj;
			ICFBamPopDep[] recList = schema.getCFBamBackingStore().getTablePopDep().readDerivedByRelationIdx( null,
				RelationId );
			ICFBamPopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopSubDep1Obj realised = (ICFBamPopSubDep1Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByRelationIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopSubDep1Obj arr[] = new ICFBamPopSubDep1Obj[len];
		Iterator<ICFBamPopSubDep1Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamPopSubDep1Obj> arrayList = new ArrayList<ICFBamPopSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopSubDep1Obj> cmp = new Comparator<ICFBamPopSubDep1Obj>() {
			@Override
			public int compare( ICFBamPopSubDep1Obj lhs, ICFBamPopSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamPopSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readPopSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readPopSubDep1ByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readPopSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopSubDep1ByDefSchemaIdx";
		ICFBamPopDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamPopDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep1Obj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopSubDep1Obj>();
			ICFBamPopDepObj obj;
			ICFBamPopDep[] recList = schema.getCFBamBackingStore().getTablePopDep().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamPopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopSubDep1Obj realised = (ICFBamPopSubDep1Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopSubDep1Obj arr[] = new ICFBamPopSubDep1Obj[len];
		Iterator<ICFBamPopSubDep1Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamPopSubDep1Obj> arrayList = new ArrayList<ICFBamPopSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopSubDep1Obj> cmp = new Comparator<ICFBamPopSubDep1Obj>() {
			@Override
			public int compare( ICFBamPopSubDep1Obj lhs, ICFBamPopSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamPopSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readPopSubDep1ByPopTopDepIdx( CFLibDbKeyHash256 PopTopDepId )
	{
		return( readPopSubDep1ByPopTopDepIdx( PopTopDepId,
			false ) );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readPopSubDep1ByPopTopDepIdx( CFLibDbKeyHash256 PopTopDepId,
		boolean forceRead )
	{
		final String S_ProcName = "readPopSubDep1ByPopTopDepIdx";
		ICFBamPopSubDep1ByPopTopDepIdxKey key = schema.getCFBamBackingStore().getFactoryPopSubDep1().newByPopTopDepIdxKey();
		key.setRequiredPopTopDepId( PopTopDepId );
		Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> dict;
		if( indexByPopTopDepIdx == null ) {
			indexByPopTopDepIdx = new HashMap< ICFBamPopSubDep1ByPopTopDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep1Obj > >();
		}
		if( ( ! forceRead ) && indexByPopTopDepIdx.containsKey( key ) ) {
			dict = indexByPopTopDepIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamPopSubDep1Obj>();
			ICFBamPopSubDep1Obj obj;
			ICFBamPopSubDep1[] recList = schema.getCFBamBackingStore().getTablePopSubDep1().readDerivedByPopTopDepIdx( null,
				PopTopDepId );
			ICFBamPopSubDep1 rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamPopSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamPopSubDep1Obj realised = (ICFBamPopSubDep1Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPopTopDepIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamPopSubDep1Obj arr[] = new ICFBamPopSubDep1Obj[len];
		Iterator<ICFBamPopSubDep1Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamPopSubDep1Obj> arrayList = new ArrayList<ICFBamPopSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamPopSubDep1Obj> cmp = new Comparator<ICFBamPopSubDep1Obj>() {
			@Override
			public int compare( ICFBamPopSubDep1Obj lhs, ICFBamPopSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamPopSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamPopSubDep1Obj readPopSubDep1ByUNameIdx( CFLibDbKeyHash256 PopTopDepId,
		String Name )
	{
		return( readPopSubDep1ByUNameIdx( PopTopDepId,
			Name,
			false ) );
	}

	@Override
	public ICFBamPopSubDep1Obj readPopSubDep1ByUNameIdx( CFLibDbKeyHash256 PopTopDepId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamPopSubDep1ByUNameIdxKey,
				ICFBamPopSubDep1Obj >();
		}
		ICFBamPopSubDep1ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryPopSubDep1().newByUNameIdxKey();
		key.setRequiredPopTopDepId( PopTopDepId );
		key.setRequiredName( Name );
		ICFBamPopSubDep1Obj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamPopSubDep1 rec = schema.getCFBamBackingStore().getTablePopSubDep1().readDerivedByUNameIdx( null,
				PopTopDepId,
				Name );
			if( rec != null ) {
				obj = (ICFBamPopSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamPopSubDep1Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamPopSubDep1Obj readCachedPopSubDep1ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamPopSubDep1Obj obj = null;
		obj = readCachedPopSubDep1( Id );
		return( obj );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readCachedPopSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedPopSubDep1ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamPopSubDep1Obj> arrayList = new ArrayList<ICFBamPopSubDep1Obj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamPopSubDep1Obj arr[] = new ICFBamPopSubDep1Obj[len];
				Iterator<ICFBamPopSubDep1Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamPopSubDep1Obj obj;
			Iterator<ICFBamPopSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopSubDep1Obj> cmp = new Comparator<ICFBamPopSubDep1Obj>() {
			@Override
			public int compare( ICFBamPopSubDep1Obj lhs, ICFBamPopSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readCachedPopSubDep1ByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedPopSubDep1ByRelationIdx";
		ICFBamPopDepByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamPopSubDep1Obj> arrayList = new ArrayList<ICFBamPopSubDep1Obj>();
		if( indexByRelationIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> dict;
			if( indexByRelationIdx.containsKey( key ) ) {
				dict = indexByRelationIdx.get( key );
				int len = dict.size();
				ICFBamPopSubDep1Obj arr[] = new ICFBamPopSubDep1Obj[len];
				Iterator<ICFBamPopSubDep1Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamPopSubDep1Obj obj;
			Iterator<ICFBamPopSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopSubDep1Obj> cmp = new Comparator<ICFBamPopSubDep1Obj>() {
			@Override
			public int compare( ICFBamPopSubDep1Obj lhs, ICFBamPopSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readCachedPopSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedPopSubDep1ByDefSchemaIdx";
		ICFBamPopDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamPopSubDep1Obj> arrayList = new ArrayList<ICFBamPopSubDep1Obj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamPopSubDep1Obj arr[] = new ICFBamPopSubDep1Obj[len];
				Iterator<ICFBamPopSubDep1Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamPopSubDep1Obj obj;
			Iterator<ICFBamPopSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopSubDep1Obj> cmp = new Comparator<ICFBamPopSubDep1Obj>() {
			@Override
			public int compare( ICFBamPopSubDep1Obj lhs, ICFBamPopSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> readCachedPopSubDep1ByPopTopDepIdx( CFLibDbKeyHash256 PopTopDepId )
	{
		final String S_ProcName = "readCachedPopSubDep1ByPopTopDepIdx";
		ICFBamPopSubDep1ByPopTopDepIdxKey key = schema.getCFBamBackingStore().getFactoryPopSubDep1().newByPopTopDepIdxKey();
		key.setRequiredPopTopDepId( PopTopDepId );
		ArrayList<ICFBamPopSubDep1Obj> arrayList = new ArrayList<ICFBamPopSubDep1Obj>();
		if( indexByPopTopDepIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> dict;
			if( indexByPopTopDepIdx.containsKey( key ) ) {
				dict = indexByPopTopDepIdx.get( key );
				int len = dict.size();
				ICFBamPopSubDep1Obj arr[] = new ICFBamPopSubDep1Obj[len];
				Iterator<ICFBamPopSubDep1Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamPopSubDep1Obj obj;
			Iterator<ICFBamPopSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamPopSubDep1Obj> cmp = new Comparator<ICFBamPopSubDep1Obj>() {
			@Override
			public int compare( ICFBamPopSubDep1Obj lhs, ICFBamPopSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamPopSubDep1Obj readCachedPopSubDep1ByUNameIdx( CFLibDbKeyHash256 PopTopDepId,
		String Name )
	{
		ICFBamPopSubDep1Obj obj = null;
		ICFBamPopSubDep1ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryPopSubDep1().newByUNameIdxKey();
		key.setRequiredPopTopDepId( PopTopDepId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamPopSubDep1Obj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamPopSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public void deepDisposePopSubDep1ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamPopSubDep1Obj obj = readCachedPopSubDep1ByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposePopSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposePopSubDep1ByTenantIdx";
		ICFBamPopSubDep1Obj obj;
		List<ICFBamPopSubDep1Obj> arrayList = readCachedPopSubDep1ByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamPopSubDep1Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopSubDep1ByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposePopSubDep1ByRelationIdx";
		ICFBamPopSubDep1Obj obj;
		List<ICFBamPopSubDep1Obj> arrayList = readCachedPopSubDep1ByRelationIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamPopSubDep1Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposePopSubDep1ByDefSchemaIdx";
		ICFBamPopSubDep1Obj obj;
		List<ICFBamPopSubDep1Obj> arrayList = readCachedPopSubDep1ByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamPopSubDep1Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopSubDep1ByPopTopDepIdx( CFLibDbKeyHash256 PopTopDepId )
	{
		final String S_ProcName = "deepDisposePopSubDep1ByPopTopDepIdx";
		ICFBamPopSubDep1Obj obj;
		List<ICFBamPopSubDep1Obj> arrayList = readCachedPopSubDep1ByPopTopDepIdx( PopTopDepId );
		if( arrayList != null )  {
			Iterator<ICFBamPopSubDep1Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposePopSubDep1ByUNameIdx( CFLibDbKeyHash256 PopTopDepId,
		String Name )
	{
		ICFBamPopSubDep1Obj obj = readCachedPopSubDep1ByUNameIdx( PopTopDepId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamPopSubDep1Obj updatePopSubDep1( ICFBamPopSubDep1Obj Obj ) {
		ICFBamPopSubDep1Obj obj = Obj;
		schema.getCFBamBackingStore().getTablePopSubDep1().updatePopSubDep1( null,
			Obj.getPopSubDep1Rec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getPopSubDep1TableObj().getClassCode() ) {
			obj = (ICFBamPopSubDep1Obj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deletePopSubDep1( ICFBamPopSubDep1Obj Obj ) {
		ICFBamPopSubDep1Obj obj = Obj;
		schema.getCFBamBackingStore().getTablePopSubDep1().deletePopSubDep1( null,
			obj.getPopSubDep1Rec() );
		Obj.forget();
	}

	@Override
	public void deletePopSubDep1ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamPopSubDep1Obj obj = readPopSubDep1(Id);
		if( obj != null ) {
			ICFBamPopSubDep1EditObj editObj = (ICFBamPopSubDep1EditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamPopSubDep1EditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposePopSubDep1ByIdIdx( Id );
	}

	@Override
	public void deletePopSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep1Obj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTablePopSubDep1().deletePopSubDep1ByTenantIdx( null,
				TenantId );
			Iterator<ICFBamPopSubDep1Obj> iter = dict.values().iterator();
			ICFBamPopSubDep1Obj obj;
			List<ICFBamPopSubDep1Obj> toForget = new LinkedList<ICFBamPopSubDep1Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopSubDep1().deletePopSubDep1ByTenantIdx( null,
				TenantId );
		}
		deepDisposePopSubDep1ByTenantIdx( TenantId );
	}

	@Override
	public void deletePopSubDep1ByRelationIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamPopDepByRelationIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByRelationIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByRelationIdx == null ) {
			indexByRelationIdx = new HashMap< ICFBamPopDepByRelationIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep1Obj > >();
		}
		if( indexByRelationIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> dict = indexByRelationIdx.get( key );
			schema.getCFBamBackingStore().getTablePopSubDep1().deletePopSubDep1ByRelationIdx( null,
				RelationId );
			Iterator<ICFBamPopSubDep1Obj> iter = dict.values().iterator();
			ICFBamPopSubDep1Obj obj;
			List<ICFBamPopSubDep1Obj> toForget = new LinkedList<ICFBamPopSubDep1Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByRelationIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopSubDep1().deletePopSubDep1ByRelationIdx( null,
				RelationId );
		}
		deepDisposePopSubDep1ByRelationIdx( RelationId );
	}

	@Override
	public void deletePopSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamPopDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryPopDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamPopDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep1Obj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTablePopSubDep1().deletePopSubDep1ByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamPopSubDep1Obj> iter = dict.values().iterator();
			ICFBamPopSubDep1Obj obj;
			List<ICFBamPopSubDep1Obj> toForget = new LinkedList<ICFBamPopSubDep1Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopSubDep1().deletePopSubDep1ByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposePopSubDep1ByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deletePopSubDep1ByPopTopDepIdx( CFLibDbKeyHash256 PopTopDepId )
	{
		ICFBamPopSubDep1ByPopTopDepIdxKey key = schema.getCFBamBackingStore().getFactoryPopSubDep1().newByPopTopDepIdxKey();
		key.setRequiredPopTopDepId( PopTopDepId );
		if( indexByPopTopDepIdx == null ) {
			indexByPopTopDepIdx = new HashMap< ICFBamPopSubDep1ByPopTopDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamPopSubDep1Obj > >();
		}
		if( indexByPopTopDepIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamPopSubDep1Obj> dict = indexByPopTopDepIdx.get( key );
			schema.getCFBamBackingStore().getTablePopSubDep1().deletePopSubDep1ByPopTopDepIdx( null,
				PopTopDepId );
			Iterator<ICFBamPopSubDep1Obj> iter = dict.values().iterator();
			ICFBamPopSubDep1Obj obj;
			List<ICFBamPopSubDep1Obj> toForget = new LinkedList<ICFBamPopSubDep1Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByPopTopDepIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTablePopSubDep1().deletePopSubDep1ByPopTopDepIdx( null,
				PopTopDepId );
		}
		deepDisposePopSubDep1ByPopTopDepIdx( PopTopDepId );
	}

	@Override
	public void deletePopSubDep1ByUNameIdx( CFLibDbKeyHash256 PopTopDepId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamPopSubDep1ByUNameIdxKey,
				ICFBamPopSubDep1Obj >();
		}
		ICFBamPopSubDep1ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryPopSubDep1().newByUNameIdxKey();
		key.setRequiredPopTopDepId( PopTopDepId );
		key.setRequiredName( Name );
		ICFBamPopSubDep1Obj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTablePopSubDep1().deletePopSubDep1ByUNameIdx( null,
				PopTopDepId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTablePopSubDep1().deletePopSubDep1ByUNameIdx( null,
				PopTopDepId,
				Name );
		}
		deepDisposePopSubDep1ByUNameIdx( PopTopDepId,
				Name );
	}
}