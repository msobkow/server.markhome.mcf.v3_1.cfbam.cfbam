// Description: Java 25 Table Object implementation for ServerProc.

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

public class CFBamServerProcTableObj
	implements ICFBamServerProcTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamServerProc.CLASS_CODE;
	protected static final int backingClassCode = ICFBamServerProc.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamServerProcObj> members;
	private Map<CFLibDbKeyHash256, ICFBamServerProcObj> allServerProc;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerProcObj > > indexByTenantIdx;
	private Map< ICFBamServerMethodByUNameIdxKey,
		ICFBamServerProcObj > indexByUNameIdx;
	private Map< ICFBamServerMethodByMethTableIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerProcObj > > indexByMethTableIdx;
	private Map< ICFBamServerMethodByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerProcObj > > indexByDefSchemaIdx;
	public static String TABLE_NAME = "ServerProc";
	public static String TABLE_DBNAME = "srvprc";

	public CFBamServerProcTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamServerProcObj>();
		allServerProc = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByMethTableIdx = null;
		indexByDefSchemaIdx = null;
	}

	public CFBamServerProcTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamServerProcObj>();
		allServerProc = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByMethTableIdx = null;
		indexByDefSchemaIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamServerProcTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamServerProcTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allServerProc = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByMethTableIdx = null;
		indexByDefSchemaIdx = null;
		List<ICFBamServerProcObj> toForget = new LinkedList<ICFBamServerProcObj>();
		ICFBamServerProcObj cur = null;
		Iterator<ICFBamServerProcObj> iter = members.values().iterator();
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
	 *	CFBamServerProcObj.
	 */
	@Override
	public ICFBamServerProcObj newInstance() {
		ICFBamServerProcObj inst = new CFBamServerProcObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamServerProcObj.
	 */
	@Override
	public ICFBamServerProcEditObj newEditInstance( ICFBamServerProcObj orig ) {
		ICFBamServerProcEditObj edit = new CFBamServerProcEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamServerProcObj realiseServerProc( ICFBamServerProcObj Obj ) {
		ICFBamServerProcObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamServerProcObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamServerProcObj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamServerProcObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamServerMethodByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByMethTableIdx != null ) {
				ICFBamServerMethodByMethTableIdxKey keyMethTableIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
				keyMethTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerProcObj > mapMethTableIdx = indexByMethTableIdx.get( keyMethTableIdx );
				if( mapMethTableIdx != null ) {
					indexByMethTableIdx.remove( keyMethTableIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamServerMethodByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamServerProcObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamServerProcObj)schema.getServerMethodTableObj().realiseServerMethod( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamServerProcObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamServerMethodByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByMethTableIdx != null ) {
				ICFBamServerMethodByMethTableIdxKey keyMethTableIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
				keyMethTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerProcObj > mapMethTableIdx = indexByMethTableIdx.get( keyMethTableIdx );
				if( mapMethTableIdx != null ) {
					mapMethTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamServerMethodByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamServerProcObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allServerProc != null ) {
				allServerProc.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamServerProcObj)schema.getServerMethodTableObj().realiseServerMethod( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allServerProc != null ) {
				allServerProc.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamServerProcObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamServerMethodByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByMethTableIdx != null ) {
				ICFBamServerMethodByMethTableIdxKey keyMethTableIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
				keyMethTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerProcObj > mapMethTableIdx = indexByMethTableIdx.get( keyMethTableIdx );
				if( mapMethTableIdx != null ) {
					mapMethTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamServerMethodByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamServerProcObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamServerProcObj createServerProc( ICFBamServerProcObj Obj ) {
		ICFBamServerProcObj obj = Obj;
		ICFBamServerProc rec = obj.getServerProcRec();
		schema.getCFBamBackingStore().getTableServerProc().createServerProc(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamServerProcObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamServerProcObj readServerProc( CFLibDbKeyHash256 pkey ) {
		return( readServerProc( pkey, false ) );
	}

	@Override
	public ICFBamServerProcObj readServerProc( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamServerProcObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamServerProc readRec = schema.getCFBamBackingStore().getTableServerProc().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamServerProcObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamServerProcObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamServerProcObj readCachedServerProc( CFLibDbKeyHash256 pkey ) {
		ICFBamServerProcObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeServerProc( ICFBamServerProcObj obj )
	{
		final String S_ProcName = "CFBamServerProcTableObj.reallyDeepDisposeServerProc() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamServerProcObj existing = readCachedServerProc( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );

					schema.getParamTableObj().deepDisposeParamByServerMethodIdx( existing.getRequiredId() );


		schema.getServerMethodTableObj().reallyDeepDisposeServerMethod( obj );
	}
	@Override
	public void deepDisposeServerProc( CFLibDbKeyHash256 pkey ) {
		ICFBamServerProcObj obj = readCachedServerProc( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamServerProcObj lockServerProc( CFLibDbKeyHash256 pkey ) {
		ICFBamServerProcObj locked = null;
		ICFBamServerProc lockRec = schema.getCFBamBackingStore().getTableServerProc().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamServerProcObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamServerProcObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockServerProc", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamServerProcObj> readAllServerProc() {
		return( readAllServerProc( false ) );
	}

	@Override
	public List<ICFBamServerProcObj> readAllServerProc( boolean forceRead ) {
		final String S_ProcName = "readAllServerProc";
		if( ( allServerProc == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamServerProcObj> map = new HashMap<CFLibDbKeyHash256,ICFBamServerProcObj>();
			allServerProc = map;
			ICFBamServerProc[] recList = schema.getCFBamBackingStore().getTableServerProc().readAllDerived( null );
			ICFBamServerProc rec;
			ICFBamServerProcObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerProcObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerProcObj realised = (ICFBamServerProcObj)obj.realise();
			}
		}
		int len = allServerProc.size();
		ICFBamServerProcObj arr[] = new ICFBamServerProcObj[len];
		Iterator<ICFBamServerProcObj> valIter = allServerProc.values().iterator();
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
		ArrayList<ICFBamServerProcObj> arrayList = new ArrayList<ICFBamServerProcObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerProcObj> cmp = new Comparator<ICFBamServerProcObj>() {
			@Override
			public int compare( ICFBamServerProcObj lhs, ICFBamServerProcObj rhs ) {
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
		List<ICFBamServerProcObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamServerProcObj> readCachedAllServerProc() {
		final String S_ProcName = "readCachedAllServerProc";
		ArrayList<ICFBamServerProcObj> arrayList = new ArrayList<ICFBamServerProcObj>();
		if( allServerProc != null ) {
			int len = allServerProc.size();
			ICFBamServerProcObj arr[] = new ICFBamServerProcObj[len];
			Iterator<ICFBamServerProcObj> valIter = allServerProc.values().iterator();
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
		Comparator<ICFBamServerProcObj> cmp = new Comparator<ICFBamServerProcObj>() {
			public int compare( ICFBamServerProcObj lhs, ICFBamServerProcObj rhs ) {
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
	public ICFBamServerProcObj readServerProcByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readServerProcByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamServerProcObj readServerProcByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamServerProcObj obj = readServerProc( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamServerProcObj> readServerProcByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readServerProcByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamServerProcObj> readServerProcByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerProcByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamServerProcObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerProcObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerProcObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerProcObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerProcObj realised = (ICFBamServerProcObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerProcObj arr[] = new ICFBamServerProcObj[len];
		Iterator<ICFBamServerProcObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerProcObj> arrayList = new ArrayList<ICFBamServerProcObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerProcObj> cmp = new Comparator<ICFBamServerProcObj>() {
			@Override
			public int compare( ICFBamServerProcObj lhs, ICFBamServerProcObj rhs ) {
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
		List<ICFBamServerProcObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamServerProcObj readServerProcByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		return( readServerProcByUNameIdx( TableId,
			Name,
			false ) );
	}

	@Override
	public ICFBamServerProcObj readServerProcByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamServerMethodByUNameIdxKey,
				ICFBamServerProcObj >();
		}
		ICFBamServerMethodByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamServerProcObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamServerMethod rec = schema.getCFBamBackingStore().getTableServerMethod().readDerivedByUNameIdx( null,
				TableId,
				Name );
			if( rec != null ) {
				obj = (ICFBamServerProcObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamServerProcObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamServerProcObj> readServerProcByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		return( readServerProcByMethTableIdx( TableId,
			false ) );
	}

	@Override
	public List<ICFBamServerProcObj> readServerProcByMethTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerProcByMethTableIdx";
		ICFBamServerMethodByMethTableIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
		key.setRequiredTableId( TableId );
		Map<CFLibDbKeyHash256, ICFBamServerProcObj> dict;
		if( indexByMethTableIdx == null ) {
			indexByMethTableIdx = new HashMap< ICFBamServerMethodByMethTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerProcObj > >();
		}
		if( ( ! forceRead ) && indexByMethTableIdx.containsKey( key ) ) {
			dict = indexByMethTableIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerProcObj>();
			ICFBamServerMethodObj obj;
			ICFBamServerMethod[] recList = schema.getCFBamBackingStore().getTableServerMethod().readDerivedByMethTableIdx( null,
				TableId );
			ICFBamServerMethod rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerProcObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerProcObj realised = (ICFBamServerProcObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByMethTableIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerProcObj arr[] = new ICFBamServerProcObj[len];
		Iterator<ICFBamServerProcObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerProcObj> arrayList = new ArrayList<ICFBamServerProcObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerProcObj> cmp = new Comparator<ICFBamServerProcObj>() {
			@Override
			public int compare( ICFBamServerProcObj lhs, ICFBamServerProcObj rhs ) {
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
		List<ICFBamServerProcObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamServerProcObj> readServerProcByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readServerProcByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamServerProcObj> readServerProcByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerProcByDefSchemaIdx";
		ICFBamServerMethodByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamServerProcObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamServerMethodByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerProcObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerProcObj>();
			ICFBamServerMethodObj obj;
			ICFBamServerMethod[] recList = schema.getCFBamBackingStore().getTableServerMethod().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamServerMethod rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerProcObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerProcObj realised = (ICFBamServerProcObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerProcObj arr[] = new ICFBamServerProcObj[len];
		Iterator<ICFBamServerProcObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerProcObj> arrayList = new ArrayList<ICFBamServerProcObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerProcObj> cmp = new Comparator<ICFBamServerProcObj>() {
			@Override
			public int compare( ICFBamServerProcObj lhs, ICFBamServerProcObj rhs ) {
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
		List<ICFBamServerProcObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamServerProcObj readCachedServerProcByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamServerProcObj obj = null;
		obj = readCachedServerProc( Id );
		return( obj );
	}

	@Override
	public List<ICFBamServerProcObj> readCachedServerProcByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedServerProcByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamServerProcObj> arrayList = new ArrayList<ICFBamServerProcObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerProcObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamServerProcObj arr[] = new ICFBamServerProcObj[len];
				Iterator<ICFBamServerProcObj> valIter = dict.values().iterator();
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
			ICFBamServerProcObj obj;
			Iterator<ICFBamServerProcObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerProcObj> cmp = new Comparator<ICFBamServerProcObj>() {
			@Override
			public int compare( ICFBamServerProcObj lhs, ICFBamServerProcObj rhs ) {
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
	public ICFBamServerProcObj readCachedServerProcByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamServerProcObj obj = null;
		ICFBamServerMethodByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamServerProcObj> valIter = members.values().iterator();
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
			Iterator<ICFBamServerProcObj> valIter = members.values().iterator();
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
	public List<ICFBamServerProcObj> readCachedServerProcByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "readCachedServerProcByMethTableIdx";
		ICFBamServerMethodByMethTableIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
		key.setRequiredTableId( TableId );
		ArrayList<ICFBamServerProcObj> arrayList = new ArrayList<ICFBamServerProcObj>();
		if( indexByMethTableIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerProcObj> dict;
			if( indexByMethTableIdx.containsKey( key ) ) {
				dict = indexByMethTableIdx.get( key );
				int len = dict.size();
				ICFBamServerProcObj arr[] = new ICFBamServerProcObj[len];
				Iterator<ICFBamServerProcObj> valIter = dict.values().iterator();
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
			ICFBamServerProcObj obj;
			Iterator<ICFBamServerProcObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerProcObj> cmp = new Comparator<ICFBamServerProcObj>() {
			@Override
			public int compare( ICFBamServerProcObj lhs, ICFBamServerProcObj rhs ) {
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
	public List<ICFBamServerProcObj> readCachedServerProcByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedServerProcByDefSchemaIdx";
		ICFBamServerMethodByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamServerProcObj> arrayList = new ArrayList<ICFBamServerProcObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerProcObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamServerProcObj arr[] = new ICFBamServerProcObj[len];
				Iterator<ICFBamServerProcObj> valIter = dict.values().iterator();
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
			ICFBamServerProcObj obj;
			Iterator<ICFBamServerProcObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerProcObj> cmp = new Comparator<ICFBamServerProcObj>() {
			@Override
			public int compare( ICFBamServerProcObj lhs, ICFBamServerProcObj rhs ) {
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
	public void deepDisposeServerProcByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamServerProcObj obj = readCachedServerProcByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeServerProcByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeServerProcByTenantIdx";
		ICFBamServerProcObj obj;
		List<ICFBamServerProcObj> arrayList = readCachedServerProcByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamServerProcObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeServerProcByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamServerProcObj obj = readCachedServerProcByUNameIdx( TableId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeServerProcByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "deepDisposeServerProcByMethTableIdx";
		ICFBamServerProcObj obj;
		List<ICFBamServerProcObj> arrayList = readCachedServerProcByMethTableIdx( TableId );
		if( arrayList != null )  {
			Iterator<ICFBamServerProcObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeServerProcByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeServerProcByDefSchemaIdx";
		ICFBamServerProcObj obj;
		List<ICFBamServerProcObj> arrayList = readCachedServerProcByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamServerProcObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamServerProcObj updateServerProc( ICFBamServerProcObj Obj ) {
		ICFBamServerProcObj obj = Obj;
		schema.getCFBamBackingStore().getTableServerProc().updateServerProc( null,
			Obj.getServerProcRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getServerProcTableObj().getClassCode() ) {
			obj = (ICFBamServerProcObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteServerProc( ICFBamServerProcObj Obj ) {
		ICFBamServerProcObj obj = Obj;
		schema.getCFBamBackingStore().getTableServerProc().deleteServerProc( null,
			obj.getServerProcRec() );
		Obj.forget();
	}

	@Override
	public void deleteServerProcByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamServerProcObj obj = readServerProc(Id);
		if( obj != null ) {
			ICFBamServerProcEditObj editObj = (ICFBamServerProcEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamServerProcEditObj)obj.beginEdit();
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
		deepDisposeServerProcByIdIdx( Id );
	}

	@Override
	public void deleteServerProcByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerProcObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerProcObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableServerProc().deleteServerProcByTenantIdx( null,
				TenantId );
			Iterator<ICFBamServerProcObj> iter = dict.values().iterator();
			ICFBamServerProcObj obj;
			List<ICFBamServerProcObj> toForget = new LinkedList<ICFBamServerProcObj>();
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
			schema.getCFBamBackingStore().getTableServerProc().deleteServerProcByTenantIdx( null,
				TenantId );
		}
		deepDisposeServerProcByTenantIdx( TenantId );
	}

	@Override
	public void deleteServerProcByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamServerMethodByUNameIdxKey,
				ICFBamServerProcObj >();
		}
		ICFBamServerMethodByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamServerProcObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableServerProc().deleteServerProcByUNameIdx( null,
				TableId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableServerProc().deleteServerProcByUNameIdx( null,
				TableId,
				Name );
		}
		deepDisposeServerProcByUNameIdx( TableId,
				Name );
	}

	@Override
	public void deleteServerProcByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		ICFBamServerMethodByMethTableIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
		key.setRequiredTableId( TableId );
		if( indexByMethTableIdx == null ) {
			indexByMethTableIdx = new HashMap< ICFBamServerMethodByMethTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerProcObj > >();
		}
		if( indexByMethTableIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerProcObj> dict = indexByMethTableIdx.get( key );
			schema.getCFBamBackingStore().getTableServerProc().deleteServerProcByMethTableIdx( null,
				TableId );
			Iterator<ICFBamServerProcObj> iter = dict.values().iterator();
			ICFBamServerProcObj obj;
			List<ICFBamServerProcObj> toForget = new LinkedList<ICFBamServerProcObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByMethTableIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableServerProc().deleteServerProcByMethTableIdx( null,
				TableId );
		}
		deepDisposeServerProcByMethTableIdx( TableId );
	}

	@Override
	public void deleteServerProcByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamServerMethodByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamServerMethodByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerProcObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerProcObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableServerProc().deleteServerProcByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamServerProcObj> iter = dict.values().iterator();
			ICFBamServerProcObj obj;
			List<ICFBamServerProcObj> toForget = new LinkedList<ICFBamServerProcObj>();
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
			schema.getCFBamBackingStore().getTableServerProc().deleteServerProcByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeServerProcByDefSchemaIdx( DefSchemaId );
	}
}