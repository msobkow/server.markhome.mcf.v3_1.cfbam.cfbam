// Description: Java 25 Table Object implementation for ClearDep.

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

public class CFBamClearDepTableObj
	implements ICFBamClearDepTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamClearDep.CLASS_CODE;
	protected static final int backingClassCode = ICFBamClearDep.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamClearDepObj> members;
	private Map<CFLibDbKeyHash256, ICFBamClearDepObj> allClearDep;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearDepObj > > indexByTenantIdx;
	private Map< ICFBamClearDepByClearDepIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearDepObj > > indexByClearDepIdx;
	private Map< ICFBamClearDepByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearDepObj > > indexByDefSchemaIdx;
	public static String TABLE_NAME = "ClearDep";
	public static String TABLE_DBNAME = "clr_dep";

	public CFBamClearDepTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamClearDepObj>();
		allClearDep = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
	}

	public CFBamClearDepTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamClearDepObj>();
		allClearDep = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamClearDepTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamClearDepTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allClearDep = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
		List<ICFBamClearDepObj> toForget = new LinkedList<ICFBamClearDepObj>();
		ICFBamClearDepObj cur = null;
		Iterator<ICFBamClearDepObj> iter = members.values().iterator();
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
	 *	CFBamClearDepObj.
	 */
	@Override
	public ICFBamClearDepObj newInstance() {
		ICFBamClearDepObj inst = new CFBamClearDepObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamClearDepObj.
	 */
	@Override
	public ICFBamClearDepEditObj newEditInstance( ICFBamClearDepObj orig ) {
		ICFBamClearDepEditObj edit = new CFBamClearDepEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamClearDepObj realiseClearDep( ICFBamClearDepObj Obj ) {
		ICFBamClearDepObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamClearDepObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamClearDepObj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamClearDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearDepObj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					mapClearDepIdx.remove( keepObj.getPKey() );
					if( mapClearDepIdx.size() <= 0 ) {
						indexByClearDepIdx.remove( keyClearDepIdx );
					}
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.remove( keepObj.getPKey() );
					if( mapDefSchemaIdx.size() <= 0 ) {
						indexByDefSchemaIdx.remove( keyDefSchemaIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamClearDepObj)schema.getScopeTableObj().realiseScope( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamClearDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearDepObj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					mapClearDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allClearDep != null ) {
				allClearDep.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamClearDepObj)schema.getScopeTableObj().realiseScope( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allClearDep != null ) {
				allClearDep.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamClearDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearDepObj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					mapClearDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamClearDepObj createClearDep( ICFBamClearDepObj Obj ) {
		ICFBamClearDepObj obj = Obj;
		ICFBamClearDep rec = obj.getClearDepRec();
		schema.getCFBamBackingStore().getTableClearDep().createClearDep(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamClearDepObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamClearDepObj readClearDep( CFLibDbKeyHash256 pkey ) {
		return( readClearDep( pkey, false ) );
	}

	@Override
	public ICFBamClearDepObj readClearDep( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamClearDepObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamClearDep readRec = schema.getCFBamBackingStore().getTableClearDep().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamClearDepObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamClearDepObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamClearDepObj readCachedClearDep( CFLibDbKeyHash256 pkey ) {
		ICFBamClearDepObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeClearDep( ICFBamClearDepObj obj )
	{
		final String S_ProcName = "CFBamClearDepTableObj.reallyDeepDisposeClearDep() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamClearDepObj existing = readCachedClearDep( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamClearDepByClearDepIdxKey keyClearDepIdx = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		keyClearDepIdx.setRequiredRelationId( existing.getRequiredRelationId() );

		ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		keyDefSchemaIdx.setOptionalDefSchemaId( existing.getOptionalDefSchemaId() );



		if( indexByClearDepIdx != null ) {
			if( indexByClearDepIdx.containsKey( keyClearDepIdx ) ) {
				indexByClearDepIdx.get( keyClearDepIdx ).remove( pkey );
				if( indexByClearDepIdx.get( keyClearDepIdx ).size() <= 0 ) {
					indexByClearDepIdx.remove( keyClearDepIdx );
				}
			}
		}

		if( indexByDefSchemaIdx != null ) {
			if( indexByDefSchemaIdx.containsKey( keyDefSchemaIdx ) ) {
				indexByDefSchemaIdx.get( keyDefSchemaIdx ).remove( pkey );
				if( indexByDefSchemaIdx.get( keyDefSchemaIdx ).size() <= 0 ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}
		}


		schema.getScopeTableObj().reallyDeepDisposeScope( obj );
	}
	@Override
	public void deepDisposeClearDep( CFLibDbKeyHash256 pkey ) {
		ICFBamClearDepObj obj = readCachedClearDep( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamClearDepObj lockClearDep( CFLibDbKeyHash256 pkey ) {
		ICFBamClearDepObj locked = null;
		ICFBamClearDep lockRec = schema.getCFBamBackingStore().getTableClearDep().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamClearDepObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamClearDepObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockClearDep", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamClearDepObj> readAllClearDep() {
		return( readAllClearDep( false ) );
	}

	@Override
	public List<ICFBamClearDepObj> readAllClearDep( boolean forceRead ) {
		final String S_ProcName = "readAllClearDep";
		if( ( allClearDep == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamClearDepObj> map = new HashMap<CFLibDbKeyHash256,ICFBamClearDepObj>();
			allClearDep = map;
			ICFBamClearDep[] recList = schema.getCFBamBackingStore().getTableClearDep().readAllDerived( null );
			ICFBamClearDep rec;
			ICFBamClearDepObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearDepObj realised = (ICFBamClearDepObj)obj.realise();
			}
		}
		int len = allClearDep.size();
		ICFBamClearDepObj arr[] = new ICFBamClearDepObj[len];
		Iterator<ICFBamClearDepObj> valIter = allClearDep.values().iterator();
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
		ArrayList<ICFBamClearDepObj> arrayList = new ArrayList<ICFBamClearDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearDepObj> cmp = new Comparator<ICFBamClearDepObj>() {
			@Override
			public int compare( ICFBamClearDepObj lhs, ICFBamClearDepObj rhs ) {
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
		List<ICFBamClearDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearDepObj> readCachedAllClearDep() {
		final String S_ProcName = "readCachedAllClearDep";
		ArrayList<ICFBamClearDepObj> arrayList = new ArrayList<ICFBamClearDepObj>();
		if( allClearDep != null ) {
			int len = allClearDep.size();
			ICFBamClearDepObj arr[] = new ICFBamClearDepObj[len];
			Iterator<ICFBamClearDepObj> valIter = allClearDep.values().iterator();
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
		Comparator<ICFBamClearDepObj> cmp = new Comparator<ICFBamClearDepObj>() {
			public int compare( ICFBamClearDepObj lhs, ICFBamClearDepObj rhs ) {
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
	public ICFBamClearDepObj readClearDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readClearDepByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamClearDepObj readClearDepByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamClearDepObj obj = readClearDep( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamClearDepObj> readClearDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readClearDepByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamClearDepObj> readClearDepByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearDepByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamClearDepObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearDepObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearDepObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearDepObj realised = (ICFBamClearDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearDepObj arr[] = new ICFBamClearDepObj[len];
		Iterator<ICFBamClearDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearDepObj> arrayList = new ArrayList<ICFBamClearDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearDepObj> cmp = new Comparator<ICFBamClearDepObj>() {
			@Override
			public int compare( ICFBamClearDepObj lhs, ICFBamClearDepObj rhs ) {
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
		List<ICFBamClearDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearDepObj> readClearDepByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readClearDepByClearDepIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamClearDepObj> readClearDepByClearDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearDepByClearDepIdx";
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamClearDepObj> dict;
		if( indexByClearDepIdx == null ) {
			indexByClearDepIdx = new HashMap< ICFBamClearDepByClearDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearDepObj > >();
		}
		if( ( ! forceRead ) && indexByClearDepIdx.containsKey( key ) ) {
			dict = indexByClearDepIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearDepObj>();
			ICFBamClearDepObj obj;
			ICFBamClearDep[] recList = schema.getCFBamBackingStore().getTableClearDep().readDerivedByClearDepIdx( null,
				RelationId );
			ICFBamClearDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearDepObj realised = (ICFBamClearDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClearDepIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearDepObj arr[] = new ICFBamClearDepObj[len];
		Iterator<ICFBamClearDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearDepObj> arrayList = new ArrayList<ICFBamClearDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearDepObj> cmp = new Comparator<ICFBamClearDepObj>() {
			@Override
			public int compare( ICFBamClearDepObj lhs, ICFBamClearDepObj rhs ) {
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
		List<ICFBamClearDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearDepObj> readClearDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readClearDepByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamClearDepObj> readClearDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearDepByDefSchemaIdx";
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamClearDepObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamClearDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearDepObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearDepObj>();
			ICFBamClearDepObj obj;
			ICFBamClearDep[] recList = schema.getCFBamBackingStore().getTableClearDep().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamClearDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearDepObj realised = (ICFBamClearDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearDepObj arr[] = new ICFBamClearDepObj[len];
		Iterator<ICFBamClearDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearDepObj> arrayList = new ArrayList<ICFBamClearDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearDepObj> cmp = new Comparator<ICFBamClearDepObj>() {
			@Override
			public int compare( ICFBamClearDepObj lhs, ICFBamClearDepObj rhs ) {
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
		List<ICFBamClearDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamClearDepObj readCachedClearDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearDepObj obj = null;
		obj = readCachedClearDep( Id );
		return( obj );
	}

	@Override
	public List<ICFBamClearDepObj> readCachedClearDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedClearDepByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamClearDepObj> arrayList = new ArrayList<ICFBamClearDepObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearDepObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamClearDepObj arr[] = new ICFBamClearDepObj[len];
				Iterator<ICFBamClearDepObj> valIter = dict.values().iterator();
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
			ICFBamClearDepObj obj;
			Iterator<ICFBamClearDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearDepObj> cmp = new Comparator<ICFBamClearDepObj>() {
			@Override
			public int compare( ICFBamClearDepObj lhs, ICFBamClearDepObj rhs ) {
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
	public List<ICFBamClearDepObj> readCachedClearDepByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedClearDepByClearDepIdx";
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamClearDepObj> arrayList = new ArrayList<ICFBamClearDepObj>();
		if( indexByClearDepIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearDepObj> dict;
			if( indexByClearDepIdx.containsKey( key ) ) {
				dict = indexByClearDepIdx.get( key );
				int len = dict.size();
				ICFBamClearDepObj arr[] = new ICFBamClearDepObj[len];
				Iterator<ICFBamClearDepObj> valIter = dict.values().iterator();
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
			ICFBamClearDepObj obj;
			Iterator<ICFBamClearDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearDepObj> cmp = new Comparator<ICFBamClearDepObj>() {
			@Override
			public int compare( ICFBamClearDepObj lhs, ICFBamClearDepObj rhs ) {
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
	public List<ICFBamClearDepObj> readCachedClearDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedClearDepByDefSchemaIdx";
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamClearDepObj> arrayList = new ArrayList<ICFBamClearDepObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearDepObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamClearDepObj arr[] = new ICFBamClearDepObj[len];
				Iterator<ICFBamClearDepObj> valIter = dict.values().iterator();
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
			ICFBamClearDepObj obj;
			Iterator<ICFBamClearDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearDepObj> cmp = new Comparator<ICFBamClearDepObj>() {
			@Override
			public int compare( ICFBamClearDepObj lhs, ICFBamClearDepObj rhs ) {
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
	public void deepDisposeClearDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearDepObj obj = readCachedClearDepByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeClearDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeClearDepByTenantIdx";
		ICFBamClearDepObj obj;
		List<ICFBamClearDepObj> arrayList = readCachedClearDepByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamClearDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearDepByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposeClearDepByClearDepIdx";
		ICFBamClearDepObj obj;
		List<ICFBamClearDepObj> arrayList = readCachedClearDepByClearDepIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamClearDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeClearDepByDefSchemaIdx";
		ICFBamClearDepObj obj;
		List<ICFBamClearDepObj> arrayList = readCachedClearDepByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamClearDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamClearDepObj updateClearDep( ICFBamClearDepObj Obj ) {
		ICFBamClearDepObj obj = Obj;
		schema.getCFBamBackingStore().getTableClearDep().updateClearDep( null,
			Obj.getClearDepRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getClearDepTableObj().getClassCode() ) {
			obj = (ICFBamClearDepObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteClearDep( ICFBamClearDepObj Obj ) {
		ICFBamClearDepObj obj = Obj;
		schema.getCFBamBackingStore().getTableClearDep().deleteClearDep( null,
			obj.getClearDepRec() );
		Obj.forget();
	}

	@Override
	public void deleteClearDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearDepObj obj = readClearDep(Id);
		if( obj != null ) {
			ICFBamClearDepEditObj editObj = (ICFBamClearDepEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamClearDepEditObj)obj.beginEdit();
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
		deepDisposeClearDepByIdIdx( Id );
	}

	@Override
	public void deleteClearDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearDepObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearDepObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableClearDep().deleteClearDepByTenantIdx( null,
				TenantId );
			Iterator<ICFBamClearDepObj> iter = dict.values().iterator();
			ICFBamClearDepObj obj;
			List<ICFBamClearDepObj> toForget = new LinkedList<ICFBamClearDepObj>();
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
			schema.getCFBamBackingStore().getTableClearDep().deleteClearDepByTenantIdx( null,
				TenantId );
		}
		deepDisposeClearDepByTenantIdx( TenantId );
	}

	@Override
	public void deleteClearDepByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByClearDepIdx == null ) {
			indexByClearDepIdx = new HashMap< ICFBamClearDepByClearDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearDepObj > >();
		}
		if( indexByClearDepIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearDepObj> dict = indexByClearDepIdx.get( key );
			schema.getCFBamBackingStore().getTableClearDep().deleteClearDepByClearDepIdx( null,
				RelationId );
			Iterator<ICFBamClearDepObj> iter = dict.values().iterator();
			ICFBamClearDepObj obj;
			List<ICFBamClearDepObj> toForget = new LinkedList<ICFBamClearDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByClearDepIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableClearDep().deleteClearDepByClearDepIdx( null,
				RelationId );
		}
		deepDisposeClearDepByClearDepIdx( RelationId );
	}

	@Override
	public void deleteClearDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamClearDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearDepObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearDepObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableClearDep().deleteClearDepByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamClearDepObj> iter = dict.values().iterator();
			ICFBamClearDepObj obj;
			List<ICFBamClearDepObj> toForget = new LinkedList<ICFBamClearDepObj>();
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
			schema.getCFBamBackingStore().getTableClearDep().deleteClearDepByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeClearDepByDefSchemaIdx( DefSchemaId );
	}
}